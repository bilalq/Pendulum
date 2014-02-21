package pendulum.view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Stack;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GuiView extends JFrame {

    // Serialization number
    private static final long serialVersionUID = 6776430621716469058L;

    // Stack of windows maintained by the frame
    private final Stack<BaseWindow> windowStack;

    private String appName;

    /**
     * Initializes gui application
     */
    public GuiView(String appName, BaseWindow initialWindow) {
        super(appName);
        this.appName = appName;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.windowStack = new Stack<BaseWindow>();
        this.windowStack.push(initialWindow);
        this.add(initialWindow);
    }

    /**
     * Packs, centers, and displays the gui.
     */
    public void render() {
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Pushes a new window onto the stack and updates the gui.
     *
     * @param window
     */
    public void pushWindow(BaseWindow window) {
        this.setActiveWindow(this.windowStack.peek(), window);
        this.windowStack.push(window);
    }

    /**
     * Pops a window from the stack and updates the gui.
     */
    public void popWindow() {
        BaseWindow prev = this.windowStack.pop();
        BaseWindow next = this.windowStack.peek();
        this.setActiveWindow(prev, next);
    }

    /**
     * Helper method to set the current active window.
     *
     * @param previous Last window
     * @param next Current window
     */
    private void setActiveWindow(BaseWindow previous, BaseWindow next) {
        this.add(next);
        this.remove(previous);
        if (next.getTitle() == null) {
            this.setTitle(this.appName);
        } else {
            this.setTitle(this.appName + " | " + next.getTitle());
        }
        next.focus();
        if (! next.getTitle().equals("Login")) {
            this.setMenu(next);
        }
        this.validate();
        this.repaint();
        this.render();
    }

    /**
     * Constructs the menu for the window.
     *
     * @param currentWindow
     */
    private void setMenu(BaseWindow currentWindow) {
        final BaseWindow current = currentWindow;
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
                GuiView.this.dispose();
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

    public Stack<BaseWindow> getWindowStack() {
        return this.windowStack;
    }

}
