package org.example.model;

public class Greeting {

	public int getId() {
		return id;
	}

	public void setId(int nextInt) {
		this.id = nextInt;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	private int id;
	
	private String text;

	public Greeting(int id, String text) {
		super();
		this.id = id;
		this.text = text;
	}
	
	public Greeting() {
		super();
	}
}
