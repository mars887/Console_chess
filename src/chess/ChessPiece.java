package chess;

public abstract class ChessPiece {
    protected String color = "";
    protected boolean check = true;

    public ChessPiece(String color) {
        this.color = color;
    }

    abstract public String getColor();
    abstract public boolean canMoveToPosition(ChessBoard cboard,int line,int column,int toLine,int toColumn);
    abstract public String getSymbol();
}
