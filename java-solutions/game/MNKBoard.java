package game;

import java.util.Arrays;

public class MNKBoard implements Board {
    int n, m, k;
    private final Cell[][] field;
    private Cell turn;
    private int freeCell;
    private final boolean isRomb;
    private final int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    private final int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    public MNKBoard(int n, int m, int k, boolean isRomb) {
        if (!isRomb) {
            this.n = n;
            this.m = m;
            this.k = k;
            this.freeCell = n * m;
            field = new Cell[n][m];
            for (Cell[] row : field) {
                Arrays.fill(row, Cell.E);
            }
            turn = Cell.X;
        } else {
            this.n = n;
            this.k = k;
            this.m = 1 + (n - 1) * 2;
            this.field = new Cell[this.m][this.m];
            this.freeCell = this.n * this.n + (this.n - 1) * (this.n - 1);
            for (Cell[] row : field) {
                Arrays.fill(row, Cell.S);
            }
            for (int i = 0; i < this.m; i++) {
                int space;
                int currentLength;
                if (i < this.n) {
                    space = this.n - 1 - i;
                    currentLength = 1 + i * 2;
                } else {
                    space = i - this.n + 1;
                    currentLength = this.m - (i - this.n + 1) * 2;
                }
                for (int j = space; j < space + currentLength; j++) field[i][j] = Cell.E;
            }
            turn = Cell.X;
        }
        this.isRomb = isRomb;
    }

    @Override
    public Position getPosition() {
        if (!isRomb) return new MNKPosition(this.field, this.turn, n, m, k);
        return new MNKPosition(this.field, this.turn, m, m, k);
    }

    @Override
    public Result makeMove(Move move) {
        if (!getPosition().isValid(move)) {
            return Result.LOSE;
        }
        field[move.getRow()][move.getCol()] = move.getCell();
        if (checkWin(move)) {
            return Result.WIN;
        }
        freeCell--;
        if (freeCell == 0) {
            return Result.DRAW;
        }
        turn = (turn == Cell.X) ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    private boolean checkWin(Move move) {
        int rowStart = move.getRow();
        int colStart = move.getCol();
        for (int i = 0; i < 8; i++) {
            int countCell = 0;
            for (int j = 0; j < k; j++) {
                int r = rowStart + j * dx[i];
                int c = colStart + j * dy[i];
                if (r >= 0 && r < n && c >= 0 && c < m && field[r][c] == turn) {
                    countCell++;
                }
            }
            if (countCell >= k) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Board getNewBoard() {
        if (!isRomb) return new MNKBoard(n, m, k, false);
        return new MNKBoard(n, m, k, true);
    }
}
