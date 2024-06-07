import java.util.Scanner;

public class TicTacToe {

    private static char[][] board;
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    private static char currentPlayer;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            initializeBoard();
            currentPlayer = PLAYER_X;
            boolean gameWon = false;

            while (!gameWon && !isBoardFull()) {
                printBoard();
                playerMove(scanner);
                gameWon = checkWin();

                if (!gameWon) {
                    switchPlayer();
                }
            }

            printBoard();

            if (gameWon) {
                System.out.println("Player " + currentPlayer + " wins!");
            } else {
                System.out.println("The game is a draw!");
            }

            System.out.println("Do you want to play again? (yes/no)");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        }

        scanner.close();
    }

    private static void initializeBoard() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    private static void printBoard() {
        System.out.println("Board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void playerMove(Scanner scanner) {
        int row = -1;
        int col = -1;
        boolean validMove = false;

        while (!validMove) {
            System.out.println("Player " + currentPlayer + ", enter your move (row and column): ");
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;

            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
                board[row][col] = currentPlayer;
                validMove = true;
            } else {
                System.out.println("This move is not valid");
            }
        }
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }

        // Check diagonals
        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
            (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
            return true;
        }

        return false;
    }
}
