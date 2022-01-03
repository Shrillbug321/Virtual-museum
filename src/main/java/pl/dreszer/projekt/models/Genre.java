package pl.dreszer.projekt.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="genres")
public class Genre {
	@Id
	private int id;
	private String name;

	public Genre(int id, String name)
	{
		this.id = id;
		this.name = name;
	}
}
