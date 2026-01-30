package me.amenon.pokeyman.messages;

public enum Messages {
    PLAYER_TURN("%s's turn:"),
    NO_MORE_POKEYMAN_LEFT("Uh oh! %s has no pokeyman left!!"),
    NO_MOVES("Your pokeyman (%s) hasn't learnt any moves! :O"),
    CHOOSE_MOVE("Which move will you use? (Enter by integer)"),
    SHOW_MOVE("  %d) %s"),
    POKEYMAN_FAINTED("Uh oh! %s's pokeyman (%s) just fainted :O"),
    INVALID_INT_ENTRY("That is not a valid entry. Please enter an integer: "),
    DAMAGE_DEALT("%s used %s, dealing %d HP damage"),
    DAMAGE_EFFECT("%s's pokeyman %s lost some health: %d -> %d ;-;"),
    NO_EFFECT("%s's pokeyman %s lost no health: %d -> %d :P"),
    PLAYER_WINS("%s wins!! :D"),
    SHOW_POKEYMAN("%s: [%d HP]");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void show(Object ...args) {
        System.out.printf(this.getMessage() + "\n", args);
    }
}
