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
        if (!cboard.checkPos(line) || !cboard.checkPos(column) || !cboard.checkPos(toLine)
                || !cboard.checkPos(toColumn) || cboard.board[toLine][toColumn] != null
                || column != toColumn || Math.abs(line - toLine) > 2 || line - toLine == 0) return false;

        int step = toLine - line;
        if(color.equals("white")) {
            if(line == 1 && step >= 1) return true;
            if(line != 1 && step == 1) return true;
        } else if (color.equals("black")) {
            if(line == 6 && step <= -1) return true;
            if(line != 6 && step == -1) return true;
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
