package spring.spring_core.scope;

import static org.assertj.core.api.Assertions.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithPrototypeTest1 {

	@Test
	void prototypeFind() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
			PrototypeBean.class);
		PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
		prototypeBean1.addCount();
		assertThat(prototypeBean1.getCount()).isEqualTo(1);

		PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
		prototypeBean2.addCount();
		assertThat(prototypeBean2.getCount()).isEqualTo(1);
	}

	@Test
	void singletonClientUsePrototype() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
			ClientBean.class, PrototypeBean.class);

		ClientBean clientBean1 = ac.getBean(ClientBean.class);
		int count1 = clientBean1.logic();
		assertThat(count1).isEqualTo(1);

		ClientBean clientBean2 = ac.getBean(ClientBean.class);
		int count2 = clientBean2.logic();
		assertThat(count2).isEqualTo(1);
	}

	@Scope("singleton")
	static class ClientBean {

		// @Autowired
		// //스프링이 자동으로 빈에 등록
		// private ObjectProvider<PrototypeBean> prototypeBeanObjectProvider;
		// //Provider가 Factory를 상속받고 편의기능(옵션, 스트림 등) 몇 개 추가
		// // private ObjectFactory<PrototypeBean> prototypeBeanObjectProvider;
		//
		// public int logic() {
		// 	PrototypeBean prototypeBean = prototypeBeanObjectProvider.getObject();
		// 	prototypeBean.addCount();
		// 	return prototypeBean.getCount();
		// }

		//스프링에 덜 의존적인 자바 표준 기술 사용
		//단위테스트를 만들거나 mock 코드를 만들기 훨씬 쉽다
		//단, 별도의 라이브러리가 필요하다
		//JPA는 이미 표준이 됐지만, 스프링과 자바 표준은 기준이 애매하다
		//기능보고 더 적합하거나 더 편한 걸 고르면 된다. 웬만해서는 스프링 권장. 사실상 표준
		@Autowired
		private Provider<PrototypeBean> prototypeBeanProvider;

		public int logic() {
			PrototypeBean prototypeBean = prototypeBeanProvider.get();
			prototypeBean.addCount();
			return prototypeBean.getCount();
		}
	}

	@Scope("prototype")
	static class PrototypeBean {
		private int count = 0;

		public void addCount() {
			++count;
		}

		public int getCount() {
			return count;
		}

		@PostConstruct
		public void init() {
			System.out.println("PrototypeBean.init " + this);
		}

		@PreDestroy
		public void destroy() {
			System.out.println("PrototypeBean.destroy");
		}
	}
}
