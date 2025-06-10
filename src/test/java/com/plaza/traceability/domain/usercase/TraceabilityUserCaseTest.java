package com.plaza.traceability.domain.usercase;

import com.plaza.traceability.domain.exception.ExceptionResponse;
import com.plaza.traceability.domain.exception.TraceabilityUserCaseValidationException;
import com.plaza.traceability.domain.model.*;
import com.plaza.traceability.domain.spi.ITraceabilityPersistencePort;
import com.plaza.traceability.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

class TraceabilityUserCaseTest {

    @Mock
    private ITraceabilityPersistencePort traceabilityPersistencePort;
    @Mock
    private IUserPersistencePort userPersistencePort;
    @InjectMocks
    private TraceabilityUserCase traceabilityUserCase;

    private Traceability traceability;
    private User user;
    private int page;
    private int size;
    private String sortBy;
    private String sortDir;
    private PageResult<Traceability> pageResultTraceability;
    private TraceabilityOrderReport traceabilityOrderReport;
    private TraceabilityEmployeeReport traceabilityEmployeeReport;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        Long idOrder = 1L;
        String idCustomer = "1";
        Status statusBefore = Status.PENDIENTE;
        Status status = Status.ENTREGADO;
        Date dateTime = new Date();

        traceability = new Traceability(1L,
                idCustomer,
                statusBefore,
                status,
                dateTime,
                "cristian",
                "el forno",
                1L,
                "pedro",
                2L);

        user = new User(Role.OWNER.name(), 9L, 19L, "+573155828235");

        List<Traceability> testTraceability = new ArrayList<>();
        testTraceability.add(traceability);
        testTraceability.add(traceability);

        pageResultTraceability = new PageResult<>();
        pageResultTraceability.setContent(testTraceability);
        pageResultTraceability.setPageNumber(1);
        pageResultTraceability.setPageSize(1);
        pageResultTraceability.setTotalPages(1);
        pageResultTraceability.setLast(true);
        pageResultTraceability.setTotalElements(1);

        page = 0;
        size = 2;
        sortBy = "id";
        sortDir = "ASC";

        Long idCustomerOrdrReport = 1L;
        String nameCustomer = "Bertil";
        String restaurantName = "Il Forno";
        Long idRestaurant = 1L;
        Duration time = Duration.ofMinutes(10);
        traceabilityOrderReport = new TraceabilityOrderReport(idOrder, idCustomerOrdrReport, nameCustomer, restaurantName, idRestaurant, time);


        String employeeName = "Ana Perez";
        Long idEmployee=100L;
        Long average = 2L;
        traceabilityEmployeeReport = new TraceabilityEmployeeReport(employeeName, idEmployee, average, time);

    }



    @Test
    void saveTraceability() {

        doNothing().when(traceabilityPersistencePort).saveTraceability(any());
        traceabilityUserCase.saveTraceability(traceability);
        verify(traceabilityPersistencePort).saveTraceability(any());
    }

    @Test
    void saveTraceabilityWhenIdCustomerIsEmpty() {
        traceability.setIdCustomer(null);
        doNothing().when(traceabilityPersistencePort).saveTraceability(any());
        TraceabilityUserCaseValidationException exception = assertThrows(TraceabilityUserCaseValidationException.class, () -> {
            traceabilityUserCase.saveTraceability(traceability);
        });
        assertEquals(ExceptionResponse.VALIDATION_CUSTOMER.getMessage(), exception.getMessage());
    }

    @Test
    void saveTraceabilityWhenCustomerIsNull() {
        traceability.setIdCustomer(null);

        TraceabilityUserCaseValidationException exception = assertThrows(TraceabilityUserCaseValidationException.class, () -> {
            traceabilityUserCase.saveTraceability(traceability);
        });
        assertEquals(ExceptionResponse.VALIDATION_CUSTOMER.getMessage(), exception.getMessage());

    }

    @Test
    void getAllTraceabilityByCustomer() {
        Mockito.when(userPersistencePort.getUseAuth()).thenReturn(user);
        Mockito.when(traceabilityPersistencePort.getAllTraceabilityByCustomer(user.getIdUser(), page, size, sortBy, sortDir )).thenReturn(pageResultTraceability);
        PageResult<Traceability> traceabilityPageReturn = traceabilityUserCase.getAllTraceabilityByCustomer(page, size, sortBy, sortDir);
        assertEquals(2, traceabilityPageReturn.getContent().size());

    }

    @Test
    void getAllOrdersByRestaurant() {

        List<TraceabilityOrderReport> traceabilityOrderReportList = new ArrayList<>();
        traceabilityOrderReportList.add(traceabilityOrderReport);
        Mockito.when(userPersistencePort.getUseAuth()).thenReturn(user);
        Mockito.when(traceabilityPersistencePort.getAllOrdersByRestaurant(anyLong())).thenReturn(traceabilityOrderReportList);
        List<TraceabilityOrderReport> traceabilityOrderReportListResponse = traceabilityUserCase.getAllOrdersByRestaurant();
        assertEquals(1, traceabilityOrderReportListResponse.size());
    }

    @Test
    void getReportEmployeeByRestaurant() {

        List<TraceabilityEmployeeReport> traceabilityEmployeeReportList = new ArrayList<>();
        traceabilityEmployeeReportList.add(traceabilityEmployeeReport);

        Mockito.when(userPersistencePort.getUseAuth()).thenReturn(user);
        Mockito.when(traceabilityPersistencePort.getReportEmployeeByRestaurant(anyLong())).thenReturn(traceabilityEmployeeReportList);
        List<TraceabilityEmployeeReport> traceabilityOrderReportListResponse = traceabilityUserCase.getReportEmployeeByRestaurant();
        assertEquals(1, traceabilityOrderReportListResponse.size());

    }

    @Test
    void testModelTraceabilityEmployeeReport(){

        String employeeName = traceabilityEmployeeReport.getEmployeeName();
        Long idEmployee = traceabilityEmployeeReport.getIdEmployee();
        Long average =  traceabilityEmployeeReport.getAverage();
        Duration duration =  traceabilityEmployeeReport.getTime();

        traceabilityEmployeeReport.setEmployeeName(employeeName);
        traceabilityEmployeeReport.setIdEmployee(idEmployee);
        traceabilityEmployeeReport.setAverage(average);
        traceabilityEmployeeReport.setTime(duration);

        assertEquals(employeeName, traceabilityEmployeeReport.getEmployeeName());

    }

    @Test
    void testModelTraceabilityOrderReport(){

        Long idOrder = traceabilityOrderReport.getIdOrder();
        Long idCustomer = traceabilityOrderReport.getIdCustomer();
        String nameCustomer =  traceabilityOrderReport.getnameCustomer();
        String restaurantName =  traceabilityOrderReport.getRestaurantName();
        Long idRestaurant =  traceabilityOrderReport.getIdRestaurant();
        Duration duration =  traceabilityOrderReport.getTime();

        traceabilityOrderReport.setIdOrder(idOrder);
        traceabilityOrderReport.setIdCustomer(idCustomer);
        traceabilityOrderReport.setnameCustomer(nameCustomer);
        traceabilityOrderReport.setRestaurantName(restaurantName);
        traceabilityOrderReport.setIdRestaurant(idRestaurant);
        traceabilityOrderReport.setTime(duration);

        assertEquals(idOrder, traceabilityOrderReport.getIdOrder());

    }
}