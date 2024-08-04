package hr.vsite.igavlik.zavrsnirad.dto;

import hr.vsite.igavlik.zavrsnirad.entity.Event;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class TeamDto {
    private String id;
    private String name;
}
