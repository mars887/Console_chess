import chess.ChessBoard;
import chess.chessPieces.Pawn;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        Pawn pawn = new Pawn("black");
        board.printBoard();

        Scanner scanner = new Scanner(System.in);

        while(true) {
            String vals[] = scanner.nextLine().split(" ");
            System.out.println(pawn.canMoveToPosition(board,Integer.parseInt(vals[0]),Integer.parseInt(vals[1])
                    ,Integer.parseInt(vals[2]),Integer.parseInt(vals[3])));
        }
    }
}