package comp3111.webscraper;

import java.io.File;

import javax.swing.filechooser.FileFilter;
// Helper Class
// Xinyu Sun's Helper Function
/**
 * This is a helper class for the filechooser of Function Load(). 
 * <p>It provides functions to display only the file with "*.sxy"</p>
 * <p>Test Function <b>testHelper()</b></p>
 * @author Xinyu Sun
 */
public class TxTFileFilter extends FileFilter {
    public String getDescription() {  
        return "*.sxy";  
    }  
    public boolean accept(File file) {  
        String name = file.getName();  
        return file.isDirectory() || name.toLowerCase().endsWith(".sxy"); 
    }  
}  
/* End */
