package com.liuyan.study.rpc.clent;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by liuyan on 2017/12/26.
 */
@Getter
@Setter
//@ConfigurationProperties("rpc.client")
public class ClientProperties {
    private final Registry registry = new Registry();

    @Getter
    @Setter
    public static class Registry {
        String address;
    }
}
