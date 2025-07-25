package hello.core.member;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();
    @Test
    void join(){
        //given
        Member member = new Member(1L, "MemberA", Grade.VIP);
        //when
        memberService.join(member);
        Member member1 = memberService.findMember(1L);
        //then
        Assertions.assertEquals(member, member1);
    }
}
