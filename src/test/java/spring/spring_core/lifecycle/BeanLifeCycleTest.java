package spring.spring_core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class BeanLifeCycleTest {

	@Test
	public void lifeCycleTest() {
		ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(
			LifeCycleConfig.class);
		NetworkClient client = ac.getBean(NetworkClient.class);
		ac.close();
	}

	@Configuration
	static class LifeCycleConfig {

		@Bean
		//외부라이브러리 같은 경우는 아래와 같이 직접 설정
		// @Bean(initMethod = "init", destroyMethod = "close")
		public NetworkClient networkClient() {
			NetworkClient networkClient = new NetworkClient();
			networkClient.setUrl("http://spring-core.dev");
			return networkClient;
		}
	}

}
