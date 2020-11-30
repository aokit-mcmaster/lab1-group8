import java.awt.Color;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class EGRAM extends javax.swing.JFrame {
    
    private static DCM_SerialCOM SERIAL_COM = DCM_SerialCOM.getInstance();
    private volatile boolean PAUSED = false;
    private volatile int SAMPLE_PERIOD = 10;
    private TimeSeries atrial;
    private TimeSeries ventricular;
    
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
        setTitle("Real-Time Electrogram (EGRAM)");
        
        atrial = new TimeSeries("ATR_SIGNAL", Millisecond.class);
        TimeSeriesCollection atrialDataSet = new TimeSeriesCollection(atrial);
        
        ventricular = new TimeSeries("VENT_SIGNAL", Millisecond.class);
        TimeSeriesCollection ventricularDataSet = new TimeSeriesCollection(ventricular);
        
        JFreeChart chart = createChart(atrialDataSet, ventricularDataSet);
        chart.getPlot().setBackgroundPaint(Color.BLACK);
        ChartPanel chartPanel = new ChartPanel(chart);
        
        add(chartPanel);
        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        pack();
        
        this.start();
    }

    private JFreeChart createChart(XYDataset dataSet1, XYDataset dataSet2) {
        XYItemRenderer renderer1 = new StandardXYItemRenderer();
        XYItemRenderer renderer2 = new StandardXYItemRenderer();

        DateAxis domainAxis = new DateAxis("Time");
        domainAxis.setAutoRange(true);
        domainAxis.setFixedAutoRange(10 * 1000);
        
        ValueAxis rangeAxis = new NumberAxis("Amplitude (V)");
        rangeAxis.setAutoRange(false);
        rangeAxis.setRange(0, 5);
        rangeAxis.setLowerBound(0);
        rangeAxis.setUpperBound(5);
        
        XYPlot plot = new XYPlot(dataSet1, domainAxis, rangeAxis, renderer1);
        plot.setDataset(1, dataSet2);
        plot.setRenderer(1, renderer2);
        
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        
        JFreeChart chart = new JFreeChart("",
            JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        ChartUtilities.applyCurrentTheme(chart);
        
        return chart;
    }
    
    public synchronized void update() {
        double[] values = SERIAL_COM.returnAtrVentSignals();
        atrial.add(new Millisecond(), values[0]);
        ventricular.add(new Millisecond(), values[1]);
    }
    
    public void pause() {
        PAUSED = true;
    }
    
    public void resume() {
        PAUSED = false;
    }
    
    public void start() {
        new Thread(()->{
            try {
                while(SERIAL_COM.isConnected()) {
                    if(!PAUSED) 
                        update();
                    Thread.sleep(SAMPLE_PERIOD);
                }
                destroyInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
    
    public static void destroyInstance() {
        if(soleInstance != null)
            soleInstance.dispose();
        soleInstance = null;
    }
    
}