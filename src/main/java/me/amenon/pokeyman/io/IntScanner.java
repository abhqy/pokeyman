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
        int var = scanner.nextInt();
        while (var < 0 || var > rangeEnd) {
            Messages.INVALID_INT_ENTRY.show();
            var = scanner.nextInt();
        }
        return var;
    }
}
