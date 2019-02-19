////////////////////////////////////////////////////////////////
// Copyright 2012, CompuScholar, Inc.
//
// This source code is for use by the students and teachers who 
// have purchased the corresponding TeenCoder or KidCoder product.
// It may not be transmitted to other parties for any reason
// without the written consent of CompuScholar, Inc.
// This source is provided as-is for educational purposes only.
// CompuScholar, Inc. makes no warranty and assumes
// no liability regarding the functionality of this program.
//
////////////////////////////////////////////////////////////////

// This class is provided as part of the activity starter.
// STUDENT SHOULD COMPLETE THE CODE AREAS MARKED BELOW!

/** TeenCoder: Java Programming
 * Chapter 15 - Game Pieces Project
 * This abstract class represents one of the game pieces in the 
 * Jail Break game.
 * @author CompuScholar, Inc.
 * @version 1.0
*/

import java.util.*;

public abstract class AbstractGamePiece {
	// All class members are provided as part of the activity starter!

	// These two constants define the Outlaws and Posse teams
	static public final int PLAYER_OUTLAWS = 0;
	static public final int PLAYER_POSSE = 1;

	// These variables hold the piece's column and row index
	protected int myCol;
	protected int myRow;

	// This variable indicates which team the piece belongs to
	protected int myPlayerType;

	// These two strings contain the piece's full name and first letter abbreviation
	protected String myAbbreviation;// +

	// +

	protected String myName;

	// All derived classes will need to implement this method
	abstract public boolean hasEscaped();

	// The student should complete this constructor by initializing the member
	// variables with the provided data.
	public AbstractGamePiece(String myName, String myAbbreviation, int myPlayerType) {
		this.myName = myName;
		this.myAbbreviation = myAbbreviation;
		this.myPlayerType = myPlayerType;
	}

	public AbstractGamePiece() {
		// TODO Auto-generated constructor stub
	}

	public int getPlayertype() {
		return myPlayerType;
	}

	public void setPosition(int col, int row) {
		this.myCol = col;
		this.myRow = row;
	}

	public int getCol() {
		return myCol;
	}

	public int getrow() {
		return myRow;
	}

	public String getAbbreviation() {
		return myAbbreviation;
	}

	public String toString(String name, String playerType, int myCol, int myRow) {
		String msg = "";

		return msg;
	}

	public boolean canMoveTOLocation(List<GameSquare> path) {
		path.equals(null);
		
		return false; 
	}

	public boolean isCaptured(GameBoard gameboard) {
		return false;
	}

}
