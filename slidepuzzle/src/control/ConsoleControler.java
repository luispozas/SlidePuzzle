package control;

import java.util.Scanner;

import model.GameError;
import model.SlidePuzzle;

public class ConsoleControler extends Controller {

	private Scanner in;

	public ConsoleControler(Scanner in, SlidePuzzle game) {
		super(game);
		this.in = in;
	}

	public void run() {
		String line;

		while (!exit) {

			// print the state
			System.out.println(game);
			if (game.isFinished())
				System.out.println("Game is Over!");
			System.out.println();

			// ask for command
			System.out.print("Please enter a command: ");
			line = in.nextLine().toLowerCase();
			Command command = CommandSet.parse(line);

			// execute the command
			if (command != null) {
				try {
					command.execute(this);
				} catch (GameError e) {
					System.err.println(e.getMessage());
				}
			} else {
				System.err.println("Invalid command (use HELP): " + line);
			}
		}

		System.out.println("Closing the game... ");
	}
}
