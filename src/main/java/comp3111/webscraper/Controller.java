/**
 * 
 */
package comp3111.webscraper;


import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.StringWriter;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;


import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.StringWriter;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;


import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.text.ParseException;
/**
 * 
 * @author kevinw
 * @author Haoran Li
 * @author Xinyu Sun
 * @author Haoqi Wang
 *
 * Controller class that manage GUI interaction. Please see document about JavaFX for details.
 * 
 */
public class Controller {

    @FXML 
    private Label labelCount; 

    @FXML 
    private Label labelPrice; 

    @FXML 
    private Hyperlink labelMin; 

    @FXML 
    private Hyperlink labelLatest; 
    
    /* Xinyu Sun Part */
    @FXML
    private MenuItem LastSearchBotton;
    /* End */
    
    //haoqi***************************************************************************************************
    @FXML
    private BarChart<String, Number> barChartHistogram;
    //*********************************************************************************************************
	
    @FXML
    private TextField textFieldKeyword;
    
    @FXML
    private TextArea textAreaConsole;
    
    /* Xinyu Sun Part */
    private List<Item> LastResult;
    private List<Item> CurrentResult;
    private String LastSearchName;
    private String CurrentSearchName;
    private int SearchTimes = 0;
    /* End */
 // haoran added
    @FXML 
    private TableView table;
    @FXML 
    private TableColumn<Item,String> title;
    @FXML 
    private TableColumn<Item,Number> price;
    @FXML 
    private TableColumn<Item,Hyperlink> url;
    @FXML 
    private TableColumn<Item,String> postDate;
    @FXML 
    private NumberAxis Yaxis;
    @FXML 
    private LineChart<String, Number> lineChart;
    @FXML 
    private CategoryAxis Xaxis;
    @FXML 
    private ComboBox<String> searchRecord;
    
    private String trendCache[] = new String[5];
    private int countCache = 0;
    XYChart.Series<String, Number>[] seriesArray = new XYChart.Series[5];
    //haoran ended
    private WebScraper scraper;
    
    /**
     * Default controller
     */
    public Controller() {
    	scraper = new WebScraper();
    }

    /**
     * Default initializer. It is empty.
     */
    @FXML
    private void initialize() {
    	/* Xinyu SUN Part */
    	LastSearchBotton.setDisable(true);
    	/* End */
    }
    
    /**
     * Called when the search button is pressed.
     */
    @FXML
    private void actionSearch() {
    	/* Xinyu Sun Part */
    	if (CurrentResult != null)
    	{
    		LastResult = CurrentResult;
    		LastSearchName = CurrentSearchName;
    	}
    	/* End */
    	System.out.println("actionSearch: " + textFieldKeyword.getText());
    	List<Item> result = scraper.scrape(textFieldKeyword.getText());

    	/* Xinyu Sun Part */
    	SearchTimes ++;
    	CurrentResult = result;
    	CurrentSearchName = textFieldKeyword.getText();
//    	if (result != null)
//    	{
//        	LastSearchBotton.setDisable(false);
//        	
//    	}
    	LastSearchBotton.setDisable(false);
    	/* End */
    	
    	
    	String output = "";
    	
    	for (Item item : result) {
    		output += item.getTitle() + "\t" + item.getPrice() + "\t" + item.getUrl() + "\n";
    	}
    	textAreaConsole.setText(output);
    	
    	//haoqi****************************************************************************************************
    	Basic1(result);
    	Advance1(result, textFieldKeyword.getText());
    	//*********************************************************************************************************
    	tableFill( result);			//****haoran added
    	trendFill(result, textFieldKeyword.getText());
    
    }
    

    
    
    //haoqi****************************************************************************************************
    //encapsulation function
    /**
     * This is a UI implementation function for Basic Task 1. 
     * <p>It sets the value of labelCount, labelPrice, labelMin and labelLatest.</p>
     * <p>It sets the URI events on labelMin and labelLatest.</p>
     * <p>The test case is <b>excluded</b>.</p>
     * <p>Its logic implementation functions are <b>summeryFill()</b>, <b>summeryClickMin()</b> and <b>summeryClickLatest()</b>.</p>
     * <p><b>Returns:</b></p>
     * <p>void since it is a UI implementation function, it has no return variable.</p>
     * @param result a list of all items scrapped by the WebScrapper.
     * @exception IllegalArgumentException Price of some items are negative may result in undefined behavior <i>in logic function summeryFill(), summeryClickMin() and summeryClickLatest()</i>.
     * @exception NullPointerException the length of item.getTime() being not equal to 7 will result in undefined behavior <i>in logic function summeryFill(), summeryClickMin() and summeryClickLatest()</i>.
     * @author WANG Haoqi 20412986 hwangby@connect.ust.hk silicon00
     */
    @FXML
    public void Basic1(List<Item> result) {
    	//Basic 1
    	String[] res0 = summeryFill(result);
    	labelCount.setText(res0[0]);
    	labelPrice.setText(res0[1]);
    	labelMin.setText(res0[2]);
    	labelLatest.setText(res0[3]);
    	labelMin.setOnAction(e -> {
    		summeryClickMin(result);
    	});
    	labelLatest.setOnAction(e -> {
    		summeryClickLatest(result);
    	});
    }
    
