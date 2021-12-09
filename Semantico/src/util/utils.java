package util;

// Daniel Busarello

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class utils {
    public static File file = null;

    public static final String FLOAT = "float64";
    public static final String INTEGER = "int64";
    public static final String BOOL = "bool";
    public static final String STRING = "string";

    public static final String LDCR8 = "ldc.r8";
    public static final String LDCI8 = "ldc.i8";
    public static final String LDSTR = "ldstr";
    public static final String CONVR8 = "conv.r8";
    public static final String CONVI8 = "conv.i8";

    public static final String ADD = "add";
    public static final String SUB = "sub";
    public static final String MUL = "mul";
    public static final String DIV = "div";

    public static final String AND = "AND";
    public static final String OR = "OR";

    public static boolean isInteger(String str) {
        if (str.matches(INTEGER))
            return true;
        return str.matches("[0-9]*");
    }

    public static boolean isFloat(String str) {
        if (str.matches(FLOAT))
            return true;
        return str.matches("[+-]?([0-9]*[.])?[0-9]+");
    }

    public static String getTypeByPrefix(String id) {
        if (id.startsWith("I")) {
            return INTEGER;
        } else if (id.startsWith("F")) {
            return FLOAT;
        } else if (id.startsWith("S")) {
            return STRING;
        } else
            return BOOL;
    }

    public static void saveFile(String text) {
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivo .il", "il"));

        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();

            if(!file.getAbsolutePath().contains(".il")) {
                file = new File(file.getPath() + ".il");
            }

            saveContentFile(text);
        }
    }

    private static void saveContentFile(String text) {
        try {
            PrintWriter pw = new PrintWriter(file);

            pw.write(text);
            pw.flush();

            pw.close();

        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo. " + e.getMessage());
        }
    }
}
