package comp3111.webscraper;


import org.junit.Test;

//import comp3111.webscraper.Controller.TxTFileFilter;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Hyperlink;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.text.ParseException;

public class ItemTest {

	@Test
	public void testSetTitle() {
		Item i = new Item();
		i.setTitle("ABCDE");
		assertEquals(i.getTitle(), "ABCDE");
	}
	
	@Test
	public void testWebScraper() {
		WebScraper ws = new WebScraper();
		List<Item> result = ws.scrape("book");
		for(Item item: result) {
			assertEquals(item.getTime() != null, 1==1);
		}
		
		result = ws.scrape("qwefqobiufbvielrgjasdihviernbflarjbferughifdsebrasdfnjwef");
		assertEquals(result.isEmpty(), 1==1);
	}
	
	@Test
	public void testSummeryFill() {
		Vector<Item> result = new Vector<Item>();
		Controller contro = new Controller();
		
		String[] returnValue = contro.summeryFill(result);
		assertEquals(returnValue[0], "0");
		assertEquals(returnValue[1], "-");
		assertEquals(returnValue[2], "-");
		assertEquals(returnValue[3], "-");	
		
		Item a = new Item();
		a.setPrice(0.0);
		a.setUrl("https://www.google.com");
		String mystr = "2018-06-21 03:46"+" "+"Thu 21 Jun 03:46:53 AM";
		String[] timeArr = mystr.split(" ");
		a.setTime(timeArr);
		result.add(a);

		returnValue = contro.summeryFill(result);
		assertEquals(returnValue[0], ""+1);
		assertEquals(returnValue[1], "-");
		assertEquals(returnValue[2], "-");
		assertEquals(returnValue[3], "2018-06-21 03:46:53 AM");		
		
		Item b = new Item();
		b.setPrice(60.0);
		b.setUrl("https://www.amazon.com");
		mystr = "2018-11-01 01:58"+" "+"Thu 01 Nov 01:58:44 AM";
		timeArr = mystr.split(" ");
		b.setTime(timeArr);
		result.add(b);

		returnValue = contro.summeryFill(result);
		assertEquals(returnValue[0], ""+2);
		assertEquals(returnValue[1], ""+60.0);
		assertEquals(returnValue[2], ""+60.0);
		assertEquals(returnValue[3], "2018-11-01 01:58:44 AM");	
		
		Item c = new Item();
		c.setPrice(30.0);
		c.setUrl("https://www.netflix.com");
		mystr = "2018-09-30 21:18"+" "+"Sun 30 Sep 09:18:07 PM";
		timeArr = mystr.split(" ");
		c.setTime(timeArr);
		result.add(c);

		returnValue = contro.summeryFill(result);
		assertEquals(returnValue[0], ""+3);
		assertEquals(returnValue[1], ""+45.0);
		assertEquals(returnValue[2], ""+30.0);
		assertEquals(returnValue[3], "2018-11-01 01:58:44 AM");	
		
		Item d = new Item();
		d.setPrice(0.0);
		d.setUrl("https://www.facebook.com");
		mystr = "2018-11-01 02:58"+" "+"Thu 01 Nov 02:58:44 AM";
		timeArr = mystr.split(" ");
		d.setTime(timeArr);
		result.add(d);

		returnValue = contro.summeryFill(result);
		assertEquals(returnValue[0], ""+4);
		assertEquals(returnValue[1], ""+45.0);
		assertEquals(returnValue[2], ""+30.0);
		assertEquals(returnValue[3], "2018-11-01 02:58:44 AM");	
		
		Item e = new Item();
		e.setPrice(90.0);
		e.setUrl("https://www.apple.com");
		mystr = "2018-11-01 02:58"+" "+"Thu 01 Nov 02:58:45 AM";
		timeArr = mystr.split(" ");
		e.setTime(timeArr);
		result.add(e);

		returnValue = contro.summeryFill(result);
		assertEquals(returnValue[0], ""+5);
		assertEquals(returnValue[1], ""+60.0);
		assertEquals(returnValue[2], ""+30.0);
		assertEquals(returnValue[3], "2018-11-01 02:58:45 AM");	
		
		Item f = new Item();
		f.setPrice(0.0);
		f.setUrl("https://www.bbc.com");
		mystr = "2018-11-01 02:48"+" "+"Thu 01 Nov 02:48:00 AM";
		timeArr = mystr.split(" ");
		f.setTime(timeArr);
		result.add(f);

		returnValue = contro.summeryFill(result);
		assertEquals(returnValue[0], ""+6);
		assertEquals(returnValue[1], ""+60.0);
		assertEquals(returnValue[2], ""+30.0);
		assertEquals(returnValue[3], "2018-11-01 02:58:45 AM");	
		
		Item g = new Item();
		g.setPrice(0.0);
		g.setUrl("https://www.imdb.com");
		mystr = "2018-11-01 02:58"+" "+"Thu 01 Nov 02:58:00 AM";
		timeArr = mystr.split(" ");
		g.setTime(timeArr);
		result.add(g);

		returnValue = contro.summeryFill(result);
		assertEquals(returnValue[0], ""+7);
		assertEquals(returnValue[1], ""+60.0);
		assertEquals(returnValue[2], ""+30.0);
		assertEquals(returnValue[3], "2018-11-01 02:58:45 AM");	
		
		Item h = new Item();
		h.setPrice(-90.0);
		h.setUrl("https://www.twitter.com");
		mystr = "2018-09-15 14:23"+" "+"Sat 15 Sep 02:23:42 PM ";
		timeArr = mystr.split(" ");
		h.setTime(timeArr);
		result.add(h);
		
		boolean thrown = false;
		try {
			returnValue = contro.summeryFill(result);
		} catch (IllegalArgumentException exception) {
			thrown = true;
		}
		assertTrue(thrown);
		
		result.remove(h);
		h.setPrice(0.0);
		mystr = "2018-09-15 14:23"+" "+"15 Sep 02:23:42 PM ";
		timeArr = mystr.split(" ");
		h.setTime(timeArr);
		result.add(h);
		
		thrown = false;
		try {
			returnValue = contro.summeryFill(result);
		} catch (NullPointerException exception) {
			thrown = true;
		}
		assertTrue(thrown);
		
	}
	
