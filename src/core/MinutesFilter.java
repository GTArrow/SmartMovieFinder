package core;

/**
 * Write a description of FirstRatings here.
 * 
 * @author Yijun Chen
 * @version 26-04-2020
 */
public class MinutesFilter implements Filter{
    private int minMin;
    private int maxMin;
    
    public MinutesFilter(int min, int max) {
        minMin = min;
        maxMin = max;
    }
    
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getMinutes(id) >= minMin && 
            MovieDatabase.getMinutes(id) <= maxMin;
    }
}
