package gui;

import java.util.ArrayList;
import java.util.Collections;

import core.*;
import processing.core.PFont;
import processing.core.PImage;

public class Recommendation extends TextBox{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int filterS = 180;
	private int submitS = 250;
	private int posterS = 300;
	private int generalW =0;
	
	
	private PFont titleFont;
	private PFont paragraph;
	private PFont introFont;
	
	private Recommendation applet;
	private ArrayList<TextBox> textBoxes;
	private ButtonBox buttonS;
	private ButtonBox buttonR;
	
	private boolean[] filter = new boolean[3];
	private boolean genreFilter = false;
	private boolean yearFilter = false;
	private boolean countryFilter = false;
	private boolean send = false;
	private String[] input = new String[3];
	private String genreInput = "";
	private String yearInput = "";
	private String countryInput = "";
	
	private ArrayList<Rating> outputMovies;
	
	
	public void setup() {
		size(1300,750);
		applet=this;
		filter[0]=genreFilter;	input[0]=genreInput;
		filter[1]=yearFilter;	input[1]=yearInput;
		filter[2]=countryFilter;	input[2]=countryInput;
		textBoxes = new ArrayList<TextBox>();
		titleFont = createFont("Georgia", 30);
		paragraph = createFont("Georgia", 20);
		introFont = createFont("Georgia", 13);
		initLayout();
		buttonS = new ButtonBox((width-generalW)/2-generalW, submitS,100,35);
		buttonR = new ButtonBox((width-generalW)/2+generalW, submitS,100,35);
	}
	
	public void draw() {
		size(1300,750);
		background(color(20,20,20));
		//initLayout();
		textFont(titleFont);
		String title = "Smart Movie Finder";
		fill(255);
		text(title,(width-textWidth(title))/2, 100);
		
		//draw input boxes
		drawKey();
		for(TextBox tb: textBoxes) {
			tb.draw(applet);
		}
		buttonS.draw(applet);
		buttonR.setText("Reset");
		buttonR.draw(applet);
		//draw movie posters and introductions
		if(send==true) {
			drawMovies();
			drawIntro();
		}
	}
	
	public void drawIntro() {
		if (outputMovies.isEmpty() || outputMovies == null) {
		} else {
			int count = 1;
			for (Rating rating : outputMovies) {
				if (count == 5) {
					break;
				}
				String titleM =  MovieDatabase.getTitle(rating.getItem());
				String avg = "Rating: "+Double.toString(rating.getValue());
				String year = "Year: "+Integer.toString(MovieDatabase.getYear(rating.getItem()));
				String director = MovieDatabase.getDirector(rating.getItem());
				textFont(introFont);
				fill(255);
				//textAlign(LEFT,TOP);
				text(titleM, (width - 201) / 4 * count - 130, posterS+320);
				text(avg, (width - 201) / 4 * count - 130, posterS+350);
				text(year, (width - 201) / 4 * count - 130, posterS+380);
				text(director, (width - 201) / 4 * count - 130, posterS+410);
				count++;
			}
		}
	}
	
	public void drawMovies() {
		if (outputMovies.isEmpty() || outputMovies == null) {
			textFont(paragraph);
			String warning = "Please Input the Filters";
			fill(255);
			text(warning, (width - textWidth(warning)) / 2, 550);
		} else {
			int count = 1;
			for (Rating rating : outputMovies) {
				if (count == 5) {
					break;
				}
				String ap = MovieDatabase.getPoster(rating.getItem()).substring(7);
				System.out.println(ap);
				String url = "https://www.dukelearntoprogram.com//capstone/data/"
						+ ap;
				System.out.println(url);
				PImage post = loadImage(url, "jpg");
				if(post.width<1) {
					System.out.println("image is wrong");
				}
				post.resize(0, 300);
				image(post, (width - post.width) / 4 * count - 130, posterS);
				count++;
			}
		}
	}
	
	public void mousePressed() {
		for(TextBox tb: textBoxes) {
			tb.pressed(mouseX,mouseY);
		}
		if(buttonS.pressed(mouseX, mouseY)) {
			outputMovies = getMovieWithFilter();
			send=true;
		}
		if(buttonR.pressed(mouseX, mouseY)) {
			send=false;
			filter[0]=false;
			filter[1]=false;
			filter[2]=false;
			for(TextBox tb: textBoxes) {
				tb.setText("");
			}
		}
	}
	
	public void keyPressed() {
		int index=0;
		for(TextBox tb: textBoxes) {
			if(tb.keyPressed(key,keyCode,applet)) {
				filter[index] = true;
				input[index] = textBoxes.get(index).getText();
			}
			index++;
		}
	}
	
	public void drawKey() {
		textFont(paragraph);
		String filter1 = "Genre:";
		fill(255);
		textAlign(LEFT,TOP);
		text(filter1,(width-generalW)/7-80, filterS);
		
		String filter2 = "Year:";
		text(filter2,(width-generalW)/2-80, filterS);
		
		String filter3 = "Country:";
		text(filter3,(width-generalW)/7*6-100, filterS);	
	}
	
	private void initLayout() {
		TextBox box1 = new TextBox();
		box1.setW(150);box1.setH(35);
		generalW = box1.getW();
		box1.setX((width-box1.getW())/7); box1.setY(filterS);
		
		TextBox box2 = new TextBox();
		box2.setW(150);box2.setH(35);
		box2.setX((width-box2.getW())/2); box2.setY(filterS);
		
		TextBox box3 = new TextBox();
		box3.setW(150);box3.setH(35);
		box3.setX((width-box3.getW())/7*6); box3.setY(filterS);
		textBoxes.add(box1);
		textBoxes.add(box2);
		textBoxes.add(box3);
	}
	
	public ArrayList<Rating> getMovieWithFilter(){
		ThirdRatings sr = new ThirdRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        AllFilters allF = new AllFilters();
        if(filter[1]==true) {
        	Filter yearF = new YearAfterFilter(
        			Integer.parseInt(input[1].trim()));
        	allF.addFilter(yearF);
        }
        if(filter[0]==true) {
        	Filter genreF = new GenreFilter(
        			input[0].trim());
        	allF.addFilter(genreF);
        }
        if(filter[2]==true) {
        	Filter countryF = new CountryFilter(
        			input[2].trim());
        	allF.addFilter(countryF);
        }
        ArrayList<Rating> avgRating = sr.getAverageRatingsByFilter(5,allF);
        //System.out.println("Found "+ avgRating.size()+" movies:");
        Collections.sort(avgRating);
        /*
        for(Rating rating: avgRating){
            System.out.println(rating.getValue()+" "+MovieDatabase.getTitle(rating.getItem()));
        }
        */
        return avgRating;
    }
	
}