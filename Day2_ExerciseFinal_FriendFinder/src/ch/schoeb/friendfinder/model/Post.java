package ch.schoeb.friendfinder.model;

public class Post {

	public Post(int id, String name, String comment, double longitude, double latitude)
	{
		this.id = id;
		this.name = name;
		this.comment = comment;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public int id;
	public String name;
	public String comment;
	public double longitude;
	public double latitude;
	
}
