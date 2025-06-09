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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        Long idOrder = 1L;
        String idCustomer = "1";
        Status statusBefore = Status.PENDIENTE;
        Status status = Status.ENTREGADO;
        Date dateTime = new Date();
        traceability = new Traceability.Builder().idOrder(idOrder)
                .idCustomer(idCustomer)
                .statusBefore(statusBefore)
                .status(status)
                .dateTime(dateTime).build();

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

    }

    @Test
    void saveTraceability() {

        doNothing().when(traceabilityPersistencePort).saveTraceability(any());
        traceabilityUserCase.saveTraceability(traceability);
        verify(traceabilityPersistencePort).saveTraceability(any());
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
}