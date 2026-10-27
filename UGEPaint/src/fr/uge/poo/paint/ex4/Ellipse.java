package fr.uge.poo.paint.ex4;

import java.awt.*;
import java.util.Objects;

public record Ellipse(int x, int y, int width, int height) implements Figure {
    public void draw(Graphics2D graphics) {
        graphics.drawOval(x, y, width, height);
    }

    public int squareDistance(Point p) {
        Objects.requireNonNull(p);
        return p.squaredDistanceTo(new Point(x+width/2, y+height/2));
    }
}
