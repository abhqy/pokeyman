package me.amenon.pokeyman.models;

public class Move {
  private final String name;
  private final int damage;

  public Move(String name, int damage) {
    this.name = name;
    this.damage = damage;
  }

  public String getName() {
    return name;
  }

  public int getDamage() {
    return damage;
  }

  @Override
  public String toString() {
    return this.name + " (" + this.damage + " HP)";
  }
}
