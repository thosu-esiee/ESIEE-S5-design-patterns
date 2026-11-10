package fr.uge.poo.imagine;

import java.util.Map;

class LazyImage implements Image {

    public String url;
    public boolean isLoaded;
    public com.evilcorp.imagine.Image image;

    public LazyImage(String url) {
        this.url = url;
    }

    private com.evilcorp.imagine.Image image() {
        if (!isLoaded) {
            image = com.evilcorp.imagine.Image.download(url);
            isLoaded = true;
        }
        return image;
    }

    public String name() {
        return image().name();
    }

    public int size() {
        return image().size();
    }

    public double hue() {
        return image().hue();
    }
    // oi mate
    // Do not override equals() or hashCode() because that is the behaviour intended
//    @Override
//    public boolean equals(Object o) {
//        return super.equals(o);
//    }
//
//    @Override
//    public int hashCode() {
//        return super.hashCode();
//    }

    public static void main(String[] args) {
        var map = Map.of("cat","http://www.example.com/cat.png",
                "dog","http://www.example.com/dog.png",
                "mice","http://www.example.com/mice.png");
        var images = map.values().stream().map(Image::downloadLazy).toList();
        System.out.println(images.get(0).hue());
    }
}
