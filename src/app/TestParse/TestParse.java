package app.TestParse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.impl.file.PsiJavaDirectoryImpl;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import ui.ShowVersionUI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TestParse extends AnAction {

    /**
     * 获取Gson
     */
    private static Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();

    /**
     * asdasdasd
     *
     * @param e
     */
    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here


        Project project = e.getData(CommonDataKeys.PROJECT);
        PsiFile psiFile = e.getData(CommonDataKeys.PSI_FILE);

        PsiDocumentManager documentManager = PsiDocumentManager.getInstance(project);

        PsiFile[] psiFiles = FilenameIndex.getFilesByName(project, "LearnApplication.java", GlobalSearchScope.projectScope(project));

        Collection<VirtualFile> aaaa = FilenameIndex.getAllFilesByExt(project, "java", GlobalSearchScope.projectScope(project));

        VirtualFile vfile = project.getProjectFile();


        List<PostInfo> res = new ArrayList<>();

        PsiFileSystemItem[] bbb = FilenameIndex.getFilesByName(project, "src", GlobalSearchScope.projectScope(project), true);
        PsiJavaDirectoryImpl ccc = (PsiJavaDirectoryImpl) bbb[0];
        List<PsiFile> myFiles = walkDir(ccc);
        for (PsiFile file : myFiles) {
            if (file != null && file.getLanguage().toString().equals("Language: JAVA")) {
                PsiJavaFile javaFile = (PsiJavaFile) file;
                PsiClass[] javaClss = javaFile.getClasses();
                for (PsiClass thisClass : javaClss) {
                    if (thisClass != null) {
                        PsiMethod[] methods = thisClass.getMethods();
                        for (PsiMethod method : methods) {
                            if (method != null) {
                                PsiDocComment doc = method.getDocComment();
                                if (doc != null) {
                                    PostInfo info = new PostInfo();
                                    info.func = method.getName();
                                    info.path = file.getName();
                                    info.doc = doc.getText();
                                    res.add(info);
                                }
                            }
                        }
                    }
                }
            }
        }

        String jsonStr = gson.toJson(res);
        ShowVersionUI ui = new ShowVersionUI();
        ui.setShowVersion(jsonStr);
        ui.pack();
        ui.setVisible(true);

        return;


    }

    private ArrayList<PsiFile> walkDir(PsiJavaDirectoryImpl dir) {
        PsiFile[] files = dir.getFiles();
        ArrayList<PsiFile> resList = new ArrayList<>();
        for (PsiFile file : files) {
            resList.add(file);
        }
        PsiElement[] children = dir.getChildren();
        for (PsiElement c : children) {
            if (c instanceof PsiJavaDirectoryImpl) {
                List<PsiFile> newList = walkDir((PsiJavaDirectoryImpl) c);
                resList.addAll(newList);
            }
        }
        return resList;
    }
}
