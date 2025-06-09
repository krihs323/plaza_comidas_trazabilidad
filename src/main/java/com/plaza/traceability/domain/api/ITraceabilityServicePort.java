package com.plaza.traceability.domain.api;

import com.plaza.traceability.domain.model.PageResult;
import com.plaza.traceability.domain.model.Traceability;
import com.plaza.traceability.domain.model.TraceabilityEmployeeReport;
import com.plaza.traceability.domain.model.TraceabilityOrderReport;

import java.util.List;

public interface ITraceabilityServicePort {

    void saveTraceability(Traceability traceability);

    PageResult<Traceability> getAllTraceabilityByCustomer(int page, int size, String sortBy, String sortDir);

    List<TraceabilityOrderReport> getAllOrdersByRestaurant();

    List<TraceabilityEmployeeReport> getReportEmployeeByRestaurant();
}
