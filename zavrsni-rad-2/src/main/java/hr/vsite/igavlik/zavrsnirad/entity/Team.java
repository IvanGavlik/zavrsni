package hr.vsite.igavlik.zavrsnirad.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "team")
@NoArgsConstructor
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "team_id", columnDefinition = "uuid", updatable = false)
    private UUID id;

    @Column(name = "name")
    private String name;


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Team)) {
            return false;
        }
        Team e = (Team) obj;
        if(!this.getName().equals(e.getName())) {
            return false;
        }
        return true;
    }
}
