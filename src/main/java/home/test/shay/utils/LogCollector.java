package home.test.shay.utils;

import home.test.shay.model.Line;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogCollector {

    public List<Line> readText(String fileName) throws IOException {

        File file = findFile(fileName);
        List<String> out = readFile(file);
        return  parseFile(out);

    }

    private File findFile(String fileName){
        ClassLoader classLoader = getClass().getClassLoader();
        String path  = classLoader.getResource(fileName).getPath();
        return new File(path);
    }


    private List<String> readFile(File file) throws IOException {

        return FileUtils.readLines(file,"UTF-8");
    }

    private List<Line> parseFile(List<String> section){
        List<Line> lines = new ArrayList<Line>();
        for(String row : section){
           lines.add(new Line(row));
        }
        return lines;
    }
}
