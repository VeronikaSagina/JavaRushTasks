package com.javarush.task.task32.task3209;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public HTMLDocument getDocument() {
        return document;
    }

    public Controller(View view) {
        this.view = view;
    }

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }

    public void resetDocument() {
        if (document != null) {
            document.removeUndoableEditListener(view.getUndoListener());
        }
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        document = (HTMLDocument) htmlEditorKit.createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }

    public void setPlainText(String text) {
        resetDocument();
        StringReader reader = new StringReader(text);
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        try {
            htmlEditorKit.read(reader, document, 0);
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public String getPlainText() {
        StringWriter stringWriter = new StringWriter();
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        try {
            htmlEditorKit.write(stringWriter, document, 0, document.getLength());
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
        return stringWriter.toString();
    }

    public void exit() {
        System.exit(0);
    }

    public void init() {
        createNewDocument();
    }

    public void createNewDocument() {
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор.");
        view.resetUndo();
        currentFile = null;
    }

    public void openDocument() {
        view.selectHtmlTab();
        JFileChooser chooser = new JFileChooser();
        HTMLFileFilter htmlFileFilter = new HTMLFileFilter();
        chooser.setFileFilter(htmlFileFilter);
        final int i = chooser.showOpenDialog(view);
        if (i == JFileChooser.OPEN_DIALOG) {
            currentFile = chooser.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());
            try (FileReader fileReader = new FileReader(currentFile)) {
                HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
                htmlEditorKit.read(fileReader, document, 0);
                view.resetUndo();
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocument() {
        view.selectHtmlTab();
        if (currentFile != null) {
            try (FileWriter fileWriter = new FileWriter(currentFile)) {
                HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
                htmlEditorKit.write(fileWriter, document, 0, document.getLength());
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        } else saveDocumentAs();
    }

    public void saveDocumentAs() {
        view.selectHtmlTab();
        JFileChooser chooser = new JFileChooser();
        HTMLFileFilter htmlFileFilter = new HTMLFileFilter();
        chooser.setFileFilter(htmlFileFilter);
        final int i = chooser.showSaveDialog(view);
        if (i == JFileChooser.APPROVE_OPTION) {
            currentFile = chooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try (FileWriter fileWriter = new FileWriter(currentFile)) {
                HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
                htmlEditorKit.write(fileWriter, document, 0, document.getLength());
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }
}
