package net.bati.guilib.gui.screen;

import net.bati.guilib.gui.components.Widget;
import net.minecraft.client.util.math.MatrixStack;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class ScreenUtils {

    public static void renderWidgets(HashMap<String, Widget> widgets, MatrixStack matrices, int x, int y, float delta) {
        Optional<Map.Entry<String, Widget>> widgetEntry = widgets.entrySet().stream().filter((entry) -> entry.getValue().isVisible() && entry.getValue().isHovered(x,y)).max(Comparator.comparingInt(current -> current.getValue().getRecursiveZ()));

        widgets.forEach((key, value) -> {
            if(value.hasParent())
                value.setFocused(widgetEntry.isPresent() && key.contentEquals(widgetEntry.get().getKey()) && widgetEntry.get().getValue().getParent().isFocused());
            else
                value.setFocused(widgetEntry.isPresent() && key.contentEquals(widgetEntry.get().getKey()));

            value.render(matrices, x, y, delta);
        });
    }
}
