package com.learn.springboot.backend_manager.actuator.endpoint;

import java.util.Collections;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "myEndpoint")
public class MyEndpoint {
    
    @ReadOperation
    public Map<String, String> getDockerInfo() {
        return Collections.singletonMap("dockerinfo", "started");
    }

    @WriteOperation
    public void setDockerInfo(String dockerInfo) {
        System.out.println("setDockerInfo: " + dockerInfo);
    }
}
