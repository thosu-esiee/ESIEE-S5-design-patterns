package fr.uge.poo.paint.ex5;

import java.awt.*;

public sealed interface Figure permits Line, Rectangle, Ellipse {
    void draw(Graphics graphics, PaintColor color);
    int squareDistance(Point p);
}
