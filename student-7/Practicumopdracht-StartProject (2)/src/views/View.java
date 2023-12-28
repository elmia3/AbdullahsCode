package views;

import javafx.scene.Parent;

/**
 * @author abdul
 */
public abstract class View {
    private Parent root;

    public View() {
        this.root = initializeview();
    }

    public Parent getRoot() {
        return root;
    }

    protected abstract Parent initializeview();
}