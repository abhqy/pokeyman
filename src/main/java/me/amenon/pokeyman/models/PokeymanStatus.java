package me.amenon.pokeyman.models;

public enum PokeymanStatus {
    ALIVE(true),
    FAINTED(false);

    public final boolean value;

    PokeymanStatus(boolean value) {
        this.value = value;
    }
}
