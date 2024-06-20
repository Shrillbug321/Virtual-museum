package pl.dreszer.projekt.controllers.filters;

import org.springframework.data.jpa.domain.Specification;
import pl.dreszer.projekt.models.Painting;

import java.time.LocalDate;

public class PaintingsSpecifications {
    public static Specification<Painting> findByPaintedDate(LocalDate minDate, LocalDate maxDate) {
        return (painting, query, cb) -> cb.between(painting.get("paintedDate"), minDate, maxDate);
    }
}
