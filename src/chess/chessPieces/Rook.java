package chess.chessPieces;

import chess.ChessBoard;
import chess.ChessPiece;

public class Rook extends ChessPiece {

    public Rook(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return super.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard cboard, int line, int column, int toLine, int toColumn) {
        int xp = toColumn - column, GDirection = Integer.compare(xp, 0);
        int yp = toLine - line, VDirection = Integer.compare(yp, 0);

        if ((cboard.board[toLine][toColumn] != null && cboard.board[toLine][toColumn].getColor().equals(color))
                || Math.abs(GDirection) == Math.abs(VDirection))
            return false;

        for (int x = line + VDirection, y = column + GDirection; x != toLine || y != toColumn; x += VDirection, y += GDirection) {
            if (cboard.board[x][y] != null) return false;
        }
        return true;
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}
