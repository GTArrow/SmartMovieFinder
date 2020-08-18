package core;

/**
 * Write a description of FirstRatings here.
 * 
 * @author Yijun Chen
 * @version 26-04-2020
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename){
        FileResource file = new FileResource(filename);
        CSVParser parser = file.getCSVParser();
        ArrayList<Movie> movArr = new ArrayList<Movie>();
        for(CSVRecord record: parser){
            String id = record.get(0);
            String title = record.get(1);
            String year = record.get(2);
            String genres = record.get(4);
            String director = record.get(5);
            String country= record.get(3);
            String poster = record.get(7);
            int minutes = Integer.parseInt(record.get(6).trim());
            Movie mov = new Movie(id,title,year,genres,director,country,poster,minutes);
            movArr.add(mov);
        }
        return movArr;
    }
    
    public ArrayList<Rater> loadRaters(String filename){
        FileResource file = new FileResource(filename);
        CSVParser parser = file.getCSVParser();
        ArrayList<Rater> raterArr = new ArrayList<Rater>();
        for(CSVRecord record:parser){
            String movieId = record.get(1);
            double rating = Double.parseDouble(record.get(2));
            String raterId = record.get(0);
            Rater rater = new EfficientRater(raterId);
            if(raterArr.contains(rater)){
                int index = raterArr.indexOf(rater);
                Rater newRater = raterArr.get(index);
                newRater.addRating(movieId, rating);
                raterArr.set(index,newRater);
            }else{
                rater.addRating(movieId, rating);
                raterArr.add(rater);
            }
        }
        return raterArr;
    }
    
    
    public void testLoadMovies(){
        System.out.println("************");
        ArrayList<Movie> arr = loadMovies("data/ratedmoviesfull.csv");
        System.out.println("Array size: "+arr.size());
        int numComedy = 0;
        int numMovGre150 = 0;
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        for(Movie m: arr){
            if(m.getGenres().contains("Comedy")){
                numComedy+=1;
            }
            if(m.getMinutes()>150){
                numMovGre150+=1;
            }
            
            for(String director: m.getDirector().split(",")){
                director = director.trim();
                String movie = m.getTitle();
                if(!map.containsKey(director)){
                    ArrayList <String> movies = new ArrayList <String>();
                    movies.add(movie);
                    map.put(director,movies);
                }else{
                    ArrayList <String> movies = map.get(director);
                    movies.add(movie);
                    map.put(director,movies);
                }
            }
        }
        int maxNumM=0;
        for(String s: map.keySet()){
            if(map.get(s).size()>maxNumM){
                maxNumM = map.get(s).size();
            }
        }
        ArrayList<String> maxNumD= new ArrayList<String>();
        for(String s: map.keySet()){
            if(map.get(s).size()==maxNumM){
                maxNumD.add(s);
            }
        }
        System.out.println("Movies in Comedy Genre: "+numComedy);
        System.out.println("Movies minutes higher than 150 min "+numMovGre150);
        System.out.println("Maximum number of movies by any director: "+maxNumM);
        System.out.println("Directors that direct that many movies: ");
        for(String s: maxNumD){
            System.out.print(s+" ");
            System.out.println();
        }
        System.out.println("There are "+maxNumD.size()+" directors");
    }
    
    public void testLoadRaters(){
        System.out.println("************");
        ArrayList<Rater> arr = loadRaters("data/ratings.csv");
        System.out.println("Array size: "+arr.size());
        /*
        for(Rater r: arr){
            System.out.println("RaterId: "+r.getID()+" NumOfRatings: "+r.numRatings());
            ArrayList<Rating> ratArr = r.getRating();
            for(Rating rr: ratArr){
                System.out.println(rr);
            }
            
        }*/
        int numOfRatings = 0;
        for(Rater r: arr){
            if(r.getID().equals("193")){
                numOfRatings = r.getRating().size();
                break;
            }
        }
        System.out.println("NumOfRating is "+numOfRatings);
        int maxNumOfRating = 0;
        int countNumRatingByItem = 0;
        ArrayList<String> itemArr = new ArrayList<String>();
        for(Rater r: arr){
            if(r.getRating().size()>maxNumOfRating){
                maxNumOfRating = r.getRating().size();
            }
            if(r.hasRating("1798709")){
                countNumRatingByItem+=1;
            }
            ArrayList<String> temp = r.getItemsRated();
            for(String s: temp){
                if(!itemArr.contains(s)){
                    itemArr.add(s);
                }
            }
        }
        System.out.println("the maximum number of ratings "+maxNumOfRating);
        ArrayList<String> raterId = new ArrayList<String>();
        for(Rater r: arr){
            if(r.getRating().size()==maxNumOfRating){
                raterId.add(r.getID());
            }
        }
        for(String s: raterId){
            System.out.print("Rater "+s+" ");
            System.out.println();
        }
        System.out.println("There are "+raterId.size()+" raters");
        System.out.println("Number of ratings a particular movie has "+countNumRatingByItem);
        System.out.println("Num of different movies have been rated "+ itemArr.size());
        
    }

}
