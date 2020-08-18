package core;

/**
 * Write a description of FirstRatings here.
 * 
 * @author Yijun Chen
 * @version 26-04-2020
 */
import java.util.*;

public class DirectorsFilter implements Filter{
    private ArrayList<String> myDir;
    
    public DirectorsFilter(ArrayList<String> dir) {
        myDir = dir;
    }
    
    @Override
    public boolean satisfies(String id) {
        for(String s: myDir){
            if(MovieDatabase.getDirector(id).contains(s)){
                return true;
            }
        }
        return false;
    }
}
