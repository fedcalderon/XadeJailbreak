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

// This class is provided partially complete as part of the activity starter.
// STUDENT SHOULD COMPLETE THE CODE AREAS MARKED BELOW!

/** TeenCoder: Java Programming
 * Chapter 16 - Jail Break Project
 * This class contains the main game logic for the 
 * Jail Break game.
 * @author CompuScholar, Inc.
 * @version 1.0
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

// The JailBreak class implements ActionListener to receive button click events
public class JailBreak implements ActionListener {
	// Declare class member variables to keep track of the UI controls on the JFrame
	private JFrame myFrame = null;
	private JPanel mainPanel = null;
	private JLabel playerTurnLabel = null;
	private JButton resetButton = null;

	// This member will contain the main game board object
	private GameBoard gameBoard = null;

	// This member will track which square (if any) is currently selected
	private GameSquare selectedSquare = null;

	// This flag will be set to true when the game is over, or should be false
	// otherwise
	private boolean gameOver = false;

	// AbstractGamePiece.PLAYER_OUTLAWS or AbstractGamePiece.PLAYER_POSSE
	private int currentPlayerTurn;

	// This member will hold a reference to the kingpin so we can check it later
	private AbstractGamePiece kingpinPiece;

	private static int clickCounter = 0;
	private static int col = 0;
	private static int row = 0;
	private static String clickedAbreviation = "";
	private boolean isSelected = false;

	// This method is provided as part of the activity starter. No student
	// modification is needed.
	// The main method just creates a new JailBreak object to run the game
	public static void main(String[] args) {
		new JailBreak();
	}

	// This method is provided as part of the activity starter. No student
	// modification is needed.
	// The JailBreak constructor will build all of the GUI elements, initialize the
	// game board,
	// and reset the game to its initial state
	public JailBreak() {
		kingpinPiece = new AbstractGamePiece("King", "K", 0) {

			@Override
			public boolean hasEscaped() {
				// TODO Auto-generated method stub
				return false;
			}
		};

		// Create new JFrame and set the title.
		myFrame = new JFrame();
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setTitle("Jail Break!");
		myFrame.setResizable(false); // don't let the user resize or board

		// The main panel will contain a vertical box layout
		mainPanel = (JPanel) myFrame.getContentPane();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		// The top panel just contains the player turn label
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		playerTurnLabel = new JLabel();
		topPanel.add(playerTurnLabel);
		mainPanel.add(topPanel);

		// The middle panel contains the game board. The GameBoard constructor
		// will build out all of the panel details, including all of the squares!
		JPanel boardPanel = new JPanel();
		gameBoard = new GameBoard(boardPanel, this);
		mainPanel.add(boardPanel);

		// The bottom panel will contain the reset button.
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		resetButton = new JButton("Reset");
		resetButton.addActionListener(this);
		bottomPanel.add(resetButton);
		mainPanel.add(bottomPanel);

		selectedSquare = new GameSquare(0, 0, topPanel, null);

		// reset the game to it's initial state
		reset();

		// we're ready, so pack and show the screen!
		myFrame.pack();
		myFrame.setVisible(true);
	}

	// This method is defined but not implemented as part of the activity starter.
	// This method is called when the game is first created, and any
	// time you want to restart a new game.
	// STUDENT SHOULD COMPLETE THIS ENTIRE METHOD!
	private void reset() {
		gameOver = false;
		gameBoard.reset();
		Kingpin K = new Kingpin("Kingpin", "K", 1);
		kingpinPiece = K;
		gameBoard.setPiece(4, 4, kingpinPiece);
		gameBoard.setPiece(4, 3, new Henchman("Henchman", "H", 0));
		gameBoard.setPiece(4, 2, new Henchman("Henchman", "H", 0));
		gameBoard.setPiece(4, 5, new Henchman("Henchman", "H", 0));
		gameBoard.setPiece(4, 6, new Henchman("Henchman", "H", 0));
		gameBoard.setPiece(3, 4, new Henchman("Henchman", "H", 0));
		gameBoard.setPiece(2, 4, new Henchman("Henchman", "H", 0));
		gameBoard.setPiece(5, 4, new Henchman("Henchman", "H", 0));
		gameBoard.setPiece(6, 4, new Henchman("Henchman", "H", 0));

		gameBoard.setPiece(0, 3, new Deputy("Deputy", "D", 1));
		gameBoard.setPiece(0, 4, new Deputy("Deputy", "D", 1));
		gameBoard.setPiece(0, 5, new Deputy("Deputy", "D", 1));
		gameBoard.setPiece(1, 4, new Deputy("Deputy", "D", 1));
		gameBoard.setPiece(3, 0, new Deputy("Deputy", "D", 1));
		gameBoard.setPiece(4, 0, new Deputy("Deputy", "D", 1));
		gameBoard.setPiece(5, 0, new Deputy("Deputy", "D", 1));
		gameBoard.setPiece(4, 1, new Deputy("Deputy", "D", 1));
		gameBoard.setPiece(8, 3, new Deputy("Deputy", "D", 1));
		gameBoard.setPiece(8, 4, new Deputy("Deputy", "D", 1));
		gameBoard.setPiece(8, 5, new Deputy("Deputy", "D", 1));
		gameBoard.setPiece(7, 4, new Deputy("Deputy", "D", 1));
		gameBoard.setPiece(4, 7, new Deputy("Deputy", "D", 1));
		gameBoard.setPiece(3, 8, new Deputy("Deputy", "D", 1));
		gameBoard.setPiece(4, 8, new Deputy("Deputy", "D", 1));
		gameBoard.setPiece(5, 8, new Deputy("Deputy", "D", 1));

		currentPlayerTurn = AbstractGamePiece.PLAYER_OUTLAWS;
	}

	// This method is provided as part of the activity starter. No student
	// modification is needed.
	// switch the current player's turn
	private void changePlayerTurn() {
		if (currentPlayerTurn == AbstractGamePiece.PLAYER_OUTLAWS)
			currentPlayerTurn = AbstractGamePiece.PLAYER_POSSE;
		else
			currentPlayerTurn = AbstractGamePiece.PLAYER_OUTLAWS;
	}

	// This method is provided as part of the activity starter. No student
	// modification is needed.
	// change the playerTurnLabel to reflect the current player's turn
	private void setPlayerTurnLabel() {
		if (currentPlayerTurn == AbstractGamePiece.PLAYER_OUTLAWS)
			playerTurnLabel.setText("Player Turn: Outlaws!");
		else
			playerTurnLabel.setText("Player Turn: Posse!");
	}

	// This method is provided as part of the activity starter. No student
	// modification is needed.
	// Implement the ActionListener interface. Button clicks from the game board
	// buttons will be received by this method.
	public void actionPerformed(ActionEvent source) {
		// if the reset button was clicked
		if (source.getSource() == resetButton) {
			reset(); // reset the game
		} else if (!gameOver) // else if the game is not over
		{
			// figure out which square, if any, on the game board was clicked
			GameSquare clickedSquare = gameBoard.getClickedSquare(source.getSource());
			if (clickedSquare != null) {
				// pass the clicked square to the main game logic function
				handleClickedSquare(clickedSquare, kingpinPiece);

				// update the current player's turn in case it was changed by the game logic
				setPlayerTurnLabel();
			}

		}
	}

	// This method is defined but not implemented as part of the activity starter.
	// If this method is called, the clickedSqure parameter will not be null!
	// STUDENT SHOULD COMPLETE THIS ENTIRE METHOD!
	private void handleClickedSquare(GameSquare clickedSquare, AbstractGamePiece kingpinPiece) {
		if (clickedSquare.getPiece() != null) {
			// For debugging
			System.out.println(String.format("You clicked [%d,%d] = %s", 
					clickedSquare.getCol(), 
					clickedSquare.getRow(),
					gameBoard.getPiece(clickedSquare.getCol(), clickedSquare.getRow()).myAbbreviation));

			// First Case
			if (clickCounter == 0) {
				clickedSquare.select();
				isSelected = true;
				col = clickedSquare.getCol();
				row = clickedSquare.getRow();
				clickedAbreviation = clickedSquare.getPiece().getAbbreviation();
				kingpinPiece.setPosition(col, row);
				kingpinPiece.myAbbreviation = clickedAbreviation;
				clickCounter++;
			}
			// Second case
			else if (clickCounter > 0) {
				if (col == clickedSquare.getCol() && row == clickedSquare.getRow()) {
					clickedSquare.deselect();
					isSelected = false;
					clickCounter = 0;
					// Third Case
				} else {
					clickedSquare.select();
					col = clickedSquare.getCol();
					row = clickedSquare.getRow();
					clickedAbreviation = clickedSquare.getPiece().getAbbreviation();
					kingpinPiece.setPosition(col, row);
					kingpinPiece.myAbbreviation = clickedAbreviation;
					clickCounter++;
				}
			}
		}
		else {
			kingpinPiece = selectedSquare.getPiece();
			List<GameSquare> path = gameBoard.buildPath(selectedSquare, clickedSquare);
//			 kingpinPiece.canMoveToLocation(List<GameSquare> path);
			 clickedSquare.setPiece(kingpinPiece);
			 selectedSquare.clearSquare();
			 selectedSquare.deselect();
			 selectedSquare = null;
			 changePlayerTurn();
			
		}
	}

	// This method is provided as part of the activity starter. No student
	// modification is needed.
	// This method will return a list of captured opponents if a piece has moved to
	// the indicated column and row.
	private List<AbstractGamePiece> findCapturedOpponents(int col, int row) {
		// initialize an ArrayList that will hold any captured opponents
		ArrayList<AbstractGamePiece> capturedOpponents = new ArrayList<AbstractGamePiece>();

		// we are going to check each square up/down/left/right to see if there is a
		// piece present.
		// If so, let the piece itself determine if it has been captured or not!
		AbstractGamePiece nearbyPiece = null;

		// get above piece
		nearbyPiece = gameBoard.getPiece(col, row - 1);
		// if this piece exists and has been captured
		if ((nearbyPiece != null) && (nearbyPiece.isCaptured(gameBoard))) {
			capturedOpponents.add(nearbyPiece); // add captured piece to the list
		}

		// get below piece
		nearbyPiece = gameBoard.getPiece(col, row + 1);
		// if this piece exists and has been captured
		if ((nearbyPiece != null) && (nearbyPiece.isCaptured(gameBoard))) {
			capturedOpponents.add(nearbyPiece); // add captured piece to the list
		}

		// get left piece+
		nearbyPiece = gameBoard.getPiece(col - 1, row);
		// if this piece exists and has been captured
		if ((nearbyPiece != null) && (nearbyPiece.isCaptured(gameBoard))) {
			capturedOpponents.add(nearbyPiece); // add captured piece to the list
		}

		// get right piece
		nearbyPiece = gameBoard.getPiece(col + 1, row);
		// if this piece exists and has been captured
		if ((nearbyPiece != null) && (nearbyPiece.isCaptured(gameBoard))) {
			capturedOpponents.add(nearbyPiece); // add captured piece to the list
		}

		// return the list of captured opponents (may be empty!)
		return capturedOpponents;
	}
}
