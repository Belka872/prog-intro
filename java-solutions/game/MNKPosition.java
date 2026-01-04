package game;

import java.util.Map;

public class MNKPosition implements Position {
    private final Cell[][] field;
    private final Cell turn;
    private final int n;
    private final int m;
    private final int k;

    private static final Map<Cell, String> CELL_STRING_MAP = Map.of(
            Cell.X, "X",
            Cell.O, "O",
            Cell.E, ".",
            Cell.S, "#"
    );

    public MNKPosition(Cell[][] field, Cell turn, int n, int m, int k) {
        this.field = new Cell[field.length][];
        for (int i = 0; i < field.length; i++) {
            this.field[i] = new Cell[field[i].length];
            System.arraycopy(field[i], 0, this.field[i], 0, field[i].length);
        }
        this.turn = turn;
        this.n = n;
        this.m = m;
        this.k = k;
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public int getN() {
        return n;
    }

    @Override
    public int getM() {
        return m;
    }

    @Override
    public int getK() {
        return k;
    }

    @Override
    public boolean isValid(Move move) {
        return isValid(move, field, turn, n, m);
    }

    private static boolean isValid(Move move, Cell[][] curField, Cell curTurn, int n, int m) {
        return move != null
                && 0 <= move.getRow() && move.getRow() < n
                && 0 <= move.getCol() && move.getCol() < m
                && curField[move.getRow()][move.getCol()] == Cell.E
                && move.getCell() == curTurn
                && curField[move.getRow()][move.getCol()] != Cell.S;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        for (int i = 0; i < m; i++) {
            sb.append(String.format("%2d", i + 1) + " ");
        }
        sb.append("\n");
        for (int i = 0; i < n; i++) {
            sb.append(String.format("%2d", i + 1));
            for (int j = 0; j < m; j++) {
                sb.append(" " + CELL_STRING_MAP.get(field[i][j]) + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
