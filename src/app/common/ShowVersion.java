package app.common;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import ui.ShowVersionUI;

public class ShowVersion extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        ShowVersionUI ui = new ShowVersionUI();
        ui.setShowVersion("hello miaomiao");
        ui.pack(); //TODO 一定要加，1. main不会执行 2. pack不加，Panel缩成渣渣（不加载）
        ui.setVisible(true);
    }
}
