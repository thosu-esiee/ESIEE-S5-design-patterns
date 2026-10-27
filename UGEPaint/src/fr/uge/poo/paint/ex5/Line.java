package fr.uge.poo.paint.ex5;

import java.awt.*;
import java.util.Objects;

public record Line(int x1, int y1, int x2, int y2) implements Figure {
    public void draw(Graphics graphics, PaintColor color) {
        graphics.drawLine(x1, y1, x2, y2, color);
    }

    public int squareDistance(Point p) {
        Objects.requireNonNull(p);
        return p.squaredDistanceTo(new Point(x1 + (x2-x1)/2, y1 + (y2-y1)/2));
    }
}
