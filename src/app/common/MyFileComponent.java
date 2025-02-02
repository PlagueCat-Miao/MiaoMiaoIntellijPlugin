package app.common;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.BaseComponent;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.util.messages.MessageBus;
import org.jetbrains.annotations.NotNull;

public class MyFileComponent implements BaseComponent {

    @Override
    public void initComponent() {
        MessageBus bus = ApplicationManager.getApplication().getMessageBus();
        MyFileListener array = new MyFileListener();
        bus.connect().subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, array);
    }




}
