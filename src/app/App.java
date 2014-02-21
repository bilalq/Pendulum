package app;
import app.views.HomeWindow;
import pendulum.view.GuiView;

public class App {
    public static void main(String[] args) {
        GuiView view = new GuiView("My App", new HomeWindow());
        view.render();
    }
}
