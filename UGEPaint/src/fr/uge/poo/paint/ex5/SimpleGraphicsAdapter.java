package fr.uge.poo.paint.ex5;

import fr.uge.poo.simplegraphics.SimpleGraphics;

import java.awt.*;
import java.util.function.BiConsumer;

public class SimpleGraphicsAdapter implements Graphics {
    private final SimpleGraphics area;

    public SimpleGraphicsAdapter() {
        area = new SimpleGraphics("area", 800, 600);
    }
    
    private static Color convertColor(PaintColor color) {
        return switch (color) {
            case WHITE -> Color.WHITE;
            case BLACK -> Color.BLACK;
            case RED -> Color.RED;
            case GREEN -> Color.GREEN;
            case BLUE -> Color.BLUE;
        };
    }

    @Override
    public void clear(PaintColor color) {
        area.clear(convertColor(color));
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2, PaintColor color) {
        area.render(graphics -> {
            graphics.setColor(convertColor(color));
            graphics.drawLine(x1, y1, x2, y2);
        });
    }

    @Override
    public void drawRectangle(int x, int y, int width, int height, PaintColor color) {
        area.render(graphics -> {
            graphics.setColor(convertColor(color));
            graphics.drawRect(x, y, width, height);
        });
    }

    @Override
    public void drawEllipse(int x, int y, int width, int height, PaintColor color) {
        area.render(graphics -> {
            graphics.setColor(convertColor(color));
            graphics.drawOval(x, y, width, height);
        });
    }

    @Override
    public void handleClick(BiConsumer<Integer, Integer> callback) {
        area.waitForMouseEvents((x, y) -> {
            callback.accept(x, y);
        });
    }
}
