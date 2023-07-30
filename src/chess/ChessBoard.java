package chess;

import chess.chessPieces.*;

import java.util.ArrayList;

public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    public String nowPlayer;
    private ArrayList<King> kings = new ArrayList<>(2);
    private ArrayList<String> kingNames = new ArrayList<>(2);

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn) && board[startLine][startColumn] != null) {

            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) {
                return false;
            }

            if (checkPos(startLine) && checkPos(startColumn) && checkPos(endLine) && checkPos(endColumn)
                    && board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {

                if(board[endLine][endColumn] != null && board[endLine][endColumn].getSymbol().equals("K")) {
                    System.out.println(board[endLine][endColumn].getColor() + " King killed");
                }
                board[endLine][endColumn] = board[startLine][startColumn]; // if piece can move, we moved a piece
                board[startLine][startColumn] = null; // set null to previous cell
                board[endLine][endColumn].check = true;


                this.nowPlayer = this.nowPlayer.equals("White") ? "Black" : "White";

                return true;
            }
        }
        return false;
    }

    public boolean castling0() {
        return castling(0);
    }

    public boolean castling7() {
        return castling(7);
    }

    public boolean castling(int type) {
        int castLine = nowPlayer.equals("White") ? 0 : 7;
        int KStart = 4;
        int KEnd = (type == 0 ? 2 : 6);
        int RStart = (type == 0 ? 0 : 7);
        int REnd = (type == 0 ? 3 : 5);

        if (board[castLine][RStart] == null || board[castLine][KStart] == null) return false;

        if (board[castLine][RStart].getSymbol().equals("R") && board[castLine][KStart].getSymbol().equals("K") && // check that King and Rook

                ((type == 0 && board[castLine][1] == null && board[castLine][2] == null && board[castLine][3] == null)
                || (type == 7 && board[castLine][6] == null && board[castLine][5] == null))) {

            if (board[castLine][RStart].getColor().equals(nowPlayer) && board[castLine][KStart].getColor().equals(nowPlayer) &&
                    board[castLine][RStart].check && board[castLine][KStart].check &&
                    !new King(nowPlayer).isUnderAttack(this, castLine, KEnd)) {
                board[castLine][KStart] = null;
                board[castLine][KEnd] = new King(nowPlayer);   // move King
                board[castLine][KEnd].check = false;
                board[castLine][RStart] = null;
                board[castLine][REnd] = new Rook(nowPlayer);   // move Rook
                board[castLine][REnd].check = false;
                nowPlayer = nowPlayer.equals("White") ? "Black" : "White";  // next turn
                return true;
            } else return false;
        } else return false;
    }

    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
        System.out.println();
        for(int i = 0;i < kings.size();i++) {
            King king = kings.get(i);
            if(king.getColor().equals(nowPlayer)) {
                if(king.isUnderAttack(this)) {
                    System.out.println("King " + king.getColor() + " is under attack");
                }
            }
        }
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public void addKing(King king, String name) {
        kings.add(king);
        kingNames.add(name);
    }
}
