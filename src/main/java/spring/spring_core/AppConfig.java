package spring.spring_core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.spring_core.discount.DiscountPolicy;
import spring.spring_core.discount.FixDiscountPolicy;
import spring.spring_core.member.MemberRepository;
import spring.spring_core.member.MemberService;
import spring.spring_core.member.MemberServiceImpl;
import spring.spring_core.member.MemoryMemberRepository;
import spring.spring_core.order.OrderService;
import spring.spring_core.order.OrderServiceImpl;

@Configuration
public class AppConfig {

	//예를 들어 순차적으로 실행될 때 예상(실제는 실행 순서를 보장하지는 않음)
	//call AppConfig.memberService
	//call AppConfig.memberRepository
	//call AppConfig.memberRepository
	//call AppConfig.orderService
	//call AppConfig.memberRepository

	//실제 호출
	//call AppConfig.memberService
	//call AppConfig.memberRepository
	//call AppConfig.orderService

	@Bean
	public MemberService memberService() {
		System.out.println("call AppConfig.memberService");
		return new MemberServiceImpl(memberRepository());
	}

	@Bean
	public MemberRepository memberRepository() {
		System.out.println("call AppConfig.memberRepository");
		return new MemoryMemberRepository();
	}

	@Bean
	public OrderService orderService() {
		System.out.println("call AppConfig.orderService");
		return new OrderServiceImpl(memberRepository(), discountPolicy());
	}

	@Bean
	public DiscountPolicy discountPolicy() {
		return new FixDiscountPolicy();
		// return new RateDiscountPolicy();
	}
}
