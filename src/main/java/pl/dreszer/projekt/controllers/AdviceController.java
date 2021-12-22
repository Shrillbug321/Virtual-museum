package pl.dreszer.projekt.controllers;

import org.hibernate.exception.JDBCConnectionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.dreszer.projekt.exceptions.PaintingNotFoundException;

@ControllerAdvice
public class AdviceController {

	@ExceptionHandler(PaintingNotFoundException.class)
	public String paintingNotFound()
	{
		return "errors/paintingNotFound";
	}
	@ExceptionHandler(JDBCConnectionException.class)
	public String notConnectedWithDB()
	{
		return "errors/notConnectedWithDB";
	}
}
