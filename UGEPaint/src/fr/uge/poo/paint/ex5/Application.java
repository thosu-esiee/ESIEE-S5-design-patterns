package fr.uge.poo.paint.ex5;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Paths;

public class Application {
    private static void callback(Paint paint, Point mousePoint, Drawing drawing) {
        drawing.onClick(mousePoint);
        paint.clear(PaintColor.WHITE);
        paint.draw(drawing);
    }

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
        var paint = new Paint(graphics);
        paint.clear(PaintColor.WHITE);
        paint.draw(drawing);
        paint.handleClick((x, y) -> callback(paint, new Point(x, y), drawing));
    }
}
