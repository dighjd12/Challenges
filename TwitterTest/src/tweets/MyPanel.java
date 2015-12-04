package tweets;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The Class TrendCenterPanel.
 */
public class MyPanel extends JPanel{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The parent frame. */
	private JFrame parentFrame;
	
	/** The listener. */
	private FrameworkCore listener;
	
	/** The home panel. */
	private JPanel home;
	
	/** The date text. */
	private JTextField text;
	
	/**
	 * Instantiates a new trend center panel.
	 *
	 * @param f the frame
	 */
	public MyPanel(JFrame f){
		parentFrame = f;
		parentFrame.add(this);
		
		initGui();
	}
	
	/**
	 * Initiates the gui
	 */
	private void initGui(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		home = new JPanel();
		home.setLayout(new FlowLayout());
		home.add(createmainPanel());
		
		text = new JTextField();
		text.setText("Tweet Keywords in 5 Mins!");
		
		add(text);
		add(home);
	}
	
	/**
	 * Creates the input panel.
	 *
	 * @return the j panel
	 */
	private JPanel createmainPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JButton startButton = new JButton("Start Getting Tweets");
		
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.onStart();
				text.setText("Wait for 5 minutes..");
			}
		});
		
		panel.add(startButton);
		return panel;
	}
	
	/**
	 * On register.
	 *
	 * @param listener the framework core
	 */
	public void onRegister(FrameworkCore listener){
		this.listener = listener;
	}

	
	/**
	 * On analysis done.
	 *
	 * @param jp the JPanel to replace display
	 */
	public void onAnalysisDone(JPanel jp) {
		home.removeAll();
		home.add(jp);
	}

}
