/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph.util;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 *
 * @author victo
 */
public class Dijkstra {
    NodeList adjacentNodesList;
    NodeList auxAdjacentList;
    ArrayList<Edge> aux = new ArrayList();
    public String dijkstraNodes = "";
    NodeList nodes = new NodeList();

    public Dijkstra() {
        this.adjacentNodesList = new NodeList();
    }
    
    private void fillWithAdjacents(Node node){
    if(node != null){
        ArrayList<Link> auxList = node.getNodesAdjacentsList();
        if(auxList != null){
            for(Link link : auxList){
                Node auxTwo = link.getNode();
                if(!auxTwo.isMark())
                    if(this.adjacentNodesList.isContained(auxTwo)){
                        double length = node.getPathLength() + link.getEdge().getWeight();
                        if(auxTwo.getPathLength() > length){
                            auxTwo.setPathLength(length);
                            auxTwo.setPreviousDijkstra(node);
                        }
                    } else {
                        auxTwo.setPathLength(node.getPathLength()+link.getEdge().getWeight());
                        auxTwo.setPreviousDijkstra(node);
                        this.adjacentNodesList.add(auxTwo);
                        
                    }
            }
            
        }
    }
    }
    
    public void execute(Node firstNode){
        try{
            firstNode.setPathLength(0.0D);
            if(firstNode != null){
                this.adjacentNodesList = new NodeList();
                this.adjacentNodesList.add(firstNode);
                while(!this.adjacentNodesList.isEmpty()){
                Node shorter = this.adjacentNodesList.searchingTheShortest();
                shorter.setMark(true);
                this.adjacentNodesList.remove(shorter);
                fillWithAdjacents(shorter);
                } 
                this.dijkstraNodes += " "+firstNode.getName();
                
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Necesita crear un grafo primero","Error durante el recorrido", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void shortestPath(Node leastNode){
    this.aux.clear();
    Node auxNode = leastNode;
    Edge auxEdge;
    while(auxNode.getPreviousDijkstra() != null){
        auxEdge = getEdge(auxNode, auxNode.getPreviousDijkstra());
        nodes.add(auxNode);
        auxEdge.setColor(Color.GREEN);
        this.aux.add(auxEdge);
        auxNode = auxNode.getPreviousDijkstra();
    }
    addingNodes();
    }
    private void addingNodes(){
    for(int i = nodes.size()-1; i>=0; i--){
    dijkstraNodes = dijkstraNodes +" "+nodes.get(i).getName();
    }
    }
    public boolean isAdjacent(Node nodeOne, Node nodeTwo){
    boolean adjacent = false;
    ArrayList<Link> edgesList = nodeOne.getNodesAdjacentsList();
    if(edgesList != null){
        for(int i = 0; i < edgesList.size(); i++){
        if(((Link)edgesList.get(i)).getNode()==nodeTwo)
            adjacent = true;
        }
    }
    return adjacent;
    }
    
    public Edge getEdge(Node nodeOne, Node nodeTwo){
    Edge edge = null;
    if(isAdjacent(nodeOne, nodeTwo)){
        ArrayList<Link> edgesList = nodeOne.getNodesAdjacentsList();
        for(int i = 0; i <edgesList.size(); i++){
            if(((Link)edgesList.get(i)).getNode()==nodeTwo)
                edge = ((Link)edgesList.get(i)).getEdge();
        }
    } else if(isAdjacent(nodeTwo, nodeOne)){
    edge = getEdge(nodeTwo,nodeOne);
    }
    return edge;
    }
}
