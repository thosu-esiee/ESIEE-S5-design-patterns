package fr.uge.poo.paint.ex4;

import fr.uge.poo.simplegraphics.SimpleGraphics;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Paint {
    private static void callback(SimpleGraphics area, Point mousePoint, Drawing drawing) {
        area.render(graphics -> {
            drawing.onClick(mousePoint);
            area.clear(Color.WHITE);
            area.render(drawing::drawAll);
        });
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            IO.println("Missing file in arguments!");
            return;
        }

        var filename = args[0];
        var path = Paths.get(filename);
        var drawing = Drawing.fromFile(path);
        var area = new SimpleGraphics("area", 800, 600);
        area.clear(Color.WHITE);
        area.render(graphics -> {
            drawing.drawAll(graphics);
        });
        area.waitForMouseEvents((x, y) -> callback(area, new Point(x, y), drawing));
    }
}
