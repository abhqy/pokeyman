package me.amenon.pokeyman.battle;

import me.amenon.pokeyman.messages.Messages;
import me.amenon.pokeyman.io.IntScanner;
import me.amenon.pokeyman.models.Move;
import me.amenon.pokeyman.models.Player;
import me.amenon.pokeyman.models.Pokeyman;
import me.amenon.pokeyman.models.WinStatus;

import java.util.List;
import java.util.Objects;

public class Battle {

    private final Player player1;
    private final Player player2;

    private final IntScanner intScanner = new IntScanner(System.in);

    Move selectMove(Pokeyman pokeyman) {
        var moves = pokeyman.getMoveList();
        if (moves.isEmpty()) {
            Messages.NO_MOVES.show(pokeyman);
            return null;
        } else {
            Messages.CHOOSE_MOVE.show();
            pokeyman.showMoveList();
        }

        int moveChoice = intScanner.read(moves.size());
        return moves.get(moveChoice - 1);
    }

    WinStatus takeTurn(Player player) {
        var pokeymans = player.getPokeymans();
        if (pokeymans.isEmpty()) {
            Messages.NO_MORE_POKEYMAN_LEFT.show(player.getName());
            return WinStatus.LOSS;
        }
        var currentPokeyman = pokeymans.peek();

        Messages.PLAYER_TURN.show(player.getName());
        System.out.println(currentPokeyman);

        var move = selectMove(currentPokeyman);
        if (Objects.isNull(move)) {
            Messages.NO_MOVES.show(currentPokeyman.getName());
        } else {
            int damageDealt = player.callAttack(currentPokeyman, move);
            // damageDealt = -1 if no pokeyman left to deal damage to
            if (damageDealt >= 0) {
                Messages.DAMAGE_DEALT.show(currentPokeyman, move, damageDealt);
            }
        }
        return player.checkWin();
    }

    public Battle(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void play() {
        player1.setEnemy(player2);
        player2.setEnemy(player1);
        playPokeyman(List.of(player1, player2));
    }

    void playPokeyman(List<Player> players){
        int iter = 0;
        WinStatus winStatus = WinStatus.CONTINUE;
        while (winStatus == WinStatus.CONTINUE) {
            winStatus = takeTurn(players.get(iter%2));
            ++iter;
        }
        var winnerIndex = (iter - 1) % 2;
        Messages.PLAYER_WINS.show(players.get(winnerIndex).getName());
    }

    public static void main(String[] args) {
        Player player1 = new Player("Ash");
        Player player2 = new Player("Giovanni");
        Battle b = new Battle(player1, player2);
        b.play();
    }

}
