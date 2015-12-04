package tweets;

import javax.swing.JPanel;

/**
 * The Class KeywordAnalysis.
 */
public class KeywordAnalysis {
  
  /** The main panel. */
  JPanel mainPanel;
  
  /**
   * Creates a new keyword analysis chart
   */
  public KeywordAnalysis(){
    mainPanel=new JPanel();
  }

  /**
   * create the Chart
   *
   * @param data the data
   * @return the j panel
   */
  public JPanel analyzeData(Data data) {
    KeywordChart myChart=new KeywordChart("Keyword Frequency", "Keyword Frequency");
    myChart.createChart(data);
    
    myChart.pack();
    myChart.setVisible(true);
    return mainPanel;
  }
    
    
    
   
  }

