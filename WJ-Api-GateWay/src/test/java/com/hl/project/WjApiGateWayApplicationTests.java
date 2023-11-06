package com.hl.project;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hl.project.rpc.RpcDemoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WjApiGateWayApplicationTests {

	@Reference
	private RpcDemoService rpcDemoService;
			@Test
			void testRpc() {
				System.out.println(rpcDemoService.sayHello("world"));
			}



}
