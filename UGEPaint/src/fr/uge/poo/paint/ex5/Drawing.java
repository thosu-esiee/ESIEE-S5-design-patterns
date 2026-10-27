package fr.uge.poo.paint.ex5;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Drawing {
    private ArrayList<Figure> figures;
    private Figure selected;

    private Drawing(ArrayList<Figure> figures) {
        this.figures = figures;
    }

    public static Drawing fromFile(Path path) throws IOException {
        var figures = new ArrayList<Figure>();
        try (var lines = Files.lines(path)) {
            lines.forEach(line -> figures.add(createFigure(line)));
        }
        return new Drawing(figures);
    }

    public void drawAll(Graphics graphics) {
        for (var figure : figures) {
            if (figure == selected) {
                figure.draw(graphics, PaintColor.RED);
            } else {
                figure.draw(graphics, PaintColor.BLACK);
            }
        }
    }

    public void onClick(Point p) {
        if (figures.isEmpty()) {
            return;
        }
        var closestFigure = figures.getFirst();
        for (var i = 1; i < figures.size(); i++) {
            var figure = figures.get(i);
            closestFigure = closestFigure.squareDistance(p) <= figure.squareDistance(p) ? closestFigure : figure;
        }
        selected = closestFigure;
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
}
