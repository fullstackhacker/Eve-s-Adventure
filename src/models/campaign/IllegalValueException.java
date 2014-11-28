package models.campaign;

public class IllegalValueException extends Exception{

	public IllegalValueException(){
		System.err.println("IllegalValueException: please provide correct value");
	}
}
