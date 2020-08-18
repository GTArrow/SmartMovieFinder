package core;

/**
 * Write a description of FirstRatings here.
 * 
 * @author Yijun Chen
 * @version 26-04-2020
 */
import java.util.*;

public class FourthRatings {
    
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
    
    private double getAverageByIDAndSimilarity
    (String id, int minimalRaters,int numSimilarRaters,ArrayList<Rating> similarRating){
        int raterCount=0;
        double sumRating = 0;
        int count=1;
        for(Rating rater: similarRating){
            if(count>numSimilarRaters){
                break;
            }
            if(RaterDatabase.getRater(rater.getItem()).hasRating(id)){
                raterCount+=1;
                sumRating+=rater.getValue()*
                    RaterDatabase.getRater(rater.getItem()).getRatingFromItem(id);
            }
            count+=1;
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
    
    private int dotProduct(Rater me, Rater r){
        int product=0;
        ArrayList<String> movieArr1= me.getItemsRated();
        for(String s : movieArr1){
            if(r.hasRating(s)){
                product+=(me.getRatingFromItem(s)-5)*(r.getRatingFromItem(s)-5);
            }
        }
        return product;
    }
    
    public ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> rating = new ArrayList<Rating>();
        ArrayList<Rater> raterArr = RaterDatabase.getRaters();
        Rater me = RaterDatabase.getRater(id);
        for(Rater r: raterArr){
            if(r.equals(me)){
                continue;
            }
            int dotPro = dotProduct(me,r);
            if(dotPro>0){
                rating.add(new Rating(r.getID(),dotPro));
            }
        }
        Collections.sort(rating);
        return rating;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, 
    int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> similarRating = getSimilarities(id);
        ArrayList<Rating> ratingArr = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String s: movies){
            double avg = getAverageByIDAndSimilarity(s,minimalRaters,
                    numSimilarRaters,similarRating);
            if(avg!=0.0){
                Rating avgRating = new Rating(s,avg);
                ratingArr.add(avgRating);
            }
        }
        Collections.sort(ratingArr);
        return ratingArr;
    }
      
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, 
    int numSimilarRaters, int minimalRaters, Filter criteria){
        ArrayList<Rating> similarRating = getSimilarities(id);
        ArrayList<Rating> ratingArr = new ArrayList<Rating>();
        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(criteria);
        ArrayList<String> movies = MovieDatabase.filterBy(allFilter);
        for(String s: movies){
            double avg = getAverageByIDAndSimilarity(s,minimalRaters,
                    numSimilarRaters,similarRating);
            if(avg!=0.0){
                Rating avgRating = new Rating(s,avg);
                ratingArr.add(avgRating);
            }
        }
        Collections.sort(ratingArr);
        return ratingArr;
    }
    

}
