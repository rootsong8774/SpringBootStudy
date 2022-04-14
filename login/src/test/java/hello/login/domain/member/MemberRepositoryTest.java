package hello.login.domain.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberRepositoryTest {
    
    @Autowired
    MemberRepository repository;
    
    @Test
    public void memberTest() throws Exception {
        System.out.println(repository.findById(1L).getLoginId());
    }
}
