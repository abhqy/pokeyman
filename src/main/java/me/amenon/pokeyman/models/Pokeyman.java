package me.amenon.pokeyman.models;

import lombok.Getter;
import lombok.Setter;
import me.amenon.pokeyman.messages.Messages;
import me.amenon.pokeyman.enums.Type;

import java.util.List;

@Getter
@Setter
public class Pokeyman implements Comparable {
    private String name;
    private Type type;
    private int hp;
    private int attack;
    private int defense;
    private List<Move> moveList;
    private final Player owner;

    public Pokeyman(Player owner) {
        this.owner = owner;
    }

    @Override
    public int compareTo(Object o) {
        if (o.getClass() != type.getClass()) {
            return 0;
        }
        Type otherType = (Type) o;
        if (type.name().equals(otherType.name())) {
            return 0;
        } else if (type.getWeaknesses().contains(otherType.name())) {
            return -1;
        } else {
            return 1;
        }
    }

    public PokeymanStatus isAlive() {
        if (this.hp <= 0) {
            return PokeymanStatus.FAINTED;
        }
        return PokeymanStatus.ALIVE;
    }

    private int receiveDamage(Move move, Pokeyman attacker) {
        double damageMagnitude = (((double) attacker.getAttack() / defense) * move.damage());
        int typeComparison = this.type.compareTo(attacker.getType());
        double damageMultiplier = Math.pow(2, typeComparison);
        int totalDamage = (int) (damageMagnitude * damageMultiplier);
        this.hp -= totalDamage;
        return totalDamage;
    }

    public int attack(Move move, Pokeyman enemyPokeyman) {
        return enemyPokeyman.receiveDamage(move, this);
    }

    @Override
    public String toString() {
        return String.format("%s: [%d HP]", name, hp);
    }

    public void showMoveList() {
        for (int i = 0; i < moveList.size(); i++) {
            Messages.SHOW_MOVE.show(i + 1, moveList.get(i).toString());
        }
    }

}
