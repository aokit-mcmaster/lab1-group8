
import java.awt.BorderLayout;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class EGRAM extends javax.swing.JFrame {
    
    private TimeSeries atrial;
    private TimeSeries ventricular;
    
    private double lastValue1 = 50;
    private double lastValue2 = 50;
    
    /* singleton method */
    private static EGRAM soleInstance;
    public static EGRAM getInstance() {
        if(soleInstance == null)
            soleInstance = new EGRAM();
        return soleInstance;
    }
    
    private EGRAM() {
        initComponents();
    }
    
    @SuppressWarnings("deprecation")
    private void initComponents() {
        
        setTitle("Real-Time Electrogram");
        
        atrial = new TimeSeries("Atrial", Millisecond.class);
        final TimeSeriesCollection dataset1 = new TimeSeriesCollection(atrial);
        final JFreeChart chart1 = createChart(dataset1);
        final ChartPanel chartPanel1 = new ChartPanel(chart1);
        
        ventricular = new TimeSeries("Ventricular", Millisecond.class);
        final TimeSeriesCollection dataset2 = new TimeSeriesCollection(ventricular);
        final JFreeChart chart2 = createChart(dataset2);
        final ChartPanel chartPanel2 = new ChartPanel(chart2);
        
        getContentPane().add(chartPanel1, BorderLayout.NORTH);
        getContentPane().add(chartPanel2, BorderLayout.SOUTH);
        // setPreferredSize(new java.awt.Dimension(500, 400));
        pack();
        
        new Thread(()->{
            try {
                while(true) {
                    update();
                    Thread.sleep(100);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private JFreeChart createChart(XYDataset dataset) {
        JFreeChart result = ChartFactory.createTimeSeriesChart(
            "", 
            "Time", 
            "Amplitude (V)",
            dataset, 
            true, 
            true, 
            false
        );
        XYPlot plot = result.getXYPlot();
        ValueAxis axis = plot.getDomainAxis();
        // axis.setAutoRange(true);
        axis.setFixedAutoRange(60*1000);  // 60 seconds
        axis = plot.getRangeAxis();
        // axis.setAutoRange(true);
        axis.setRange(0.0, 100.0); 
        return result;
    }
    
    public void update() {
        lastValue1 *= 0.9 + 0.2*Math.random();
        lastValue2 *= 0.9 + 0.2*Math.random();
        atrial.add(new Millisecond(), lastValue1);
        ventricular.add(new Millisecond(), lastValue2);
    }
    
//    private JFreeChart createChart(XYDataset dataset) {
//        JFreeChart chart = ChartFactory.createXYLineChart(
//                "Average salary per age",
//                "Time",
//                "Voltage",
//                dataset,
//                PlotOrientation.VERTICAL,
//                true,
//                true,
//                false
//        );
//
//        XYPlot plot = chart.getXYPlot();
//
//        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
//        renderer.setSeriesPaint(0, Color.GREEN);
//        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
//
//        plot.setRenderer(renderer);
//        plot.setBackgroundPaint(Color.BLACK);
//
//        plot.setRangeGridlinesVisible(true);
//        plot.setRangeGridlinePaint(Color.WHITE);
//
//        plot.setDomainGridlinesVisible(true);
//        plot.setDomainGridlinePaint(Color.WHITE);
//
//        chart.getLegend().setFrame(BlockBorder.NONE);
//
//        chart.setTitle(new TextTitle("EGRAM",
//                        new Font("Serif", java.awt.Font.BOLD, 18)
//                )
//        );
//
//        return chart;
//    }
    
}