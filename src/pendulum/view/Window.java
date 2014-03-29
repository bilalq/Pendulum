package pendulum.view;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import pendulum.Pendulum;

@SuppressWarnings("serial")
public abstract class Window extends JPanel {

    // Title of panel
    protected String title;

    // List of menu items for this window
    protected List<JMenuItem> menuItems;

    public Window() {
        super();
    }

    public Window(LayoutManager layout) {
        super(layout);
    }

    public Window(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    public Window(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }

    /**
     * Called when window is brought into focus.
     */
    protected abstract void onFocus();

    /**
     * Opens the specified window and pushes it onto the stack.
     *
     * @param window
     */
    public void open(Window window) {
        Pendulum parent = this.getFrame();
        parent.pushWindow(window);
    }

    /**
     * Closes current window and pops from the stack.
     */
    public void close() {
        Pendulum parent = this.getFrame();
        parent.popWindow();
    }

    /**
     * Get reference to parent frame.
     *
     * @return GuiView Root GUI frame of application
     */
    public Pendulum getFrame() {
        return (Pendulum) SwingUtilities.getWindowAncestor(this);
    }

    /**
     * Method that is triggered when a panel is brought into focus.
     */
    public void focus() {
        this.onFocus();
    }

    /**
     * Redraws current panel.
     */
    public void redraw() {
        this.getFrame().redraw();
    }

    public String getTitle() {
        return this.title;
    }

    public List<JMenuItem> getMenuItems() {
        if (menuItems == null) {
            this.menuItems = new ArrayList<JMenuItem>();
        }
        return this.menuItems;
    }

    public Window addMenuItem(JMenuItem item) {
        this.getMenuItems().add(item);
        return this;
    }
}
