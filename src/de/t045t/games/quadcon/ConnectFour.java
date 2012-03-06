package de.t045t.games.quadcon;

/**
* This class implements the necessary game logic to play "Connect 4", using a {@see Board} and two
* {@see Player}s.
*/
public class ConnectFour {
	private Player player1;
	private Player player2;
	private Board field;
	
	/**
	* Constructs a new instance of {@code ConnectFour} using the given values. Will throw an 
	* {@code IllegalArgumentException} if the field is too small for either player to be able
	* to actually put four game pieces in a row on it.
	*
	* @param player1 the first player
	* @param player2 the second player
	* @param rows the number of rows for the {@code Board}
	* @param cols the number of columns for the {@code Board}
	* @throws IllegalArgumentException
	*/
	public ConnectFour(Player player1, Player player2, int rows, int cols) {
		this.player1 = player1;
		this.player2 = player2;
		if ((rows > 3 && cols > 1) || (cols > 3 && rows > 1)) {
			this.field = new Board(rows, cols);
		}
		else {
			throw new IllegalArgumentException("Error! There's not enough room on this field ("
				+ rows + " high and " + cols + " wide) to play Connect Four.");
		}
	}
	
	/**
	* Puts a game piece into the given column. A piece will "fall down" through empty spaces 
	* in that column
	* until there is another game piece beneath it.
	*
	* @param col the column to drop a game piece in - cannot be larger than the number of columns
	* on this field
	* @param player {@code 1} for player 1, {@code 2} for player 2
	* @return {@code 0} if the game continues, {@code 1} if the first player won,
	* {@code 2} if the second player won and {@code -1} if the move could not be completed.
	* @throws IllegalArgumentException
	*/
	public int put(int col, Player player) {
		assert (player.equals(player1) || player.equals(player2)) : ("Illegal player " + player);
		if (col < field.getCols() && col >= 0) {
			int freeSpot = -1;
			for (int i = 0; i < field.getRows(); i++) {
				freeSpot = field.get(i, col) == 0 ? i : freeSpot;
			}
			if (freeSpot == -1) {
				throw new IllegalArgumentException("Column " + (col + 1) + " is already full.");
			}
			else {
				int playerID = 0;
				if (player.equals(player1)) {
					playerID = 1;
				}
				else if (player.equals(player2)) {
					playerID = 2;
				}
				field.set(freeSpot, col, playerID);
				return checkWinFrom(freeSpot, col);
			}
		}
		else {
			throw new IllegalArgumentException("Column " + (col + 1) + " is not on the "
				+ "Board.");
		}
	}
	
	/**
	* Draws the field using the two players' current colors
	*/
	public void draw() {
		System.out.print("-");
		for (int j = 0; j < field.getCols(); j++) {
			System.out.print(" - -");
		}
		for (int i = 0; i < field.getRows(); i++) {
			System.out.println();
			System.out.print("|");
			for (int j = 0; j < field.getCols(); j++) {
				System.out.print(" " + getFieldAsChar(i, j) + " |");
			}
			System.out.println();
			System.out.print("-");
			for (int k = 0; k < field.getCols(); k++) {
				System.out.print(" - -");
			}
		}
		System.out.println();
	}
	
	/**
	* Reads the value of the given field on the board and converts it to the corresponding 
	* {@see Player}'s color, or a space if the field is empty.
	*
	* @param row the row of the desired board field
	* @param col the column of the desired board field
	* @return the character representing the corresponding {@code Player}'s color, or a 
	* space if the field is empty
	* @throws UnsupportedOperationException
	*/
	public char getFieldAsChar(int row, int col) {
		int value = field.get(row, col);
		switch (value) {
			case -1:
			case 0:
				return ' ';
			case 1:
				return player1.getColor();
			case 2:
				return player2.getColor();
			default:
				throw new UnsupportedOperationException("Cannot assign board value '" + value 
					+ "' to a player");
		}
	}
	/**
	* Private helper method to check whether anyone has won yet. Checks if the specified
	* game piece is part of a row of length 4 in any direction.
	*
	* @param row the row of the game piece to check
	* @param col the column of the game piece to check
	* @return {@code 1} if the first player wins, {@code 2 if the second player wins,
	* {@code 0} if nobody wins
	*/
	private int checkWinFrom(int row, int col) {
		int rowLength = 0;
		/* Diagonal Check 1 */
		for (int i = -3; i < 4; i++) {
			if (field.get(row + i, col + i) == field.get(row, col)) {
				rowLength++;
				if (rowLength == 4) {
					return field.get(row, col);
				}
			}
			else {
				rowLength = 0;
			}
		}
		/* Diagonal Check 2 */
		for (int i = -3; i < 4; i++) {
			if (field.get(row + i, col - i) == field.get(row, col)) {
				rowLength++;
				if (rowLength == 4) {
					return field.get(row, col);
				}
			}
			else {
				rowLength = 0;
			}
		}
		/* vertical check */
		for (int i = -3; i < 4; i++) {
			if (field.get(row + i, col) == field.get(row, col)) {
				rowLength++;
				if (rowLength == 4) {
					return field.get(row, col);
				}
			}
			else {
				rowLength = 0;
			}
		}
		/* horizontal check */
		for (int i = -3; i < 4; i++) {
			if (field.get(row, col + i) == field.get(row, col)) {
				rowLength++;
				if (rowLength == 4) {
					return field.get(row, col);
				}
			}
			else {
				rowLength = 0;
			}
		}
		// If there are no rows of 4:
		return 0;		
	}
}