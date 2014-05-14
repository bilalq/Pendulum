package pendulum.gui;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import pendulum.Pendulum;

@SuppressWarnings("serial")
public abstract class Window extends JPanel {

    /** Title of window */
    protected String title;

    /** List of menu items for this window */
    protected final List<JMenuItem> menuItems = new ArrayList<JMenuItem>();

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
        Pendulum frame = this.getFrame();
        frame.pushWindow(window);
    }

    /**
     * Closes current window and pops from the stack.
     */
    public void close() {
        Pendulum frame = this.getFrame();
        frame.popWindow();
    }

    /**
     * Get reference to the root Pendulum instance.
     *
     * @return Pendulum instance
     */
    public Pendulum getFrame() {
        return (Pendulum) SwingUtilities.getWindowAncestor(this);
    }

    /**
     * Method that is triggered when a window is brought into focus.
     */
    public void focus() {
        this.onFocus();
    }

    /**
     * Redraws current window.
     */
    public void redraw() {
        this.getFrame().redraw();
    }

    /**
     * Gets the title of the window
     * */
    public String getTitle() {
        return this.title;
    }

    /**
     * Gets the list of menu items in the window
     */
    public List<JMenuItem> getMenuItems() {
        return this.menuItems;
    }

    /**
     * Helper function for adding an item to the window menu
     *
     * @param item The menu item to add
     * @return The window itself to allow for fluent chaining
     */
    public Window addMenuItem(JMenuItem item) {
        this.menuItems.add(item);
        return this;
    }
}
