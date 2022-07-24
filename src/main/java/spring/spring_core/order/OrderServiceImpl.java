package spring.spring_core.order;

import org.springframework.stereotype.Component;

import spring.spring_core.annotation.MainDiscountPolicy;
import spring.spring_core.discount.DiscountPolicy;
import spring.spring_core.member.Member;
import spring.spring_core.member.MemberRepository;

@Component
//final이 있는 필드를 파라미터로 받아 생성자를 자동 생성
//자바의 애노테이션 프로세서라는 기능을 이용해서 컴파일 시점에 생성자 코드를 자동 생성
//'class'를 열어보면 다음 코드가 추가되어 있음
// @RequiredArgsConstructor
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
	// @Autowired
	//생성자 하나 있으면 생략 가능
	//@RequiredArgsConstructor로 대체
	public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
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
