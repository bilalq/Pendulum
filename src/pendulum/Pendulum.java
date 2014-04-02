package pendulum;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import pendulum.gui.Window;

@SuppressWarnings("serial")
public class Pendulum extends JFrame {

    // Stack of windows maintained by the frame
    protected final Stack<Window> windowStack;

    // Name of the application
    protected String appName;

    /**
     * Initializes gui application
     *
     * @param appName Name of application that is used as the title prefix
     * @param initialWindow Initial window to launch when the app is rendered
     */
    public Pendulum(String appName, Window initialWindow) {
        super(appName);
        this.appName = appName;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.windowStack = new Stack<Window>();
        this.windowStack.push(initialWindow);
        this.add(initialWindow);
    }

    /**
     * Packs, centers, and displays the gui.
     */
    public void render() {
        this.pack();
        this.setLocationRelativeTo(null);
        this.setMenu();
        this.setVisible(true);
    }

    /**
     * Pushes a new window onto the stack and updates the gui.
     *
     * @param window
     */
    public void pushWindow(Window window) {
        this.setActiveWindow(this.windowStack.peek(), window);
        this.windowStack.push(window);
    }

    /**
     * Pops a window from the stack and updates the gui.
     *
     * @return Window that was popped from the stack
     */
    public Window popWindow() {
        Window prev = this.windowStack.pop();
        Window next = this.windowStack.peek();
        this.setActiveWindow(prev, next);
        return prev;
    }

    /**
     * Helper method to set the current active window.
     *
     * @param previous Last window
     * @param next Current window
     */
    protected void setActiveWindow(Window previous, Window next) {
        this.add(next);
        this.remove(previous);
        if (next.getTitle() == null) {
            this.setTitle(this.appName);
        } else {
            this.setTitle(this.appName + " | " + next.getTitle());
        }
        next.focus();
        this.validate();
        this.repaint();
        this.render();
    }

    /**
     * Constructs the menu for the window.
     *
     * @param currentWindow
     */
    protected void setMenu() {
        Window currentWindow = this.windowStack.peek();
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription("Main menu");
        menuBar.add(menu);

        for (JMenuItem item : currentWindow.getMenuItems()) {
            menu.add(item);
        }

        JMenuItem quit = new JMenuItem("Quit", KeyEvent.VK_T);
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Pendulum.this.dispose();
                System.exit(0);
            }
        });
        menu.add(quit);

        this.setJMenuBar(menuBar);
    }

    /**
     * Redraws the gui.
     */
    public void redraw() {
        this.validate();
        this.repaint();
        this.pack();
        this.setVisible(true);
    }

    public Stack<Window> getWindowStack() {
        return this.windowStack;
    }

}
