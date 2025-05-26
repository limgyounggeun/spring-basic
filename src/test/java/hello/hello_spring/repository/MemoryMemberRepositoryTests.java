package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MemoryMemberRepositoryTests {
    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        memoryMemberRepository.storeClear();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("Spring");
        memoryMemberRepository.save(member);
        Member rs = memoryMemberRepository.findById(member.getId()).get();
        Assertions.assertThat(member).isEqualTo(rs);
    }

    @Test
    public void findByName(){
        Member member = new Member();
        member.setName("Spring");
        memoryMemberRepository.save(member);
        Member member1 = new Member();
        member1.setName("Spring1");
        memoryMemberRepository.save(member1);
        Member rs =memoryMemberRepository.findByName("Spring1").get();
        Assertions.assertThat(member1).isEqualTo(rs);
    }

    @Test
    public void findAll(){
        Member member = new Member();
        member.setName("Spring");
        memoryMemberRepository.save(member);
        Member member1 = new Member();
        member1.setName("Spring1");
        memoryMemberRepository.save(member1);
        List<Member> members = memoryMemberRepository.findAll();
        Assertions.assertThat(members.size()).isEqualTo(2);
    }
}
