package core;


public class CountryFilter implements Filter{
    private String myCon;
    
    public CountryFilter(String con) {
        myCon = con;
    }
    
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getCountry(id).toLowerCase().
        		contains(myCon.toLowerCase());
    }
}

