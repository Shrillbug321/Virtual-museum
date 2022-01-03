package pl.dreszer.projekt.controllers.filters;

import lombok.Data;

@Data
public class PaintingFilter {
    String phrase;
    String where;
    float min, max;
    String minDate, maxDate;
}
