package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Tournament {
    private final Board board;
    private final List<TournamentPlayer> players;
    private final List<TournamentPlayer> winGrid;
    private final List<TournamentPlayer> loserGrid;
    private final Random random = new Random();

    public Tournament(Board board, List<TournamentPlayer> players) {
        this.board = board;
        this.players = players;
        this.winGrid = new ArrayList<>();
        this.loserGrid = new ArrayList<>();
    }

    public void start() {
        if(players.size()==1) {
            System.out.println(players.get(0).getName() + " win");
            return;
        }
        Collections.shuffle(players);
        winGrid.addAll(players);
        int round = 1;
        while (true) {
            System.out.println("Round" + round);
            round++;
            playRound();
            if (winGrid.size() == 1 && loserGrid.size() == 1) {
                break;
            }
        }
        TournamentPlayer player1 = winGrid.get(0);
        TournamentPlayer player2 = loserGrid.get(0);
        System.out.println(player1.getName() + " vs " + player2.getName());
        int result;
        do {
            boolean rand = random.nextBoolean();
            Game game = new Game(board.getNewBoard(), rand ? player1.getPlayer() : player2.getPlayer(), rand ? player2.getPlayer() : player1.getPlayer(), false);
            result = game.play();
        } while (result == 0);
        if (result == 1) {
            System.out.println("second place " + player2.getName() + "\nfirst place " + player1.getName());
        } else {
            System.out.println("second place " + player1.getName() + "\nfirst place " + player2.getName());
        }
    }

    private void playRound() {
        System.out.println("Play winner grid");
        playGrid(winGrid, true);
        System.out.println("Play losers grid");
        playGrid(loserGrid, false);
    }

    private void playGrid(List<TournamentPlayer> grid, boolean flag) {
        List<TournamentPlayer> winners = new ArrayList<>();
        List<TournamentPlayer> losers = new ArrayList<>();
        int n = grid.size();
        if (n == 1) {
            return;
        }
        int toNextRound = Integer.highestOneBit(n) * 2 - n;
        List<TournamentPlayer> nextRoundPlayer = new ArrayList<>();
        if (n % 2 != 0) {
            for (int i = 0; i < toNextRound; i++) {
                nextRoundPlayer.add(grid.get(n - toNextRound + i));
            }
        }
        grid.removeAll(nextRoundPlayer);
        n = grid.size();
        for (int i = 0; i < n; i += 2) {
            if (i + 1 >= n) {
                winners.add(grid.get(i));
                continue;
            }
            TournamentPlayer player1 = grid.get(i);
            TournamentPlayer player2 = grid.get(i + 1);
            System.out.println(player1.getName() + " vs " + player2.getName());
            int result;
            do {
                boolean rand = random.nextBoolean();
                Game game = new Game(board.getNewBoard(), rand ? player1.getPlayer() : player2.getPlayer(), rand ? player2.getPlayer() : player1.getPlayer(), false);
                result = game.play();
            } while (result == 0);
            if (result == 1) {
                winners.add(player1);
                System.out.println(player1.getName() + " wins");
                losers.add(player2);
            } else {
                winners.add(player2);
                System.out.println(player2.getName() + " wins");
                losers.add(player1);
            }
        }
        if (flag) {
            loserGrid.addAll(losers);
        } else {
            for (TournamentPlayer p : losers) {
                System.out.println(p.getName() + " eleminated");
            }
            if (loserGrid.size() == 2 && winGrid.size() == 1)
                System.out.println("third place " + losers.get(0).getName());
        }
        grid.clear();
        grid.addAll(winners);
        grid.addAll(nextRoundPlayer);
    }
}
