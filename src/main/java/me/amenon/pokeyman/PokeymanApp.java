package me.amenon.pokeyman;

import me.amenon.pokeyman.battle.Battle;
import me.amenon.pokeyman.models.Player;

public class PokeymanApp {
    public static void main(String[] args) {
        Player player1 = new Player("Ash");
        Player player2 = new Player("Giovanni");
        Battle b = new Battle(player1, player2);
        b.play();
    }
}
