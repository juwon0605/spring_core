package spring.spring_core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
	//ComponentScan 범위 지정
	//Default는 @ComponentScan이 있는 패키지
	//프로젝트 시작 루트 위치에 두는 것이 관례. 권장
	// basePackages = "spring.spring_core.member",

	//AppConfig(수동 설정)와의 비교하기 위한 예외 설정
	//실무에서는 이렇게 설정하지는 않음
	excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
