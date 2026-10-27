package fr.uge.poo.paint.ex2;

import fr.uge.poo.simplegraphics.SimpleGraphics;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Paint {
    private static void drawLines(Graphics2D graphics, List<Line> lines) {
        graphics.setColor(Color.BLACK);
        for (var line : lines) {
            line.draw(graphics);
        }
    }

    private static Line createLine(String textLine) {
        var tokens = textLine.split(" ");
        var x1 = Integer.parseInt(tokens[1]);
        var y1 = Integer.parseInt(tokens[2]);
        var x2 = Integer.parseInt(tokens[3]);
        var y2 = Integer.parseInt(tokens[4]);
        return new Line(x1, y1, x2, y2);
    }

    private static List<Line> parseFile(String filename) throws IOException {
        var lines = new ArrayList<Line>();
        var path = Paths.get(filename);
        try (var textLines = Files.lines(path)) {
            textLines.forEach(textLine -> lines.add(createLine(textLine)));
        }
        return lines;
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            IO.println("Missing file in arguments!");
            return;
        }

        var filename = args[0];
        var lines = parseFile(filename);
        var area = new SimpleGraphics("area", 800, 600);
        area.render(graphics -> {
            Paint.drawLines(graphics, lines);
        });
    }
}
