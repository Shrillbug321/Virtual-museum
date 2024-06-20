package pl.dreszer.projekt.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.dreszer.projekt.models.authorization.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "trips")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tripId;
    @ManyToOne
    private Guider guider;
    private String subject;
    @DateTimeFormat(pattern = "dd-MM-yyyy", iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    private String description;
    @ManyToMany
    private Set<User> users = new HashSet<>();

    public Trip(int tripId, Guider guider, String subject, LocalDate date, String description) {
        this.tripId = tripId;
        this.guider = guider;
        this.subject = subject;
        this.date = date;
        this.description = description;
        this.users = new HashSet<>();
    }
}
