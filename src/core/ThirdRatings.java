package core;

/**
 * Write a description of FirstRatings here.
 * 
 * @author Yijun Chen
 * @version 26-04-2020
 */
import java.util.*;
public class ThirdRatings {
   
    
    private double getAverageByID(String id, int minimalRaters){
        int raterCount=0;
        double sumRating = 0;
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        for(Rater rater: myRaters){
            if(rater.hasRating(id)){
                raterCount+=1;
                sumRating+=rater.getRatingFromItem(id);
            }
        }
        double avg =0;
        if(raterCount>=minimalRaters){
            avg = sumRating/raterCount;
        }
        return avg;
    }
      
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> ratingArr = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String id: movies){
            double avg = getAverageByID(id,minimalRaters);
            if(avg!=0.0){
                Rating avgRating = new Rating(id,avg);
                ratingArr.add(avgRating);
            }
        }
        return ratingArr;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter criteria){
        ArrayList<Rating> ratingArr = new ArrayList<Rating>();
        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(criteria);
        ArrayList<String> movies = MovieDatabase.filterBy(allFilter);
        for(String id: movies){
            double avg = getAverageByID(id,minimalRaters);
            if(avg!=0.0){
                Rating avgRating = new Rating(id,avg);
                ratingArr.add(avgRating);
            }
        }
        return ratingArr;
    }

}
