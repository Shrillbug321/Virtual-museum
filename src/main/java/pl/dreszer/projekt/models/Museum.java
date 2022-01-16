package pl.dreszer.projekt.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter @Setter

@Entity
@Table(name="museums")
public class Museum {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int museum_id;
	private String name;
	private String place;

	public Museum(int museum_id, String name, String place) {
		this.museum_id = museum_id;
		this.name = name;
		this.place = place;
	}
}