	@Test
	public void testSummeryClickMin() {
		Vector<Item> result = new Vector<Item>();
		Controller contro = new Controller();
		
		String returnValue = contro.summeryClickMin(result);
		assertEquals(returnValue, "");
		
		Item a = new Item();
		a.setPrice(0.0);
		a.setUrl("https://www.google.com");
		String mystr = "2018-06-21 03:46"+" "+"Thu 21 Jun 03:46:53 AM";
		String[] timeArr = mystr.split(" ");
		a.setTime(timeArr);
		result.add(a);
		
		returnValue = contro.summeryClickMin(result);
		assertEquals(returnValue, "");
		
		Item b = new Item();
		b.setPrice(60.0);
		b.setUrl("https://www.amazon.com");
		mystr = "2018-11-01 01:58"+" "+"Thu 01 Nov 01:58:44 AM";
		timeArr = mystr.split(" ");
		b.setTime(timeArr);
		result.add(b);

		returnValue = contro.summeryClickMin(result);
		assertEquals(returnValue, "https://www.amazon.com");
	}
	
	@Test
	public void testSummeryClickLatest() {
		Vector<Item> result = new Vector<Item>();
		Controller contro = new Controller();
		
		String returnValue = contro.summeryClickLatest(result);
		assertEquals(returnValue, "");
		
		Item a = new Item();
		a.setPrice(0.0);
		a.setUrl("https://www.google.com");
		String mystr = "2018-06-21 03:46"+" "+"Thu 21 Jun 03:46:53 AM";
		String[] timeArr = mystr.split(" ");
		a.setTime(timeArr);
		result.add(a);
		
		returnValue = contro.summeryClickLatest(result);
		assertEquals(returnValue, "https://www.google.com");
		
		Item b = new Item();
		b.setPrice(60.0);
		b.setUrl("https://www.amazon.com");
		mystr = "2018-11-01 01:58"+" "+"Thu 01 Nov 01:58:44 AM";
		timeArr = mystr.split(" ");
		b.setTime(timeArr);
		result.add(b);

		returnValue = contro.summeryClickLatest(result);
		assertEquals(returnValue, "https://www.amazon.com");
	}
	
