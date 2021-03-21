package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
//jpa 가 구현체를 자동으로 등록해줌
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //인터페이스 이름만으로 개발이 끝남 
    @Override
    Optional<Member> findByName(String name);
}
