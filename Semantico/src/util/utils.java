package util;

// Daniel Busarello

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileWriter;
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

    public static void saveFile(String content) {
        try {
            String curDir = System.getProperty("user.dir") + "/generatedCode.il";

            FileWriter archive = new FileWriter(curDir);
            PrintWriter writeArc = new PrintWriter(archive);
            writeArc.println(content);
            writeArc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
