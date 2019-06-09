import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Driver {

    LinkList<Coordinate> initialCoords = new LinkList<>();
    Stack boardStates = new Stack(64);

    public void greet() {
        System.out.println("♘ Hello and welcome to the Knight's Tour. ♘\n" +
                "Please keep all hands, feet, and chess pieces inside the\n" +
                "car at all times and save any questions until the end.\n\n" +
                "Enter the coordinates of the square you'd like to start the\n" +
                "Knight's Tour from below.\n");
    }

    public void getCoords() {
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("x-value: ");
            int xPos = input.nextInt();
            System.out.print("y-value: ");
            int yPos = input.nextInt();

            initialCoords.append(new Coordinate(xPos, yPos));

                try {
                    System.out.println("Would you like to enter the starting coordinate for another tour? (Y/N):");
                    String response = input.next();
                    if(response.equals("Y") || response.equals("y")) {
                        this.getCoords();
                    } else if(response.equals("N") || response.equals("n")) {
                        System.out.println("Very well.");
                        this.confirm();
                    } else {
                        System.out.println("That is not a valid input, please start over");
                        // Since getCoords() being re-called, get rid of the data that was already submitted
                        initialCoords.delete(initialCoords.getLength()-1);
                        this.getCoords();
                    }
                } catch(InputMismatchException e) {
                    System.out.println("That is not a valid input, please start over");
                    // Since getCoords() being re-called, get rid of the data that was already submitted
                    initialCoords.delete(initialCoords.getLength()-1);
                    this.getCoords();
                }
        } catch(InputMismatchException e) {
            System.out.println("That is not a valid input, please enter an integer.");
            this.getCoords();
        }
    }

    public void confirm() {
        Scanner input = new Scanner(System.in);

        System.out.println("Please confirm these are the starting positions that you\n" +
                "would like to start the Knight's Tour from. Specify whether you would like \n" +
                "to (1) Add (2) Delete or (3) Modify any of the entries from this list, or (4) Continue\n");

        initialCoords.printList();

        for(int i = 0;i < initialCoords.getLength(); i++){
            System.out.println((i + 1) + ". (" + initialCoords.get(i).getData().getPos('x') +
                    ", " + initialCoords.get(i).getData().getPos('y') + ")\n");
        }


        String response = input.next();

        try {
            // At this point I've had it with the try/catch blocks, please just enter valid inputs <3
            if(response.equals("1")) {
                this.getCoords();
            } else if(response.equals("2")) {
                System.out.println("Which number entry would you like to delete?");
                int deletedEntry = input.nextInt();
                initialCoords.delete(deletedEntry-1);
                this.confirm();
            } else if(response.equals("3")) {
                System.out.println("Which number entry would you like to modify?");
                int modifiedEntry = input.nextInt();
                System.out.print("Please specify the new x-value you would like:");
                int newX = input.nextInt();
                System.out.print("Please specify the new y-value you would like:");
                int newY = input.nextInt();
                initialCoords.get(modifiedEntry - 1).getData().setPos('x',newX);
                initialCoords.get(modifiedEntry - 1).getData().setPos('y',newY);
                this.confirm();
            } else if(response.equals("4")) {
                int i = 0;
                while(i < initialCoords.getLength()) {
                    this.knightsTour(initialCoords.get(i).getData()).printBoard();
                    i++;
                }
            } else {
                System.out.println("That is not a valid input, please start over.");
                this.confirm();
            }
        } catch(InputMismatchException e) {
            System.out.println("That is not a valid input, please start over.");
            this.confirm();
        }
    }

    public Board knightsTour(Coordinate start) {
        int moves = 0;
        Coordinate deadSpace = new Coordinate(-1,-1);
        Coordinate position = start;
        // Create empty board
        Board board = new Board();
        // Set the initial position on said board to 0
        board.set(position, moves++);

        try {
            boardStates.push(board);

            while (!board.isComplete()) {
                if (moves < 64) {
                    position = board.nextMove(position);
                    board = new Board(board, position, moves++);
                    boardStates.push(board);

                    if (board.isDead()) {
                        // Reset potential old deadSpace
                        board.set(deadSpace, -1);
                        // Set current space to 65 so that Board.scan() sees it as occupied and
                        // the knight doesn't go back to a dead square
                        deadSpace = board.currentPosition;
                        //System.out.println(deadSpace.xPos + "," + deadSpace.yPos);

                        // Backtrack through the stack
                        boardStates.pop();
                        board = boardStates.peek();
                        board.set(deadSpace, 65);
                        moves--;
                    }
                }
            }
        } catch(RuntimeException e){
            return(boardStates.peek());
        }

        return(boardStates.peek());
    }

    public static void main(String args[]) {
        Driver driver = new Driver();

        driver.greet();
        driver.getCoords();
    }
}
