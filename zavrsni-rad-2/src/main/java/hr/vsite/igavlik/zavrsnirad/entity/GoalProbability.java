package hr.vsite.igavlik.zavrsnirad.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "poisson_guest_goal_probability")
@NoArgsConstructor
@Getter
@Setter
public class GoalProbability {

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uuid", updatable = false )
    private UUID id;

    @Column(name = "goal")
    private Integer goal;

    @Column(name = "probability")
    private Double probability;

    public GoalProbability(Integer goal, Double probability) {
        this.goal = goal;
        this.probability = probability;
    }

    @Override
    public int hashCode() {
        return goal.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)  {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        if(! (obj instanceof GoalProbability)) {
            return false;
        }
        return Objects.equals(this.getGoal(), ((GoalProbability) obj).getGoal());
    }

    @Override
    public String toString() {
        return "goal:" + this.goal + " probability: " + this.probability;
    }
}
