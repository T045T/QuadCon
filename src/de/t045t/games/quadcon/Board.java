package de.t045t.games.quadcon;

/**
* This class represents an arbitrary board, handled internally as a matrix of {@code Integer} values.
* By assigning those values meaning in other classes, it can be used for many different things, like
* for example most board games.
*/
public class Board {
	private int[][] fieldMatrix;
	
	/**
	* Creates a new universal {@code Board} of the given size. One of the sides can have 
	* the length {@code 0}, but not both. Will throw an Exception only if you try to create
	* a field with both sides of length {@code 0} or less.
	*
	* @param rows the number of rows
	* @param cols the number of columns
	* @throws IllegalArgumentException
	*/
	public Board(int rows, int cols) {
		if (rows >= 0 || cols >= 0) {
			fieldMatrix = new int[rows][cols];
		}
		else {
			throw new IllegalArgumentException("0x0 Fields are not allowed." + cols + rows);
		}
	}
	
	/**
	* Returns the contents of the {@code Board} at the given coordinates.
	*
	* @param row the row of the desired field
	* @param col the column of the desired field
	* @return the value of the given field
	*/
	public int get(int row, int col) {
		if (row < fieldMatrix.length && row >= 0 && col < fieldMatrix[0].length && col >= 0) {
			return fieldMatrix[row][col];
		}
		else {
			return -1;
		}
	}
	
	/**
	* Returns the number of rows on this {@code Board}.
	*
	* @return the number of rows on this {@code Board}
	*/
	public int getRows() {
		return fieldMatrix.length;
	}

	/**
	* Returns the number of columns on this {@code Board}.
	*
	* @return the number of columns on this {@code Board}
	*/	
	public int getCols() {
		return fieldMatrix[0].length;
	}
	
	/**
	* Sets the {@code value} of the internal matrix at {(@code row, col)}. Will leave matrix 
	* unchanged
	* if one or both indices are out of range.
	*
	* @param row the row of the matrix item to be changed
	* @param col the column of the matrix item to be changed
	* @param val the value to change to
	*/
	public void set(int row, int col, int val) {
		if (row < fieldMatrix.length && col < fieldMatrix[0].length) {
			fieldMatrix[row][col] = val;
		}
		else {
			return;
		}
	}
}