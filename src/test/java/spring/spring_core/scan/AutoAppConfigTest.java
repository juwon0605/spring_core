package spring.spring_core.scan;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.spring_core.AutoAppConfig;
import spring.spring_core.member.MemberService;

public class AutoAppConfigTest {

	@Test
	void basicScan() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

		MemberService memberService = ac.getBean(MemberService.class);
		assertThat(memberService).isInstanceOf(MemberService.class);
	}
}
