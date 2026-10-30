package fr.uge.poo.paint.ex5;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Paths;

public class Application {
    static void main(String[] args) throws IOException {
        Graphics graphics;
        if (args.length == 2 && args[1].equals("-legacy")) {
            graphics = new SimpleGraphicsAdapter();
            IO.println("Using Simple graphics");
        } else if (args.length == 1) {
            graphics = new CoolGraphicsAdapter();
            IO.println("Using EvilCorp graphics");
        } else {
            IO.println("Wrong arguments!");
            return;
        }

        var filename = args[0];
        var path = Paths.get(filename);
        var drawing = Drawing.fromFile(path);

        graphics.clear(PaintColor.WHITE);
        drawing.drawAll(graphics);
        graphics.handleClick((x, y) -> {
            graphics.clear(PaintColor.WHITE);
            drawing.onClick(new Point(x, y));
            drawing.drawAll(graphics);
        });
    }
}
