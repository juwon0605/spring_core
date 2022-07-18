package spring.spring_core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import spring.spring_core.order.OrderService;

@SpringBootTest
class SpringCoreApplicationTests {

	//Test용은 여기서만 쓰는 거라서 @Autowired 써도 무관
	@Autowired
	OrderService orderService;

	@Test
	void contextLoads() {
	}

}
