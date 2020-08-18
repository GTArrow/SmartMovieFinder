package core;

/**
 * Write a description of FirstRatings here.
 * 
 * @author Yijun Chen
 * @version 26-04-2020
 */
import java.util.*;

public class EfficientRater implements Rater{
    private String myID;
    private HashMap<String, Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String, Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item,new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        if(myRatings.containsKey(item)){
            return true;
        }
        return false;
    }

    public String getID() {
        return myID;
    }

    public double getRatingFromItem(String item) {
        double rating =-1;
        if(myRatings.containsKey(item)){
            rating = myRatings.get(item).getValue();
        }
        
        return rating;
    }

    public int numRatings() {
        return myRatings.size();
    }
    
    public boolean equals(Object o){
        EfficientRater other = (EfficientRater) o;
        if(myID.equals(other.getID())){
            return true;
        }
        return false;
    }
    
    public ArrayList<Rating> getRating(){
        ArrayList<Rating> list = new ArrayList<Rating>();
        for(String s: myRatings.keySet()){
            list.add(myRatings.get(s));
        }
        return  list;
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(String s: myRatings.keySet()){
            list.add(s);
        }
        
        return list;
    }
}
