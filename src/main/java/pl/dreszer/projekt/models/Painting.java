package pl.dreszer.projekt.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import pl.dreszer.projekt.configurations.Profiles;
import pl.dreszer.projekt.repositories.GenresRepository;
import pl.dreszer.projekt.repositories.TechniquesRepository;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@ToString
@Profile(Profiles.PLAIN_CONTROLLERS)
@Entity
@NamedQuery(name = "Painting.findByTechnique", query = "select p, t from Painting p, Technique t where t.name = ?1 or p.name=?1")
@Table(name = "paintings")
public class Painting implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paintingId;

    @Column(nullable = false)
    @NotEmpty
    private String name;

    @Column(nullable = false)
    @NotEmpty
    private String author;

    @Column(name = "add_date", nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy", iso = DateTimeFormat.ISO.DATE)
    private LocalDate addDate;

    @Column(name = "painted_date", nullable = false)
    @Past
    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy", iso = DateTimeFormat.ISO.DATE)
    private LocalDate paintedDate;

    @Column(nullable = false)
    @NotNull
    @NumberFormat(pattern = "#.##")
    private float value;

    @Column(nullable = false)
    private boolean exhibited = false;

    @Column(nullable = false)
    private String dimensions;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "technique_id", nullable = false)
    @ToString.Exclude
    private Technique technique = new Technique();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "paintings_genres",
            joinColumns = @JoinColumn(name = "painting_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "museum_id")
    private Museum museum;

    @Column
    @NotNull
    private int exemplars;

    @Transient
    @Autowired
    private TechniquesRepository techniquesRepository;

    @Transient
    @Autowired
    private GenresRepository genresRepository;

    public Painting() {
        this.technique = new Technique();
        this.genres = new HashSet<>();
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}