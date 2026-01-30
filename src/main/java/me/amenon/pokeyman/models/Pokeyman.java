package me.amenon.pokeyman.models;

import me.amenon.pokeyman.messages.Messages;
import me.amenon.pokeyman.enums.Type;

import java.util.List;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public List<Move> getMoveList() {
        return moveList;
    }

    public int getHp() {
        return hp;
    }

    public void setMoveList(List<Move> moveList) {
        this.moveList = moveList;
    }

    public PokeymanStatus isAlive() {
        if (this.hp <= 0) {
            return PokeymanStatus.FAINTED;
        }
        return PokeymanStatus.ALIVE;
    }

    private int receiveDamage(Move move, Pokeyman attacker) {
        double damageMagnitude = (((double) attacker.getAttack() / defense) * move.getDamage());
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
