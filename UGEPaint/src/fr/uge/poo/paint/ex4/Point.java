package fr.uge.poo.paint.ex4;

public record Point(int x, int y) {
    public int squaredDistanceTo(Point p) {
        return (p.x - x) * (p.x - x) + (p.y - y) * (p.y - y);
    }
}
