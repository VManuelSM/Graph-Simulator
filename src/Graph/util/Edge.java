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
 * @param enabled Edge's enable (boolean)
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
 * 
 * @return int
 */
    public int getSense() {
        return sense;
    }
/**
 * 
 * @param sense 
 */
    public void setSense(int sense) {
        this.sense = sense;
    }
/**
 * 
 * @return 
 */
    public double getWeight() {
        return weight;
    }
/**
 * 
 * @param weight 
 */
    public void setWeight(double weight) {
        this.weight = weight;
    }
/**
 * 
 * @return 
 */
    public boolean isEnabled() {
        return enabled;
    }
/**
 * 
 * @param enabled 
 */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
/**
 * 
 * @return 
 */
    public String getName() {
        return name;
    }
/**
 * 
 * @param name 
 */
    public void setName(String name) {
        this.name = name;
    }
/**
 * 
 * @return 
 */
    public Color getColor() {
        return color;
    }
/**
 * 
 * @param color 
 */
    public void setColor(Color color) {
        this.color = color;
    }
/**
 * 
 * @return 
 */
    public Node getNodeOne() {
        return nodeOne;
    }
/**
 * 
 * @param nodeOne 
 */
    public void setNodeOne(Node nodeOne) {
        this.nodeOne = nodeOne;
    }
/**
 * 
 * @return 
 */
    public Node getNodeTwo() {
        return nodeTwo;
    }
/**
 * 
 * @param nodeTwo 
 */
    public void setNodeTwo(Node nodeTwo) {
        this.nodeTwo = nodeTwo;
    }
    
    
}
