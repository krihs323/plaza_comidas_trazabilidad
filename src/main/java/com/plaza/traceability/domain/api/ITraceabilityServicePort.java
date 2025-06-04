package com.plaza.traceability.domain.api;

import com.plaza.traceability.domain.model.PageResult;
import com.plaza.traceability.domain.model.Traceability;

public interface ITraceabilityServicePort {

    void saveTraceability(Traceability traceability);

    PageResult<Traceability> getAllTraceabilityByCustomer(int page, int size, String sortBy, String sortDir);
}
