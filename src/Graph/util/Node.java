/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph.util;

import java.util.ArrayList;

/**
 *
 * @author Víctor Manuel
 */
public class Node {
    private int x;
    private int y;
    private int markedNode = -1;
    private double pathLength;
    private String name;
    private boolean mark;
    private boolean visited;
    private Node previousDijkstra;
    private ArrayList<Link> nodesAdjacentsList;

    public Node(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.nodesAdjacentsList = new ArrayList();
        startDijkstra();
    }
    
    public Node () {startDijkstra();}
    
    /**
     * Start the properties to execute Dijkstra algorithm.
     */
    private void startDijkstra(){
    this.pathLength = -1.0D;
    this.previousDijkstra = null;
    this.mark = false;
    }
    
    public void addAdjacentNode(Link edge){
    this.nodesAdjacentsList.add(edge);
    }
    
    public void addAdjacentNode(Edge way, Node node){
    addAdjacentNode(new Link(way, node));
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getMarkedNode() {
        return markedNode;
    }

    public void setMarkedNode(int markedNode) {
        this.markedNode = markedNode;
    }

    public double getPathLength() {
        return pathLength;
    }

    public void setPathLength(double pathLength) {
        this.pathLength = pathLength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Node getPreviousDijkstra() {
        return previousDijkstra;
    }

    public void setPreviousDijkstra(Node previousDijkstra) {
        this.previousDijkstra = previousDijkstra;
    }
    //Puede que necesite editarlo
    public ArrayList<Link> getNodesAdjacentsList() {
        return nodesAdjacentsList;
    }

    public void setNodesAdjacentsList(ArrayList<Link> nodesAdjacentsList) {
        this.nodesAdjacentsList = nodesAdjacentsList;
    }
}
