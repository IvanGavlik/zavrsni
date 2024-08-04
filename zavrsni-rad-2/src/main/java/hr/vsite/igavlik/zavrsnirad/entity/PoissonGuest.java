package hr.vsite.igavlik.zavrsnirad.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "poisson_guest")
@NoArgsConstructor
@Getter
@Setter
public class PoissonGuest {

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uuid", updatable = false )
    private UUID id;


    @OneToOne()
    @JoinColumn(name = "team_id", unique = true)
    private Team team;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "poisson_guest_id")
    private Set<GoalProbability> goalProbability = new HashSet<>();

    @Override
    public String toString() {
        return "PoissonGuest{" +
                "id=" + id +
                ", team=" + team +
                ", goalProbability=" + goalProbability +
                '}';
    }
}


