package chess.chessPieces;

import chess.ChessBoard;
import chess.ChessPiece;

public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return super.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard cboard, int line, int column, int toLine, int toColumn) {
        if (Math.abs(column - toColumn) == 1) {

            if (toLine - line == getDirectionForColor(color) && cboard.board[toLine][toColumn] != null
                    && !cboard.board[toLine][toColumn].getColor().equals(color)) {
                return true;
            }
        } else if (Math.abs(column - toColumn) == 0 && cboard.board[toLine][toColumn] == null) {

            if (Math.abs(line - toLine) == 2 && (line == 1 || line == 6) && toLine - line == getDirectionForColor(color) * 2
                    && cboard.board[toLine - (toLine - line) / 2][toColumn] == null)
                return true;

            else return Math.abs(line - toLine) == 1 && toLine - line == getDirectionForColor(color);
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "P";
    }

    public int getDirectionForColor(String color) {
        return color.equals("Black") ? -1 : 1;
    }
}
