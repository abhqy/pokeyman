package me.amenon.pokeyman.enums;

import lombok.Getter;

import java.util.Set;

@Getter
public enum Type {
    NORMAL(Set.of(), Set.of("ROCK", "GHOST")),
    FIRE(Set.of("GRASS", "ICE", "BUG"), Set.of("FIRE", "WATER", "ROCK", "DRAGON")),
    WATER(Set.of("FIRE", "GROUND", "ROCK"), Set.of("WATER", "GRASS", "DRAGON")),
    ELECTRIC(Set.of("FLYING", "WATER"), Set.of("ELECTRIC", "GRASS", "GROUND", "DRAGON")),
    GRASS(Set.of("WATER", "GROUND", "ROCK"), Set.of("FIRE", "GRASS", "POISON", "FLYING", "BUG", "DRAGON")),
    ICE(Set.of("GRASS", "GROUND", "FLYING", "DRAGON"), Set.of("WATER", "ICE")),
    FIGHTING(Set.of("NORMAL", "ICE", "ROCK"), Set.of("POISON", "FLYING", "PSYCHIC", "BUG")),
    POISON(Set.of("GRASS", "BUG"), Set.of("POISON", "GROUND", "ROCK", "GHOST")),
    GROUND(Set.of("FIRE", "ELECTRIC", "POISON", "ROCK"), Set.of("GRASS", "FLYING", "BUG")),
    FLYING(Set.of("GRASS", "FIGHTING", "BUG"), Set.of("ELECTRIC", "ROCK")),
    PSYCHIC(Set.of("FIGHTING", "POISON"), Set.of("PSYCHIC")),
    BUG(Set.of("GRASS", "POISON", "PSYCHIC"), Set.of("FIRE", "FIGHTING", "FLYING", "GHOST")),
    ROCK(Set.of("FIRE", "ICE", "FLYING", "BUG"), Set.of("FIGHTING", "GROUND")),
    GHOST(Set.of("GHOST"), Set.of("NORMAL", "PSYCHIC")),
    DRAGON(Set.of("DRAGON"), Set.of());

    private final Set<String> strengths;
    private final Set<String> weaknesses;

    Type(Set<String> strengths, Set<String> weaknesses) {
        this.strengths = strengths;
        this.weaknesses = weaknesses;
    }

}
