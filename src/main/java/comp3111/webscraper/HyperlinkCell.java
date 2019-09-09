package comp3111.webscraper;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
/*
public class HyperlinkCell implements Callback<TableColumn<Item, String>, TableCell<Item, String>> {

	@Override
	public TableCell<Item, String> call(TableColumn<Item, String> param) {
		// TODO Auto-generated method stub
		return null;
	}

}
*/
import javafx.scene.control.Hyperlink;

/**
 * This is a helper class for the trendFill() function. 
 * <p>It helps implement table url to make them to be hyperlink.</p>
 * <p>Test Function <b>UI related, no need to test</b></p>
 * @author Li Haoran
 */
public class HyperlinkCell implements  Callback<TableColumn<Item, Hyperlink>, TableCell<Item, Hyperlink>> {
	 
    @Override
    public TableCell<Item, Hyperlink> call(TableColumn<Item, Hyperlink> arg) {
        TableCell<Item, Hyperlink> cell = new TableCell<Item, Hyperlink>() {
            @Override
            protected void updateItem(Hyperlink item, boolean empty) {
                setGraphic(item);
            }
            
        };
        return cell;
    }
}


