package spring.spring_core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import spring.spring_core.AppConfig;

public class MemberServiceTest {

	//    MemberService memberService = new MemberServiceImpl();
	MemberService memberService;

	//  Test 실행 전 매번 의존관계 주입
	@BeforeEach
	public void beforeEach() {
		AppConfig appConfig = new AppConfig();
		memberService = appConfig.memberService();
	}

	@Test
	void join() {
		//given
		Member member = new Member(1L, "memberA", Grade.VIP);

		//when
		memberService.join(member);
		Member findMember = memberService.findMember(1L);

		//then
		Assertions.assertThat(member).isEqualTo(findMember);
	}
}
