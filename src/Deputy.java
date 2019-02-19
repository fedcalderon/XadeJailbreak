
public class Deputy extends AbstractGamePiece {

	public Deputy(String myName, String myAbbreviation, int myPlayerType) {

		super(myName, myAbbreviation, myPlayerType);

	}
	protected boolean isSquareRestricted(GameSquare step) {
		GameSquare.getType();
//	    GameSquare.TYPE_JAIL;
	    return true;
	}

	public boolean hasEscaped() {
		return false;
	}

}