package pl.dreszer.projekt.metamodels;

import lombok.Data;
import pl.dreszer.projekt.models.Genre;
import pl.dreszer.projekt.models.Painting;
import pl.dreszer.projekt.models.Technique;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import java.util.Set;
@Data
@StaticMetamodel(Painting.class)
public class Painting_ {
	public static volatile SingularAttribute<Painting, Integer> id;
	public static volatile SingularAttribute<Painting, String> name;
	public static volatile SingularAttribute<Painting, String> author;
	public static volatile SingularAttribute<Painting, LocalDate> addDate;
	public static volatile SingularAttribute<Painting, LocalDate> paintedDate;
	public static volatile SingularAttribute<Painting, Float> value;
	public static volatile SingularAttribute<Painting, Boolean> exhibited;
	public static volatile SingularAttribute<Painting, Technique> technique;
	public static volatile SetAttribute<Painting, Set<Genre>>  genres;
}
