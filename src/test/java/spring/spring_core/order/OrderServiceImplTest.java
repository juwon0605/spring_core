package spring.spring_core.order;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import spring.spring_core.discount.FixDiscountPolicy;
import spring.spring_core.member.Grade;
import spring.spring_core.member.Member;
import spring.spring_core.member.MemberRepository;
import spring.spring_core.member.MemoryMemberRepository;

class OrderServiceImplTest {

	@Test
	void createOrder() {
		//setter만 만들면 일일이 하나씩 의존관계 생성해야해서 누락 발생 가능성이 높음
		//필드 주입만 쓰면 스프링 안 돌리고 테스트 할 수가 없어짐
		//따라서 생성자 주입 권장
		//생성자 주입은 임의로 의존관계 주입해서 테스트할 수도 있음!
		//게다가 생성자 주입을 쓰면 final 쓸 수도 있음!
		//final 쓰면 생성자에서 실수로 의존관계 주입 빼먹으면 초기화 안돼서 컴파일 에러 발생!
		//setter 주입과 필드 주입은 모두 생성자 이후에 호출되므로, 필드에 final 키워드를 사용할 수 없다
		MemberRepository memberRepository = new MemoryMemberRepository();
		memberRepository.save(new Member(1L, "name", Grade.VIP));

		OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
		Order order = orderService.createOrder(1L, "iteamA", 10000);
		assertThat(order.getDiscountPrice()).isEqualTo(1000);
	}
}
