package fr.uge.poo.paint.ex3;

import java.awt.*;

public sealed interface Figure permits Line, Rectangle, Ellipse {
    public void draw(Graphics2D graphcis);
}
