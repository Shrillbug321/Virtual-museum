package pl.dreszer.projekt.controllers.filters;

import org.springframework.data.jpa.domain.Specification;
import pl.dreszer.projekt.metamodels.Painting_;
import pl.dreszer.projekt.models.Painting;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;

public class PaintingsSpecifications {
	public static Specification<Painting> findByPaintedDate(LocalDate minDate, LocalDate maxDate)
	{
		return (painting, query, cb) -> {
			return cb.between(painting.get("paintedDate"), minDate, maxDate);
		};
		//return null;
	}
}
