package com.codecool.filemirror;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileMirror {
    private Path path;
    private String pathString;
    private String fileName;

    public static final String PATH = "src/main/resources/";

    public FileMirror(String path) {
        this.path = Paths.get(path);
        this.fileName = createFileName(path);
    }

    private String createFileName(String path) {
        String[] s = path.split("/");
        return fileName =  s[s.length-1];
     }

    public void mirror() {
        List<String> rows = readFile();
        List<String> result = mirrorRows(rows);
        writeFile(result);
    }

    private List<String> mirrorRows(List<String> rows) {
        List<String> mirrorRows = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            String row = rows.get(rows.size() - 1 - i);
            mirrorRows.add(row);
        }
        return mirrorRows;
    }

    private List<String> readFile() {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    private void writeFile(List<String> result) {
        String s = "";
        for (String res : result) {
            if (s.length() != 0) {
                s += '\n';
            }
            s += res;
        }
        String f = fileName.replace(".txt", "");
        String writePath = PATH + f + ".mirror.txt";
        Path pathWrite = Paths.get(writePath);
        try {
            Files.writeString(pathWrite,s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
