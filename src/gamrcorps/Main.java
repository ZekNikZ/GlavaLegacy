package gamrcorps;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {

    private static final String commonImports = "import java.util.*;\nimport java.lang.*;\nimport java.io.*;\nimport java.awt.*;\nimport javax.swing.*;\nimport java.text.*;\nimport java.util.regex.*;\n";
    private static final String variables = "    public static int m = 0;\n    public static int n = 0;\n    public static double d = 0d;\n    public static float f = 0f;\n    public static String s = \"\";\n    public static String t = \"\";\n    public static String u = \"abcdefghijklmnopqrstuvwxyz\";\n    public static String U = \"ABCDEFGHIJKLMNOPQRSTUVWXYZ\";\n    public static char c = 'A';\n    public static boolean T = true;\n    public static boolean F = false;\n";
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Missing File Argument");
            return;
        }
        String fileContents = null;
        try {
            if (!Objects.equals(args[0].split("\\.")[args[0].split("\\.").length - 1], "jg")) {
                System.out.println("Wrong file extension!");
                return;
            }
            File file = new File(args[0]);
            FileInputStream fr = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fr.read(data);
            fr.close();
            fileContents = new String(data, "UTF-8");
        } catch (FileNotFoundException fnf) {
            System.out.println("File not found!");
        } catch (IOException io) {
            System.out.println("Could not read data from file!");
        }
        String mainClassName = "Main";
        if (fileContents != null) {
            int pointer = 0;
            boolean isString = false;
            boolean massImport = false;
            boolean mainMethod = false;
            boolean mainClass = false;
            List<Character> openSeparators = new ArrayList<>();
            while (pointer < fileContents.length()) {
                try {
                    switch (fileContents.charAt(pointer)) {
                        case '"':
                            if (!isString){ openSeparators.add('"'); isString=true;}
                            else if (isString && fileContents.charAt(pointer-1) != '\\'){ openSeparators.remove(openSeparators.size() - 1);isString = false;}
                            pointer++;
                            continue;
                        case 'I':
                            if (!isString && fileContents.charAt(pointer + 1) == '|' && fileContents.charAt(pointer + 2) != '|') {
                                fileContents = insertStringAtPoint(fileContents, pointer, "import ", 2);
                                pointer += "import ".length() - 2;
                            }
                            pointer++;
                            continue;
                        case 'p':
                            if (!isString && fileContents.charAt(pointer + 1) == '|' && fileContents.charAt(pointer + 2) != '|') {
                                fileContents = insertStringAtPoint(fileContents, pointer, "public ", 2);
                                pointer += "public ".length() - 2;
                            }
                            if (!isString && Objects.equals(fileContents.substring(pointer, pointer + "p(".length()), "p(")) {
                                fileContents = insertStringAtPoint(fileContents, pointer, "System.out.print(", "p(".length());
                                pointer += "System.out.print(".length() - "p(".length() + 1;
                                openSeparators.add(')');
                            }
                            pointer++;
                            continue;
                        case 'P':
                            if (!isString && Objects.equals(fileContents.substring(pointer, pointer + "P(".length()), "P(")) {
                                fileContents = insertStringAtPoint(fileContents, pointer, "System.out.println(", "P(".length());
                                pointer += "System.out.println(".length() - "P(".length() + 1;
                                openSeparators.add(')');
                            }
                            pointer++;
                            continue;
                        case 'F':
                            if (!isString && Objects.equals(fileContents.substring(pointer, pointer + "F(".length()), "F(")) {
                                fileContents = insertStringAtPoint(fileContents, pointer, "System.out.printf(", "F(".length());
                                pointer += "System.out.printf(".length() - "F(".length() + 1;
                                openSeparators.add(')');
                            }
                            pointer++;
                            continue;
                        case 'c':
                            if (!isString && fileContents.charAt(pointer + 1) == '|' && fileContents.charAt(pointer + 2) != '|') {
                                fileContents = insertStringAtPoint(fileContents, pointer, "class ", 2);
                                pointer += "class ".length() - 2;
                            }
                            pointer++;
                            continue;
                        case 'C':
                            if (!isString && fileContents.charAt(pointer + 1) == '|' && fileContents.charAt(pointer + 2) != '|') {
                                fileContents = insertStringAtPoint(fileContents, pointer, "public class ", 2);
                                pointer += "public class ".length() - 2;
                                if (!mainClass) mainClassName = String.valueOf(fileContents.charAt(pointer + 2));
                                mainClass = true;
                            }
                            pointer++;
                            continue;
                        case 'm':
                            if (!isString && fileContents.charAt(pointer + 1) == '|' && fileContents.charAt(pointer + 2) != '|') {
                                fileContents = insertStringAtPoint(fileContents, pointer, "public static void main (String[] A) {", 2);
                                pointer += "public static void main (String[] A) {".length() - 2;
                                openSeparators.add('}');
                                mainMethod = true;
                            }
                            pointer++;
                            continue;
                        case 'd':
                            if (!isString && fileContents.charAt(pointer + 1) == '|' && fileContents.charAt(pointer + 2) != '|') {
                                fileContents = insertStringAtPoint(fileContents, pointer, "double ", 2);
                                pointer += "double ".length() - 2;
                            }
                            pointer++;
                            continue;
                        case 'f':
                            if (!isString && fileContents.charAt(pointer + 1) == '|' && fileContents.charAt(pointer + 2) != '|') {
                                fileContents = insertStringAtPoint(fileContents, pointer, "float ", 2);
                                pointer += "float ".length() - 2;
                            }
                            if (!isString && Objects.equals(fileContents.substring(pointer, pointer + "f(".length()), "f(")) {
                                fileContents = insertStringAtPoint(fileContents, pointer, "for(", "f(".length());
                                pointer += "for(".length() - "f(".length();
                                openSeparators.add(')');
                            }
                            pointer++;
                            continue;
                        case 'i':
                            if (!isString && fileContents.charAt(pointer + 1) == '|' && fileContents.charAt(pointer + 2) != '|') {
                                fileContents = insertStringAtPoint(fileContents, pointer, "int ", 2);
                                pointer += "int ".length() - 2;
                            }
                            if (!isString && Objects.equals(fileContents.substring(pointer, pointer + "i(".length()), "i(")) {
                                fileContents = insertStringAtPoint(fileContents, pointer, "if(", "i(".length());
                                pointer += "if(".length() - "i(".length();
                                openSeparators.add(')');
                            }
                            pointer++;
                            continue;
                        case 'b':
                            if (!isString && fileContents.charAt(pointer + 1) == '|' && fileContents.charAt(pointer + 2) != '|') {
                                fileContents = insertStringAtPoint(fileContents, pointer, "boolean ", 2);
                                pointer += "boolean ".length() - 2;
                            }
                            pointer++;
                            continue;
                        case 's':
                            if (!isString && fileContents.charAt(pointer + 1) == '|' && fileContents.charAt(pointer + 2) != '|') {
                                fileContents = insertStringAtPoint(fileContents, pointer, "String ", 2);
                                pointer += "String ".length() - 2;
                            }
                            pointer++;
                            continue;
                        case 't':
                            if (!isString && fileContents.charAt(pointer + 1) == '|' && fileContents.charAt(pointer + 2) != '|') {
                                fileContents = insertStringAtPoint(fileContents, pointer, "try {", 2);
                                pointer += "try {".length() - 2;
                                openSeparators.add('}');
                            }
                            pointer++;
                            continue;
                        case 'r':
                            if (!isString && fileContents.charAt(pointer + 1) == '|' && fileContents.charAt(pointer + 2) != '|') {
                                fileContents = insertStringAtPoint(fileContents, pointer, "return ", 2);
                                pointer += "return ".length() - 2;
                            }
                            pointer++;
                            continue;
                        case 'n':
                            if (!isString && fileContents.charAt(pointer + 1) == '|' && fileContents.charAt(pointer + 2) != '|') {
                                fileContents = insertStringAtPoint(fileContents, pointer, "new ", 2);
                                pointer += "new ".length() - 2;
                            }
                            pointer++;
                            continue;
                        case 'N':
                            if (!isString && fileContents.charAt(pointer + 1) == '|' && fileContents.charAt(pointer + 2) != '|') {
                                fileContents = insertStringAtPoint(fileContents, pointer, "null ", 2);
                                pointer += "null ".length() - 2;
                            }
                            pointer++;
                            continue;
                        case 'v':
                            if (!isString && fileContents.charAt(pointer + 1) == '|' && fileContents.charAt(pointer + 2) != '|') {
                                fileContents = insertStringAtPoint(fileContents, pointer, "void ", 2);
                                pointer += "void ".length() - 2;
                            }
                            pointer++;
                            continue;
                        case 'z':
                            if (!isString && fileContents.charAt(pointer + 1) == '|' && fileContents.charAt(pointer + 2) != '|') {
                                fileContents = insertStringAtPoint(fileContents, pointer, commonImports, 2);
                                pointer += commonImports.length() - 2;
                                massImport = true;
                            }
                            pointer++;
                            continue;
                        case 'M':
                            if (!isString && fileContents.charAt(pointer + 1) == '|' && fileContents.charAt(pointer + 2) != '|') {
                                fileContents = insertStringAtPoint(fileContents, pointer, "Math.", 2);
                                pointer += "Math.".length() - 2;
                            }
                            pointer++;
                            continue;
                        case '}':
                            if (!isString) openSeparators.remove(openSeparators.size() - 1);
                            pointer++;
                            continue;
                        case '(':
                            if (!isString) openSeparators.add(')');
                            pointer++;
                            continue;
                        case ')':
                            if (!isString) openSeparators.remove(openSeparators.size() - 1);
                            pointer++;
                            continue;
                        case '[':
                            if (!isString) openSeparators.add(']');
                            pointer++;
                            continue;
                        case ']':
                            if (!isString) openSeparators.remove(openSeparators.size() - 1);
                            pointer++;
                            continue;
                        case '#':
                            if (!isString) replaceChar(fileContents, pointer, "[]");
                            pointer++;
                            continue;
                        case '$':
                            if (!isString) {
                                fileContents = insertStringAtPoint(fileContents, pointer, "={", 1);
                                openSeparators.add('$');
                            }
                            pointer++;
                            continue;
                        case '\'':
                            if (!isString) {
                                fileContents = insertStringAtPoint(fileContents, pointer+2, "'", 0);
                                pointer+=2;
                            }
                            pointer++;
                            continue;
                        case '\n':
                            if (isString) {
                                fileContents = insertStringAtPoint(fileContents, pointer-1, "\\n", 2);
                            }
                            pointer++;
                            continue;
                        default:
                            if (isString) {
                                pointer++;
                                continue;
                            }
                            if (isWhitespace(fileContents.charAt(pointer))) {
                                pointer++;
                                continue;
                            }
                            pointer++;
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    pointer++;
                }
            }
            for (int i = openSeparators.size() - 1; i >= 0; i--) {
                if (openSeparators.get(i) == '}') fileContents += String.valueOf(';');
                if (openSeparators.get(i) == '$') {
                    fileContents += String.valueOf('}');
                } else {
                    fileContents += String.valueOf(openSeparators.get(i));
                }
            }
            if (!massImport && mainClass && mainMethod) {
                fileContents = commonImports + fileContents;
            } else if (!massImport && !mainClass && mainMethod) {
                fileContents = commonImports + "\npublic class " + mainClassName + " {\n" + variables + "\n" + fileContents + "\n;}";
            } else if (!massImport && !mainClass && !mainMethod) {
                fileContents = commonImports + "\npublic class " + mainClassName + " {\n" + variables + "\n    public static void main (String[] A) {\n        " + fileContents + ";\n    }\n}";
            }
            System.out.println(fileContents);
        }
        if (new File(mainClassName + ".java").exists()) {
            new File(mainClassName + ".java").delete();
        }
        try {
            new File(mainClassName + ".java").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter out = null;
        try {
            out = new PrintWriter(mainClassName + ".java");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        out.println(fileContents);
        out.close();
        String arguments = "";
        for (int i = 1; i < args.length; i++) {
            arguments += args[i] + " ";
        }
        try {
            runProcess("javac " + mainClassName + ".java", false);
            runProcess("java " + mainClassName + " "+ arguments, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (new File(mainClassName + ".java").exists()) {
            new File(mainClassName + ".java").delete();
        }
        if (new File(mainClassName + ".class").exists()) {
            new File(mainClassName + ".class").delete();
        }
    }

    public static String insertStringAtPoint(String str, int index, String toInsert, int removeAmount) {
        return str.substring(0, index) + toInsert + str.substring(index + removeAmount, str.length());
    }

    public static String replaceChar(String str, int index, String toInsert) {
        return str.substring(0, index) + toInsert + str.substring(index + 1, str.length());
    }

    public static boolean isWhitespace(char c) {
        return c == ' ' || c == '\n' || c == '\t';
    }

    public static void printLines(String name, InputStream ins) throws Exception {
        String line = null;
        BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));
        System.out.println(name);
        while ((line = in.readLine()) != null) {
            System.out.println("  " + line);
        }
    }

    public static void runProcess(String command, boolean name) throws Exception {
        Process pro = Runtime.getRuntime().exec(command);
        printLines(name?command + "\n stdout:":"", pro.getInputStream());
        printLines(name?command + "\n stderr:":"", pro.getErrorStream());
        pro.waitFor();
    }
}