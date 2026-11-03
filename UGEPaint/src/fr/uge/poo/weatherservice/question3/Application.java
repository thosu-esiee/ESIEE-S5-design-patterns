package fr.uge.poo.weatherservice.question3;

public class Application {
    public void start() throws InterruptedException {
        System.out.println("Warming  up");
        Thread.sleep(5_000);
        System.out.println("Starting");

        Thread.ofPlatform().start(() -> {
            var service = WeatherServices.getInstance();
            for(;;){
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    throw new AssertionError();
                }
                if (service.isEmpty()) {
                    continue;
                }
                System.out.println("Paris : "+service.get().query("Paris"));
            }
        });

        Thread.ofPlatform().start(() -> {
            for(;;){
                var service = WeatherServices.getInstance();
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    throw new AssertionError();
                }
                if (service.isEmpty()) {
                    continue;
                }
                System.out.println("Madrid : "+service.get().query("Madrid"));
            }
        });
    }

    static void main(String[] args) throws InterruptedException {
         var application = new Application();
         application.start();
    }
}