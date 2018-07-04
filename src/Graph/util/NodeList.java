/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph.util;

import java.util.ArrayList;

/**
 * This class extends from ArrayList and is fill it with instanced
 * nodes from Graph.util.Node. It's necesary to manager the searching 
 * of the shortest
 * @author Víctor Manuel
 */
public class NodeList extends ArrayList<Node>{
    /**
     * This function compares the nodes lenght from the instance 
     * of this class.
     * @return The node with the shortest path
     */
    public Node searchingTheShortest(){
    Node auxNode = new Node();
    auxNode.setPathLength(9999999.0D);
    for(Node node : this){
        if(node.getPathLength() < auxNode.getPathLength()) 
            auxNode = node;
    }
    return auxNode;
    }
    /**
     * Funtion that search if a Node is contained in a instance of this class
     * @param node will be search it
     * @return true if is contained, false if it is not.
     */
    public boolean isContained(Node node){
    boolean returned = false;
    for(Node auxNode : this){
        if(auxNode == node)
            returned = true;
    }
    return returned;
    }
}
