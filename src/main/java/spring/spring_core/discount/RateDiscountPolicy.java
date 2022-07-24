package spring.spring_core.discount;

import org.springframework.stereotype.Component;

import spring.spring_core.annotation.MainDiscountPolicy;
import spring.spring_core.member.Grade;
import spring.spring_core.member.Member;

@Component
// @Qualifier("mainDiscountPolicy")
//문자이기 때문에 오타가 나도 컴파일 타임에 잡아낼 수가 없음!
//따라서 자체 어노테이션을 만들어서 사용하면 더 좋음!
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

	private int discountPercent = 10;

	@Override
	public int discount(Member member, int price) {
		if (member.getGrade() == Grade.VIP) {
			return price * discountPercent / 100;
		} else {
			return 0;
		}
	}
}
