package spring.spring_core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

	//    추상화와 구체화 모두 의존. DIP 원칙 위반.
	//    private final MemberRepository memberRepository = new MemoryMemberRepository();

	//  추상화에만 의존. DIP 원칙수 준수.
	private final MemberRepository memberRepository;

	//  AppConfig를 통한 생성자 주입(DI: Dependency Injection, 의존관계 주입)
	//  관심사 분리: 객체를 생성하고 연결하는 역할과 실행하는 역할 분리
	@Autowired
	// 간략하게 ac.getBean(MemberRepository.class)와 같이 동작
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override
	public void join(Member member) {
		memberRepository.save(member);
	}

	@Override
	public Member findMember(Long memberId) {
		return memberRepository.findById(memberId);
	}

	//테스트 용도
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
}
