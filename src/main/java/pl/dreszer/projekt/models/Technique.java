package pl.dreszer.projekt.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@NoArgsConstructor
@Data

@Entity
@Table(name="techniques")
public class Technique {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private String name;

	public Technique(int id, String name) {
		this.id = id;
		this.name = name;
	}
}