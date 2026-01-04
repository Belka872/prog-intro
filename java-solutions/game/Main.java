package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("1 - play one game\n2 - play tournament");
        Scanner sc = new Scanner(System.in);
        int gameType;
        gameType = sc.nextInt();
        if (gameType == 1) {
            int typePlayer1;
            int typePlayer2;
            System.out.println("1 - HumanPlayer\n2 - RandomPlayer\n3 - SequencePlayer\n4 - CheaterPlayer");
            typePlayer1 = sc.nextInt();
            System.out.println("1 - HumanPlayer\n2 - RandomPlayer\n3 - SequencePlayer\n4 - CheaterPlayer");
            typePlayer2 = sc.nextInt();
            Player Player1;
            Player Player2;
            if (typePlayer1 == 1) Player1 = new HumanPlayer();
            else if (typePlayer1 == 2) Player1 = new RandomPlayer();
            else if (typePlayer1 == 3) Player1 = new SequencePlayer();
            else Player1 = new CheaterPlayer();

            if (typePlayer2 == 1) Player2 = new HumanPlayer();
            else if (typePlayer2 == 2) Player2 = new RandomPlayer();
            else if (typePlayer2 == 3) Player2 = new SequencePlayer();
            else Player2 = new CheaterPlayer();

            System.out.println("Select board type\n1 - TicTacToeBoard\n2 - MNKBoard\n3 - RombBoard");
            int boardType = sc.nextInt();
            Board board;
            if (boardType == 1) {
                board = new MNKBoard(3, 3, 3, false);
            } else if (boardType == 2) {
                System.out.println("n, m, k");
                int n = sc.nextInt();
                int m = sc.nextInt();
                int k = sc.nextInt();
                board = new MNKBoard(n, m, k, false);
            } else {
                System.out.println("n, k");
                int n = sc.nextInt();
                int k = sc.nextInt();
                board = new MNKBoard(n, n, k, true);
            }

            Game game = new Game(board, Player1, Player2, true);
            int result = game.play();
            if (result == 0) System.out.println("draw");
            else if (result == 1) System.out.println("first");
            else System.out.println("second");
        } else {
            System.out.println("Select board type\n1 - TicTacToeBoard\n2 - MNKBoard\n3 - RombBoard");
            int boardType = sc.nextInt();
            Board board;
            if (boardType == 1) {
                board = new MNKBoard(3, 3, 3, false);
            } else if (boardType == 2) {
                System.out.println("n, m, k");
                int n = sc.nextInt();
                int m = sc.nextInt();
                int k = sc.nextInt();
                board = new MNKBoard(n, m, k, false);
            } else {
                System.out.println("n, k");
                int n = sc.nextInt();
                int k = sc.nextInt();
                board = new MNKBoard(n, n, k, true);
            }
            List<TournamentPlayer> players = new ArrayList<>();
            System.out.println("Number of players");
            int numPlayers = sc.nextInt();
            for (int i = 0; i < numPlayers; i++) {
                players.add(new TournamentPlayer(new RandomPlayer(), "Random " + (i + 1)));
            }
            Tournament tournament = new Tournament(board, players);
            tournament.start();
        }
    }
}
