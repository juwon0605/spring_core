package spring.spring_core.autowired;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.spring_core.AutoAppConfig;
import spring.spring_core.discount.DiscountPolicy;
import spring.spring_core.member.Grade;
import spring.spring_core.member.Member;

public class AllBeanTest {

	@Test
	void findAllBean() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class,
			DiscountService.class);

		DiscountService discountService = ac.getBean(DiscountService.class);
		Member member = new Member(1L, "userA", Grade.VIP);
		int discountPrice = discountService.dicount(member, 10000, "fixDiscountPolicy");

		assertThat(discountService).isInstanceOf(DiscountService.class);
		assertThat(discountPrice).isEqualTo(1000);

		int rateDiscountPrice = discountService.dicount(member, 20000, "rateDiscountPolicy");
		assertThat(rateDiscountPrice).isEqualTo(2000);
	}

	static class DiscountService {
		private final Map<String, DiscountPolicy> policyMap;
		private final List<DiscountPolicy> policies;

		@Autowired
		public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
			this.policyMap = policyMap;
			this.policies = policies;
			System.out.println("policyMap = " + policyMap);
			System.out.println("policies = " + policies);
		}

		public int dicount(Member member, int price, String discountCode) {
			DiscountPolicy discountPolicy = policyMap.get(discountCode);
			return discountPolicy.discount(member, price);
		}
	}
}
