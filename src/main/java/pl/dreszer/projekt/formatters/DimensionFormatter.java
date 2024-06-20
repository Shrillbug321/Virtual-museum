package pl.dreszer.projekt.formatters;

import org.springframework.format.Formatter;
import pl.dreszer.projekt.models.Dimension;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Locale;

public class DimensionFormatter implements Formatter<Dimension> {
    @Override
    public Dimension parse(String text, Locale locale) throws ParseException {
        if (text.isEmpty())
            return null;
        String[] tokens = text.split("x");
        return new Dimension(Float.valueOf(tokens[0]), Float.valueOf(tokens[1]));
    }

    @Override
    public String print(@NotNull Dimension dimension, Locale locale) {
        return String.format("%fx%f", dimension.getWidth(), dimension.getHeight());
    }
}