	@SuppressWarnings("restriction")
	@Test
	public void testBarChartFill() {
		Vector<Item> result = new Vector<Item>();
		Controller contro = new Controller();
		
		XYChart.Series<String, Number> returnValue = contro.barChartFill(result, "zeroItem");
		assertEquals(returnValue.getData().get(0).getYValue(), 0);
		
		Item a = new Item();
		a.setPrice(30.0);
		a.setUrl("https://www.google.com");
		String mystr = "2018-06-21 03:46"+" "+"Thu 21 Jun 03:46:53 AM";
		String[] timeArr = mystr.split(" ");
		a.setTime(timeArr);
		result.add(a);

		returnValue = contro.barChartFill(result, "oneItem");
		assertEquals(returnValue.getData().get(0).getYValue(), 1);
		
		Item b = new Item();
		b.setPrice(60.0);
		b.setUrl("https://www.amazon.com");
		mystr = "2018-11-01 01:58"+" "+"Thu 01 Nov 01:58:44 AM";
		timeArr = mystr.split(" ");
		b.setTime(timeArr);
		result.add(b);

		returnValue = contro.barChartFill(result, "twoItems");
		assertEquals(returnValue.getData().get(0).getYValue(), 1);
		assertEquals(returnValue.getData().get(9).getYValue(), 1);
		
		Item d = new Item();
		d.setPrice(0.0);
		d.setUrl("https://www.facebook.com");
		mystr = "2018-11-01 02:58"+" "+"Thu 01 Nov 02:58:44 AM";
		timeArr = mystr.split(" ");
		d.setTime(timeArr);
		result.add(d);
		
		returnValue = contro.barChartFill(result, "twoItems");
		assertEquals(returnValue.getData().get(0).getYValue(), 1);
		assertEquals(returnValue.getData().get(9).getYValue(), 1);
	}
	
