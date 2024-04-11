import java.util.Scanner;

public class ConnectFour {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private static final char EMPTY = '-';
    private static char[][] board = new char[ROWS][COLS];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        initializeBoard();
        printBoard();
        Scanner scanner = new Scanner(System.in);

        boolean gameFinished = false;
        while (!gameFinished) {
            System.out.println("Player " + currentPlayer + "'s turn. Enter column (1-7): ");
            int column = scanner.nextInt() - 1;
            if (isValidMove(column)) {
                dropPiece(column);
                printBoard();
                if (checkWin()) {
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameFinished = true;
                } else if (isBoardFull()) {
                    System.out.println("It's a draw!");
                    gameFinished = true;
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            } else {
                System.out.println("Invalid move! Please choose another column.");
            }
        }
        scanner.close();
    }

    private static void initializeBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = EMPTY;
            }
        }
    }

    private static void printBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println("1 2 3 4 5 6 7");
    }

    private static boolean isValidMove(int col) {
        return col >= 0 && col < COLS && board[0][col] == EMPTY;
    }

    private static void dropPiece(int col) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][col] == EMPTY) {
                board[row][col] = currentPlayer;
                break;
            }
        }
    }

    private static boolean checkWin() {
       
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS - 3; col++) {
                if (board[row][col] == currentPlayer &&
                    board[row][col+1] == currentPlayer &&
                    board[row][col+2] == currentPlayer &&
                    board[row][col+3] == currentPlayer) {
                    return true;
                }
            }
        }

        for (int row = 0; row < ROWS - 3; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col] == currentPlayer &&
                    board[row+1][col] == currentPlayer &&
                    board[row+2][col] == currentPlayer &&
                    board[row+3][col] == currentPlayer) {
                    return true;
                }
            }
        }
       
        for (int row = 0; row < ROWS - 3; row++) {
            for (int col = 0; col < COLS - 3; col++) {
                if (board[row][col] == currentPlayer &&
                    board[row+1][col+1] == currentPlayer &&
                    board[row+2][col+2] == currentPlayer &&
                    board[row+3][col+3] == currentPlayer) {
                    return true;
                }
            }
        }
      
        for (int row = 3; row < ROWS; row++) {
            for (int col = 0; col < COLS - 3; col++) {
                if (board[row][col] == currentPlayer &&
                    board[row-1][col+1] == currentPlayer &&
                    board[row-2][col+2] == currentPlayer &&
                    board[row-3][col+3] == currentPlayer) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isBoardFull() {
        for (int col = 0; col < COLS; col++) {
            if (board[0][col] == EMPTY) {
                return false;
            }
        }
        return true;
    }
}
