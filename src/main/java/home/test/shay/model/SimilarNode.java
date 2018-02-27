package home.test.shay.model;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SimilarNode {
    Set<Line> nodes;
    Set<String> diffWords;
    Set<String> content;

    public SimilarNode(Line lineOne, Line lineTwo,Set<String> simialrWords){
        nodes = new HashSet<>();
        diffWords = new HashSet<>();
        content = new HashSet<>();
        nodes.add(lineOne);
        nodes.add(lineTwo);
        diffWords.addAll(simialrWords);
        content.add(lineOne.getLineContent());
        content.add(lineTwo.getLineContent());
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Line line : nodes){
            sb.append(line.getRaw());
            sb.append("\n");
        }
        sb.append("The changing word was:");
        sb.append(StringUtils.join(diffWords," ,"));
        return sb.toString();
    }

    public static List<SimilarNode> arrangeSimilarNodes(List<SimilarNode> nodes , SimilarNode node){
        boolean added = false;
        for(SimilarNode similarNode : nodes){
            if(compare(similarNode,node)){
                nodes.remove(similarNode);
                similarNode.nodes.addAll(node.nodes);
                nodes.add(similarNode);
                added = true;
                break;
            }
        }
        if(!added){
            nodes.add(node);
        }
        return nodes;
    }

    public static boolean compare(SimilarNode nodeA , SimilarNode nodeB){
        return CollectionUtils.containsAny(nodeA.nodes,nodeB.nodes)
                && CollectionUtils.containsAny(nodeA.diffWords,nodeB.diffWords);
    }


}
