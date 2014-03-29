package app.views;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import pendulum.view.Window;

@SuppressWarnings("serial")
public class HomeWindow extends Window {

    public HomeWindow() {
        super();
        this.title = "Home";

        JPanel form = new JPanel(new GridLayout(3, 1));
        JLabel label = new JLabel("Username:");
        JTextField input = new JTextField("", 10);
        JButton submit = new JButton("Login");

        form.add(label);
        form.add(input);
        form.add(submit);
        form.setBorder(new EmptyBorder(10, 80, 20, 80) );
        this.add(form);
    }

    protected void onFocus() {
        return;
    }
}
