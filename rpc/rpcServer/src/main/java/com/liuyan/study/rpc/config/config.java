package com.liuyan.study.rpc.config;

import com.liuyan.study.rpc.registry.ServiceRegistry;
import com.liuyan.study.rpc.server.RpcServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liuyan on 2017/12/26.
 */
@Configuration
public class config {

    @Value("rpc.server.address")
    private String address;

    @Value("rpc.server.registry.address")
    private String registryAddress;

    @Bean
    ServiceRegistry serviceRegistry() {
        return new ServiceRegistry(registryAddress);
    }

    @Bean
    public RpcServer rpcServer() {
        return new RpcServer(address, serviceRegistry());
    }
}
