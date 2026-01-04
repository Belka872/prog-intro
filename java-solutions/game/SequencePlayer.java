package game;

public class SequencePlayer implements Player {
    @Override
    public Move makeMove(Position position) {
        for (int r = 0; r < position.getN(); r++) {
            for (int c = 0; c < position.getM(); c++) {
                Move move = new Move(
                        r,
                        c,
                        position.getTurn());
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        return null;
    }
}
