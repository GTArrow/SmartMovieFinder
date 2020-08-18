package core;

/**
 * Write a description of FirstRatings here.
 * 
 * @author Yijun Chen
 * @version 26-04-2020
 */
import java.util.*;

public interface Rater {
    
    public void addRating(String item, double rating);
    public boolean hasRating(String item);
    public String getID();
    public double getRatingFromItem(String item);
    public int numRatings();
    public boolean equals(Object o);
    public ArrayList<Rating> getRating();
    public ArrayList<String> getItemsRated();

}
