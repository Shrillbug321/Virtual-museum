package pl.dreszer.projekt;

import lombok.Getter;
import lombok.Setter;
import pl.dreszer.projekt.models.Genre;
import pl.dreszer.projekt.models.Technique;
import java.util.Set;
import java.util.stream.Collectors;

@Getter @Setter
public class PaintingDTO
{
    private int paintingId;
    private String name;
    private String author;
    private int techniqueId;
    private Set<Integer> genresIds;

   /* public void setTechnique(Technique technique){
        this.techniqueId = technique.getId();
    }*/

    public void setGenres(Set<Genre> genres)
    {
        genresIds = genres.stream().map(x->x.getId()).collect(Collectors.toSet());
    }
}
