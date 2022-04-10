package study.querydsl.entity;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.ToString.Exclude;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Team {
    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long id;
    private String name;
    @OneToMany(mappedBy = "team")
    @Exclude
    private List<Member> members = new ArrayList<>();
    public Team(String name) {
        this.name = name;
    }
}
