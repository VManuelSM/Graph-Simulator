/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph.util;

import java.util.ArrayList;

/**
 *
 * @author victo
 */
public class NodeList extends ArrayList<Node>{
    public Node lookingForTheSmaller(){
    Node auxNode = new Node();
    auxNode.setPathLength(9999999.0D);
    for(Node node : this){
        if(node.getPathLength() < auxNode.getPathLength()) 
            auxNode = node;
    }
    return auxNode;
    }
    
    public boolean isContained(Node node){
    boolean returned = false;
    for(Node auxNode : this){
        if(auxNode == node)
            returned = true;
    }
    return returned;
    }
}
