package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberRepository repository;


    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Long join(Member member){
        //같은 이름이 있는 중복 회원은 안된다.
        validateDuplicateMember(member); //중복회원 검증
        repository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        repository.findByName(member.getName())
                .ifPresent(m->{
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        });
    }
    public List<Member> findMembers(){
        return repository.findAll();
    }

    public Optional<Member> findMember(Long id){
        return repository.findById(id);
    }

}
