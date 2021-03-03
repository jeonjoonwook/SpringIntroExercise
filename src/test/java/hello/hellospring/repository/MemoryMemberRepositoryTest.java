package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();
    
    //테스트 할때마다 데이터 정리 용도
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){

        Member member = new Member();
        member.setName("spring");
        repository.save(member);
        //반환형이 optional일때는 get으로 꺼낸다.
        Member result=repository.findById(member.getId()).get();
        System.out.println("result = "+ (result == member));
        Assertions.assertEquals(member, result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        System.out.println("result = "+ (result == member1));
        Assertions.assertEquals(member1, result);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        System.out.println("result = "+ (result.size() == 2));

    }
}
