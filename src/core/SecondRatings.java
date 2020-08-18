package core;

/**
 * Write a description of SecondRatings here.
 * 
 * @author Yijun Chen
 * @version 25-04-2020
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("data/ratedmoviesfull.csv", "data/ratings.csv");
        //this("data/ratedmoviesfull.csv", "data/ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile){
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getMovieSize(){
        return myMovies.size();
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters){
        int raterCount=0;
        double sumRating = 0;
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
    
    public double getAverageRatingFromTitle(String title){
        String id = getID(title);
        double avg = getAverageByID(id,1);
        return avg;
    }
        
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> ratingArr = new ArrayList<Rating>();
        for(Movie movie: myMovies){
            double avg = getAverageByID(movie.getID(),minimalRaters);
            if(avg!=0.0){
                Rating avgRating = new Rating(movie.getID(),avg);
                ratingArr.add(avgRating);
            }
        }
        return ratingArr;
    }
    
    public String getTitle(String id){
        String title = "ID was not Found.";
        for(Movie movie: myMovies){
            if(movie.getID().equals(id)){
                title = movie.getTitle();
                break;
            }
        }
        return title;
    }
    
    public String getID(String title){
        String id = "No Such Title Found.";
        for(Movie movie: myMovies){
            if(movie.getTitle().equals(title)){
                id = movie.getID();
                break;
            }
        }
        return id;
    }
        
    
 
}