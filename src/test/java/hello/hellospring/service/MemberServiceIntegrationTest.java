package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@SpringBootTest
@Transactional //테스트 끝나면 rollback 해줌
public class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;


    @Test
    @Commit
    void 회원가입() throws SQLException {
        //given
        Member member = new Member();
        member.setName("joonwook");
        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertEquals(member.getName(), findMember.getName());
    }

    @Test
    public void 중복_회원_예외() throws SQLException {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        memberService.join(member1);

        IllegalStateException e = Assertions.assertThrows(IllegalStateException.class, ()->memberService.join(member2));
        Assertions.assertEquals(e.getMessage(),"이미 존재하는 회원입니다.");

    }

    @Test
    void findMembers(){

    }

    @Test
    void findOne(){

    }

}
