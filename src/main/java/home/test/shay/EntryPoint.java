package home.test.shay;

import com.google.inject.Guice;
import com.google.inject.Injector;
import home.test.shay.logic.LineCompere;
import home.test.shay.model.Line;
import home.test.shay.model.SimilarNode;
import home.test.shay.utils.Config;
import home.test.shay.utils.LogCollector;
import home.test.shay.view.ReportGenerator;

import java.io.IOException;
import java.util.List;

public class EntryPoint {


    public static void main(String[] args) throws IOException {
        Injector injector = Guice.createInjector();
        Config config = injector.getInstance(Config.class);
        LogCollector collector = injector.getInstance(LogCollector.class);
        LineCompere logic = injector.getInstance(LineCompere.class);
        ReportGenerator report = injector.getInstance(ReportGenerator.class);

        String logName = config.getValue("log-file");
        List<Line> parsed = collector.readText(logName);

        int wordDiff = Integer.valueOf(config.getValue("word-diff"));

        List<SimilarNode> similarNodes = logic.findSimilarNodes(parsed,wordDiff);

        String outputReport = config.getValue("output-file");
        report.printReport(outputReport,similarNodes);
    }
}
