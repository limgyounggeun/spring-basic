package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @AfterEach
    public void afterEach(){
       memberRepository = new MemoryMemberRepository();
       memberService = new MemberService(memberRepository);
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("John");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findMember(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    void joinException() {
        //given
        Member member1 = new Member();
        member1.setName("John");
        Member member2 = new Member();
        member2.setName("John");
        //when
        memberService.join(member1);
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        try {
//            Long saveId2 = memberService.join(member2);
//            fail();
//        }catch (Exception e){
//            assertThat(e.getMessage().equals("이미 존재하는 회원입니다."));
//        }
        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findMember() {
    }
}