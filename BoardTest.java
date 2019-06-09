import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {
    @Test
    public void setTest() throws Exception {
        Board board = new Board();

        board.set(new Coordinate(0,0),0);

        board.printBoard();
    }

    @Test
    public void scanTest() throws Exception {
        Board board = new Board();

        Coordinate position = new Coordinate(0,0);

        System.out.println(board.scan(position));
    }

    @Test
    public void printBoard() throws Exception {
        Board board = new Board();

        board.set(new Coordinate(5,0), 0);

        board.printBoard();
    }

    @Test
    public void testScan() {
        Board board = new Board();

        Coordinate position = new Coordinate(6,0);

        printMoves(board, position);
    }

    @Test
    public void scanTestAgain() {
        Board board = new Board();

        Coordinate position = new Coordinate(3,3);
        printMoves(board, position);
    }

    private void printMoves(Board board, Coordinate position) {
        Coordinate[] nextMove = board.scan(position);

        for(Coordinate i : nextMove) {
            System.out.println("("+i.getPos('x')+", " + i.getPos('y') + ")");
        }
    }

    @Test
    public void testBest() {
        Board board = new Board();
        //board.set(new Coordinate(3,1),0);

        board.printBoard();

        Coordinate position = new Coordinate(7,7);
        Coordinate nextMove = board.nextMove(position);

        System.out.println("("+nextMove.getPos('x')+", " + nextMove.getPos('y') + ")");
    }

    @Test
    public void currentBoardTest() {
        Board board = new Board();
        Coordinate position = new Coordinate(2,3);

        Board newBoard = new Board(board,position,0);

        board.printBoard();
        System.out.println("---------------");
        newBoard.printBoard();
        System.out.println("(("+newBoard.currentPosition.xPos+"," + newBoard.currentPosition.yPos+"))");
    }

    @Test
    public void isDeadTest() {
        Board board = new Board();
        board.set(new Coordinate(5,1),0);
        board.set(new Coordinate(6,2),1);

        board.currentPosition = new Coordinate(7,0);

        System.out.println(board.isDead());
    }

}