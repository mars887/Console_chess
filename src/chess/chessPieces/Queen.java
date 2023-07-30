package chess.chessPieces;

import chess.ChessBoard;
import chess.ChessPiece;

public class Queen extends ChessPiece {
    public Queen(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return super.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard cboard, int line, int column, int toLine, int toColumn) {
        ChessPiece piece = new Bishop(color);
        if (piece.canMoveToPosition(cboard, line, column, toLine, toColumn)) return true;
        piece = new Rook(color);
        return piece.canMoveToPosition(cboard, line, column, toLine, toColumn);
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