    /**
     * This is a UI implementation function for Advance Task 1. 
     * <p>It sets the values of barChart.</p>
     * <p>It sets the color of the double clicked bars to be blue. Initialize and restore the rest of bars to be orange.</p>
     * <p>The test case is <b>excluded</b>.</p>
     * <p>Its logic implementation functions are <b>barChartFill()</b> and <b>barChartClick()</b>.</p>
     * <p><b>Returns:</b></p>
     * <p>void since it is a UI implementation function, it has no return variable.</p>
     * @param result a List of all items scrapped by the WebScrapper.
     * @param searchName a String of the search keyword, to display on the legend.
     * @exception IllegalArgumentException Price of some items are negative may result in undefined behavior <i>in logic function barChartFill() and barChartClick()</i>.
     * @exception NullPointerException the length of item.getTime() being not equal to 7 will result in undefined behavior <i>in logic function barChartFill() and barChartClick()</i>.
     * @author WANG Haoqi 20412986 hwangby@connect.ust.hk silicon00
     */
    @FXML
    public void Advance1(List<Item> result, String searchName) {
    	//Advance 1
    	//set value of barChart.
    	barChartHistogram.getData().setAll(barChartFill(result, searchName));
    	//color settings.
    	for (Series<?,?> serie: barChartHistogram.getData()){
			for (XYChart.Data<?, ?> i: serie.getData()){
				i.getNode().setStyle("-fx-bar-fill: #ff5700;");
				i.getNode().setOnMouseClicked((MouseEvent event) -> {
					if (event.getClickCount() == 2) {
						i.getNode().setStyle("-fx-bar-fill: navy;");
						textAreaConsole.setText(barChartClick(result, i));
						for (XYChart.Data<?, ?> j: serie.getData()){
							if(j.getNode() != null && j.getNode()!=i.getNode())
								j.getNode().setStyle("-fx-bar-fill: #ff5700;");
						}
					}
				});
			}
		}
    }
    //*********************************************************************************************************
    //haoqi****************************************************************************************************
    //tested functions Basic1
    /**
     * This is a logic implementation function for Basic Task 1. 
     * <p>During each search, it calculates the values of labelCount, labelPrice, labelMin, labelLatest, lowestPriceURI, latestPostURI, highestPrice.</p>
     * <p>If the result is not found, the Average selling price, Lowest selling price and Latest post will be displayed as "-".</p>
     * <p>If the items scrapped of price 0, these items are not included in the calculation of Average selling price and Lowest selling price.</p>
     * <p>If all the item prices are 0, the Average selling price and Lowest selling price will be "-".</p>
     * <p>The test case is <b>testSummeryFill()</b>.</p>
     * @param result a List of all items scrapped by the WebScrapper.
     * @return a array of string containing the value of labelCount, labelPrice, labelMin, labelLatest, lowestPriceURI, latestPostURI, highestPrice.
     * @exception IllegalArgumentException Price of some items are negative may result in undefined behavior.
     * @exception NullPointerException the length of item.getTime() being not equal to 7 will result in undefined behavior.
     * @author WANG Haoqi 20412986 hwangby@connect.ust.hk silicon00
     */
    @FXML
    public String[] summeryFill(List<Item> result) {
    	double totalPrice = 0.0;
    	int count = 0;
    	double lowestPrice = Double.MAX_VALUE;
    	double highestPrice = 0.0;
    	String[] latestPost = null;
    	String lowestURL = "";
    	String latestP = "";
    	String labelCounttxt="";
    	String labelPricetxt="";
    	String labelMintxt="";
    	String labelLatesttxt="";
    	String highestPricetxt="";
    	for (Item item : result) {
    		if(item.getPrice()<0) {
    			throw new IllegalArgumentException();
    		}
    		if(item.getTime().length != 7) {
    			throw new NullPointerException();
    		}
    		totalPrice += item.getPrice();
    		if (item.getPrice() != 0 ) {
    			count++;
    			if (item.getPrice()<lowestPrice) {
    			lowestPrice = item.getPrice();
    			lowestURL = item.getUrl();
    			}
    		}
    		if (item.getPrice()>highestPrice) {
    			highestPrice = item.getPrice();
    		}
    		if (latestPost == null) {
    			latestPost = item.getTime();
    		}
    		if (item.getTime()[0].compareTo(latestPost[0])>0) {
    			latestPost = item.getTime();
				latestP = item.getUrl();
    		}
    		else if (item.getTime()[0].compareTo(latestPost[0])==0 && item.getTime()[1].compareTo(latestPost[1])>0) {
    			latestPost = item.getTime();
				latestP = item.getUrl();
    		}
    		else if (item.getTime()[0].compareTo(latestPost[0])==0 && item.getTime()[1].compareTo(latestPost[1])==0 && item.getTime()[5].compareTo(latestPost[5])>=0) {
    			latestPost = item.getTime();
    			latestP = item.getUrl();
    		}
    	}
    	labelCounttxt = ""+result.size();
    	if (result.isEmpty() || totalPrice==0)
    		labelPricetxt = "-";
    	else
    		labelPricetxt = ""+totalPrice/count;
    	if (result.isEmpty() || lowestPrice==Double.MAX_VALUE)
    		labelMintxt = "-";
    	else
    		labelMintxt = ""+lowestPrice;
    	if (result.isEmpty())
    		labelLatesttxt = "-";
    	else
    		labelLatesttxt = latestPost[0]+" "+latestPost[5]+" "+latestPost[6];
    	if (highestPrice!=0) {
    		highestPricetxt = ""+highestPrice;
    	}
    	String[] res = {labelCounttxt, labelPricetxt, labelMintxt, labelLatesttxt, lowestURL, latestP, highestPricetxt};
    	return res;
    }
    
