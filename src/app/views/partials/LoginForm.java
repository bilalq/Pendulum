package app.views.partials;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class LoginForm extends JPanel {

    public LoginForm() {
        super(new GridLayout(3, 1));

        this.add(new JLabel("Username:"));
        this.add(new JTextField("", 10));
        this.add(new JButton("Login"));

        this.setBorder(new EmptyBorder(10, 80, 20, 80) );
    }

}
