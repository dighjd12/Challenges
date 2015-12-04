package tweets;

import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * The Class Framework.
 */
public class Framework extends JFrame{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The framework. */
	private FrameworkCore framework;
	
	/** The title. */
	private static String title = "Popular Keywords in Twitter";
	
	public Framework(){
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		framework = new FrameworkCore();
		MyPanel panel = new MyPanel(this);
		framework.registerListener(panel);
	    add((JPanel) panel);
		
		this.pack();
		this.setResizable(true);
		this.setVisible(true);
		
	}
	
	
	
	
	
	
}
