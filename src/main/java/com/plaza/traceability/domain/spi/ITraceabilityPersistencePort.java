package com.plaza.traceability.domain.spi;

import com.plaza.traceability.domain.model.PageResult;
import com.plaza.traceability.domain.model.Traceability;
import com.plaza.traceability.domain.model.TraceabilityEmployeeReport;
import com.plaza.traceability.domain.model.TraceabilityOrderReport;

import java.util.List;

public interface ITraceabilityPersistencePort {

    void saveTraceability(Traceability traceability);

    PageResult<Traceability> getAllTraceabilityByCustomer(Long idUser, int page, int size, String sortBy, String sortDir);

    List<TraceabilityOrderReport> getAllOrdersByRestaurant(Long idRestaurantEmployee);

    List<TraceabilityEmployeeReport> getReportEmployeeByRestaurant(Long idRestaurantEmployee);
}
