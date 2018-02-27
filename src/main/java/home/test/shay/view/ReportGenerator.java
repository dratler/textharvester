package home.test.shay.view;

import home.test.shay.model.SimilarNode;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ReportGenerator {
    public void printReport(String fileName ,List<SimilarNode> nodes) throws IOException {
        String content = formatReport(nodes);
        File file = findFile(fileName);
        writeToLog(file,content);

    }

    private String formatReport(List<SimilarNode> nodes){
        StringBuilder sb = new StringBuilder("=====");
        sb.append("\n");
        nodes.forEach(node -> {
            sb.append(node.toString());
            sb.append("\n");

        });
        sb.append("=====");
        return sb.toString();
    }

    private void writeToLog(File f,String context) throws IOException {
        deleteOldFile(f);
        printFile(f,context);

    }
    private File findFile(String fileName){
        return FileUtils.getFile(fileName);
    }

    private void deleteOldFile(File f){
        if(f.exists()) {
            FileUtils.deleteQuietly(f);
        }
    }
    private void printFile(File f,String data) throws IOException {
        FileUtils.write(f,data,"UTF-8");
    }
}
