package launcher;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import control.ConsoleControler;
import control.Controller;
import model.SlidePuzzle;
import views.MainWindow;

public class Main {

	public static void main_0() {
		SlidePuzzle game = new SlidePuzzle();
		ConsoleControler ctrl = new ConsoleControler( new Scanner(System.in), game);
		ctrl.run();
	}

	public static void main_1() {
		SlidePuzzle game = new SlidePuzzle();
		Controller ctrl = new Controller(game);	
		SwingUtilities.invokeLater( new Runnable() {
			
			@Override
			public void run() {
				new MainWindow(ctrl);
			}
		});
		
	}

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		main_1();
	}

}
