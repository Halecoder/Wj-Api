package com.hl.project.rpc.impl;

import com.hl.project.rpc.RpcDemoService;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;

@Service(version = "${dubbo.service.version}")
public class RpcDemoServiceImpl implements RpcDemoService {
    @Override
    public String sayHello(String name) {
        System.out.println("Hello " + name + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return "Hello " + name;
    }
}
