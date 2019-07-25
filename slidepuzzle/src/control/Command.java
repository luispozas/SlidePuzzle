package control;

public interface Command {
	void execute(Controller c) throws model.GameError;
	String helpText();
	Command parse(String []lineWords);	
}
