package com.plaza.traceability.infraestructure.output.mongo.adapter;

import com.plaza.traceability.domain.model.PageResult;
import com.plaza.traceability.domain.model.Traceability;
import com.plaza.traceability.domain.spi.ITraceabilityPersistencePort;
import com.plaza.traceability.infraestructure.output.mongo.document.TraceabilityEntity;
import com.plaza.traceability.infraestructure.output.mongo.mapper.TraceabilityEntityMapper;
import com.plaza.traceability.infraestructure.output.mongo.repository.ITraceabilityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
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
                .collect(Collectors.toList());

        return new PageResult<>(
                domainTraceabilities,
                traceabilityEntityListByCustomer.getNumber(),
                traceabilityEntityListByCustomer.getSize(),
                traceabilityEntityListByCustomer.getTotalElements(),
                traceabilityEntityListByCustomer.getTotalPages(),
                traceabilityEntityListByCustomer.isLast()
        );
    }
}

