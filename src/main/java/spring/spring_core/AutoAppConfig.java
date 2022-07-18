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

	//원래는 수동 vs 자동 충돌시 수동이 우선권을 가진다
	//지금은 운영상의 복잡함과 버그 등의 문제로 충돌이 나게 설정되어 있다
	//application.properties에서 설정할 수 있음
	// @Bean(name = "memoryMemberRepository")
	// public MemberRepository memberRepository() {
	// 	return new MemoryMemberRepository();
	// }

	// @Autowired
	// MemberRepository memberRepository;
	// @Autowired
	// DiscountPolicy discountPolicy;
	//
	// @Bean
	// OrderService orderService() {
	// 	return new OrderServiceImpl(memberRepository, discountPolicy);
	// }

	// @Bean(name = "memoryMemberRepository")
	// MemberRepository memberRepository() {
	// 	return new MemoryMemberRepository();
	// }
}
