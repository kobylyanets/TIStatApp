package ru.indraft.utils;

import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

public class JavaFXUtils {

    private static JavaFXUtils me;

    private JavaFXUtils() {
    }

    public static JavaFXUtils get() {
        if (me == null) {
            me = new JavaFXUtils();
        }
        return me;
    }

    public EventHandler<MouseEvent> consumeMouseEventfilter = (MouseEvent mouseEvent) -> {
        if (((Toggle) mouseEvent.getSource()).isSelected()) {
            mouseEvent.consume();
        }
    };

    public void addAlwaysOneSelectedSupport(final ToggleGroup toggleGroup) {
        toggleGroup.getToggles().addListener((ListChangeListener.Change<? extends Toggle> c) -> {
            while (c.next()) {
                for (final Toggle addedToggle : c.getAddedSubList()) {
                    addConsumeMouseEventfilter(addedToggle);
                }
            }
        });
        toggleGroup.getToggles().forEach(this::addConsumeMouseEventfilter);
    }

    private void addConsumeMouseEventfilter(Toggle toggle) {
        ((ToggleButton) toggle).addEventFilter(MouseEvent.MOUSE_PRESSED, consumeMouseEventfilter);
        ((ToggleButton) toggle).addEventFilter(MouseEvent.MOUSE_RELEASED, consumeMouseEventfilter);
        ((ToggleButton) toggle).addEventFilter(MouseEvent.MOUSE_CLICKED, consumeMouseEventfilter);
    }

}
