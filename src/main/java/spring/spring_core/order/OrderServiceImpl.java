package spring.spring_core.order;

import spring.spring_core.discount.DiscountPolicy;
import spring.spring_core.member.Member;
import spring.spring_core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

	//    추상화와 구현체 모두 의존. DIP 원칙 위반.
	//    private final MemberRepository memberRepository = new MemoryMemberRepository();
	//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
	//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

	//  추상화에만 의존. DIP 원칙 준수.
	private final MemberRepository memberRepository;
	private final DiscountPolicy discountPolicy;

	//  AppConfig를 통한 생성자 주입(DI: Dependency Injection, 의존관계 주입)
	//  관심사 분리: 객체를 생성하고 연결하는 역할과 실행하는 역할 분리
	public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice);

		return new Order(memberId, itemName, itemPrice, discountPrice);
	}

	//테스트 용도
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
}
