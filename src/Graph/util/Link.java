/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph.util;

/**
 *
 * @author victo
 */
class Link {
    private Edge edge;
    private Node node;

    public Link() {
        this(new Edge(), new Node());
    }

    public Link(Edge edge, Node node) {
        this.edge = edge;
        this.node = node;
    }

    public Edge getEdge() {
        return edge;
    }

    public void setEdge(Edge edge) {
        this.edge = edge;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
