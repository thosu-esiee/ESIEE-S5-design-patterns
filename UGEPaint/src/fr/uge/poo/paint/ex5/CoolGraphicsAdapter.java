package fr.uge.poo.paint.ex5;

import com.evilcorp.coolgraphics.CoolGraphics;

import java.util.function.BiConsumer;

public class CoolGraphicsAdapter implements Graphics {
    private final CoolGraphics area;

    public CoolGraphicsAdapter() {
        area = new CoolGraphics("Example",800,600);
    }

    public CoolGraphics.ColorPlus convertColor(PaintColor color) {
        return switch (color) {
            case WHITE -> CoolGraphics.ColorPlus.WHITE;
            case BLACK -> CoolGraphics.ColorPlus.BLACK;
            case RED -> CoolGraphics.ColorPlus.RED;
            case GREEN -> CoolGraphics.ColorPlus.GREEN;
            case BLUE -> CoolGraphics.ColorPlus.BLUE;
        };
    }

    @Override
    public void clear(PaintColor color) {
        area.repaint(convertColor(color));
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2, PaintColor color) {
        area.drawLine(x1, y1, x2, y2, convertColor(color));
    }

    @Override
    public void drawRectangle(int x, int y, int width, int height, PaintColor color) {
        var colorPlus = convertColor(color);
        area.drawLine(x, y, x+width, y, colorPlus);
        area.drawLine(x+width, y, x+width, y+height, colorPlus);
        area.drawLine(x+width, y+height, x, y+height, colorPlus);
        area.drawLine(x, y+height, x, y, colorPlus);
    }

    @Override
    public void drawEllipse(int x, int y, int width, int height, PaintColor color) {
        area.drawEllipse(x, y, width, height, convertColor(color));
    }

    @Override
    public void handleClick(BiConsumer<Integer, Integer> callback) {
        area.waitForMouseEvents((x, y) -> {
            callback.accept(x, y);
        });
    }
}
