
public class Kingpin extends AbstractGamePiece {

	public Kingpin(String myName, String myAbbreviation, int myPlayerType) {
		super(myName, myAbbreviation, myPlayerType);
	}

	public boolean hasEscaped() {
		return true;
	}

	@Override
	public boolean isCaptured(GameBoard gameboard) {
		return true;
	}

}
