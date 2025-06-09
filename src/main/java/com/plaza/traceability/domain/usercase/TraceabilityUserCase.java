package com.plaza.traceability.domain.usercase;

import com.plaza.traceability.domain.api.ITraceabilityServicePort;
import com.plaza.traceability.domain.model.*;
import com.plaza.traceability.domain.spi.ITraceabilityPersistencePort;
import com.plaza.traceability.domain.spi.IUserPersistencePort;
import com.plaza.traceability.domain.validation.TraceabilityValidations;

import java.util.List;

public class TraceabilityUserCase implements ITraceabilityServicePort {

    private final ITraceabilityPersistencePort traceabilityPersistencePort;
    private final IUserPersistencePort userPersistencePort;


    public TraceabilityUserCase(ITraceabilityPersistencePort traceabilityPersistencePort, IUserPersistencePort userPersistencePort) {
        this.traceabilityPersistencePort = traceabilityPersistencePort;
        this.userPersistencePort = userPersistencePort;
    }


    @Override
    public void saveTraceability(Traceability traceability) {
        TraceabilityValidations.saveTraceability(traceability);
        traceabilityPersistencePort.saveTraceability(traceability);
    }

    @Override
    public PageResult<Traceability> getAllTraceabilityByCustomer(int page, int size, String sortBy, String sortDir) {
       User user = userPersistencePort.getUseAuth();
       return traceabilityPersistencePort.getAllTraceabilityByCustomer(user.getIdUser(), page, size, sortBy, sortDir);
    }

    @Override
    public List<TraceabilityOrderReport> getAllOrdersByRestaurant() {
        User user = userPersistencePort.getUseAuth();
        return traceabilityPersistencePort.getAllOrdersByRestaurant(user.getIdRestaurantEmployee());
    }

    @Override
    public List<TraceabilityEmployeeReport> getReportEmployeeByRestaurant() {
        User user = userPersistencePort.getUseAuth();
        return traceabilityPersistencePort.getReportEmployeeByRestaurant(user.getIdRestaurantEmployee());
    }
}