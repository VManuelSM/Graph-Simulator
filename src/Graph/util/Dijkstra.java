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
                Node shorter = this.adjacentNodesList.searchingTheShorter();
                shorter.setMark(true);
                this.adjacentNodesList.remove(shorter);
                fillWithAdjacents(shorter);
                } 
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Necesita crear un grafo primero","Error durante el recorrido", JOptionPane.ERROR_MESSAGE);
        }
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
                auxTwo.setPreviousDijkstra(node);
            } else {
                auxTwo.setPreviousDijkstra(node);
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
    while(auxNode.getPreviousDijkstra() != null){
        auxEdge = getEdge(auxNode, auxNode.getPreviousDijkstra());
        
        auxEdge.setColor(Color.GREEN);
        this.aux.add(auxEdge);
        auxNode = auxNode.getPreviousDijkstra();
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
