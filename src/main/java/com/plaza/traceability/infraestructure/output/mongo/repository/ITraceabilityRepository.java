package com.plaza.traceability.infraestructure.output.mongo.repository;

import com.plaza.traceability.infraestructure.output.mongo.document.TraceabilityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ITraceabilityRepository extends MongoRepository<TraceabilityEntity, String> {

    Page<TraceabilityEntity> findByIdCustomer(Long idUser, Pageable pageable);
}
