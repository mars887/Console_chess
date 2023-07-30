package chess.chessPieces;

import chess.ChessBoard;
import chess.ChessPiece;

public class Horse extends ChessPiece {

    public Horse(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return super.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard cboard, int line, int column, int toLine, int toColumn) {

        if ((cboard.board[toLine][toColumn] != null && cboard.board[toLine][toColumn].getColor().equals(color))) return false;

        return (Math.abs(line - toLine) == 1 && Math.abs(column - toColumn) == 2)
                || (Math.abs(line - toLine) == 2 && Math.abs(column - toColumn) == 1);
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}
