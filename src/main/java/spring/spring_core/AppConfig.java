package spring.spring_core;

import spring.spring_core.discount.FixDiscountPolicy;
import spring.spring_core.member.MemberService;
import spring.spring_core.member.MemberServiceImpl;
import spring.spring_core.member.MemoryMemberRepository;
import spring.spring_core.order.OrderService;
import spring.spring_core.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
