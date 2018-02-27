package home.test.shay.model;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Line {

    private String raw;
    private LocalDateTime time;
    private String lineContent;
    private List<String> tokenString;

    public Line(String raw) {
        parseLine(raw);
    }


    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    private void parseLine(String raw) {
        setRaw(raw);
        String[] line = StringUtils.split(getRaw());
        setTime(line[0]+" "+line[1]);
        setLineContent(line);
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.time = LocalDateTime.parse(dateTime,formatter);
    }

    public String getLineContent() {
        return lineContent;
    }

    public void setLineContent(String[] lineContent) {
        this.tokenString = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 2;i<lineContent.length;i++){
            sb.append(lineContent[i]);
            sb.append(" ");
            this.tokenString.add(lineContent[i]);
        }
        this.lineContent = StringUtils.trim(sb.toString());
    }

    public List<String> getTokenString() {
        return tokenString;
    }
}

