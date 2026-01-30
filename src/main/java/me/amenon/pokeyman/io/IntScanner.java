package me.amenon.pokeyman.io;

import me.amenon.pokeyman.messages.Messages;

import java.io.InputStream;
import java.util.Scanner;

public class IntScanner {
    private final Scanner scanner;

    public IntScanner(InputStream stream) {
        this.scanner = new Scanner(stream);
    }

    public int read(int rangeEnd) {
        while (scanner.hasNext()) {
            if (!scanner.hasNextInt()) {
                scanner.next();
            } else {
                int var = scanner.nextInt();
                if (var >= 0 && var <= rangeEnd) {
                    return var;
                }
            }
            Messages.INVALID_INPUT.show(0, rangeEnd);
        }
        throw new IllegalStateException(Messages.ILLEGAL_STATE.getMessage());
    }
}
