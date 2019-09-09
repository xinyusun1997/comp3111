package comp3111.webscraper;


import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;

public class Item {
	private String title ; 
	private double price ;
	private String url ;
	
	
	//haoqi
	private String[] time;
	//haoran
	private String timeString;
	private Hyperlink hyperlink;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	//haoqi
	public String[] getTime() {
		return time;
	}
	public void setTime(String[] time) {
		this.time = time;
	}
	//haoran
		public String getTimeString() {
			return timeString;
		}
		public void setTimeString(String time) {
			this.timeString = time;
		}
		public Hyperlink getHyperlink() {
			return hyperlink;
		}
		public void setHyperlink() {
			this.hyperlink = new Hyperlink(getUrl());
			this.hyperlink.setOnAction(e -> {
				if(Desktop.isDesktopSupported())
				{
					try {
						Desktop.getDesktop().browse(new URI(getUrl()));
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
			});
		}

}
