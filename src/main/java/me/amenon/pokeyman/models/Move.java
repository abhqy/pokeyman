package me.amenon.pokeyman.models;

public record Move(String name, int damage) {

  @Override
  public String toString() {
    return this.name + " (" + this.damage + " HP)";
  }
}
