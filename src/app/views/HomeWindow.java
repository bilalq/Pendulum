package app.views;
import pendulum.view.Window;

import app.views.partials.LoginForm;

@SuppressWarnings("serial")
public class HomeWindow extends Window {

    public HomeWindow() {
        super();
        this.title = "Home";

        LoginForm form = new LoginForm();
        this.add(form);
    }

    protected void onFocus() {
        return;
    }
}
