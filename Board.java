public class Board {

    private int[][] board;
    int moves = 0;

    // Element at each position in both arrays corresponds with a change in
    // that given direction for each axis, for instance the first elements
    // in each, [-2] and [1], refer to moving the night 2 to the left and 1 up
    int [] moveX = {-2,-1,1,2,2,1,-1,-2};
    int [] moveY = {1,2,2,1,-1,-2,-2,-1};

    Coordinate currentPosition;

    // Constructor creates board of -1's symbolizing no knight moves
    public Board() {
        // Initializes 8x8 array
        this.board = new int[8][8];

        // Populates said array with -1's
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                this.board[i][j] = -1;
            }
        }
    }

    // Overloaded constructor creates new board based on an old board
    public Board(Board oldBoard, Coordinate position, int value) {

        this.board = new int[8][8];
        for(int i = 0; i < 8; i++) {
            this.board[i] = oldBoard.board[i].clone();
        }

        this.currentPosition = position;

        this.set(position, value);
    }

    public void set(Coordinate position, int value) {
        try {
            this.board[position.getPos('x')][position.getPos('y')] = value;
        } catch(ArrayIndexOutOfBoundsException e) {
            // Pass when Driver.knightsTour() will try to set [-1,-1] to -1
        }
    }

    public int get(Coordinate position) {
        return (this.board[position.getPos('x')][position.getPos('y')]);
    }

    // Returns array of positions accessible from given position
    public Coordinate[] scan(Coordinate position) {

        // Stores valid moves from given position, (-1,-1) indicates invalid move
        Coordinate[] positions = new Coordinate[8];

        for(int i = 0; i < 8; i++){
            try {
                // Adds empty position to array
                if (this.board[position.getPos('x') + moveX[i]][position.getPos('y') + moveY[i]] == -1) {
                    positions[i] = new Coordinate(position.getPos('x') +moveX[i],position.getPos('y') + moveY[i]);
                // If position is taken, don't count it
                } else {
                    positions[i] = new Coordinate(-1,-1);
                }
            // If position is out of bounds, don't count it
            } catch(ArrayIndexOutOfBoundsException e) {
                positions[i] = new Coordinate(-1,-1);
            }
        }

        return(positions);
    }

    // Utilizes scan() to determine the best position to transition to based
    // on Warnsdoff's rule, at some Coordinate 'position'
    public Coordinate nextMove(Coordinate position){
        Coordinate[] positions = this.scan(position);
        // By default set bestMove to an invalid coord, if there is a valid move then it will
        // be overwritten, if not then it is a signal that the knight has run out of valid moves
        Coordinate bestMove = new Coordinate(-1,-1);

        int movesOfBest = 8;

        // Iterate through the possible positions to move to from 'position'
        for(int i = 0; i < 8; i++) {
            // Ensure that the point is not an invalid move (-1,-1)
            if (positions[i].xPos != -1) {
                int moves = 0;
                // If a potential move from that possible position is a free space, iterate moves
                Coordinate[] potentialMoves = this.scan(positions[i]);
                for (Coordinate j : potentialMoves) {
                    try {
                        if (this.get(j) == -1) {
                            moves++;
                        }
                    } catch(ArrayIndexOutOfBoundsException e) {
                        // Ignore the point if it is out of bounds
                    }
                }

                // Compare the amount of moves from this possible position i with that of
                // the current "best move" and if it has fewer possible moves then make
                // it the new "best move"
                if (moves < movesOfBest) {
                    bestMove = positions[i];
                    movesOfBest = moves;
                }
            }
        }
        return(bestMove);
    }

    public void printBoard() {
        for(int i = 7; i >= 0; i--) {
            System.out.print("|");
            for(int j = 0; j <= 7; j++) {
                System.out.printf("%3s", this.get(new Coordinate(j,i)) + "|");
            }
            System.out.print("\n");
        }
    }

    public boolean isDead() {
        int deadSpots = 0;
        Coordinate[] potentialPositions = this.scan(currentPosition);

        for(Coordinate coord : potentialPositions) {
            if(coord.xPos == -1) {
                deadSpots++;
            }
        }

        if(deadSpots == 9) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isComplete() {
        int unvisited = 0;

        for(int[] x : this.board) {
            for(int y : x) {
                if(y == -1) {
                    unvisited++;
                }
            }
        }

        if(unvisited == 0) {
            return(true);
        } else {
            return false;
        }
    }
}
