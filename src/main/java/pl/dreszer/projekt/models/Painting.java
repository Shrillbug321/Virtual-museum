package pl.dreszer.projekt.models;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class Painting {

    private int id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String author;
    @DateTimeFormat(pattern = "dd-MM-yyyy", iso=DateTimeFormat.ISO.DATE)
    private LocalDate addDate;
    @Past
    @DateTimeFormat(pattern = "dd-MM-yyyy", iso=DateTimeFormat.ISO.DATE)
    private LocalDate paintedDate;
    @NumberFormat(pattern = "#.00")
    private float value;
    private boolean exhibited;
    private Technique technique;

    public Painting()
    {
        this.technique = new Technique();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getAddDate() {
        return addDate;
    }

    public void setAddDate(LocalDate addDate) {
        this.addDate = addDate;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isExhibited() {
        return exhibited;
    }

    public void setExhibited(boolean exhibited) {
        exhibited = exhibited;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getPaintedDate() {
        return paintedDate;
    }

    public void setPaintedDate(LocalDate paintedDate) {
        this.paintedDate = paintedDate;
    }

    public Technique getTechnique() {
        return technique;
    }

    public void setTechnique(Technique technique) {
        this.technique = technique;
    }
}
