package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    //member 리포지토리를 외부에서 주입


    public MemberService(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }


    //회원가입
    public Long join(Member member) throws SQLException {


            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();

    }

    private void validateDuplicateMember(Member member) throws SQLException {
        //같은 이름이 있는 중복 회원X
        memberRepository.findByName(member.getName())
                .ifPresent(m ->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    //전체 회원조회
    public List<Member> findMembers() throws SQLException {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) throws SQLException {
        return memberRepository.findById(memberId);
    }
}
