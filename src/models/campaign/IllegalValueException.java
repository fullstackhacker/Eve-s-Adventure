package models.campaign;

public class IllegalValueException extends RuntimeException{

	private static final long serialVersionUID = 4788344374109456041L;
	
	/**
     * Constructs a {@code NullPointerException} with no detail message.
     */
	public IllegalValueException(){
		super("Please insert correct parameter.");
	}
	
	/**
     * Constructs a {@code IllegalValueException} with the specified
     * detail message.
     *
     * @param   s   the detail message.
     */
	public IllegalValueException(String s){
		super(s);
	}
}
