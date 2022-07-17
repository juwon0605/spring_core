package spring.spring_core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.spring_core.member.Grade;
import spring.spring_core.member.Member;
import spring.spring_core.member.MemberService;
import spring.spring_core.order.Order;
import spring.spring_core.order.OrderService;

public class OrderApp {

	public static void main(String[] args) {
		//        MemberService memberService = new MemberSer viceImpl();
		//        OrderService orderService = new OrderServiceImpl(memberRepository, discountPolicy);

		//  AppConfig를 통한 생성자 주입(DI: Dependency Injection, 의존관계 주입)
		//  관심사 분리: 객체를 생성하고 연결하는 역할과 실행하는 역할 분리
		// AppConfig appConfig = new AppConfig();
		// MemberService memberService = appConfig.memberService();
		// OrderService orderService = appConfig.orderService();

		// ApplicationContext: 스프링 컨테이너
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
		OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

		Long memberId = 1L;
		Member member = new Member(memberId, "memberA", Grade.VIP);
		memberService.join(member);

		Order order = orderService.createOrder(memberId, "itemA", 10000);

		System.out.println("order = " + order); // order에 overriding된 toString 메서드로 return
	}
}
