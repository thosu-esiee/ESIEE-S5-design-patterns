package fr.uge.poo.paint.ex5;

import fr.uge.poo.simplegraphics.SimpleGraphics;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.function.BiConsumer;

public class Paint {
    private final Graphics graphics;

    public Paint(Graphics graphics) {
        this.graphics = graphics;
    }

    public void clear(PaintColor color) {
        graphics.clear(color);

    }

    public void draw(Drawing drawing) {
        drawing.drawAll(graphics);
    }

    public void handleClick(BiConsumer<Integer, Integer> callback) {
        graphics.handleClick(callback);
    }

    public void testdraw() {
        graphics.drawLine(20, 40, 220, 240, PaintColor.RED);
        graphics.drawRectangle(300, 300, 200, 150, PaintColor.GREEN);
        graphics.drawEllipse(400, 200, 200, 150, PaintColor.BLUE);
    }
}
