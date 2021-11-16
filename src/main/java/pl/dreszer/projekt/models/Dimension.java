package pl.dreszer.projekt.models;

public class Dimension {
	private Float width;
	private Float height;

	public Dimension(Float width, Float heigth) {
		this.width = width;
		this.height = heigth;
	}

	public Float getWidth() {
		return width;
	}

	public void setWidth(Float width) {
		this.width = width;
	}

	public Float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
		this.height = height;
	}
}
