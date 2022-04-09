package study.datajpa.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.dto.MemberDTO;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

@Transactional
@SpringBootTest
@Rollback(value = false)
class MemberRepositoryTest {
    
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TeamRepository teamRepository;
    @PersistenceContext
    EntityManager entityManager;
    
    @Test
    public void testMember() throws Exception {
        //given
        Member member = new Member("memberA");
        //when
        Member savedMember = memberRepository.save(member);
        Member findMember = memberRepository.findById(member.getId()).get();
        //then
        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);
        
    }
    
    @Test
    public void basicCRUD() throws Exception {
        //given
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        Member findMember1 = memberRepository.findById(member1.getId()).get();
        Member findMember2 = memberRepository.findById(member2.getId()).get();
        //then
        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);
        
        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);
        
        long count = memberRepository.count();
        assertThat(count).isEqualTo(2);
        
        memberRepository.delete(member1);
        memberRepository.delete(member2);
        
        long deletedCount = memberRepository.count();
        assertThat(deletedCount).isEqualTo(0);
        
        
    }
    
    @Test
    public void findByUsernameAndAgeGraterThanTest() throws Exception {
        //given
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);
        //when
        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("AAA", 15);
        //then
        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
        assertThat(result.get(0).getAge()).isEqualTo(20);
        assertThat(result.size()).isEqualTo(1);
    }
    
    @Test
    public void findUser() throws Exception {
        //given
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);
        //when
        List<Member> result = memberRepository.findUser("AAA", 10);
        //then
        assertThat(result.get(0)).isEqualTo(m1);
        
    }
    
    @Test
    public void findUsernameList() throws Exception {
        //given
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);
        //when
        List<String> result = memberRepository.findUsernameList();
        for (String s : result) {
            System.out.println("s = " + s);
        }
    }
    
    @Test
    public void findMemberDTO() throws Exception {
        //given
        Team team = new Team("teamA");
        teamRepository.save(team);
        
        Member m1 = new Member("AAA", 10);
        m1.setTeam(team);
        memberRepository.save(m1);
        
        //when
        List<MemberDTO> memberDTO = memberRepository.findMemberDTO();
        for (MemberDTO dto : memberDTO) {
            System.out.println("dto = " + dto);
        }
        
    }
    
    @Test
    public void findByNames() throws Exception {
        //given
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);
        //when
        List<Member> result = memberRepository.findByNames(Arrays.asList("AAA", "BBB"));
        for (Member member : result) {
            System.out.println("member = " + member);
        }
    }
    
    @Test
    public void paging() throws Exception {
        //given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));
        memberRepository.save(new Member("member6", 10));
        
        int age = 10;
        Pageable pageRequest = PageRequest.of(0, 3, Sort.by(Direction.DESC, "username"));
        //when
        Page<Member> page = memberRepository.findByAge(age, pageRequest);
    
        Page<MemberDTO> toMap = page.map(
            member -> new MemberDTO(member.getId(), member.getUsername(), null));
        //then
        List<Member> content = page.getContent();
        long totalElements = page.getTotalElements();
        assertThat(content.size()).isEqualTo(3);
        assertThat(page.getTotalElements()).isEqualTo(6);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.isFirst()).isEqualTo(true);
        assertThat(page.hasNext()).isEqualTo(true);
    }
    
    @Test
    public void bulkUpdate() throws Exception {
      //given
        memberRepository.save(new Member("member1",10));
        memberRepository.save(new Member("member2",19));
        memberRepository.save(new Member("member3",20));
        memberRepository.save(new Member("member4",23));
        memberRepository.save(new Member("member5",34));
        memberRepository.save(new Member("member6",42));
      //when
        int resultCount = memberRepository.bulkAgePlus(20);
//        entityManager.flush();
//        entityManager.clear();
    
        List<Member> member5 = memberRepository.findByUsername("member5");
        Member member = member5.get(0);
        System.out.println(member);
    
        //then
        assertThat(resultCount).isEqualTo(4);
    }
    
   
}