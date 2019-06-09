import java.io.IOException;

public class Coordinate {

    int xPos;
    int yPos;

    // Coordinate can be created and have values assigned later
    public Coordinate() {

    }

    public Coordinate(int xPos,int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getPos(char axis) {
        if(axis == 'x') {
            return this.xPos;
        } else {
            return this.yPos;
        }
    }

    public void setPos(char axis, int position) {
        if(axis == 'x') {
            this.xPos = position;
        } else {
            this.yPos = position;
        }
    }
}
