package pl.dreszer.projekt.models;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import pl.dreszer.projekt.repositories.GenresRepository;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString

@Entity
@NamedEntityGraph
        (
                name="pgraph",
                attributeNodes = {
                        @NamedAttributeNode("technique"),
                        @NamedAttributeNode("genres")
                }
        )
@NamedQuery(name="Painting.findByTechnique", query="select p, t from Painting p, Technique t where t.name = ?1 or p.name=?1")
@Table(name="paintings")
public class Painting
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(nullable=false)
    @NotEmpty
    private String name;

    @Column(nullable=false)
    @NotEmpty
    private String author;

    @Column(name="add_date", nullable=false)
    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy", iso=DateTimeFormat.ISO.DATE)
    private LocalDate addDate;

    @Column(name="painted_date", nullable=false)
    @Past
    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy", iso=DateTimeFormat.ISO.DATE)
    private LocalDate paintedDate;

    @Column(nullable=false)
    @NotNull
    @NumberFormat(pattern = "#.##")
    private float value;

    @Column(nullable=false)
    private boolean exhibited;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="technique_id", nullable = false)
    @ToString.Exclude
    private Technique technique;
    //dodaj kierunek, nurt?

    @JoinColumn(name = "genres_id")
    @ManyToMany(fetch=FetchType.EAGER)
    private Set<Genre> genres;
    @Transient
    @Autowired
    private GenresRepository genresRepository;
    public Painting()
    {
        this.technique = new Technique();
        this.genres = new HashSet<>();
    }

    @Bean("paintingBean")
    public Painting createPainting()
    {
        Painting painting = new Painting();
        painting.setId(1);
        painting.setName("Słoneczniki");
        painting.setAuthor("Van Gogh");
        painting.setAddDate(LocalDate.of(2021, 10, 20));
        painting.setPaintedDate(LocalDate.of(1872, 5, 15));
        painting.setValue(1000000.99f);
        painting.setExhibited(false);
        painting.setTechnique(new Technique(1,"Olej na płótnie"));
        Set<Genre> a = new HashSet<>();
        //a.add(new Genre(1,"lll"));
        painting.setGenres(a);
        return painting;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Painting painting = (Painting) o;
        return id != null && Objects.equals(id, painting.id);
    }*/

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
