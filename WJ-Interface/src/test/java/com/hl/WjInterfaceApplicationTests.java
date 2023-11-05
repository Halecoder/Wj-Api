package com.hl;

import com.hl.client.WJApiClient;
import com.hl.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

@SpringBootTest
class WjInterfaceApplicationTests {

	@Resource
	private WJApiClient wjApiClient;
	@Test
	void contextLoads() throws UnsupportedEncodingException {
		User user = new User();
		user.setUsername("张三");
		 String usernameByPost = wjApiClient.getNameByPostWithJson(user);
		System.out.println(usernameByPost);

	}

}
