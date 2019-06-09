import org.junit.Test;

import static org.junit.Assert.*;

public class DriverTest {

    @Test
    public void greetTest() {
        Driver driver = new Driver();

        driver.greet();

        driver.getCoords();
    }

    @Test
    public void tourTest() {
        Driver driver = new Driver();

        Coordinate start = new Coordinate(6,0);

        driver.knightsTour(start);

        for(int i = 0;i<64;i++) {
            System.out.println(i + "\n");
            driver.boardStates.stack[i].printBoard();
            System.out.println("-----------------------------");
        }
    }

    @Test
    public void tourTestAgain() {
        Driver driver = new Driver();

        Coordinate start = new Coordinate(6,0);

        driver.knightsTour(start).printBoard();
    }


    public static void main(String args[]) {
        Driver driver = new Driver();

        driver.greet();
        driver.getCoords();
    }
}