package com.plaza.traceability.infraestructure.output.mongo.adapter;

import com.plaza.traceability.domain.model.*;
import com.plaza.traceability.domain.spi.ITraceabilityPersistencePort;
import com.plaza.traceability.infraestructure.output.mongo.document.TraceabilityEntity;
import com.plaza.traceability.infraestructure.output.mongo.mapper.TraceabilityEntityMapper;
import com.plaza.traceability.infraestructure.output.mongo.repository.ITraceabilityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.Duration;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class TraceabilityJpaAdapter implements ITraceabilityPersistencePort {

    private final ITraceabilityRepository traceabilityRepository;
    private final TraceabilityEntityMapper traceabilityEntityMapper;

    public TraceabilityJpaAdapter(ITraceabilityRepository traceabilityRepository, TraceabilityEntityMapper traceabilityEntityMapper) {
        this.traceabilityRepository = traceabilityRepository;
        this.traceabilityEntityMapper = traceabilityEntityMapper;
    }

    @Override
    public void saveTraceability(Traceability traceability) {

        TraceabilityEntity traceabilityEntity = traceabilityEntityMapper.toEntity(traceability);
        traceabilityRepository.insert(traceabilityEntity);
    }

    @Override
    public PageResult<Traceability> getAllTraceabilityByCustomer(Long idUser, int page, int size, String sortBy, String sortDir) {
        Sort sort = Sort.by(Sort.Direction.ASC , sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<TraceabilityEntity> traceabilityEntityListByCustomer = traceabilityRepository.findByIdCustomer(idUser, pageable);

        List<Traceability> domainTraceabilities = traceabilityEntityListByCustomer
                .getContent()
                .stream()
                .map(traceabilityEntityMapper::toTraceability)
                .toList();

        return new PageResult<>(
                domainTraceabilities,
                traceabilityEntityListByCustomer.getNumber(),
                traceabilityEntityListByCustomer.getSize(),
                traceabilityEntityListByCustomer.getTotalElements(),
                traceabilityEntityListByCustomer.getTotalPages(),
                traceabilityEntityListByCustomer.isLast()
        );
    }

    @Override
    public List<TraceabilityOrderReport> getAllOrdersByRestaurant(Long idRestaurantEmployee) {

        List<TraceabilityEntity> traceabilityEntityList = traceabilityRepository.findByIdRestaurant(1L);

        return traceabilityEntityList.stream()
                .collect(Collectors.groupingBy(TraceabilityEntity::getIdOrder))
                .entrySet()
                .stream()
                .map(entry -> {
                    Long idOrder = entry.getKey();
                    List<TraceabilityEntity> traceabilityEntities = entry.getValue();

                    TraceabilityEntity traceabilityEntity = traceabilityEntities.stream().filter(x -> (x.getIdOrder().equals(idOrder)))
                            .findFirst().orElseThrow();

                    Optional<Date> timeReady = traceabilityEntities.stream()
                            .filter(c -> Status.LISTO.name().equals(c.getStatus()))
                            .map(TraceabilityEntity::getDateTime)
                            .min(Comparator.naturalOrder());

                    Optional<Date> timeDelivery = traceabilityEntities.stream()
                            .filter(c -> Status.ENTREGADO.name().equals(c.getStatus()))
                            .map(TraceabilityEntity::getDateTime)
                            .min(Comparator.naturalOrder());

                    if (timeReady.isPresent() && timeDelivery.isPresent()) {
                        Duration duration = Duration.between(timeReady.get().toInstant().atZone(ZoneId.systemDefault())
                                .toLocalDateTime(), timeDelivery.get().toInstant().atZone(ZoneId.systemDefault()));
                        return new TraceabilityOrderReport(idOrder, traceabilityEntity.getIdCustomer(),
                                traceabilityEntity.getNameCustomer(),
                                traceabilityEntity.getRestaurantName(),
                                traceabilityEntity.getIdRestaurant(),
                                duration);
                    } else {
                        return null;
                    }
                }).filter(Objects::nonNull)
                .sorted(Comparator.comparing(TraceabilityOrderReport::getTime).reversed()) // Orden descendente
                .toList();

    }

    @Override
    public List<TraceabilityEmployeeReport> getReportEmployeeByRestaurant(Long idRestaurantEmployee) {

        List<TraceabilityEntity> traceabilityEntityList = traceabilityRepository.findByIdRestaurant(1L);

        List<TraceabilityEmployeeReport> traceabilityOrderReportList = traceabilityEntityList.stream()
                .collect(Collectors.groupingBy(TraceabilityEntity::getIdOrder))
                .entrySet()
                .stream()
                .map(entry -> {
                    Long idOrder = entry.getKey();
                    List<TraceabilityEntity> traceabilityEntities = entry.getValue();

                    TraceabilityEntity traceabilityEntity = traceabilityEntities.stream().filter(x -> (x.getIdOrder().equals(idOrder)))
                            .findFirst().orElseThrow();

                    Optional<Date> timeReady = traceabilityEntities.stream()
                            .filter(c -> Status.LISTO.name().equals(c.getStatus()))
                            .map(TraceabilityEntity::getDateTime)
                            .min(Comparator.naturalOrder());

                    Optional<Date> timeDelivery = traceabilityEntities.stream()
                            .filter(c -> Status.ENTREGADO.name().equals(c.getStatus()))
                            .map(TraceabilityEntity::getDateTime)
                            .min(Comparator.naturalOrder());

                    if (timeReady.isPresent() && timeDelivery.isPresent()) {
                        Duration duration = Duration.between(timeReady.get().toInstant().atZone(ZoneId.systemDefault())
                                .toLocalDateTime(), timeDelivery.get().toInstant().atZone(ZoneId.systemDefault()));
                        return new TraceabilityEmployeeReport(traceabilityEntity.getEmployeeName(),
                                traceabilityEntity.getIdEmployee(),
                                null,
                                duration);
                    } else {
                        return null;
                    }
                }).filter(Objects::nonNull)
                .sorted(Comparator.comparing(TraceabilityEmployeeReport::getTime).reversed()) // Orden descendente
                .toList();

        Map<Long, Double> averageByEmployee = traceabilityOrderReportList.stream()
                .collect(Collectors.groupingBy(
                        TraceabilityEmployeeReport::getIdEmployee,
                        Collectors.averagingLong(r -> r.getTime().toMinutes())
                ));

        Map<Long, TraceabilityEmployeeReport> filterByEmployee = traceabilityOrderReportList.stream()
                .collect(Collectors.toMap(
                        TraceabilityEmployeeReport::getIdEmployee,
                        employee -> employee,
                        (existing, replacement) -> existing
                ));


        return averageByEmployee.entrySet().stream()
                .map(entry -> {
                    long idEmployee = entry.getKey();
                    double averageTime = entry.getValue();
                    TraceabilityEmployeeReport traceabilityEmployeeReport = filterByEmployee.get(idEmployee);
                    return new TraceabilityEmployeeReport(traceabilityEmployeeReport.getEmployeeName(), idEmployee, (long) averageTime, null);
                })
                .sorted(Comparator.comparingDouble(TraceabilityEmployeeReport::getAverage))
                .toList();
    }


}

