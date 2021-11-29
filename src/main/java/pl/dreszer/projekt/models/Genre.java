package pl.dreszer.projekt.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
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
