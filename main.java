import javax.swing.*;
public class main {
	
	public static void main(String[] args) {
		
		GUI gui;
		TUI tui;
		
		
		if (args.length == 0) {
			JFrame.setDefaultLookAndFeelDecorated(true);
			gui = new GUI();
		}
		else {
			System.out.println("W³¹czam tryb tekstowy");
			tui = new TUI();
		}
			
		
						

	}

}
