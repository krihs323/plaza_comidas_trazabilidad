package com.plaza.traceability.domain.validation;

import com.plaza.traceability.domain.exception.ExceptionResponse;
import com.plaza.traceability.domain.exception.TraceabilityUserCaseValidationException;
import com.plaza.traceability.domain.model.Traceability;

public class TraceabilityValidations extends Validate {

    public static void saveTraceability(Traceability traceability) {

        if (traceability.getIdCustomer() == null || traceability.getIdCustomer().isEmpty()) {
            throw new TraceabilityUserCaseValidationException(ExceptionResponse.VALIDATION_CUSTOMER.getMessage());
        }


    }

}
