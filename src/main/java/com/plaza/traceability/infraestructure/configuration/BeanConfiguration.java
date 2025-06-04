package com.plaza.traceability.infraestructure.configuration;

import com.plaza.traceability.domain.api.ITraceabilityServicePort;
import com.plaza.traceability.domain.spi.*;
import com.plaza.traceability.domain.usercase.TraceabilityUserCase;
import com.plaza.traceability.infraestructure.output.client.adapter.UserClientAdapter;
import com.plaza.traceability.infraestructure.output.client.mapper.UserEntityMapper;
import com.plaza.traceability.infraestructure.output.client.repository.IUserFeignClient;
import com.plaza.traceability.infraestructure.output.mongo.adapter.TraceabilityJpaAdapter;
import com.plaza.traceability.infraestructure.output.mongo.mapper.TraceabilityEntityMapper;
import com.plaza.traceability.infraestructure.output.mongo.repository.ITraceabilityRepository;
import com.plaza.traceability.infraestructure.security.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class BeanConfiguration {

    private final IUserFeignClient userFeignClient;
    private final UserEntityMapper userEntityMapper;
    private final HttpServletRequest httpServletRequest;
    private final JwtService jwtService;
    private final ITraceabilityRepository traceabilityRepository;
    private final TraceabilityEntityMapper traceabilityEntityMapper;

    public BeanConfiguration(IUserFeignClient userFeignClient, UserEntityMapper userEntityMapper, HttpServletRequest httpServletRequest, JwtService jwtService, ITraceabilityRepository traceabilityRepository, TraceabilityEntityMapper traceabilityEntityMapper) {
        this.userFeignClient = userFeignClient;
        this.userEntityMapper = userEntityMapper;
        this.httpServletRequest = httpServletRequest;
        this.jwtService = jwtService;
        this.traceabilityRepository = traceabilityRepository;
        this.traceabilityEntityMapper = traceabilityEntityMapper;
    }

    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserClientAdapter(userFeignClient, userEntityMapper, httpServletRequest, jwtService);
    }

    @Bean
    public ITraceabilityPersistencePort traceabilityPersistencePort(){
        return new TraceabilityJpaAdapter(traceabilityRepository, traceabilityEntityMapper);
    }

    @Bean
    public ITraceabilityServicePort traceabilityServicePort(){
        return new TraceabilityUserCase(traceabilityPersistencePort(), userPersistencePort());
    }

}
