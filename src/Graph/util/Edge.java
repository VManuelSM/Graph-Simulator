/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph.util;

import java.awt.Color;

/**
 *Edge Class
 * @author Víctor Manuel
 */
public class Edge {
    private int sense;
    private double weight;
    private boolean enabled;
    private String name;
    private Color color = Color.WHITE;
    Node nodeOne;
    Node nodeTwo;
/**
 * Edge Builder
 * @param sense Edge's sense (int)
 * @param weight Edge's weight (double)
 * @param name Edge's name (String)
 * @param nodeOne Edge's nodeOne (Node)
 * @param nodeTwo Edge's nodeTwo (Node)
 */
    public Edge(Node nodeOne, Node nodeTwo, double weight, int sense, String name) {
        this.sense = sense;
        this.weight = weight;
        this.enabled = true;
        this.name = name;
        this.nodeOne = nodeOne;
        this.nodeTwo = nodeTwo;
    }
/**
 * Edger's builder that just enable the edge
 */
    public Edge() {
        this.enabled = true;
    }
/**
 * sense Getter
 * @return int sense
 */
    public int getSense() {
        return sense;
    }
/**
 * sense setter
 * @param sense int sense 
 */
    public void setSense(int sense) {
        this.sense = sense;
    }
/**
 * weight getter
 * @return  double weight
 */
    public double getWeight() {
        return weight;
    }
/**
 * weight setter
 * @param weight double weight 
 */
    public void setWeight(double weight) {
        this.weight = weight;
    }
/**
 * enable getter
 * @return boolean enabled
 */
    public boolean isEnabled() {
        return enabled;
    }
/**
 * enable setter
 * @param enabled boolean enabled 
 */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
/**
 * name getter
 * @return String name
 */
    public String getName() {
        return name;
    }
/**
 * name setter
 * @param name String name 
 */
    public void setName(String name) {
        this.name = name;
    }
/**
 * color getter
 * @return Color color 
 */
    public Color getColor() {
        return color;
    }
/**
 * color setter
 * @param color Color color 
 */
    public void setColor(Color color) {
        this.color = color;
    }
/**
 * nodeOne getter
 * @return Node nodeOne
 */
    public Node getNodeOne() {
        return nodeOne;
    }
/**
 * nodeOne setter
 * @param nodeOne Node nodeOne 
 */
    public void setNodeOne(Node nodeOne) {
        this.nodeOne = nodeOne;
    }
/**
 * nodeTwo getter
 * @return Node nodeTwo
 */
    public Node getNodeTwo() {
        return nodeTwo;
    }
/**
 * nodeTwo setter
 * @param nodeTwo Node nodeTwo 
 */
    public void setNodeTwo(Node nodeTwo) {
        this.nodeTwo = nodeTwo;
    }
    
    
}