    /**
     * This is a logic implementation function for Basic Task 1. 
     * <p>During each search, a clickable URL link will be displayed in the field Lowest Selling Price.</p>
     * <p>While the user decided to click the displayed URL link, the corresponding windows/browser showing that item will pop up.</p>
     * <p>If the result is not found or all the item prices are 0, the URL of Lowest selling price do not pop up windows, since price 0 items are not included in the calculation of Lowest selling price.</p>
     * <p>If there exists multiple items that are of the lowest price, only one of URL of the lowest price items will be displayed.</p>
     * <p>The test case is <b>testSummeryClickMin()</b>.</p>
     * @param result a List of all items scrapped by the WebScrapper.
     * @return The URL string of the lowest priced item.
     * @exception IllegalArgumentException Price of some items are negative may result in undefined behavior.
     * @exception NullPointerException the length of item.getTime() being not equal to 7 will result in undefined behavior.
     * @author WANG Haoqi 20412986 hwangby@connect.ust.hk silicon00
     */
    public String summeryClickMin(List<Item> result) {
    	String[] res0 = summeryFill(result);
    	if (res0[4] != "") {
			if(Desktop.isDesktopSupported())
			{
				try {
					Desktop.getDesktop().browse(new URI(res0[4]));
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		}
    	return res0[4];
    }
    
    /**
     * This is a logic implementation function for Basic Task 1. 
     * <p>During each search, a clickable URL link will be displayed in the field Latest post.</p>
     * <p>While the user decided to click the displayed URL link, the corresponding windows/browser showing that item will pop up.</p>
     * <p>If the result is not found, the URL of Latest post do not pop up windows.</p>
     * <p>If there exists multiple items that are the latest post, only one of URL of the latest post items will be displayed.</p>
     * <p>The test case is <b>testSummeryClickLatest()</b>.</p>
     * @param result a List of all items scrapped by the WebScrapper.
     * @return The URL string of the lowest priced item.
     * @exception IllegalArgumentException Price of some items are negative may result in undefined behavior.
     * @exception NullPointerException the length of item.getTime() being not equal to 7 will result in undefined behavior.
     * @author WANG Haoqi 20412986 hwangby@connect.ust.hk silicon00
     */
    public String summeryClickLatest(List<Item> result) {
    	String[] res0 = summeryFill(result);
    	if (res0[5] != "") {
			if(Desktop.isDesktopSupported())
			{
				try {
					Desktop.getDesktop().browse(new URI(res0[5]));
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			} 
		}
    	return res0[5];
    }
    //*********************************************************************************************************
    //haoqi****************************************************************************************************
    //tested functions Advance1
    /**
     * This is a logic implementation function for Advance Task 1. 
     * <p>During each search, the contents in the distribution tab will be refreshed and displayed according to the scrapped data of the search item.</p>
     * <p>The barChart is divided into 10 equal width bins. The range of the bins is determined by the price range of the search product. The first bin contains the lowest price while the last bin contains highest price.</p>
     * <p>The barChart has proper X-axis label, Y-axis label, legend which says The selling prize of SEARCH KEYWORD.</p>
     * <p>If there is only one non-zero price of the histogram, there is one bar in the chart.</p>
     * <p>If the result is not found, the barChart will be empty. There is no legend.</p>
     * <p>If the items scrapped of price 0, these items are not included in the bars.</p>
     * <p>If all the item prices are 0, the barChart will be empty. But there is still the legend showing the selling price of SEARCH KEYWORD</p>
     * <p>The test case is <b>testBarChartFill()</b>.</p>
     * @param result a List of all items scrapped by the WebScrapper.
     * @param SearchName a String of the search keyword, to display on the legend.
     * @return a barChart whose x-axis is the string of the highest price range of each bar, and y-axis is the number of items in that bar range i.e. [X axis value - barwidth, X axis value) for bar 0-8 and [X axis value - barwidth, X axis value] for bar 9.
     * If there is only one non-zero price of the histogram, there is one bar in the chart.
     * @exception IllegalArgumentException Price of some items are negative may result in undefined behavior.
     * @exception NullPointerException the length of item.getTime() being not equal to 7 will result in undefined behavior.
     * @author WANG Haoqi 20412986 hwangby@connect.ust.hk silicon00
     */
    //@SuppressWarnings("unchecked")
	@FXML
    public XYChart.Series<String, Number> barChartFill(List<Item> result, String SearchName) {
    	String[] res0 = summeryFill(result);
    	double barWidth = 0.0;
    	int numberOfBars = 10;
    	XYChart.Series<String, Number> myChart = new XYChart.Series<String, Number>();
    	//barChart
    	//myChart.setName("The selling price of "+textFieldKeyword.getText());
    	myChart.setName("The selling price of "+SearchName);
    	int[] barHeight = {0,0,0,0,0,0,0,0,0,0};
    	int category = 0;
    	if (res0[6] == "") {
    		//barChartHistogram.getData().clear();
    		myChart.getData().add(new XYChart.Data<String, Number>("", 0));
    	}
    	else {
    		double lowestPrice = Double.parseDouble(res0[2]);
        	double highestPrice = Double.parseDouble(res0[6]);
        	barWidth = (highestPrice-lowestPrice)/numberOfBars;
    		for (Item item : result) {
    			category = (int) ((item.getPrice()-lowestPrice)/barWidth);
    			if (item.getPrice()-lowestPrice < 0) {
    				continue;
    			}
    			else if (category != numberOfBars) {
    				++barHeight[category];
    			}
    			else {
    				++barHeight[numberOfBars-1];
    			}
    		}
    		myChart.getData().add(new XYChart.Data<String, Number>(""+(lowestPrice+barWidth), barHeight[0]));
    		if (barHeight[1]+barHeight[2]+barHeight[3]+barHeight[4]+barHeight[5]+barHeight[6]+barHeight[7]+barHeight[8]+barHeight[9]>0) {
    			myChart.getData().add(new XYChart.Data<String, Number>(""+(lowestPrice+barWidth*2), barHeight[1]));
        		myChart.getData().add(new XYChart.Data<String, Number>(""+(lowestPrice+barWidth*3), barHeight[2]));
        		myChart.getData().add(new XYChart.Data<String, Number>(""+(lowestPrice+barWidth*4), barHeight[3]));
        		myChart.getData().add(new XYChart.Data<String, Number>(""+(lowestPrice+barWidth*5), barHeight[4]));
        		myChart.getData().add(new XYChart.Data<String, Number>(""+(lowestPrice+barWidth*6), barHeight[5]));
        		myChart.getData().add(new XYChart.Data<String, Number>(""+(lowestPrice+barWidth*7), barHeight[6]));
        		myChart.getData().add(new XYChart.Data<String, Number>(""+(lowestPrice+barWidth*8), barHeight[7]));
        		myChart.getData().add(new XYChart.Data<String, Number>(""+(lowestPrice+barWidth*9), barHeight[8]));
        		myChart.getData().add(new XYChart.Data<String, Number>(""+(lowestPrice+barWidth*10), barHeight[9]));
    		}
    		//barChartHistogram.getData().setAll(myChart);
        }
    	return myChart;
    }
	/**
     * This is a logic implementation function for Advance Task 1. 
     * <p>During each search, while the user decided to double-click a bar displayed in the barChart, the console screen is cleared and displays those items with the price falling in the double-clicked bar (in the default format).</p>
     * <p>If the result is not found or if all the item prices are 0, there are no double-clickable bars.</p>
     * <p>The test case is <b>testBarChartClick()</b>.</p>
     * @param result a List of all items scrapped by the WebScrapper.
     * @param i a double clicked bar in the barChart.
     * @return a string of informations of those items with the price falling in the double-clicked bar (in the default format) to display in the console.
     * @exception IllegalArgumentException Price of some items are negative may result in undefined behavior.
     * @exception NullPointerException the length of item.getTime() being not equal to 7 will result in undefined behavior.
     * @author WANG Haoqi 20412986 hwangby@connect.ust.hk silicon00
     */
    public String barChartClick(List<Item> result, XYChart.Data<?, ?> i) {
    	String[] res0 = summeryFill(result);
    	List<Item> displayItem = new ArrayList<Item>();
    	if (res0[6] != "") {
    		double tempLowestPrice = Double.parseDouble(res0[2]);
    		double temphighestPrice = Double.parseDouble(res0[6]);
    		int numberOfBars = 10;
    		double tempBarWidth = (temphighestPrice-tempLowestPrice)/numberOfBars;
    		for (Item it : result) {
    			if (it.getPrice() != 0.0) {
    				if (it.getPrice()<Double.parseDouble(""+i.getXValue())) {
    					if (Double.parseDouble(""+i.getXValue())==(int)(tempLowestPrice+tempBarWidth)) {
    						displayItem.add(it);
    					}
    					else if (it.getPrice()>=Double.parseDouble(""+i.getXValue())-tempBarWidth)
    						displayItem.add(it);
    				}
    				else if (it.getPrice()==Double.parseDouble(""+i.getXValue()) && (Double.parseDouble(""+i.getXValue())==(int)(tempLowestPrice+tempBarWidth*10))) {
    					displayItem.add(it);
    				}
    			}
    		}
    	}
		String consoleOut = "";
		for (Item itr:displayItem) {
			consoleOut += itr.getTitle() + "\t" + itr.getPrice() + "\t" + itr.getUrl() + "\n";
		}
    	return consoleOut;
    }
    //*********************************************************************************************************
    /*
     * haoran's part begins -.-
     */
    /**
     * This is implementation function for Basic Task 4 which consists most UI files. 
     * <p>During each search, it outputs title, price, URL, post date in corresponding position in a table format</p>
     * <p>Sort the result in ascending order on user clicking each column, and sort in descending order when user click again.</p>
     * <p>All cells in the table not editable. </p>
     * <p>Pop up a new windows/browser showing the item when the URL is clicked.</p>
     * <p>Refresh the table on another search.</p>
     * <p>The test case is <b>testTableFill()</b>.</p>
     * @param result a List of all items scrapped by the WebScrapper.
     * @return mydata, it is a ObservableList Item for test purpose
     * @exception IllegalArgumentException empty url in string may result in undefined behavior.
     * @exception NullPointerException the length of item.getTime() being not equal to 7 will result in undefined behavior.
     * @author Li Haoran 20413540 hlibt@connect.ust.hk teapotliid
     */
    @FXML
    public ObservableList<Item> tableFill(List<Item> result) {
    	table.getItems().clear();
    	 ObservableList<Item> mydata =  FXCollections.observableArrayList();
    	 
    	 for (Item item : result) {
    		 if(item.getTime().length != 7) {
     			throw new NullPointerException();
     		}
    		 if(item.getUrl() == "") {
     			throw new IllegalArgumentException();
     		}
    		 item.setHyperlink();
    		 mydata.add(item);
    	 }
    	 
    	 if(!mydata.isEmpty()) {
    		 title.setCellValueFactory(
    				 new PropertyValueFactory<Item,String>("title")
    				 );
    		 price.setCellValueFactory(
    				 new PropertyValueFactory<Item,Number>("price")
    				 );
    		 //url.setCellValueFactory(
    		 //	    new PropertyValueFactory<Item,String>("url")
    		 //	);
    		 url.setCellValueFactory(new PropertyValueFactory<>("hyperlink"));
    		 url.setCellFactory(new HyperlinkCell());
    		 postDate.setCellValueFactory(
    				 new PropertyValueFactory<Item,String>("timeString")
    				 );
    	 table.setItems(mydata);
    	 }
    	 return mydata;
    }
    /**
     * This is logic implementation function for Basic Task 4 for test purpose. 
     * <p>my data contains item which has title, price, URL, post date in corresponding position in a table format</p>
     * <p>The test case is <b>testTableFill()</b>.</p>
     * @param result a List of all items scrapped by the WebScrapper.
     * @return mydata, it is a ObservableList of Item for test purpose
     * @exception IllegalArgumentException empty url in string may result in undefined behavior.
     * @exception NullPointerException the length of item.getTime() being not equal to 7 will result in undefined behavior.
     * @author Li Haoran 20413540 hlibt@connect.ust.hk teapotliid
     */
    public ObservableList<Item> tableFillLogic(List<Item> result) {
    	ObservableList<Item> mydata =  FXCollections.observableArrayList();
   	 for (Item item : result) {
   		 if(item.getTime().length != 7) {
    			throw new NullPointerException();
    		}
   		 if(item.getUrl() == "") {
    			throw new IllegalArgumentException();
    		}
   		 //item.setHyperlink();
   		 mydata.add(item);
   		 
   	 }
   	 return mydata;
    }
    //(-.-)divide line
    /**
     * This is implementation function for Advance Task 3 which contains most UI parts. 
     * <p>During each search, We output a line chart that contains 7 points. Each point denotes the average price of the products of the searched keyword those are posted on that day.When no data is available on a day the chart will draw one less point.</p>
     * <p>the Search Record combo box displays the keyword of the last five searched keywords.On select any of the search record and update the histogram instantly.</p>
     * <p>When double-click on a particular point of the chart, The console screen will be cleared and display those items posted on that day. The point being double-click will change its color and the rest of the points should restore its color to default. </p>
     * <p>The test case is <b>testTrendFill()</b>.</p>
     * <p>This is a void function, so please don't expect a return in javadoc.</p>
     * @param result a List of all items scrapped by the WebScrapper.
     * @param SearchName a String of the search keyword, to display on the legend.
     * @exception IllegalArgumentException empty url in string may result in undefined behavior.
     * @exception NullPointerException the length of item.getTime() being not equal to 7 will result in undefined behavior since our parser can't read it.
     * @author Li Haoran 20413540 hlibt@connect.ust.hk teapotliid
     */
    @FXML
    public void trendFill(List<Item> result, String SearchName) {
    	//lineChart.Series[0].Points.Clear();
    	for (Item item : result) {
   		 if(item.getTimeString() == "") {
    			throw new NullPointerException();
    		}
   		 if(item.getUrl() == "") {
    			throw new IllegalArgumentException();
    		}
    	}
    	lineChart.setAnimated(false);
    	//lineChart.setTitle("The average selling prize of the "+search);
    	//lineChart =new LineChart<String,Number>(Xaxis,Yaxis);
    	//defining a series
        XYChart.Series<String,Number> series = new XYChart.Series<String,Number>();
        series.setName("The selling price of "+SearchName);
        String output[] = new String[7];
        for(int i=0;i<7;i++) {
        	output[i] = "";
        }
        lineChart.getData().clear();
        DateFormat myDateFormat = new SimpleDateFormat("yyyy/MM/dd");
      //get current date time with Calendar()
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        //TODAY IS System.out.println(myDateFormat.format(cal.getTime()));
    	//String today = myDateFormat.format(cal.getTime());
        //cal.setTime(dateInstance);
        // get x-axis for date
        //cal.add(Calendar.DATE, -1);
        Date x[] = new Date[7];
        for(int i=6;i>=0;i--) {
        	cal.add(Calendar.DATE, -1);
        	x[i] = cal.getTime();
        }
        //Date x7 = cal.getTime();

        //cal.add(Calendar.DATE, -1);
        //Date x1 = cal.getTime();

        System.out.println("x6 is:"+x[5]);
        //arrange Xaxis
        DateFormat dateToString = new SimpleDateFormat("MM/dd/yyyy");
        Xaxis.setCategories(FXCollections.<String>observableArrayList
        		   (dateToString.format(x[0]), dateToString.format(x[1]),dateToString.format(x[2]),dateToString.format(x[3]),
        				   dateToString.format(x[4]),dateToString.format(x[5]),dateToString.format(x[6])));  
        //end
        double a[] = new double[7];
        for(int i=0;i<a.length;i++)
            a[i] = 0.0;
        int count[] = new int[7];
        for(int i=0;i<count.length;i++)
            count[i] = 0;
        String tempstr = "";
        for(Item item : result) {
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            //String dateInString = "7-Jun-2013";
        	
        	//tempstr = item.getTimeString().substring(0, Math.min(item.getTimeString().length(), 10));
        	//System.out.println(tempstr);
        	
            try {

                Date tempDate = formatter.parse(item.getTimeString());
                //System.out.println(tempDate);
                for(int i=0;i<7;i++) {
                	if(tempDate.compareTo(x[i]) == 0) {
                		//System.out.println("x7 called");
                		a[i] = a[i] + item.getPrice();
                		count[i] += 1;
                		output[i] += item.getTitle() + "\t" + item.getPrice() + "\t" + item.getUrl() + "\n";
                	}
                }
               
            } catch (ParseException e) {
                e.printStackTrace();
            }
            
        }
           // if()
        //DateFormat dateToString = new SimpleDateFormat("MM/dd/yyyy");
        //String standardString[] = new String[7];
        for(int i=0;i<7;i++) {
          	if(count[i] != 0) {
           		a[i] = a[i]/count[i];
           		//standardString[i] = 
           		//System.out.println(a[i]);
           	 //series.getData().add(new XYChart.Data("Jan", 23));
           	}
        }
        lineChart.getData().setAll(series);
        XYChart.Data<String,Number> data0 = new XYChart.Data<String,Number>(dateToString.format(x[0]), a[0]);
        XYChart.Data<String,Number> data1 = new XYChart.Data<String,Number>(dateToString.format(x[1]), a[1]);
        XYChart.Data<String,Number> data2 = new XYChart.Data<String,Number>(dateToString.format(x[2]), a[2]);
        XYChart.Data<String,Number> data3 = new XYChart.Data<String,Number>(dateToString.format(x[3]), a[3]);
        XYChart.Data<String,Number> data4 = new XYChart.Data<String,Number>(dateToString.format(x[4]), a[4]);
        XYChart.Data<String,Number> data5 = new XYChart.Data<String,Number>(dateToString.format(x[5]), a[5]);
        XYChart.Data<String,Number> data6 = new XYChart.Data<String,Number>(dateToString.format(x[6]), a[6]);
        //mystyle = data6.getNode().getStyle();
        //System.out.println("mystyle is: "+mystyle);
        if(count[0] != 0) {
        	series.getData().add(data0);
        }
        if(count[1] != 0) {
        	series.getData().add(data1);
        }
        if(count[2] != 0) {
        	series.getData().add(data2);

        }
        if(count[3] != 0) {
        	series.getData().add(data3);

        }
        if(count[4] != 0) {
        	series.getData().add(data4);
        }
        if(count[5] != 0) {
        	series.getData().add(data5);

        }
        if(count[6] != 0) {
        	series.getData().add(data6);
        }
        //lineChart.getData().setAll(series);
        if(count[0] != 0) {
        	//series.getData().add(data0);
        	data0.getNode().setOnMouseClicked((MouseEvent mouseEvent) -> {
        		if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount() == 2){
                        //System.out.println("Double clicked");
                    	textAreaConsole.setText(output[0]);
                    	if(count[0] != 0)
                    		data0.getNode().setStyle("-fx-background-color: #fff7ad;");
                    	//
                    	if(count[1] != 0)data1.getNode().setStyle("");
                    	if(count[2] != 0)data2.getNode().setStyle("");
                    	if(count[3] != 0)data3.getNode().setStyle("");
                    	if(count[4] != 0)data4.getNode().setStyle("");
                    	if(count[5] != 0)data5.getNode().setStyle("");
                    	if(count[6] != 0)data6.getNode().setStyle("");
                    }
                }
        	});
        	//series.getData().add(data0);
        }
        if(count[1] != 0) {
        	//series.getData().add(data1);
        	data1.getNode().setOnMouseClicked((MouseEvent mouseEvent) -> {
        		if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount() == 2){
                        //System.out.println("Double clicked");
                    	textAreaConsole.setText(output[1]);
                    	data1.getNode().setStyle("-fx-background-color: #fff7ad;");
                    	//
                     	if(count[0] != 0)data0.getNode().setStyle("");
                    	if(count[2] != 0)data2.getNode().setStyle("");
                    	if(count[3] != 0)data3.getNode().setStyle("");
                    	if(count[4] != 0)data4.getNode().setStyle("");
                    	if(count[5] != 0)data5.getNode().setStyle("");
                    	if(count[6] != 0)data6.getNode().setStyle("");
                    }
                }
        	});
        }
        if(count[2] != 0) {
        	//series.getData().add(data2);
        	data2.getNode().setOnMouseClicked((MouseEvent mouseEvent) -> {
        		if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount() == 2){
                        //System.out.println("Double clicked");
                    	textAreaConsole.setText(output[2]);
                    	data2.getNode().setStyle("-fx-background-color: #fff7ad;");
                    	//
                    	if(count[1] != 0)data1.getNode().setStyle("");
                    	if(count[0] != 0)data0.getNode().setStyle("");
                    	if(count[3] != 0)data3.getNode().setStyle("");
                    	if(count[4] != 0)data4.getNode().setStyle("");
                    	if(count[5] != 0)data5.getNode().setStyle("");
                    	if(count[6] != 0)data6.getNode().setStyle("");
                    }
                }
        	});
        }
        if(count[3] != 0) {
        	//series.getData().add(data3);
        	data3.getNode().setOnMouseClicked((MouseEvent mouseEvent) -> {
        		if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount() == 2){
                        //System.out.println("Double clicked");
                    	textAreaConsole.setText(output[3]);
                    	data3.getNode().setStyle("-fx-background-color: #fff7ad;");
                    	//
                    	if(count[1] != 0)data1.getNode().setStyle("");
                    	if(count[2] != 0)data2.getNode().setStyle("");
                    	if(count[0] != 0)data0.getNode().setStyle("");
                    	if(count[4] != 0)data4.getNode().setStyle("");
                    	if(count[5] != 0)data5.getNode().setStyle("");
                    	if(count[6] != 0)data6.getNode().setStyle("");
                    }
                }
        	});
        }
        if(count[4] != 0) {
        	//series.getData().add(data4);
        	data4.getNode().setOnMouseClicked((MouseEvent mouseEvent) -> {
        		if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount() == 2){
                        //System.out.println("Double clicked");
                    	textAreaConsole.setText(output[4]);
                    	data4.getNode().setStyle("-fx-background-color: #fff7ad;");
                    	//
                    	if(count[1] != 0)data1.getNode().setStyle("");
                    	if(count[2] != 0)data2.getNode().setStyle("");
                    	if(count[3] != 0)data3.getNode().setStyle("");
                    	if(count[0] != 0)data0.getNode().setStyle("");
                    	if(count[5] != 0)data5.getNode().setStyle("");
                    	if(count[6] != 0)data6.getNode().setStyle("");
                    }
                }
        	});
        }
        if(count[5] != 0) {
        	//series.getData().add(data5);
        	data5.getNode().setOnMouseClicked((MouseEvent mouseEvent) -> {
        		if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount() == 2){
                        //System.out.println("Double clicked");
                    	textAreaConsole.setText(output[5]);
                    	data5.getNode().setStyle("-fx-background-color: #fff7ad;");
                    	//
                    	if(count[1] != 0)data1.getNode().setStyle("");
                    	if(count[2] != 0)data2.getNode().setStyle("");
                    	if(count[3] != 0)data3.getNode().setStyle("");
                    	if(count[4] != 0)data4.getNode().setStyle("");
                    	if(count[0] != 0)data0.getNode().setStyle("");
                    	if(count[6] != 0)data6.getNode().setStyle("");
                    }
                }
        	});
        }
        if(count[6] != 0) {
        	//series.getData().add(data6);
        	data6.getNode().setOnMouseClicked((MouseEvent mouseEvent) -> {
        		if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount() == 2){
                        //System.out.println("Double clicked");
                    	textAreaConsole.setText(output[6]);
                    	data6.getNode().setStyle("-fx-background-color: #fff7ad;");
                    	//
                    	if(count[1] != 0)data1.getNode().setStyle("");
                    	if(count[2] != 0)data2.getNode().setStyle("");
                    	if(count[3] != 0)data3.getNode().setStyle("");
                    	if(count[4] != 0)data4.getNode().setStyle("");
                    	if(count[5] != 0)data5.getNode().setStyle("");
                    	if(count[0] != 0)data0.getNode().setStyle("");
                    }
                }
        	});
        }
        int is_duplciate = 0;
        /*
        for(int i=0;i<countCache;i++) {
        	if(trendCache[i] == textFieldKeyword.getText()) {
        		is_duplciate = 1;
        		seriesArray[i] = series;
        		break;
        		
        	}
        }
        */
        if(countCache < 5 && is_duplciate == 0) {
    		trendCache[countCache] = textFieldKeyword.getText();
    		seriesArray[countCache] = series;
    		countCache += 1;
    	}
        else {
        	if(countCache == 5 && is_duplciate == 0) {
        		for(int i=0;i<=3;i++) {
        			trendCache[i] = trendCache[i+1];
        			seriesArray[i] = seriesArray[i+1];
        		}
        		trendCache[4] = textFieldKeyword.getText();
        		seriesArray[4] = series;
        	}
        }
    	//combo boxes	private ComboBox<String> searchRecord
    	ObservableList<String> options = 
    		    FXCollections.observableArrayList();
    	for(int i=0;i<countCache;i++) {
    		options.add(trendCache[i]);
    	}
    	searchRecord.getItems().setAll(options);
    	searchRecord.setOnAction((e) -> {
    		String selectedString = searchRecord.getSelectionModel().getSelectedItem();
    		System.out.println("ComboBox Action (selected: " + selectedString + ")");
    		String myTemp = null;
    		if(selectedString==myTemp) {
    			//lineChart.getData().clear();
				//lineChart.getData().setAll(series);
    		}
    		else {
    			for(int i=0;i<countCache;i++) {
    			
    				if(trendCache[i] == selectedString) {
    					lineChart.getData().clear();
    					lineChart.getData().setAll(seriesArray[i]);
    					break;
    				}
    			}
    		}
    	});
        
    }
    /**
     * This is a logic implementation function for Advance Task 3. 
     * <p>This is used for logic test case of advance task3 </p>
     * <p>We only care about X,Y point of series</p>
     * <p>The test case is <b>testTrendFill()</b>.</p>
     * @param result a List of all items scrapped by the WebScrapper.
     * @param SearchName a String of the search keyword, to display on the legend.
     * @return series XYChart.Series that has argument String,Number 
     * @exception IllegalArgumentException empty url in string may result in undefined behavior.
     * @exception NullPointerException empty time string could result in undefined behavior.
     * @author Li Haoran 20413540 hlibt@connect.ust.hk teapotliid
     */
    public XYChart.Series<String,Number> trendFillLogic(List<Item> result, String SearchName) {
    	//lineChart.Series[0].Points.Clear();
    	for (Item item : result) {
   		 if(item.getTimeString() == "") {
    			throw new NullPointerException();
    		}
   		 if(item.getUrl() == "") {
    			throw new IllegalArgumentException();
    		}
    	}
    	XYChart.Series<String,Number> series = new XYChart.Series<String,Number>();
        series.setName("The selling price of "+SearchName);
        String output[] = new String[7];
        for(int i=0;i<7;i++) {
        	output[i] = "";
        }
        DateFormat myDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        //get current date time with Calendar()
          Calendar cal = Calendar.getInstance();
          cal.set(Calendar.HOUR_OF_DAY, 0);
          cal.set(Calendar.MINUTE, 0);
          cal.set(Calendar.SECOND, 0);
          cal.set(Calendar.MILLISECOND, 0);
          Date x[] = new Date[7];
          for(int i=6;i>=0;i--) {
          	cal.add(Calendar.DATE, -1);
          	x[i] = cal.getTime();
          }
          DateFormat dateToString = new SimpleDateFormat("MM/dd/yyyy");
          //Xaxis.setCategories(FXCollections.<String>observableArrayList
          		   //(dateToString.format(x[0]), dateToString.format(x[1]),dateToString.format(x[2]),dateToString.format(x[3]),
          			//	   dateToString.format(x[4]),dateToString.format(x[5]),dateToString.format(x[6])));  
          //end
          double a[] = new double[7];
          for(int i=0;i<a.length;i++)
              a[i] = 0.0;
          int count[] = new int[7];
          for(int i=0;i<count.length;i++)
              count[i] = 0;
          String tempstr = "";
          trendFillLogicHelper(result,a,x,output,count);
          /*
          for(Item item : result) {
          	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
              //String dateInString = "7-Jun-2013";
          	
          	//tempstr = item.getTimeString().substring(0, Math.min(item.getTimeString().length(), 10));
          	//System.out.println(tempstr);
          	
              try {

                  Date tempDate = formatter.parse(item.getTimeString());
                  //System.out.println(tempDate);
                  for(int i=0;i<7;i++) {
                  	if(tempDate.compareTo(x[i]) == 0) {
                  		//System.out.println("x7 called");
                  		a[i] = a[i] + item.getPrice();
                  		count[i] += 1;
                  		output[i] += item.getTitle() + "\t" + item.getPrice() + "\t" + item.getUrl() + "\n";
                  	}
                  }
                 
              } catch (ParseException e) {
                  e.printStackTrace();
              }
              
          }
          */
          
             // if()
          //DateFormat dateToString = new SimpleDateFormat("MM/dd/yyyy");
          //String standardString[] = new String[7];
          for(int i=0;i<7;i++) {
            	if(count[i] != 0) {
             		a[i] = a[i]/count[i];
             		//standardString[i] = 
             		//System.out.println(a[i]);
             	 //series.getData().add(new XYChart.Data("Jan", 23));
             	}
          }
          //lineChart.getData().setAll(series);
          XYChart.Data<String,Number> data0 = new XYChart.Data<String,Number>(dateToString.format(x[0]), a[0]);
          XYChart.Data<String,Number> data1 = new XYChart.Data<String,Number>(dateToString.format(x[1]), a[1]);
          XYChart.Data<String,Number> data2 = new XYChart.Data<String,Number>(dateToString.format(x[2]), a[2]);
          XYChart.Data<String,Number> data3 = new XYChart.Data<String,Number>(dateToString.format(x[3]), a[3]);
          XYChart.Data<String,Number> data4 = new XYChart.Data<String,Number>(dateToString.format(x[4]), a[4]);
          XYChart.Data<String,Number> data5 = new XYChart.Data<String,Number>(dateToString.format(x[5]), a[5]);
          XYChart.Data<String,Number> data6 = new XYChart.Data<String,Number>(dateToString.format(x[6]), a[6]);
          //mystyle = data6.getNode().getStyle();
          //System.out.println("mystyle is: "+mystyle);
          if(count[0] != 0) {
          	series.getData().add(data0);
          }
          if(count[1] != 0) {
          	series.getData().add(data1);
          }
          if(count[2] != 0) {
          	series.getData().add(data2);

          }
          if(count[3] != 0) {
          	series.getData().add(data3);

          }
          if(count[4] != 0) {
          	series.getData().add(data4);
          }
          if(count[5] != 0) {
          	series.getData().add(data5);

          }
          if(count[6] != 0) {
          	series.getData().add(data6);
          }
          
        
    	  return series;
    }
    /**
     * This is a logic implementation helper function for Advance Task 3 to achieve 100%. Please ignore this useless function. 
     * <p>We will use try catch to parse the string. </p>
     * <p>We will also update array a, x,output and count</p>
     * <p>This help function is to help test pass 100% junit test.</p>
     * @param result a List of all items scrapped by the WebScrapper.
     * @param a a double array that stores price
     * @param x an array stores dates of previous 7 days
     * @param output a string array to store the text to be outputted to console
     * @param count an array to show how many items show up in a specific day.
     * @author Li Haoran 20413540 hlibt@connect.ust.hk teapotliid
     */
    public void trendFillLogicHelper(List<Item> result,double[] a,Date[] x,String[] output,int[] count) {
    	for(Item item : result) {
          	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
              //String dateInString = "7-Jun-2013";
          	
          	//tempstr = item.getTimeString().substring(0, Math.min(item.getTimeString().length(), 10));
          	//System.out.println(tempstr);
          	
              try {

                  Date tempDate = formatter.parse(item.getTimeString());
                  //System.out.println(tempDate);
                  for(int i=0;i<7;i++) {
                  	if(tempDate.compareTo(x[i]) == 0) {
                  		//System.out.println("x7 called");
                  		a[i] = a[i] + item.getPrice();
                  		count[i] += 1;
                  		output[i] += item.getTitle() + "\t" + item.getPrice() + "\t" + item.getUrl() + "\n";
                  	}
                  }
                 
              } catch (ParseException e) {
                  e.printStackTrace();
              }
              
          }
    }
    /*
     * haoran's part ends -.-
     */
    
    
    
    /* Below is Xinyu Sun's Part Functions*/
    /* Xinyu Sun Summary */
    // Functions: LastSearch() LogicShowResult() closeTabs() QuitFunction() showTeamInformation() 
    // InformationString() LoadResult() LogicLoad() LogicSearchName() SaveResult() LogicSave()

    
	/**
     * This is a UI implementation function for Basic Task 6(iv). 
     * <p>When user presses Last Search Botton, it will return the last search result.</p>
     * <p>This is a simple UI function with simple logic which check the search time.</p>
     * <p>No Test Case for this</p>
     * @author SUN Xinyu 20327715 xsunan@connect.ust.hk DasVergissmeinnicht
     */
    @FXML
    private void LastSearch() {
    	/* Xinyu Sun Part */
    	if (SearchTimes <= 1){
    		Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("No Previous Record");
        	alert.setHeaderText(null);
        	alert.setContentText("This may be your first search and there is no previous record.");
       		alert.showAndWait();}
    	else {
    		LastSearchBotton.setDisable(true);
	    	String output = LogicShowResult(LastResult);
	    	textAreaConsole.setText(output);
	    /* End */
	    	//haoqi UI****************************************************************************************************
	    	Basic1(LastResult);
	    	Advance1(LastResult, LastSearchName);
	    	//*********************************************************************************************************
	    	tableFill(LastResult);			//****haoran UI added
	    	trendFill(LastResult, LastSearchName);
	    }

    }
	/**
     * This is a Logic implementation function for Get the String for MainControl. 
     * <p>When get the result list, it will return a string which contains the title, price and url.</p>
     * <p>This prepare for printing string in textAreaConsole.</p>
     * <p>The test case is <b>testLogicShowResult()</b></p>
     * @param result a List of all items provided by the UI function.
     * @return output a string contains the result information..
     * @author SUN Xinyu 20327715 xsunan@connect.ust.hk DasVergissmeinnicht
     */        
    public String LogicShowResult(List<Item> result)
    {
    	String output = "";
    	for (Item item : result) {
    		output += item.getTitle() + "\t" + item.getPrice() + "\t" + item.getUrl() + "\n";
    	}
    	return output;
    }
    
    // UI Function for close()
	/**
     * This is a UI implementation function for Basic Task 6(iii). 
     * <p>When user presses this botton, it will clear the current search record and initialize all tabs on the right to their initial state.</p>
     * <p>No Test Case for this UI function.</p>
     * @author SUN Xinyu 20327715 xsunan@connect.ust.hk DasVergissmeinnicht
     */
    @FXML
    private void closeTabs() {
    	//UI and Data Clear
    	textAreaConsole.setText(null);
    	labelMin.setText("<Lowest>");
    	labelMin.setOnAction(null);
    	labelLatest.setText("<Latest>");
    	labelLatest.setOnAction(null);
    	labelPrice.setText("<AvgPrice>");
    	labelCount.setText("<total>");
    	barChartHistogram.getData().clear();
    	table.getItems().clear();
    	lineChart.getData().clear();
    	searchRecord.getItems().clear();
    	for (int i=0; i<5; i++) 
    		trendCache[i] = "";
        countCache = 0;
        seriesArray = new XYChart.Series[5];
		LastSearchBotton.setDisable(true);
    }
    
    // UI Function for QuitFunction()
	/**
     * This is a UI implementation function for Basic Task 6(ii). 
     * <p>When user presses this botton, all connections and the whole program will quit.</p>
     * <p>In our team, we only have the original scraper.</p>
     * <p>No Test Case for this UI function.</p>
     * @author SUN Xinyu 20327715 xsunan@connect.ust.hk DasVergissmeinnicht
     */
    @FXML
    private void QuitFunction() {System.exit(0);}
    
    
    // UI Function for showTeamInformation()
	/**
     * This is a UI implementation function for Basic Task 6(i). 
     * <p>When user presses this botton, it will show a dialog which presents the team information.</p>
     * <p>This is a simple UI function without logic.</p>
     * <p>The test case is <b>testShowTeamInformation()</b>.</p>
     * @author SUN Xinyu 20327715 xsunan@connect.ust.hk DasVergissmeinnicht
     */
    @FXML
    private void showTeamInformation() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Team Information");
    	alert.setHeaderText(null);
    	String InformationString = InformationString();
    	alert.setContentText(InformationString);
   		alert.showAndWait();}
    
    
    // Logic Function for showTeamInformation()
	/**
     * This is a Logic implementation function for Basic Task 6(i). 
     * <p>The Test Case is test whether the team information is correct or not.</p>
     * <p>The test case is <b>testShowTeamInformation()</b>.</p>
     * <p>Total Lines: 6, Tested Lines: 6</p>
     * @return InformationString A String of Team Information.
     * @author SUN Xinyu 20327715 xsunan@connect.ust.hk DasVergissmeinnicht
     */
    public String InformationString() {
    	String InformationString = "Team Number 55\n";
    	InformationString += "Name           Itsc Account";
    	InformationString += "    Github Account \nSun Xinyu     xsunan         ";
    	InformationString += "  DasVergissmeinnicht\nWang Haoqi hwangby      ";
    	InformationString += " silicon00\nLi Haoran      hlibt               teapotliid";
    	return InformationString;
    }
    
    // UI Function for Load
	/**
     * This is a UI implementation function for Adv Task 2 (ii). 
     * <p>When user presses this botton, The system will give put a filechooser to allow user choose which one they want to load.</p>
     * <p>This only allow the user to choose "*.sxy" file, which is produced by Save Function.</p>
     * <p>The test case is <b> testSaveAndLoad()</b>.</p>
     * @author SUN Xinyu 20327715 xsunan@connect.ust.hk DasVergissmeinnicht
     */
    @FXML
    private void LoadResult() {
    	JFileChooser fd = new JFileChooser();
    	fd.setAcceptAllFileFilterUsed(false);
    	TxTFileFilter TxTFilter = new TxTFileFilter();
    	fd.addChoosableFileFilter(TxTFilter);
    	fd.setFileFilter(TxTFilter);
    	fd.showOpenDialog(null);
    	
    	File f = fd.getSelectedFile();
    	if(f == null){return;}
    	
    	String output = "--Data Loading from " + f.getName() + "--" + "\n";
    	
    	// Logic Lines
		Vector<Item> tempLoadResult = new Vector<Item>();
		String SearchName = null;
		tempLoadResult = LogicLoad(f);
		SearchName = LogicSearchName(f);
		// Logic Ends
		
    	//for (Item item : tempLoadResult) {output += item.getTitle() + "\t" + item.getPrice() + "\t" + item.getUrl() + "\n";}
    	output += LogicShowResult(tempLoadResult);
		textAreaConsole.setText(output);
    	
    	// Haoqi Part
    	Basic1(tempLoadResult);
    	Advance1(tempLoadResult, SearchName);
    	// ***** 
    	// Haoran Part
    	tableFill(tempLoadResult);			//****haoran added
    	trendFill(tempLoadResult, SearchName);  
    	// End
    }

    
    // Logic Function of LoadResult()
	/**
     * This is a Logic implementation function for Adv Task 2(ii). 
     * <p>It will read a file and get information about the saved result.</p>
     * <p>The test case is <b>testSaveAndLoad()</b>.</p>
     * @param f a "*.sxy" file.
     * @return tempLoadResult a vector contains all the item which saved before.
     * @author SUN Xinyu 20327715 xsunan@connect.ust.hk DasVergissmeinnicht
     */
    public Vector<Item> LogicLoad(File f) {
		Vector<Item> tempLoadResult = new Vector<Item>();
    	try {
    		FileReader reader = new FileReader(f);
    		BufferedReader br = new BufferedReader(reader);
    		String line;
    		String title = null;
    		double price = 0;
    		String url = null;
    		String[] Time = null;
    		while((line = br.readLine()) != null) 
    		{
    			//System.out.println(line.substring(0, 5));
    			if (line.substring(0, 5).equals("Index"))
    			{
    				title = null;
    				price = 0;
    				url = null;
    				Time = null;
    			}
    			if (line.substring(0, 5).equals("Title"))
    			{
    				title = line.substring(7);
    				//System.out.println(title);
    			}
    			if (line.substring(0, 5).equals("Price"))
    			{
    				price = Double.valueOf(line.substring(7));
    				//System.out.println(line.substring(7));
    			}
    			if (line.substring(0, 3).equals("Url"))
    			{
    				url = line.substring(5);
    				//System.out.println(line.substring(5));

    			}
    			if (line.substring(0, 4).equals("Time"))
    			{
    				Time = new String[7];
    				String [] arr = line.split("\\s+");
    				for(int i=0; i<7; i++){
    				    Time[i] = arr[i+1];
    				    System.out.print(Time[i]+" ");
    				}
    				//System.out.println(" ");
    				
    				
    				Item tempItem = new Item();
    				tempItem.setPrice(price);
    				tempItem.setTitle(title);
    				tempItem.setUrl(url);
    				tempItem.setTime(Time);
    				tempItem.setTimeString(line.substring(6)); 
    				tempLoadResult.add(tempItem);
    			}
    		}
    		reader.close();
    		br.close();
    	}
        catch(IOException e) {
            e.printStackTrace();
        }
    	return tempLoadResult;
    }
    
    // Logic Function of LoadResult()
	/**
     * This is a Logic implementation function for Adv Task 2(ii). 
     * <p>It will read a file and get the saved search name.</p>
     * <p>The test case is <b>testSaveAndLoad()</b>.</p>
     * @param f a "*.sxy" file.
     * @return SearchName a string contains the searched item name before.
     * @author SUN Xinyu 20327715 xsunan@connect.ust.hk DasVergissmeinnicht
     */
    public String LogicSearchName(File f) {
		String SearchName = null;
    	try {
    		FileReader reader = new FileReader(f);
    		BufferedReader br = new BufferedReader(reader);
    		String line;
			if ((line = br.readLine()) != null)
			{
				if (line.substring(0, 10).equals("SearchName"))
				{
					SearchName = line.substring(12);
				}
				else
					return "";
			}
			else
				return "";
    		reader.close();
    		br.close();
    	}
        catch(IOException e) {
            e.printStackTrace();
        }
    	return SearchName;
    }
    
    
    
	/**
     * This is a UI implementation function for Adv Task 2 (i). 
     * <p>When user presses this botton, The system will give out a dialog to allow user to type the saved fil.</p>
     * <p>This will saved the current search result into a "*.sxy" file</p>
     * <p>When there is another same filename file, the system will inform the user whether overwrite it or not.</p>
     * <p>The Saved file can be founded in the base root of this java project. (If Use Eclipse, can use "Refresh" and the file will show in the Package Explorer) </p>
     * <p>The default file name is "default.sxy".</p>
     * <p>The test case is <b>testSaveAndLoad()</b>.</p>
     * @author SUN Xinyu 20327715 xsunan@connect.ust.hk DasVergissmeinnicht
     */
    @FXML
    private void SaveResult() {
    	// Get User's Filename
    	TextInputDialog dialog = new TextInputDialog("default");
    	dialog.setTitle("Save Filename");
    	dialog.setHeaderText("Please enter the filename:");
    	Optional<String> result_filename = dialog.showAndWait();
    	if (result_filename.isPresent() == false)
    	{
    		return;
    	}
		String filename = result_filename.get() + ".sxy";
		File fileText = new File(filename);
		
		// Check whether the file exists or not
		if (fileText.exists() == true)
		{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Same Name File Exists");
			alert.setContentText("Do you want to overwrite?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() != ButtonType.OK){
			   return;
			} 
		}
		// Logic Line
		LogicSave(fileText, CurrentSearchName, CurrentResult);}
    
    
    // Logic Function of Save
	/**
     * This is a Logic implementation function for Adv Task 2(ii\). 
     * <p>It will open a file and write the current result and current search name into a "*.sxy" file</p>
     * <p>It will save the information in the original order.</p>
     * <p>The Saved file can be founded in the base root of this java project. (If Use Eclipse, can use "Refresh" and the file will show in the Package Explorer) </p>
     * <p>The saved file can be opened by text editor.</p>
     * <p>The test case is <b>testSaveAndLoad()</b>.</p>
     * @param fileText the destination file which used to store the information.
     * @param CurrentSearchName the string which is the current searched item name.
     * @param CurrentResult the vector contains the current searched result.
     * @author SUN Xinyu 20327715 xsunan@connect.ust.hk DasVergissmeinnicht
     */
    public void LogicSave(File fileText, String CurrentSearchName, List<Item> CurrentResult) {
		try {
			FileWriter fileWriter = new FileWriter(fileText);
			BufferedWriter bw = new BufferedWriter(fileWriter);
			int index = 0;
			String Firstline = "SearchName: " + CurrentSearchName + "\n";
			bw.write(Firstline);
	    	for (Item r : CurrentResult)
	    	{
	    		String line = "Index: " + index + "\n" + "Title: " + r.getTitle() + "\n"
	    		+ "Price: " + r.getPrice() + "\n" + "Url: " + r.getUrl() + "\n";
	    		line += "Time: ";
	    		String[] Time = r.getTime();
	    		for (int i=0; i<7; i++)
	    		{
	    			line += (Time[i] + " ");
	    		}
	    		line += "\n";
	    		
	    		bw.write(line);
	    		index++;
	    	}
			bw.close();
			fileWriter.close();
		}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    	}
    }
    
    
    // Helper Class
    // Xinyu Sun's Helper Function
//    /**
//     * This is a helper class for the filechooser of Function Load(). 
//     * <p>It provides functions to display only the file with "*.sxy"</p>
//     * <p>No Test Function</p>
//     * @author Xinyu Sun
//     */
//    class TxTFileFilter extends FileFilter {  
//        public String getDescription() {  
//            return "*.sxy";  
//        }  
//        public boolean accept(File file) {  
//            String name = file.getName();  
//            return file.isDirectory() || name.toLowerCase().endsWith(".sxy"); 
//        }  
//    }  
    /* End */
}