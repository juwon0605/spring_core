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

	@Bean
	public MemberService memberService() {
		return new MemberServiceImpl(memberRepository());
	}

	@Bean
	public OrderService orderService() {
		return new OrderServiceImpl(memberRepository(), discountPolicy());
	}

	@Bean
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}

	@Bean
	public DiscountPolicy discountPolicy() {
		return new FixDiscountPolicy();
		// return new RateDiscountPolicy();
	}
}
