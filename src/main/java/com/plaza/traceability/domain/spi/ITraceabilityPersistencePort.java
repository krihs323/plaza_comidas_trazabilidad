package com.plaza.traceability.domain.spi;

import com.plaza.traceability.domain.model.PageResult;
import com.plaza.traceability.domain.model.Traceability;

public interface ITraceabilityPersistencePort {

    void saveTraceability(Traceability traceability);

    PageResult<Traceability> getAllTraceabilityByCustomer(Long idUser, int page, int size, String sortBy, String sortDir);
}
