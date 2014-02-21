package pendulum.view;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public abstract class BaseWindow extends JPanel {

	// Serialization data
	public static final long serialVersionUID = 1006939417026339945L;

	// Title of panel
    protected String title;

    // List of menu items for this window
    protected List<JMenuItem> menuItems;

	public BaseWindow() {
		super();
	}

	public BaseWindow(LayoutManager layout) {
		super(layout);
	}

	public BaseWindow(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
	}

	public BaseWindow(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}

    /**
     * Called when window is brought into focus.
     */
    protected abstract void onFocus();

	/**
	 * Open the specified window and push it onto the stack.
     *
	 * @param window
	 */
	public void open(BaseWindow window) {
		GuiView parent = this.getFrame();
		parent.pushWindow(window);
	}

	/**
	 * Close current window and pop from the stack.
     *
	 */
	public void close() {
		GuiView parent = this.getFrame();
		parent.popWindow();
	}

	/**
	 * Get reference to parent frame.
     *
	 * @return GuiView Root GUI frame of application
	 */
	public GuiView getFrame() {
		return (GuiView) SwingUtilities.getWindowAncestor(this);
	}

    /**
     * Method that is triggered when a panel is brought into focus.
     *
     */
    public void focus() {
    	this.onFocus();
    }

    /**
     * Redraws current panel.
     *
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

    public BaseWindow addMenuItem(JMenuItem item) {
        this.getMenuItems().add(item);
        return this;
    }
}
