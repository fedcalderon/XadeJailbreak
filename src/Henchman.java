
public class Henchman extends AbstractGamePiece {

	public Henchman(String myName, String myAbbreviation, int myPlayerType) {

		super(myName, myAbbreviation, myPlayerType);

	}
	protected boolean isSquareRestricted(GameSquare step) {
		GameSquare.getType();
	   (GameSquare.TYPE_CAMP);
	   return true;
	}

	public boolean hasEscaped() {

		return false;

	}

}