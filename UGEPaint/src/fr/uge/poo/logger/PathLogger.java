package fr.uge.poo.logger;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class PathLogger implements Logger {
    private final Path path;

    public PathLogger(String path) {
        this.path = Path.of(path);
    }

    public void log(Level level, String message) {
        try (var writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(level + " " + message + "\n");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
