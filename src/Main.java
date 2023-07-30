import chess.ChessBoard;
import chess.ChessPiece;
import chess.chessPieces.*;

import java.util.Scanner;

public class Main {

    public static ChessBoard buildBoard() {
        ChessBoard board = new ChessBoard("White");

        board.board[0][0] = new Rook("White");
        board.board[0][1] = new Horse("White");
        board.board[0][2] = new Bishop("White");
        board.board[0][3] = new Queen("White");
        board.board[0][4] = new King("White", 0, 4);
        board.board[0][5] = new Bishop("White");
        board.board[0][6] = new Horse("White");
        board.board[0][7] = new Rook("White");
        board.board[1][0] = new Pawn("White");
        board.board[1][1] = new Pawn("White");
        board.board[1][2] = new Pawn("White");
        board.board[1][3] = new Pawn("White");
        board.board[1][4] = new Pawn("White");
        board.board[1][5] = new Pawn("White");
        board.board[1][6] = new Pawn("White");
        board.board[1][7] = new Pawn("White");

        board.board[7][0] = new Rook("Black");
        board.board[7][1] = new Horse("Black");
        board.board[7][2] = new Bishop("Black");
        board.board[7][3] = new Queen("Black");
        board.board[7][4] = new King("Black", 7, 4);
        board.board[7][5] = new Bishop("Black");
        board.board[7][6] = new Horse("Black");
        board.board[7][7] = new Rook("Black");
        board.board[6][0] = new Pawn("Black");
        board.board[6][1] = new Pawn("Black");
        board.board[6][2] = new Pawn("Black");
        board.board[6][3] = new Pawn("Black");
        board.board[6][4] = new Pawn("Black");
        board.board[6][5] = new Pawn("Black");
        board.board[6][6] = new Pawn("Black");
        board.board[6][7] = new Pawn("Black");

        board.addKing((King) board.board[0][4], "White");
        board.addKing((King) board.board[7][4], "Black");
        return board;
    }

    public static void main(String[] args) {

        ChessBoard board = buildBoard();
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Чтобы проверить игру надо вводить команды:
                'exit' - для выхода
                'restart' - для перезапуска игры
                'castling0' или 'castling7' - для рокировки по соответствующей линии
                'move 1 1 2 3' - для передвижения фигуры с позиции 1 1 на 2 3(поле это двумерный массив от 0 до 7)
                Проверьте могут ли фигуры ходить друг сквозь друга, корректно ли съедают друг друга, можно ли поставить шах и сделать рокировку?""");
        System.out.println();

        while (true) {
            board.printBoard();
            String s = scanner.nextLine();
            if (s.equals("exit")) break;
            if (s.equals("restart")) {
                board = buildBoard();
                board.printBoard();
                continue;
            }
            if (s.startsWith("admin.")) {
                String[] data = s.substring(6).split(" ");

                switch (data[0]) {
                    case "swap":
                        try {
                            ChessPiece piece = board.board[parse(data[1])][parse(data[2])];
                            board.board[parse(data[1])][parse(data[2])] = board.board[parse(data[3])][parse(data[4])];
                            board.board[parse(data[3])][parse(data[4])] = piece;
                        } catch (Exception e) {
                            System.out.println(getInfoById(0));
                        }
                        break;
                    case "copy":
                        try {
                            board.board[parse(data[1])][parse(data[2])] = getPiece(board.board[parse(data[3])][parse(data[4])]);
                        } catch (Exception e) {
                            System.out.println(getInfoById(1));
                        }
                        break;
                    case "remove":
                        try {
                            board.board[parse(data[1])][parse(data[2])] = null;
                        } catch (Exception e) {
                            System.out.println(getInfoById(2));
                        }
                        break;
                    case "move":
                        try {
                            board.board[parse(data[3])][parse(data[4])] = board.board[parse(data[1])][parse(data[2])];
                            board.board[parse(data[1])][parse(data[2])] = null;
                        } catch (Exception e) {
                            System.out.println(getInfoById(3));
                        }
                        break;
                    case "set":
                        try {
                            board.board[parse(data[1])][parse(data[2])] = getPiece(data[3], data[4]);
                        } catch (Exception e) {
                            System.out.println(getInfoById(4));
                        }
                        break;
                    default:
                        System.out.println(getInfoById(5));
                }
                continue;
            }
            String[] data = s.split(" ");
            switch (data[0]) {
                case "castling0":
                    if (board.castling0()) {
                        System.out.println("Рокировка удалась");
                    } else {
                        System.out.println("Рокировка не удалась");
                    }
                    break;
                case "castling7":
                    if (board.castling7()) {
                        System.out.println("Рокировка удалась");
                    } else {
                        System.out.println("Рокировка не удалась");
                    }
                    break;
                case "move":
                    try {
                        if (board.moveToPosition(parse(data[1]), parse(data[2]), parse(data[3]), parse(data[4]))) {
                            System.out.println("Успешно передвинулись");
                        } else System.out.println("Передвижение не удалось");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(getInfoById(6));
                    }
                    break;
                default:
                    System.out.println(getInfoById(7));
            }
        }
    }

    public static int parse(String string) {
        return Integer.parseInt(string);
    }

    public static ChessPiece getPiece(ChessPiece piece) {
        return getPiece(piece.getSymbol(), piece.getColor());
    }

    public static ChessPiece getPiece(String type, String color) {
        switch (type) {
            case "B":
                return new Bishop(color);
            case "H":
                return new Horse(color);
            case "R":
                return new Rook(color);
            case "Q":
                return new Queen(color);
            case "K":
                return new King(color);
            case "P":
                return new Pawn(color);
        }
        return null;
    }

    public static String getInfoById(int id) {
        switch (id) {
            case 0:
                return """
                        команда admin.swap меняет местами содержимое двух клеток
                        admin.swap 1 3 6 4
                        где:
                        swap - команда
                        1 3 и 6 4 - кординаты клеток
                        """;
            case 1:
                return """
                        команда admin.copy создаёт копию объекта
                        admin.copy 1 3 6 4
                        где:
                        copy - команда
                        1 3 - куда копируем
                        6 4 - что копируем
                        """;
            case 2:
                return """
                        команда admin.remove удаляет объект
                        admin.remove 1 3
                        где:
                        remove - команда
                        1 3 - кординаты удаляемого объекта
                        """;
            case 3:
                return """
                        команда admin.move перемещает объект игнорируя правила
                        admin.move 1 3 6 4
                        где:
                        move - команда
                        1 3 - откуда перемещаем
                        6 4 - куда перемещаем
                        """;
            case 4:
                return """
                        команда admin.set создаёт объект
                        admin.set 1 3 H White
                        где:
                        set - команда
                        1 3 - где создаём
                        Н - символ фигуры
                        White - цвет
                        """;
            case 5:
                return """
                        admin.swap
                        admin.copy
                        admin.remove
                        admin.move
                        admin.set
                                                    
                        напишите команду для подробного описания
                        """;
            case 6:
                return """
                        move - стандартный ход
                        move 1 3 6 4
                        где:
                        move - команда
                        1 3 - откуда ходим
                        6 4 - куда ходим                        
                        """;
            case 7:
                return """
                        castling0 и castling7 - рокировки
                        move - ход фигурой (напишите команду без параметров для описания
                        """;
            default:
                return " этого не должно здесь быть поэтому я не буду на это отвечать";
        }
    }
}