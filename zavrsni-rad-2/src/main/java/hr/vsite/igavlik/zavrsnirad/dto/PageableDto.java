package hr.vsite.igavlik.zavrsnirad.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PageableDto<E> {
    private List<E> pageItems = new ArrayList<>();
    private int pageIndex;
    private int pageSize;
    private long totalElements;
    private long totalPages;
}
