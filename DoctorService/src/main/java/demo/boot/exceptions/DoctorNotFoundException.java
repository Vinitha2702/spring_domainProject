package demo.boot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class DoctorNotFoundException extends RuntimeException{

	public DoctorNotFoundException(String message) {
		super(message);
		}

}
