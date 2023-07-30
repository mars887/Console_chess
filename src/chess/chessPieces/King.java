package chess.chessPieces;

import chess.ChessBoard;
import chess.ChessPiece;

public class King extends ChessPiece {

    private int positionX,positionY;

    public King(String color, int positionX, int positionY) {
        super(color);
        this.positionX = positionX;
        this.positionY = positionY;
    }
    public King(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return super.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard cboard, int line, int column, int toLine, int toColumn) {
        if(cboard.board[toLine][toColumn] != null && cboard.board[toLine][toColumn].getColor().equals(color)) return false;
        if (Math.abs(line - toLine) <= 1 && Math.abs(column - toColumn) <= 1) {
            positionX = toColumn;
            positionY = toLine;
            return true;
        }
        return false;
    }

    public boolean isUnderAttack(ChessBoard cboard, int line, int column) {
        for (int x = 0; x < cboard.board.length; x++) {
            for (int y = 0; y < cboard.board[0].length; y++) {
                if (cboard.board[x][y] != null && !cboard.board[x][y].getColor().equals(color)
                        && cboard.board[x][y].canMoveToPosition(cboard, x, y, line, column)) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isUnderAttack(ChessBoard cboard) {
        return isUnderAttack(cboard,positionX,positionY);
    }

    @Override
    public String getSymbol() {
        return "K";
    }
}
