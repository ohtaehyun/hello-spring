package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService service;
    MemoryMemberRepository repo;

    @BeforeEach
    public void beforeEach() {
        repo = new MemoryMemberRepository();
        service = new MemberService(repo);
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("test1");
        //when
        long saveId = service.join(member);

        //then
        Member one = service.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(one.getName());
    }

    @Test
    void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("same");
        Member member2 = new Member();
        member2.setName("same");

        service.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> service.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}