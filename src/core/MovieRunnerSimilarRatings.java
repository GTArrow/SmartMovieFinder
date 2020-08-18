package core;

/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author Yijun Chen
 * @version 26-04-2020
 */
import java.util.*;

public class MovieRunnerSimilarRatings {
    
    public void printAverageRatings(){
        System.out.println("*************");
        FourthRatings sr = new FourthRatings();
        RaterDatabase.initialize("ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("NumOfMovies: "+MovieDatabase.size());
        System.out.println("NumOfRaters: "+RaterDatabase.size());
        ArrayList<Rating> avgRating = sr.getAverageRatings(1);
        System.out.println("Found "+ avgRating.size()+" movies:");
        Collections.sort(avgRating);
        for(Rating rating: avgRating){
            System.out.println(rating.getValue()+" "+MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        System.out.println("*************");
        FourthRatings sr = new FourthRatings();
        RaterDatabase.initialize("ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("NumOfMovies: "+MovieDatabase.size());
        System.out.println("NumOfRaters: "+RaterDatabase.size());
        AllFilters allF = new AllFilters();
        Filter yearF = new YearAfterFilter(1980);
        Filter genreF = new GenreFilter("Romance");
        allF.addFilter(yearF);
        allF.addFilter(genreF);
        ArrayList<Rating> avgRating = sr.getAverageRatingsByFilter(1,allF);
        System.out.println("Found "+ avgRating.size()+" movies:");
        Collections.sort(avgRating);
        for(Rating rating: avgRating){
            System.out.println(rating.getValue()+" "+ MovieDatabase.getYear(rating.getItem())+" "+
            MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t"+MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    public void printSimilarRatings(){
        System.out.println("*************");
        FourthRatings sr = new FourthRatings();
        //RaterDatabase.initialize("ratings_short.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("NumOfMovies: "+MovieDatabase.size());
        System.out.println("NumOfRaters: "+RaterDatabase.size());
        //ArrayList<Rating> avgRating = sr.getSimilarities("3");
        ArrayList<Rating> avgRating = sr.getSimilarRatings("65",20,5);
        System.out.println("Found "+ avgRating.size()+" movies:");
        for(Rating rating: avgRating){
            System.out.println(rating.getValue()+" "+MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenre(){
        System.out.println("*************");
        FourthRatings sr = new FourthRatings();
        //RaterDatabase.initialize("ratings_short.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("NumOfMovies: "+MovieDatabase.size());
        System.out.println("NumOfRaters: "+RaterDatabase.size());
        //ArrayList<Rating> avgRating = sr.getSimilarities("3");
        Filter genre = new GenreFilter("Action");
        ArrayList<Rating> avgRating = sr.getSimilarRatingsByFilter("65",20,5,genre);
        System.out.println("Found "+ avgRating.size()+" movies:");
        for(Rating rating: avgRating){
            System.out.println(rating.getValue()+" "+MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printSimilarRatingsByDirector(){
        System.out.println("*************");
        FourthRatings sr = new FourthRatings();
        //RaterDatabase.initialize("ratings_short.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("NumOfMovies: "+MovieDatabase.size());
        System.out.println("NumOfRaters: "+RaterDatabase.size());
        //ArrayList<Rating> avgRating = sr.getSimilarities("3");
        ArrayList<String> arr = new ArrayList(Arrays.asList("Clint Eastwood","Sydney Pollack","David Cronenberg",
                    "Oliver Stone"));
        Filter dir = new DirectorsFilter(arr);
        ArrayList<Rating> avgRating = sr.getSimilarRatingsByFilter("1034",10,3,dir);
        System.out.println("Found "+ avgRating.size()+" movies:");
        for(Rating rating: avgRating){
            System.out.println(rating.getValue()+" "+MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
        System.out.println("*************");
        FourthRatings sr = new FourthRatings();
        //RaterDatabase.initialize("ratings_short.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("NumOfMovies: "+MovieDatabase.size());
        System.out.println("NumOfRaters: "+RaterDatabase.size());
        //ArrayList<Rating> avgRating = sr.getSimilarities("3");
        AllFilters allF = new AllFilters();
        Filter minutesF = new MinutesFilter(100,200);
        Filter genreF = new GenreFilter("Adventure");
        allF.addFilter(minutesF);
        allF.addFilter(genreF);
        ArrayList<Rating> avgRating = sr.getSimilarRatingsByFilter("65",10,5,allF);
        System.out.println("Found "+ avgRating.size()+" movies:");
        for(Rating rating: avgRating){
            System.out.println(rating.getValue()+" "+MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
        System.out.println("*************");
        FourthRatings sr = new FourthRatings();
        //RaterDatabase.initialize("ratings_short.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("NumOfMovies: "+MovieDatabase.size());
        System.out.println("NumOfRaters: "+RaterDatabase.size());
        //ArrayList<Rating> avgRating = sr.getSimilarities("3");
        AllFilters allF = new AllFilters();
        Filter minutesF = new MinutesFilter(80,100);
        Filter yearF = new YearAfterFilter(2000);
        allF.addFilter(yearF);
        allF.addFilter(minutesF);
        ArrayList<Rating> avgRating = sr.getSimilarRatingsByFilter("65",10,5,allF);
        System.out.println("Found "+ avgRating.size()+" movies:");
        for(Rating rating: avgRating){
            System.out.println(rating.getValue()+" "+MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public static void main(String[] args) {
    	MovieRunnerSimilarRatings t = new MovieRunnerSimilarRatings();
    	t.printSimilarRatingsByGenreAndMinutes();
	}
    

}
