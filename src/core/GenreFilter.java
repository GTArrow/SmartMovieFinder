package core;

/**
 * Write a description of FirstRatings here.
 * 
 * @author Yijun Chen
 * @version 26-04-2020
 */
public class GenreFilter implements Filter {
        private String myGenre;
	
	public GenreFilter(String Genre) {
		myGenre = Genre;
	}
	
	@Override
	public boolean satisfies(String id) {
		return MovieDatabase.getGenres(id).toLowerCase()
				.contains(myGenre.toLowerCase());
	}
}
