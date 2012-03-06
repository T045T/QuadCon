package de.t045t.games.quadcon;

/**
* Represents a {@code Player}, with a name and 'color' (i.e. a single {@code char}).
*/
public class Player {
	private char color;
	private String name;
	
	/**
	* Constructs a {@code Player} with the given name and 'color'.
	*
	* @param name the {@code Player}'s name
	* @param color the {@code Player}'s color
	*/
	public Player(String name, char color) {
		this.name = name;
		this.color = color;
	}
	
	/**
	* Changes the {@code Player}'s color.
	*
	* @param color the new color
	*/
	public void setColor(char color) {
		this.color = color;
	}
	
	/**
	* Changes the {@code Player}'s name.
	*
	* @param name the new name
	*/
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	* Returns the {@code Player}'s color.
	*
	* @return the {@code Player}'s color
	*/
	public char getColor() {
		return this.color;
	}
	
	/**
	* Returns the {@code Player}'s name.
	*
	* @return the {@code Player}'s name
	*/
	public String getName() {
		return this.name;
	}
	
	/**
	* Checks if the {@code Player} given as a parameter is equal to this one.
	*
	* @param player the {@code Player} to check for equality
	* @return {@code true} if {@code player} has the same name and color as this {@code Player}
	*/
	public boolean equals(Player player) {
		if (player.name == this.name && player.color == this.color) {
			return true;
		}
		else {
			return false;
		}
	}
}