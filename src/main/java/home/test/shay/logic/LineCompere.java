package home.test.shay.logic;

import home.test.shay.model.Line;
import home.test.shay.model.SimilarNode;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LineCompere {

    public List<SimilarNode> findSimilarNodes(List<Line> nodes ,Integer diff){
        Line lineOne,lineTwo;
        Set<String> filterWords;
        List<SimilarNode> similarNode = new ArrayList<>();
        int iterationSize = nodes.size();
        //Scanning all the lines\nodes once
        for(int i = 0 ; i < iterationSize ; i++){
            for(int j = 0; j < iterationSize && j!=i;j++){
                lineOne = nodes.get(i);
                lineTwo = nodes.get(j);
                if(lengthValidation(lineOne,lineTwo,diff)){
                    filterWords = extractNonSimlarWords(lineOne,lineTwo);
                    if(filterWords.size() <= diff*2){
                        SimilarNode.arrangeSimilarNodes(similarNode,new SimilarNode(lineOne,lineTwo,filterWords));
                        break;
                    }
                }

            }
        }
        return similarNode;
    }

    private boolean lengthValidation(Line lineOne , Line lineTwo , Integer diff){
        int totalWordsOne = lineOne.getTokenString().size();
        int totalWordsTwo = lineTwo.getTokenString().size();
        return Math.abs(totalWordsOne-totalWordsTwo) <= diff;
    }


    private Set<String> extractNonSimlarWords(Line one , Line two){
        Set<String> out = one.getTokenString().stream().filter(word -> !two.getTokenString().contains(word)).collect(Collectors.toSet());
        out.addAll(two.getTokenString().stream().filter(word -> !one.getTokenString().contains(word)).collect(Collectors.toSet()));
        return out;
    }





}
