package me.amenon.pokeyman.models;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PokeymanStatus {
    ALIVE(true),
    FAINTED(false);

    public final boolean value;
}
