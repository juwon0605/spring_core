package spring.spring_core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import spring.spring_core.AppConfig;
import spring.spring_core.member.Grade;
import spring.spring_core.member.Member;
import spring.spring_core.member.MemberService;

public class OrderServiceTest {

	//    MemberService memberService = new MemberServiceImpl();
	//    OrderService orderService = new OrderServiceImpl(memberRepository, discountPolicy);

	private MemberService memberService;
	private OrderService orderService;

	//  Test 실행 전 매번 의존관계 주입
	@BeforeEach
	public void beforeEach() {
		AppConfig appConfig = new AppConfig();
		memberService = appConfig.memberService();
		orderService = appConfig.orderService();
	}

	@Test
	void createOrder() {
		Long memberId = 1L;
		Member member = new Member(memberId, "memberA", Grade.VIP);
		memberService.join(member);

		Order order = orderService.createOrder(memberId, "itemA", 10000);
		Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
	}

	@Test
	void filedInjectionTest() {

		//테스트를 위해 setter 의존성 설정 함수가 필요하게 됨
		//@Autowired 필드 주입을 쓸 이유가 없음. 테스트에서만 간단하게 사용.
		//생성자 주입으로 명확하게 명시.
		// orderService.setMemberRepository(new MemoryMemberRepository());
		// orderService.setDiscountPolicy(new FixDiscountPolicy());

		// orderService.createOrder(1L, "itemA", 18000);
	}
}
