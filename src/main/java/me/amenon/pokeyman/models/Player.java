package me.amenon.pokeyman.models;

import lombok.Getter;
import lombok.Setter;
import me.amenon.pokeyman.messages.Messages;
import me.amenon.pokeyman.enums.Type;

import java.util.*;

@Getter
@Setter
public class Player implements Iterator<Pokeyman> {

    private final Queue<Pokeyman> pokeymans = new LinkedList<>();
    private final String name;
    private int wins;
    private int losses;
    private Player enemy;

    private final Random rng = new Random();

    public Player(String name){
        this.name = name;
        for (int i = 0; i < 3; i++){
            pokeymans.add(generateRandomPokeyman());
        }
    }

    public Player(String name, Queue<Pokeyman> pokeymans){
        this.name = name;
        for (int i = 0; i<3; i++){
            pokeymans.add(new Pokeyman(this));
        }
    }

    @Override
    public boolean hasNext() {
        return Objects.isNull(pokeymans.peek());
    }

    @Override
    public Pokeyman next() {
        pokeymans.remove();
        return pokeymans.peek();
    }

    public Pokeyman generateRandomPokeyman() {
        List<Move> genericMoveset = List.of(
                new Move("Tackle", 5),
                new Move("Shock", 5),
                new Move("Tail Whip", 5),
                new Move("Gigashock", 5)
        );

        return new Pokeyman(this) {{
            setName("Randochu " + rng.nextInt(1000));
            setType(Type.ELECTRIC);
            setAttack(rng.nextInt(25));
            setDefense(rng.nextInt(25));
            setHp(rng.nextInt(4) + 1);
            setMoveList(genericMoveset);
        }};
    }

    public WinStatus checkWin() {
        var enemyPokeymans = enemy.getPokeymans();
        if (enemyPokeymans.isEmpty()) {
            return WinStatus.WIN;
        }
        var currentEnemyPokeyman = enemyPokeymans.peek();
        while(enemy.getPokeymans().isEmpty() ||
                !currentEnemyPokeyman.isAlive().value) {
            if (enemyPokeymans.isEmpty()) {
                Messages.NO_MORE_POKEYMAN_LEFT.show(enemy.getName());
                return WinStatus.WIN;
            }
            Messages.POKEYMAN_FAINTED.show(
                    enemy.getName(), currentEnemyPokeyman.getName());
            currentEnemyPokeyman = enemy.next();
        }
        return WinStatus.CONTINUE;
    }

    public int callAttack(Pokeyman pokeyman, Move move) {
        var enemyPokeymans = enemy.getPokeymans();
        if (enemyPokeymans.isEmpty()) { return -1; }
        var currentEnemyPokeyman = enemyPokeymans.peek();

        var preHealth = currentEnemyPokeyman.getHp();
        var damageDealt =  pokeyman.attack(move, currentEnemyPokeyman);

        var messageType = Messages.DAMAGE_EFFECT;
        if (damageDealt == 0) {
           messageType = Messages.NO_EFFECT;
        }
        messageType.show(
            enemy.getName(),
            currentEnemyPokeyman,
            preHealth,
            currentEnemyPokeyman.getHp());

        return damageDealt;
    }

}
