package com.liuyan.study.rpc.clent;

import com.liuyan.study.rpc.server.common.ServiceDiscovery;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liuyan on 2017/12/26.
 */
@Configuration
public class Config {

    @Value("rpc.client.address")
    private String address;

    @Bean
    public RpcProxy rpcProxy() {
        return new RpcProxy(serviceDiscovery());
    }

    @Bean
    public ServiceDiscovery serviceDiscovery() {
        return new ServiceDiscovery(address);
    }
}
