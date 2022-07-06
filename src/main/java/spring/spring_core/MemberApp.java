package spring.spring_core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.spring_core.member.Grade;
import spring.spring_core.member.Member;
import spring.spring_core.member.MemberService;

public class MemberApp {

	public static void main(String[] args) {
		//        MemberService memberService = new MemberServiceImpl();

		//  AppConfig를 통한 생성자 주입(DI: Dependency Injection, 의존관계 주입)
		//  관심사 분리: 객체를 생성하고 연결하는 역할과 실행하는 역할 분리
		// AppConfig appConfig = new AppConfig();
		// MemberService memberService = appConfig.memberService();

		// 모든 @Bean 관리
		// AppCongif를 컨테이너에서 관리
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

		Member member = new Member(1L, "memberA", Grade.VIP);
		memberService.join(member);

		Member findeMember = memberService.findMember(1L);
		System.out.println("new member = " + member.getName());
		System.out.println("find Member = " + findeMember.getName());
	}
}
