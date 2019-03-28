package game.enums;

public enum GameObjectType {
	BACKGROUND(0),
	PLAYER(1),
	ENEMY(2),
	GOAL(3),
	WALL(4),
	DOOR(5);
	
	private int numVal;

	GameObjectType(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
}
