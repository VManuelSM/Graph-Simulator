/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph.util;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
/**
 * Graph Builder.
 * This classs is the abstraction of a Graph.
 * Contains
 * @author victo
 */
public class Graph implements Cloneable{
    List<Node> nodes = new ArrayList();
    List<Node> auxNodes = new ArrayList(2);
    List<Edge> edges = new ArrayList();
    int RADIO;
    JComboBox jComboBoxFirstNode;
    JComboBox jComboBoxLastNode;
    DefaultListModel nodesModel;
    DefaultListModel edgesModel;
    JLabel label;

    public Graph() {
    }
    
    
    public String getAuxNode(){
    String nodes = "";
    System.out.print("Camino: ");
    for(Node node: auxNodes){
        nodes+=(node.getName()+", ");
        System.out.print(node.getName());
    }
    System.out.println();
    return nodes;
    }
    
    public JComboBox getjComboBoxFirstNode() {
        return jComboBoxFirstNode;
    }

    public void setjComboBoxFirstNode(JComboBox jComboBoxFirstNode) {
        this.jComboBoxFirstNode = jComboBoxFirstNode;
    }

    public JComboBox getjComboBoxLastNode() {
        return jComboBoxLastNode;
    }

    public void setjComboBoxLastNode(JComboBox jComboBoxLastNode) {
        this.jComboBoxLastNode = jComboBoxLastNode;
    }

    public DefaultListModel getNodesModel() {
        return nodesModel;
    }

    public void setNodesModel(DefaultListModel nodesModel) {
        this.nodesModel = nodesModel;
    }

    public DefaultListModel getEdgesModel() {
        return edgesModel;
    }

    public void setEdgesModel(DefaultListModel edgesModel) {
        this.edgesModel = edgesModel;
    }
    
    public Graph(int RADIO, JComboBox jComboBoxFirstNode, JComboBox jComboBoxLastNode, DefaultListModel nodesModel, DefaultListModel edgesModel, JLabel label) {
        this.RADIO = RADIO;
        this.jComboBoxFirstNode = jComboBoxFirstNode;
        this.jComboBoxLastNode = jComboBoxLastNode;
        this.nodesModel = nodesModel;
        this.edgesModel = edgesModel;
        this.label = label;
    }
    
    public void addNode(MouseEvent e) { 
       String name = "";
        try {
            do {
            name = JOptionPane.showInputDialog("Ingrese el nodo");
            name = name.toUpperCase();
            if (name.trim().length() == 0)
            {
                JOptionPane.showMessageDialog(null, "Ingrese un nombre para el nodo");
                name = JOptionPane.showInputDialog("Ingrese el nodo");
                name = name.toUpperCase();
            }
            if (name.trim().length() == 0) {
                JOptionPane.showMessageDialog(null, "Ingreso de nodo cancelado");
                return;
            }
            name = deleteSpace(name);
            if (validateName(name)) {
                JOptionPane.showMessageDialog(null, "Ingrese otro nombre ya existe un nodo con dicho nombre ");
            }
            } while (validateName(name));
                this.nodes.add(new Node(e.getX(), e.getY(), name));
                this.jComboBoxFirstNode.addItem(name);
                this.jComboBoxLastNode.addItem(name);
                this.nodesModel.addElement(name);
     } catch (HeadlessException ex) {
       JOptionPane.showMessageDialog(null, "Ingreso de nodo cancelado");
     }
   }
    
    public void addEdge(MouseEvent e, int sen) { 
       if ((inBounds(e) != -1) && (this.auxNodes.size() == 2)) {
       String auxWeight = "";
       double weight = 0.0D;
       if (!validateEdge((Node)this.auxNodes.get(0), (Node)this.auxNodes.get(1))) {
         if (!((Node)this.auxNodes.get(0)).getName().equals(((Node)this.auxNodes.get(1)).getName())) {
           try {
             auxWeight = JOptionPane.showInputDialog("Ingrese el peso para la arista");
             weight = Double.parseDouble(auxWeight);
             if (weight <= 0.0D) {
               JOptionPane.showMessageDialog(null, "Ingrese un peso corecto para la arista por favor");
               auxWeight = JOptionPane.showInputDialog("Ingrese el peso para la arista nuevamente");
               weight = Double.parseDouble(auxWeight);
             }
             if (weight <= 0.0D) {
               JOptionPane.showMessageDialog(null, "Ingreso de arista cancelado");
               return;
             }
             Node nodeOne = (Node)this.auxNodes.get(0);
             Node nodeTwo = (Node)this.auxNodes.get(1);
             Edge edge = null;
             if (sen == 1) {
               edge = new Edge(nodeOne, nodeTwo, weight, sen, nodeOne.getName() + " ---> " + nodeTwo.getName());
               createLinksDirected(nodeOne, nodeTwo, edge);
             } else {
               edge = new Edge(nodeOne, nodeTwo, weight, sen, nodeOne.getName() + " <--> " + nodeTwo.getName());
               createLinksNotDirected( nodeOne,nodeTwo, edge);
             }
             this.edges.add(edge);
             this.edgesModel.addElement(edge.getName() + " = " + edge.getWeight());
           } catch (HeadlessException | NumberFormatException ex) {
             JOptionPane.showMessageDialog(null, "Ingreso de arista cancelado");
           }
         }
       } else
         JOptionPane.showMessageDialog(null, "Ya se ingreso una arista utilizando estos nodos"); } }
    
