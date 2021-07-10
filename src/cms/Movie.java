package cms;

import java.io.Serializable;

public class Movie implements Serializable {

	public String movie_id;
	public String movie_name;
	public String language;
	public String genre;

	public Movie(String id, String name, String language, String genre) {
		this.movie_id = id;
		this.movie_name = name;
		this.language = language;
		this.genre = genre;
	}

	public Movie(Movie movieObjFromClient) {
		this.movie_id = movieObjFromClient.movie_id;
		this.movie_name = movieObjFromClient.movie_name;
		this.language = movieObjFromClient.language;
		this.genre = movieObjFromClient.genre;
	}
}
