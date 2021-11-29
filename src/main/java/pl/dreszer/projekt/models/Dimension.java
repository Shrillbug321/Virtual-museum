package pl.dreszer.projekt.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
@Data
@NoArgsConstructor

@Embeddable
public class Dimension {
	private Float width;
	private Float height;


	public Dimension(Float width, Float heigth) {
		this.width = width;
		this.height = heigth;
	}

}