	@SuppressWarnings("restriction")
	@Test
	public void testBarChartClick() {
		Vector<Item> result = new Vector<Item>();
		Controller contro = new Controller();
		
		XYChart.Data<String, Number> i = new XYChart.Data<String, Number>("",0);
		String returnValue = contro.barChartClick(result, i);
		assertEquals(returnValue, "");
		
		Item a = new Item();
		a.setPrice(30.0);
		a.setUrl("https://www.google.com");
		String mystr = "2018-06-21 03:46"+" "+"Thu 21 Jun 03:46:53 AM";
		String[] timeArr = mystr.split(" ");
		a.setTime(timeArr);
		result.add(a);

		i = new XYChart.Data<String, Number>(""+30.0,1);
		returnValue = contro.barChartClick(result, i);
		assertEquals(returnValue, a.getTitle() + "\t" + a.getPrice() + "\t" + a.getUrl() + "\n");
	
		Item b = new Item();
		b.setPrice(60.0);
		b.setUrl("https://www.amazon.com");
		mystr = "2018-11-01 01:58"+" "+"Thu 01 Nov 01:58:44 AM";
		timeArr = mystr.split(" ");
		b.setTime(timeArr);
		result.add(b);

		i = new XYChart.Data<String, Number>(""+60.0,1);
		returnValue = contro.barChartClick(result, i);
		assertEquals(returnValue, b.getTitle() + "\t" + b.getPrice() + "\t" + b.getUrl() + "\n");
	
		Item c = new Item();
		c.setPrice(30.0);
		c.setUrl("https://www.netflix.com");
		mystr = "2018-09-30 21:18"+" "+"Sun 30 Sep 09:18:07 PM";
		timeArr = mystr.split(" ");
		c.setTime(timeArr);
		result.add(c);
		
		i = new XYChart.Data<String, Number>(""+33.0,2);
		returnValue = contro.barChartClick(result, i);
		assertEquals(returnValue, a.getTitle() + "\t" + a.getPrice() + "\t" + a.getUrl() + "\n" + c.getTitle() + "\t" + c.getPrice() + "\t" + c.getUrl() + "\n");
	
		Item d = new Item();
		d.setPrice(0.0);
		d.setUrl("https://www.facebook.com");
		mystr = "2018-11-01 02:58"+" "+"Thu 01 Nov 02:58:44 AM";
		timeArr = mystr.split(" ");
		d.setTime(timeArr);
		result.add(d);
		
		i = new XYChart.Data<String, Number>(""+33.0,2);
		returnValue = contro.barChartClick(result, i);
		assertEquals(returnValue, a.getTitle() + "\t" + a.getPrice() + "\t" + a.getUrl() + "\n" + c.getTitle() + "\t" + c.getPrice() + "\t" + c.getUrl() + "\n");
	
		Item e = new Item();
		e.setPrice(34.0);
		e.setUrl("https://www.apple.com");
		mystr = "2018-11-01 02:58"+" "+"Thu 01 Nov 02:58:45 AM";
		timeArr = mystr.split(" ");
		e.setTime(timeArr);
		result.add(e);
		
		i = new XYChart.Data<String, Number>(""+36.0,1);
		returnValue = contro.barChartClick(result, i);
		assertEquals(returnValue, e.getTitle() + "\t" + e.getPrice() + "\t" + e.getUrl() + "\n");
		
		Item f = new Item();
		f.setPrice(42.0);
		f.setUrl("https://www.bbc.com");
		mystr = "2018-11-01 02:48"+" "+"Thu 01 Nov 02:48:00 AM";
		timeArr = mystr.split(" ");
		f.setTime(timeArr);
		result.add(f);

		i = new XYChart.Data<String, Number>(""+42.0,0);
		returnValue = contro.barChartClick(result, i);
		assertEquals(returnValue, "");
		
	}
	//haoran's test code begins here
	@SuppressWarnings("restriction")
	@Test
	public void testTableFill() {
		Vector<Item> result = new Vector<Item>();
		Controller contro = new Controller();
		
		ObservableList<Item> returnValue = contro.tableFillLogic(result);
		assertEquals(returnValue.isEmpty(),true);
		
		Item a = new Item();
		a.setPrice(30.0);
		a.setUrl("https://www.google.com");
		String mystr = "2018-06-21 03:46"+" "+"Thu 21 Jun 03:46:53 AM";
		String[] timeArr = mystr.split(" ");
		a.setTime(timeArr);
		result.add(a);

		returnValue = contro.tableFillLogic(result);
		//Hyperlink h = new Hyperlink("https://www.google.com");
		assertEquals(returnValue.get(0).getUrl(),"https://www.google.com");
		
		Item b = new Item();
		b.setPrice(60.0);
		b.setUrl("https://www.amazon.com");
		mystr = "2018-11-01 01:58"+" "+"Thu 01 Nov 01:58:44 AM";
		timeArr = mystr.split(" ");
		b.setTime(timeArr);
		result.add(b);

		returnValue = contro.tableFillLogic(result);
		assertEquals(""+returnValue.get(1).getPrice(),""+ 60.0);
		assertEquals(returnValue.get(1).getTime(),timeArr);
		
		Item d = new Item();
		d.setPrice(0.0);
		d.setUrl("https://www.facebook.com");
		mystr = "2018-11-01 02:58"+" "+"Thu 01 Nov 02:58:44 AM";
		timeArr = mystr.split(" ");
		d.setTime(timeArr);
		result.add(d);
		d.setTitle("hello");
		returnValue = contro.tableFillLogic(result);
		assertEquals(returnValue.get(2).getTime(),timeArr);
		assertEquals(returnValue.get(2).getTitle(), "hello");
		
		Item h = new Item();
		h.setPrice(90.0);
		h.setUrl("");
		mystr = "2018-09-15 14:23"+" "+"Sat 15 Sep 02:23:42 PM ";
		timeArr = mystr.split(" ");
		h.setTime(timeArr);
		result.add(h);
		
		boolean thrown = false;
		try {
			returnValue = contro.tableFillLogic(result);
		} catch (IllegalArgumentException exception) {
			thrown = true;
		}
		assertTrue(thrown);
		Vector<Item> result2 = new Vector<Item>();
		Item t = new Item();
		t.setPrice(90.0);
		t.setUrl("https://www.facebook.com");
		mystr = "";
		timeArr = mystr.split(" ");
		t.setTime(timeArr);
		result2.add(t);
		
		boolean thrown2 = false;
		//Vector<Item> result2 = new Vector<Item>();
		try {
			returnValue = contro.tableFillLogic(result2);
		} catch (NullPointerException exception) {
			thrown2 = true;
		}
		assertTrue(thrown2);
	}
	/*
	 * divide haoran's code -.-
	 */
	@SuppressWarnings("restriction")
	@Test
	public void testTrendFill() {
		Vector<Item> result = new Vector<Item>();
		Controller contro = new Controller();
		
		Vector<Item> result2 = new Vector<Item>();
		Item t = new Item();
		t.setPrice(90.0);
		t.setUrl("https://www.facebook.com");
		t.setTimeString("");
		String mystr = "";
		String[] timeArr = mystr.split(" ");
		t.setTime(timeArr);
		result2.add(t);
		
		boolean thrown2 = false;
		//Vector<Item> result2 = new Vector<Item>();
		try {
			XYChart.Series<String,Number> returnValue = contro.trendFillLogic(result2,"hi");
		} catch (NullPointerException exception) {
			thrown2 = true;
		}
		assertTrue(thrown2);
		
		Vector<Item> result1 = new Vector<Item>();
		Item h = new Item();
		h.setPrice(90.0);
		h.setUrl("");
		mystr = "2018-09-15 14:23"+" "+"Sat 15 Sep 02:23:42 PM ";
		timeArr = mystr.split(" ");
		h.setTime(timeArr);
		result1.add(h);
		
		boolean thrown = false;
		try {
			XYChart.Series<String,Number> returnValue = contro.trendFillLogic(result1,"hi");
		} catch (IllegalArgumentException exception) {
			thrown = true;
		}
		assertTrue(thrown);
		// base case starts here
		Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat stdDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        // 0 case for x[6]
        Calendar cal0 = Calendar.getInstance();
        cal0.add(Calendar.DATE, -3);
        mystr = dateFormat.format(cal0.getTime());
        Item a0 = new Item();
		a0.setPrice(30.0);
		a0.setUrl("https://www.google.com");
		a0.setTimeString(mystr);
		Vector<Item> result0 = new Vector<Item>();
		result0.add(a0);
		XYChart.Series<String,Number> returnValue = contro.trendFillLogic(result0,"hi");
		assertTrue(returnValue.getData().get(0).getXValue().compareTo(stdDateFormat.format(cal0.getTime())) == 0);
		assertEquals(""+returnValue.getData().get(0).getYValue(), ""+30.0);
        //1
        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.DATE, -1);
        mystr = dateFormat.format(cal1.getTime());
        System.out.println("mystr is: "+mystr);
		Item a1 = new Item();
		a1.setPrice(30.0);
		a1.setUrl("https://www.google.com");
		//mystr = "2018-06-21 03:46"+" "+"Thu 21 Jun 03:46:53 AM";
		//timeArr = mystr.split(" ");
		a1.setTimeString(mystr);
		result.add(a1);
		returnValue = contro.trendFillLogic(result,"hi");
		cal.add(Calendar.DATE, -1);
		//assertEquals(returnValue.getData().get(6).getXValue(), cal.getTime());
		assertTrue(returnValue.getData().get(0).getXValue().compareTo(stdDateFormat.format(cal.getTime())) == 0);
		assertEquals(""+returnValue.getData().get(0).getYValue(), ""+30.0);
		//2
		//Calendar cal = Calendar.getInstance();
        cal1.add(Calendar.DATE, -1);
        mystr = dateFormat.format(cal1.getTime());
        System.out.println("mystr is: "+mystr);
		Item a2 = new Item();
		a2.setPrice(60.0);
		a2.setUrl("https://www.google.com");
		a2.setTimeString(mystr);
		result.add(a2);
		returnValue = contro.trendFillLogic(result,"hi");
		cal.add(Calendar.DATE, -1);
		assertTrue(returnValue.getData().get(0).getXValue().compareTo(stdDateFormat.format(cal.getTime())) == 0);
		assertEquals(""+returnValue.getData().get(0).getYValue(), ""+60.0);
		//3
		cal1.add(Calendar.DATE, -1);
        mystr = dateFormat.format(cal1.getTime());
        System.out.println("mystr is: "+mystr);
		Item a3 = new Item();
		a3.setPrice(90.0);
		a3.setUrl("https://www.google.com");
		a3.setTimeString(mystr);
		result.add(a3);
		returnValue = contro.trendFillLogic(result,"hi");
		cal.add(Calendar.DATE, -1);
		assertTrue(returnValue.getData().get(0).getXValue().compareTo(stdDateFormat.format(cal.getTime())) == 0);
		assertEquals(""+returnValue.getData().get(0).getYValue(), ""+90.0);
		//4
		cal1.add(Calendar.DATE, -1);
        mystr = dateFormat.format(cal1.getTime());
        System.out.println("mystr is: "+mystr);
		Item a4 = new Item();
		a4.setPrice(120.0);
		a4.setUrl("https://www.google.com");
		a4.setTimeString(mystr);
		result.add(a4);
		returnValue = contro.trendFillLogic(result,"hi");
		cal.add(Calendar.DATE, -1);
		assertTrue(returnValue.getData().get(0).getXValue().compareTo(stdDateFormat.format(cal.getTime())) == 0);
		assertEquals(""+returnValue.getData().get(0).getYValue(), ""+120.0);
		//5
		cal1.add(Calendar.DATE, -1);
        mystr = dateFormat.format(cal1.getTime());
        System.out.println("mystr is: "+mystr);
		Item a5 = new Item();
		a5.setPrice(150.0);
		a5.setUrl("https://www.google.com");
		a5.setTimeString(mystr);
		result.add(a5);
		returnValue = contro.trendFillLogic(result,"hi");
		cal.add(Calendar.DATE, -1);
		assertTrue(returnValue.getData().get(0).getXValue().compareTo(stdDateFormat.format(cal.getTime())) == 0);
		assertEquals(""+returnValue.getData().get(0).getYValue(), ""+150.0);
		//6
		cal1.add(Calendar.DATE, -1);
        mystr = dateFormat.format(cal1.getTime());
        System.out.println("mystr is: "+mystr);
		Item a6 = new Item();
		a6.setPrice(180.0);
		a6.setUrl("https://www.google.com");
		a6.setTimeString(mystr);
		result.add(a6);
		returnValue = contro.trendFillLogic(result,"hi");
		cal.add(Calendar.DATE, -1);
		assertTrue(returnValue.getData().get(0).getXValue().compareTo(stdDateFormat.format(cal.getTime())) == 0);
		assertEquals(""+returnValue.getData().get(0).getYValue(), ""+180.0);
		//7
		cal1.add(Calendar.DATE, -1);
        mystr = dateFormat.format(cal1.getTime());
        System.out.println("mystr is: "+mystr);
		Item a7 = new Item();
		a7.setPrice(210.0);
		a7.setUrl("https://www.google.com");
		a7.setTimeString(mystr);
		result.add(a7);
		returnValue = contro.trendFillLogic(result,"hi");
		cal.add(Calendar.DATE, -1);
		assertTrue(returnValue.getData().get(0).getXValue().compareTo(stdDateFormat.format(cal.getTime())) == 0);
		assertEquals(""+returnValue.getData().get(0).getYValue(), ""+210.0);
	}
	// Below is Xinyu Sun Test Part
	@SuppressWarnings("restriction")
	@Test
	public void testShowTeamInformation() {
		Controller contro = new Controller();
		String returnString = contro.InformationString();
		assertEquals(returnString, "Team Number 55\nName           Itsc Account"
    			+ "    Github Account \nSun Xinyu     xsunan         "
    			+ "  DasVergissmeinnicht\nWang Haoqi hwangby      "
    			+ " silicon00\nLi Haoran      hlibt               teapotliid");
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testSaveAndLoad() {
		File fileText = new File("Test.sxy");
		
		
		File fileText_null = new File("Test_null.sxy");
		try {
			FileWriter fileWriter = new FileWriter(fileText_null);
			BufferedWriter bw = new BufferedWriter(fileWriter);
			bw.close();
			fileWriter.close();
		}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    	}
		
		File fileText_NotSearch = new File("Test_fileText_NotSearch.sxy");
		try {
			FileWriter fileWriter = new FileWriter(fileText_NotSearch);
			BufferedWriter bw = new BufferedWriter(fileWriter);
			String Firstline = "Just A Useless Line for Testing Only\n";
			bw.write(Firstline);
			bw.close();
			fileWriter.close();
		}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    	}
		
		List<Item> CurrentResult = new Vector<Item>();
		Controller contro = new Controller();
		
		Item i = new Item();
		i.setTitle("ABCDE");
		i.setPrice(10);
		String mystr = "2018-11-01 01:58"+" "+"Thu 01 Nov 01:58:44 AM";
		String[] timeArr = mystr.split(" ");
		i.setTime(timeArr);
		i.setUrl("https://www.netflix.com");
		CurrentResult.add(i);
		
		Item b = new Item();
		b.setTitle("FGHKI");
		b.setPrice(20);
		mystr = "2018-11-01 01:58"+" "+"Thu 01 Nov 01:58:44 AM";
		timeArr = mystr.split(" ");
		b.setTime(timeArr);
		b.setUrl("https://www.google.com");
		CurrentResult.add(b);
		
		contro.LogicSave(fileText, "Test", CurrentResult);
		
		Vector<Item> result =  new Vector<Item>();
		result = contro.LogicLoad(fileText);
		
		String SearchName = contro.LogicSearchName(fileText);
		String SearchName_null = contro.LogicSearchName(fileText_null);
		String SearchName_Not = contro.LogicSearchName(fileText_NotSearch);
	    assertEquals(SearchName, "Test");
	    assertEquals(result.get(0).getTitle(), "ABCDE");
	    assertEquals(result.get(1).getTitle(), "FGHKI");
	    assertEquals(SearchName_null, "");
	    assertEquals(SearchName_Not, "");	    
	}
	
	@Test
	public void testLogicShowResult() {
		List<Item> Result = new Vector<Item>();
		Controller contro = new Controller();
		
		Item i = new Item();
		i.setTitle("ABCDE");
		i.setPrice(10);
		String mystr = "2018-11-01 01:58"+" "+"Thu 01 Nov 01:58:44 AM";
		String[] timeArr = mystr.split(" ");
		i.setTime(timeArr);
		i.setUrl("https://www.netflix.com");
		Result.add(i);
		
		Item b = new Item();
		b.setTitle("FGHKI");
		b.setPrice(20);
		mystr = "2018-11-01 01:58"+" "+"Thu 01 Nov 01:58:44 AM";
		timeArr = mystr.split(" ");
		b.setTime(timeArr);
		b.setUrl("https://www.google.com");
		Result.add(b);
		
		String goal_output = "ABCDE" + "\t" + 10.0 + "\t" + "https://www.netflix.com" + "\n";
		goal_output += "FGHKI\t";
		goal_output += 20.0;
		goal_output += "\thttps://www.google.com\n";
		
		String output = contro.LogicShowResult(Result);
		assertEquals(output, goal_output);
	}	
	@Test
	public void testHelper()
	{
		File fileText_helper = new File("Test_helper.sxy");
		try {
			FileWriter fileWriter = new FileWriter(fileText_helper);
			BufferedWriter bw = new BufferedWriter(fileWriter);
			bw.close();
			fileWriter.close();
		}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    	}
		
		File fileText_helper_null = new File("Test_helper.notsxy");
		try {
			FileWriter fileWriter = new FileWriter(fileText_helper_null);
			BufferedWriter bw = new BufferedWriter(fileWriter);
			bw.close();
			fileWriter.close();
		}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    	}
		
		TxTFileFilter helper = new TxTFileFilter();
		assertEquals(helper.getDescription(), "*.sxy");
		helper.accept(fileText_helper);
		helper.accept(fileText_helper_null);
	}
}
