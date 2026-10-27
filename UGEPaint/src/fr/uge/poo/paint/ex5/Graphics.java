package fr.uge.poo.paint.ex5;

import java.util.function.BiConsumer;

public interface Graphics {
    void clear(PaintColor color);
    void drawLine(int x1, int y1, int x2, int y2, PaintColor color);
    void drawRectangle(int x, int y, int width, int height, PaintColor color);
    void drawEllipse(int x, int y, int width, int height, PaintColor color);
    void handleClick(BiConsumer<Integer, Integer> callback);
}
