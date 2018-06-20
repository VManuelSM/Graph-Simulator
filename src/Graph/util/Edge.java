/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph.util;

import java.awt.Color;

/**
 *
 * @author victo
 */
public class Edge {
    private int sense;
    private double weight;
    private boolean enabled;
    private String name;
    private Color color = Color.BLUE;
    Node nodeOne;
    Node nodeTwo;

    public Edge(int sense, double weight, boolean enabled, String name, Node nodeOne, Node nodeTwo) {
        this.sense = sense;
        this.weight = weight;
        this.enabled = enabled;
        this.name = name;
        this.nodeOne = nodeOne;
        this.nodeTwo = nodeTwo;
    }

    public Edge() {
        this.enabled = true;
    }

    public int getSense() {
        return sense;
    }

    public void setSense(int sense) {
        this.sense = sense;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Node getNodeOne() {
        return nodeOne;
    }

    public void setNodeOne(Node nodeOne) {
        this.nodeOne = nodeOne;
    }

    public Node getNodeTwo() {
        return nodeTwo;
    }

    public void setNodeTwo(Node nodeTwo) {
        this.nodeTwo = nodeTwo;
    }
    
    
}
