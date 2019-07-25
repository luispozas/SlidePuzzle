package model;

@SuppressWarnings("serial")
public class GameError extends RuntimeException {

	public GameError(String msg) {
		super(msg);
	}

}
