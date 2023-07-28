import chess.ChessBoard;
import chess.chessPieces.Pawn;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        Pawn pawn = new Pawn("White");
        board.board[1][1] = pawn;
        board.board[6][1] = new Pawn("Black");
        board.nowPlayer = "White";
        board.printBoard();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String vals[] = scanner.nextLine().split(" ");
            System.out.println(board.moveToPosition(parse(vals[0]), parse(vals[1]), parse(vals[2]), parse(vals[3])));
            board.printBoard();
        }
    }

    public static int parse(String line) {
        return Integer.parseInt(line);
    }
}