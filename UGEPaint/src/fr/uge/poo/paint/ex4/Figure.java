package fr.uge.poo.paint.ex4;

import java.awt.*;

public sealed interface Figure permits Line, Rectangle, Ellipse {
    void draw(Graphics2D graphcis);
    int squareDistance(Point p);
}
