package fr.uge.poo.paint.ex3;

import fr.uge.poo.simplegraphics.SimpleGraphics;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Paint {
    private static void drawAll(Graphics2D graphics, List<Figure> figures) {
        graphics.setColor(Color.BLACK);
        for (var line : figures) {
            line.draw(graphics);
        }
    }

    private static Figure createFigure(String line) {
        var tokens = line.split(" ");
        var type = tokens[0];
        return switch (type) {
            case "line" -> {
                var x1 = Integer.parseInt(tokens[1]);
                var y1 = Integer.parseInt(tokens[2]);
                var x2 = Integer.parseInt(tokens[3]);
                var y2 = Integer.parseInt(tokens[4]);
                yield new Line(x1, y1, x2, y2);
            }
            case "rectangle" -> {
                var x = Integer.parseInt(tokens[1]);
                var y = Integer.parseInt(tokens[2]);
                var width = Integer.parseInt(tokens[3]);
                var height = Integer.parseInt(tokens[4]);
                yield new Rectangle(x, y, width, height);
            }
            case "ellipse" -> {
                var x = Integer.parseInt(tokens[1]);
                var y = Integer.parseInt(tokens[2]);
                var width = Integer.parseInt(tokens[3]);
                var height = Integer.parseInt(tokens[4]);
                yield new Ellipse(x, y, width, height);
            }
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }

    private static List<Figure> parseFile(String filename) throws IOException {
        var figures = new ArrayList<Figure>();
        var path = Paths.get(filename);
        try (var lines = Files.lines(path)) {
            lines.forEach(line -> figures.add(createFigure(line)));
        }
        return figures;
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            IO.println("Missing file in arguments!");
            return;
        }

        var filename = args[0];
        var figures = parseFile(filename);
        var area = new SimpleGraphics("area", 800, 600);
        area.render(graphics -> {
            Paint.drawAll(graphics, figures);
        });
    }
}
