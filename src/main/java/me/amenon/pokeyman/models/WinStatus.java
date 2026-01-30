package me.amenon.pokeyman.models;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum WinStatus {
    WIN(1),
    CONTINUE(0),
    LOSS(-1);

    public final int value;
}
