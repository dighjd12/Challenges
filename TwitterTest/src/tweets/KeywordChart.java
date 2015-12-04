package tweets;

import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

public class KeywordChart extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int CHART_SIZE = 10;
	private String chartTitle;

	public KeywordChart(String applicationTitle, String pChartTitle) {
		super(applicationTitle);

		chartTitle = pChartTitle;

	}

	public void createChart(Data data) {
		DefaultPieDataset result = new DefaultPieDataset();
		Map<String, Integer> refinedMap = data.getKeywordFreqMap();

		int counter = 0;
		for (Entry<String, Integer> entry : refinedMap.entrySet()) {
			if (counter >= CHART_SIZE) {
				break;
			}
			if (entry.getValue() >= 1) {
				result.setValue(entry.getKey() + " :" + entry.getValue(),
						entry.getValue());
			}
			counter++;
		}

		chartInit(result);

	}

	private JFreeChart chartInit(PieDataset dataset) {

		JFreeChart chart = ChartFactory.createPieChart3D(chartTitle, 
				dataset,
				true, // include legend
				true, false);

		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		
		setContentPane(chartPanel);
		return chart;

	}

}
