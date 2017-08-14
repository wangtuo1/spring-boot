package learn.springboot.error;

public class ErrorEntity {
	private String message;
	
	public ErrorEntity(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
