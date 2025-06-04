package com.plaza.traceability.domain.spi;

import com.plaza.traceability.domain.model.User;

public interface IUserPersistencePort {

    User getById(Long id);

    User getByEmail(String mail, String authorizationHeader);

    User getUseAuth();
}
