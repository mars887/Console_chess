package chess.chessPieces;

import chess.ChessBoard;
import chess.ChessPiece;

public class Bishop extends ChessPiece {

    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return super.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard cboard, int line, int column, int toLine, int toColumn) {
        if (Math.abs(line - toLine) != Math.abs(column - toColumn)
                || (cboard.board[toLine][toColumn] != null && cboard.board[toLine][toColumn].getColor().equals(color))
                || Math.abs(line - toLine) == 0) {
            return false;
        }

        if (Math.abs(line - toLine) > 1) {
            int GDirection = column > toColumn ? -1 : 1;
            int VDirection = line > toLine ? -1 : 1;

            for (int x = column + GDirection, y = line + VDirection; y != toLine; x += GDirection, y += VDirection)
                if (cboard.board[y][x] != null) return false;
        }
        return true;
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}
