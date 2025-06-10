package com.plaza.traceability.infraestructure.output.mongo.adapter;

import com.plaza.traceability.domain.model.*;
import com.plaza.traceability.infraestructure.output.mongo.document.TraceabilityEntity;
import com.plaza.traceability.infraestructure.output.mongo.mapper.TraceabilityEntityMapper;
import com.plaza.traceability.infraestructure.output.mongo.repository.ITraceabilityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

class TraceabilityJpaAdapterTest {

    @Mock
    private ITraceabilityRepository traceabilityRepository;
    @Mock
    private TraceabilityEntityMapper traceabilityEntityMapper;
    @InjectMocks
    private TraceabilityJpaAdapter traceabilityJpaAdapter;

    private TraceabilityEntity traceabilityEntity;
    private Traceability traceability;

    private int page;
    private int size;
    private String sortDir;
    private Pageable pageable;
    private String sortBy;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        String id = "123456";

        Long idOrder = 1L;

        Long idCustomer = 1L;

        Status statusBefore = Status.PENDIENTE;

        Status status = Status.EN_PREPARACION;

        Date dateTime = new Date();

        String nameCustomer = "Cristian Botina";

        String restaurantName = "Il Forno";

        Long idRestaurant = 10L;

        String employeeName = "Pedro Perez";

        Long idEmployee = 100L;

        traceabilityEntity = new TraceabilityEntity(id, idOrder, idCustomer, String.valueOf(statusBefore), String.valueOf(status), dateTime, nameCustomer, restaurantName, idRestaurant, employeeName, idEmployee);

        traceability = new Traceability(1L,
                "1",
                Status.PENDIENTE,
                Status.ENTREGADO,
                new Date(),
                "cristian",
                "el forno",
                1L,
                "pedro",
                2L);



        page = 1;
        size = 2;
        sortDir = "ASC";
        sortBy = "id";
        Sort sort = Sort.by(Sort.Direction.ASC , sortBy);
        pageable = PageRequest.of(page,size, sort);
    }

    @Test
    void saveTraceability() {
        Mockito.when(traceabilityEntityMapper.toEntity(any())).thenReturn(traceabilityEntity);
        Mockito.when(traceabilityRepository.insert(traceabilityEntity)).thenReturn(traceabilityEntity);
        traceabilityJpaAdapter.saveTraceability(traceability);
        verify(traceabilityRepository).insert(traceabilityEntity);
    }



    @Test
    void getAllTraceabilityByCustomer() {

        List<TraceabilityEntity> testTraceability = new ArrayList<>();


        testTraceability.add(traceabilityEntity);
        testTraceability.add(traceabilityEntity);
        Page<TraceabilityEntity> traceabilityEntityListByCustomerPage = new PageImpl<>(testTraceability, pageable, testTraceability.size());
        Mockito.when(traceabilityRepository.findByIdCustomer(1L, pageable)).thenReturn(traceabilityEntityListByCustomerPage);
        Mockito.when(traceabilityEntityMapper.toTraceability(traceabilityEntity)).thenReturn(traceability);

        PageResult<Traceability> restaurantsPageReturn = traceabilityJpaAdapter.getAllTraceabilityByCustomer(1L, page, size, sortBy, sortDir);

        assertEquals(2, restaurantsPageReturn.getContent().size());


    }

    @Test
    void getAllOrdersByRestaurant() {

        List<TraceabilityEntity> traceabilityEntityList = new ArrayList<>();
        traceabilityEntity.setStatus(Status.EN_PREPARACION.name());
        traceabilityEntity.setStatusBefore(Status.PENDIENTE.name());
        traceabilityEntityList.add(traceabilityEntity);

        TraceabilityEntity traceabilityEntityRecordTwo = traceabilityEntity;
        traceabilityEntityRecordTwo.setStatus(Status.ENTREGADO.name());
        traceabilityEntityRecordTwo.setStatusBefore(Status.EN_PREPARACION.name());
        traceabilityEntityList.add(traceabilityEntityRecordTwo);

        Mockito.when(traceabilityRepository.findByIdRestaurant(anyLong())).thenReturn(traceabilityEntityList);

        traceabilityJpaAdapter.getAllOrdersByRestaurant(anyLong());
        verify(traceabilityRepository).findByIdRestaurant(anyLong());

    }

    @Test
    void getReportEmployeeByRestaurant() {

            List<TraceabilityEntity> traceabilityEntityList = new ArrayList<>();
            traceabilityEntity.setStatus(Status.EN_PREPARACION.name());
            traceabilityEntity.setStatusBefore(Status.PENDIENTE.name());
            traceabilityEntityList.add(traceabilityEntity);

            TraceabilityEntity traceabilityEntityRecordTwo = traceabilityEntity;
            traceabilityEntityRecordTwo.setStatus(Status.ENTREGADO.name());
            traceabilityEntityRecordTwo.setStatusBefore(Status.EN_PREPARACION.name());
            traceabilityEntityList.add(traceabilityEntityRecordTwo);

            Mockito.when(traceabilityRepository.findByIdRestaurant(anyLong())).thenReturn(traceabilityEntityList);

            traceabilityJpaAdapter.getReportEmployeeByRestaurant(anyLong());
            verify(traceabilityRepository).findByIdRestaurant(anyLong());


    }

    @Test
    void testModel() {
        Long idOrder = traceability.getIdOrder();
        Status statusBefore = traceability.getStatusBefore();
        Status status=  traceability.getStatus();
        Date dateTime = traceability.getDateTime();
        String nameCustomer = traceability.getNameCustomer();
        String restaurantName = traceability.getRestaurantName();
        Long idRestaurant = traceability.getIdRestaurant();
        Long idEmployee = traceability.getIdEmployee();
        String employeeName = traceability.getEmployeeName();

        traceability.setIdOrder(idOrder);
        traceability.setStatusBefore(statusBefore);
        traceability.setStatus(status);
        traceability.setDateTime(dateTime);
        traceability.setNameCustomer(nameCustomer);
        traceability.setRestaurantName(restaurantName);
        traceability.setIdRestaurant(idRestaurant);
        traceability.setIdEmployee(idEmployee);
        traceability.setEmployeeName(employeeName);

        assertEquals(idOrder, traceability.getIdOrder());
    }
}