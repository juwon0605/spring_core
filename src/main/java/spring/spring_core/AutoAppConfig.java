package spring.spring_core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
	//AppConfig(수동 설정)와의 비교하기 위한 예외 설정
	//실무에서는 이렇게 설정하지는 않음
	excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