    public void updateEdgesComboBox() { 
     this.edgesModel.removeAllElements();
     for (Edge edge : this.edges)
     {
       this.edgesModel.addElement(edge.getName() + " = " + edge.getWeight()); }
    }
    
    public void updateNodesComboBox() {
     this.jComboBoxFirstNode.removeAllItems();
     this.jComboBoxLastNode.removeAllItems();
     this.nodesModel.removeAllElements();
     for (Node node : this.nodes) {
       this.jComboBoxFirstNode.addItem(node.getName());
       this.jComboBoxLastNode.addItem(node.getName());
       this.nodesModel.addElement(node.getName());
     }
     updateEdgesComboBox();
   }
    
    public void deleteEdge(String nodeName) { 
     List<Edge> edgesB = new ArrayList();
     for (int i = 0; i < this.edges.size(); i++) {
       Edge auxA = (Edge)this.edges.get(i);
       if ((auxA.getNodeOne().getName().equals(nodeName)) || (auxA.getNodeTwo().getName().equals(nodeName)))
       {
         edgesB.add(auxA);
       }
     }
     this.edges.removeAll(edgesB);
   }
    
    public Node searchNode(String name) { 
    Node auxNode = null;
     for (Node node : this.nodes) {
       if (node.getName().equals(name)) {
         auxNode = node;
       }
     }
     return auxNode; 
   }
    
    public void changeNode(String nodeName, int nodeMarked) { 
     Node nodeToChange;
     if (this.nodes.size() > 1) {
       nodeToChange = searchNode(nodeName);
       for (Node node : this.nodes) {
         if (node.getName().equals(nodeToChange.getName())) {
           nodeToChange.setMarkedNode(nodeMarked);
         } else if (node.getMarkedNode() == nodeMarked) {
           node.setMarkedNode(-1);
         }
       }
     }
     else if (this.nodes.size() == 1) {
       ((Node)this.nodes.get(0)).setMarkedNode(0);
     }
   }
    
    public String deleteSpace(String text) {
     StringBuilder stringB = new StringBuilder();
     for (int x = 0; x < text.length(); x++) {
       if (text.charAt(x) != ' ')
         stringB.append(text.charAt(x));
     }
     return stringB.toString();
   }
    
    public void deleteNode(MouseEvent e) { 
     int index = inBounds(e);
     if (index != -1) {
       int option = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el nodo?", "Dialogo de confirmación", 0);
       if (option == 0) {
         String nameNodeToDelete = ((Node)this.nodes.get(index)).getName();
         deleteEdge(nameNodeToDelete);
         this.nodes.remove(index);
         updateNodesComboBox();
         restartDijkstraGraph(this.label);
       }
     }
   }
    
    public boolean validateEdge(Node nodeOne, Node nodeTwo) {
         for (Edge edge : this.edges) {
       if (((edge.getNodeOne().getName().equals(nodeOne.getName())) && (edge.getNodeTwo().getName().equals(nodeTwo.getName()))) 
           || ((edge.getNodeTwo().getName().equals(nodeOne.getName())) && (edge.getNodeOne().getName().equals(nodeTwo.getName()))))
       {
         return true;
       }
     }
     return false;
   }
    
    public void createLinksNotDirected(Node father, Node adjacent, Edge edge) {
     createLinksDirected(father, adjacent, edge);
     createLinksDirected(adjacent, father, edge);
   }
    
    public void createLinksDirected(Node father, Node adjacent, Edge edge) {
    if ((father != null) && (adjacent != null)) {
        father.addAdjacentNode(edge, adjacent);
    }
   }
    
    public int inBounds(MouseEvent e) {
     for (int i = 0; i < this.nodes.size(); i++) {
       Node node = (Node)this.nodes.get(i);
       if ((e.getX() >= node.getX()) && (e.getX() <= node.getX() + 2 * this.RADIO) && (e.getY() >= node.getY()) && (e.getY() <= node.getY() + 2 * this.RADIO)) {
         return i;
       }
     }
     return -1;
    }
    
    public void restartDijkstraGraph(JLabel label) {
     for (Node node : this.nodes) {
       node.setMark(false);
       node.setPreviousDijkstra(null);
       node.setPathLength(-1.0D);
     }
     for (Edge edge : this.edges) {
       edge.setColor(Color.BLUE);
     }
     label.setForeground(Color.BLACK);
     label.setText("Ruta mínima:");
   }
    public void restartDijkstraGraph() {
     for (Node node : this.nodes) {
       node.setMark(false);
       node.setPreviousDijkstra(null);
       node.setPathLength(-1.0D);
     }
     for (Edge edge : this.edges) {
       edge.setColor(Color.BLUE);
     }
   }
    
    public boolean validateName(String name) {
     for (Node node : this.nodes) {
       if (node.getName().equals(name)) {
         return true;
       }
     }
     return false;
    }
    
    public Object clone(){
        Object obj=null;
        try{
            obj=super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println(" no se puede duplicar");
        }
        return obj;
    }
    public Object comboBoxClone(){
        Object obj=null;
        obj=this.jComboBoxFirstNode;
        return obj;
    }
    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Node> getAuxNodes() {
        return auxNodes;
    }

    public void setAuxNodes(List<Node> auxNodes) {
        this.auxNodes = auxNodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }
    
}
