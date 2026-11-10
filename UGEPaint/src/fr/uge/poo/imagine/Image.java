package fr.uge.poo.imagine;

public interface Image {
    String name();
    int size();
    double hue();

    static Image downloadLazy(String url) {
        return new LazyImage(url);
    }
}
