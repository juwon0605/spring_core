package spring.spring_core;

import spring.spring_core.member.Grade;
import spring.spring_core.member.Member;
import spring.spring_core.member.MemberService;
import spring.spring_core.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl();

        //  AppConfig를 통한 생성자 주입(DI: Dependency Injection, 의존관계 주입)
        //  관심사 분리: 객체를 생성하고 연결하는 역할과 실행하는 역할 분리
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findeMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findeMember.getName());
    }
}
