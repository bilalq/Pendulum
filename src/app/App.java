package app;
import app.views.HomeWindow;
import pendulum.Pendulum;

public class App {
    public static void main(String[] args) {
        Pendulum pendulum = new Pendulum("My App", new HomeWindow());
        pendulum.render();
    }
}
