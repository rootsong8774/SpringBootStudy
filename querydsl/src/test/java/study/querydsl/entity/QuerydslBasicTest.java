package study.querydsl.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static study.querydsl.entity.QMember.member;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class QuerydslBasicTest {
    
    
    @Autowired
    EntityManager em;
    
    JPAQueryFactory queryFactory;
    
    @BeforeEach
    public void before() throws Exception {
        queryFactory = new JPAQueryFactory(em);
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);
        
        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }
    
    @Test
    public void startJPQL() throws Exception {
        //member1
        Member findByJPQL = em.createQuery("select m from Member m where m.username=:username",
                Member.class)
            .setParameter("username", "member1")
            .getSingleResult();
        
        assertThat(findByJPQL.getUsername()).isEqualTo("member1");
    }
    
    @Test
    public void startQuerydsl() throws Exception {
        //given
        Member findMember = queryFactory.select(member)
            .from(member)
            .where(member.username.eq("member1"))
            .fetchOne();
        
        assertThat(findMember.getUsername()).isEqualTo("member1");
        
    }
    
    @Test
    public void search() throws Exception {
        Member findMember = queryFactory
            .selectFrom(member)
            .where(member.username.eq("member1").and(member.age.eq(10))
                .and(member.age.between(10, 30)))
            .fetchOne();
        
        assertThat(findMember.getUsername()).isEqualTo("member1");
        
    }
    
    @Test
    public void searchAndParam() throws Exception {
        Member findMember = queryFactory
            .selectFrom(member)
            .where(
                member.username.eq("member1"),
                member.age.eq(10)
            )
            .fetchOne();
    
        assertThat(findMember.getUsername()).isEqualTo("member1");
    
    }
    
    @Test
    public void resultFetch() throws Exception {
        List<Member> fetch = queryFactory
            .selectFrom(member)
            .fetch();
    
//        Member fetchOne = queryFactory
//            .selectFrom(member)
//            .fetchOne();
    
        Member fetchFirst = queryFactory
            .selectFrom(QMember.member)
            .fetchFirst();
    
        QueryResults<Member> results = queryFactory
            .selectFrom(member)
            .fetchResults();
        
        results.getTotal();
        List<Member> content = results.getResults();
    }
    
}
