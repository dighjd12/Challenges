package tweets;

import javax.swing.SwingUtilities;


/**
 * The Class Main.
 */
public class Main {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Framework();
			}
		});
	}
	
}
