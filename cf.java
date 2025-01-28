import java.util.Scanner;

public class cf {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private static final char EMPTY = '.';
    private static final char PLAYER_ONE = 'X';
    private static final char PLAYER_TWO = 'O';

    private char[][] board = new char[ROWS][COLS];

    public cf() {
        // Initialize the board with empty cells
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    public void displayBoard() {
        // Print the board
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("1 2 3 4 5 6 7"); // Column numbers
    }

    public boolean dropDisc(int col, char player) {
        // Check if column is valid
        if (col < 0 || col >= COLS) {
            return false;
        }

        // Drop the disc into the column
        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][col] == EMPTY) {
                board[row][col] = player;
                return true;
            }
        }
        return false; // Column is full
    }

    public boolean checkWin(char player) {
        // Check rows, columns, and diagonals for a win

        // Check rows
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS - 3; col++) {
                if (board[row][col] == player &&
                    board[row][col + 1] == player &&
                    board[row][col + 2] == player &&
                    board[row][col + 3] == player) {
                    return true;
                }
            }
        }

        // Check columns
        for (int col = 0; col < COLS; col++) {
            for (int row = 0; row < ROWS - 3; row++) {
                if (board[row][col] == player &&
                    board[row + 1][col] == player &&
                    board[row + 2][col] == player &&
                    board[row + 3][col] == player) {
                    return true;
                }
            }
        }

        // Check diagonals (top-left to bottom-right)
        for (int row = 0; row < ROWS - 3; row++) {
            for (int col = 0; col < COLS - 3; col++) {
                if (board[row][col] == player &&
                    board[row + 1][col + 1] == player &&
                    board[row + 2][col + 2] == player &&
                    board[row + 3][col + 3] == player) {
                    return true;
                }
            }
        }

        // Check diagonals (bottom-left to top-right)
        for (int row = 3; row < ROWS; row++) {
            for (int col = 0; col < COLS - 3; col++) {
                if (board[row][col] == player &&
                    board[row - 1][col + 1] == player &&
                    board[row - 2][col + 2] == player &&
                    board[row - 3][col + 3] == player) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isBoardFull() {
        for (int col = 0; col < COLS; col++) {
            if (board[0][col] == EMPTY) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        cf game = new cf();
        Scanner scanner = new Scanner(System.in);
        char currentPlayer = PLAYER_ONE;

        System.out.println("Welcome to Connect Four!");
        game.displayBoard();

        while (true) {
            System.out.print("Player " + (currentPlayer == PLAYER_ONE ? "1" : "2") + " (" + currentPlayer + "), choose a column (1-7): ");
            int col = scanner.nextInt() - 1;

            if (!game.dropDisc(col, currentPlayer)) {
                System.out.println("Column is full or invalid. Try again.");
                continue;
            }

            game.displayBoard();

            if (game.checkWin(currentPlayer)) {
                System.out.println("Player " + (currentPlayer == PLAYER_ONE ? "1" : "2") + " wins!");
                break;
            }

            if (game.isBoardFull()) {
                System.out.println("It's a tie!");
                break;
            }

            currentPlayer = (currentPlayer == PLAYER_ONE) ? PLAYER_TWO : PLAYER_ONE;
        }

        scanner.close();
    }
}
