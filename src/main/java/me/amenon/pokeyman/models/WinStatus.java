package me.amenon.pokeyman.models;

public enum WinStatus {
    WIN(1),
    CONTINUE(0),
    LOSS(-1);

    public final int value;

    WinStatus(int value) {
        this.value = value;
    }
}
