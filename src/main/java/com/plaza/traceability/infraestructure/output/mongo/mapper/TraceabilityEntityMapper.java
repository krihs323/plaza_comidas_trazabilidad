package com.plaza.traceability.infraestructure.output.mongo.mapper;


import com.plaza.traceability.domain.model.Traceability;
import com.plaza.traceability.infraestructure.output.mongo.document.TraceabilityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TraceabilityEntityMapper {

    TraceabilityEntity toEntity(Traceability traceability);

    Traceability toTraceability(TraceabilityEntity traceabilityEntity);


}
