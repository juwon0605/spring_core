package spring.spring_core;

import spring.spring_core.member.Grade;
import spring.spring_core.member.Member;
import spring.spring_core.member.MemberService;
import spring.spring_core.member.MemberServiceImpl;
import spring.spring_core.order.Order;
import spring.spring_core.order.OrderService;
import spring.spring_core.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order); // order에 overriding된 toString 메서드로 return
    }
}
