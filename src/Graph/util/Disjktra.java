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
public class Disjktra {
    NodeList AdjacentNodesList;
    NodeList auxAdjacentList;
    ArrayList<Edge> aux = new ArrayList();

    public Disjktra() {
        this.AdjacentNodesList = new NodeList();
    }
    
    private void fillWithAdjacents(Node node){
    if(node != null){
        ArrayList<Link> auxList = node.getNodesAdjacentsList();
        if(auxList != null){
            for(Link link : auxList){
                Node auxTwo = link.getNode();
                if(!auxTwo.isMark())
                    if(this.AdjacentNodesList.isContained(auxTwo)){
                        double length = node.getPathLength() + link.getEdge().getWeight();
                        if(auxTwo.getPathLength() > length){
                            auxTwo.setPathLength(length);
                            auxTwo.setPreviousDisjktra(node);
                        }
                    } else {
                        auxTwo.setPathLength(node.getPathLength()+link.getEdge().getWeight());
                        auxTwo.setPreviousDisjktra(node);
                        this.AdjacentNodesList.add(auxTwo);
                    }
            }
        }
    }
    }
    
    public void execute(Node firstNode){
        
    }
    
    public void AdjacentList(Node firstNode){
    int i = 0;
    if(firstNode != null){
        this.auxAdjacentList = new NodeList();
        this.auxAdjacentList.add(firstNode);
        while(i < this.auxAdjacentList.size()){
            Node search = this.auxAdjacentList.get(i);
            searchingAdjacentNodes(search);
            System.out.println();
            i++;
        }
    }
    }
    
    public void searchingAdjacentNodes(Node node){
    if(node != null){
        System.out.print(node.getName()+ " | ");
        ArrayList<Link> auxList = node.getNodesAdjacentsList();
        if(auxList != null){
            for(Link link : auxList){
            Node auxTwo = link.getNode();
            if(this.auxAdjacentList.isContained(auxTwo)){
                System.out.print(auxTwo.getName()+" , ");
                auxTwo.setPreviousDisjktra(node);
            } else {
                auxTwo.setPreviousDisjktra(node);
                this.auxAdjacentList.add(auxTwo);
            }
            }
        }
    }
    }
    
    public void shorterPath(Node leastNode){
    this.aux.clear();
    Node auxNode = leastNode;
    Edge auxEdge;
    while(auxNode.getPreviousDisjktra() != null){
        
    }
    }
}
