package com.layoutmanager.actions;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.layoutmanager.persistence.Layout;
import com.layoutmanager.persistence.LayoutConfig;
import org.jetbrains.annotations.NotNull;

public class OverwriteLayoutAction extends AnAction {

    public final int number;

    public OverwriteLayoutAction(int number) {

        this.number = number;

        Layout layout = LayoutConfig.getInstance().getLayout(number);
        Presentation presentation = this.getTemplatePresentation();
        presentation.setText(layout.getName());
        presentation.setIcon(AllIcons.Actions.Edit);
    }

    @Override
    public void update(AnActionEvent e) {
        Layout layout = LayoutConfig.getInstance().getLayout(number);
        e.getPresentation().setText(layout.getName());
        super.update(e);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Layout updatedLayout = LayoutCreator.create(event.getProject());

        if (updatedLayout != null) {
            this.storeLayout(updatedLayout);
        }
    }

    private void storeLayout(Layout layout) {
        LayoutConfig.getInstance().setLayout(this.number, layout);
    }
}
