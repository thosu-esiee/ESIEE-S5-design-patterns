package fr.uge.poo.paint.ex5;

import java.awt.*;
import java.util.Objects;

public record Rectangle(int x, int y, int width, int height) implements Figure {
    public void draw(Graphics graphics, PaintColor color) {
        graphics.drawRectangle(x, y, width, height, color);
    }

    public int squareDistance(Point p) {
        Objects.requireNonNull(p);
        return p.squaredDistanceTo(new Point(x+width/2, y+height/2));
    }
}
