package app.common;

import com.intellij.openapi.fileEditor.*;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MyFileListener implements FileEditorManagerListener  {

//   static List<String> populFileList = new ArrayList<>();

    @Override
    public void fileOpened(@NotNull FileEditorManager source, @NotNull VirtualFile file) {
//        populFileList.add(file.getPath());
        System.out.println(file.getPath());
    }
    @Override
    public void  selectionChanged(@NotNull FileEditorManagerEvent event) {

//        populFileList.add(event.getNewFile().getPath());
        System.out.println(event.getNewFile().getPath());

    }

}
