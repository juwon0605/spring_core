package spring.spring_core.beanfind;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.spring_core.member.MemberRepository;
import spring.spring_core.member.MemoryMemberRepository;

public class ApplicationContextSameBeanFindTest {

	//AppConfig를 바꾸지 않기위해 지역 변수를 만들어서 테스트
	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

	@Test
	@DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다")
	void findBeanByTypeDuplicate() {
		//MemberRepository 타입이 2개 있어서 예외 발생(NoUniqueBeanDefinitionException)
		// MemberRepository bean = ac.getBean(MemberRepository.class);
		assertThrows(NoUniqueBeanDefinitionException.class,
			() -> ac.getBean(MemberRepository.class));
	}

	@Test
	@DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다")
	void findBeanByName() {
		MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
		assertThat(memberRepository).isInstanceOf(MemberRepository.class);
	}

	@Test
	@DisplayName("특정 타입을 모두 조회하기")
	void findAllBeanByType() {
		Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
		for (String key : beansOfType.keySet()) {
			System.out.println("key + = " + key + " value = " + beansOfType.get(key));
		}
		System.out.println("beansOfType = " + beansOfType);
		assertThat(beansOfType.size()).isEqualTo(2);
	}

	//class안에 static class 선언하는 것은 이 범위에서만 쓰겠다는 의미
	@Configuration
	static class SameBeanConfig {

		@Bean
		public MemberRepository memberRepository1() {
			return new MemoryMemberRepository();
		}

		@Bean
		public MemberRepository memberRepository2() {
			return new MemoryMemberRepository();
		}
	}
}
