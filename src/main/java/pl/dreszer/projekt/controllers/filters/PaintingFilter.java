package pl.dreszer.projekt.controllers.filters;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PaintingFilter {
    String phrase;
    String where;
    float min, max;
    String minDate, maxDate;
}
