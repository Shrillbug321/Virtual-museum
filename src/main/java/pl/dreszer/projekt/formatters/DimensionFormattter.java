package pl.dreszer.projekt.formatters;

import org.springframework.format.Formatter;
import pl.dreszer.projekt.models.Dimension;

import java.text.ParseException;
import java.util.Locale;

public class DimensionFormattter implements Formatter<Dimension> {
	@Override
	public Dimension parse(String text, Locale locale) throws ParseException {
		return null;
	}

	@Override
	public String print(Dimension object, Locale locale) {
		return null;
	}
}
