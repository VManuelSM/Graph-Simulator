 package Graph.GUI;
 import Graph.util.Edge;
 import Graph.util.Dijkstra;
 import Graph.util.Graph;
 import Graph.util.Node;
 import java.awt.Color;
 import java.awt.Cursor;
 import java.awt.Dimension;
 import java.awt.Graphics;
 import java.awt.Graphics2D;
 import java.awt.Image;
 import java.awt.Toolkit;
 import java.awt.event.MouseEvent;
 import java.awt.event.MouseListener;
 import java.awt.event.MouseMotionListener;
 import javax.swing.DefaultListModel;
 import javax.swing.JComboBox;
 import javax.swing.JLabel;
 import javax.swing.JOptionPane;
 import javax.swing.JPanel;
 
 public class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener
 {
   TypeElement typeElement;
   static final int RADIO = 8;
   int index = -1;
   int posXaux = -1; 
   int posYaux = -1;
   private Image yellowNode;
   private Image redNode;
   private Image greenNode; 
   private Image blueNode; 
   
   public Graph graph;
   int x1 = 0; int x2 = 0; int y1 = 0; int y2 = 0;
   /**
    * Type of data that lists constant options.
    */
   public static enum TypeElement
   {
     NODE,  EDGE_1S,  EDGE_2S,  MOVE,  DELETE;
     
     private TypeElement() {} }
   
   
   /**
    * Drawing Panel Builder
    * @param jComboBoxFirstNode Initial node selected. It is asigned from the GUI previoulsy builded.
    * @param jComboBoxLastNode Last node selected. It is asigned from the GUI previoulsy builded.
    * @param nodesModel Nodes list. It is asigned from the GUI previoulsy builded.
    * @param edgesModel Edges list. It is asigned from the GUI previoulsy builded.
    * @param label Label which is use it to show to the user what's the shortest path between two nodes.
    */
   public DrawingPanel(JComboBox jComboBoxFirstNode, JComboBox jComboBoxLastNode, DefaultListModel nodesModel, DefaultListModel edgesModel, JLabel label)
   {
     setBackground(new Color(73, 25, 101));
     setForeground(new Color(255, 255, 255));
     setPreferredSize(new Dimension(30000, 30000));
     addMouseListener(this);
     addMouseMotionListener(this);
     this.graph = new Graph(8,jComboBoxFirstNode, jComboBoxLastNode, nodesModel, edgesModel, label);
     loadImages();
   }
   
   /**
    * Load the nodes GIF images into the JPanel.
    */
   private void loadImages()
   {
     Toolkit tk = Toolkit.getDefaultToolkit();
     this.yellowNode = tk.getImage(getClass().getResource("/Graph/media/yellowNode.gif"));
     this.redNode = tk.getImage(getClass().getResource("/Graph/media/redNode.gif"));
     this.greenNode = tk.getImage(getClass().getResource("/Graph/media/greenNode.gif"));
     this.blueNode = tk.getImage(getClass().getResource("/Graph/media/blueNode.gif"));
   }
   
   /**
    * Change the cursor respect the typeElement
    * @param typeElement CONSTANT that define which is the appropiate cursor
    * int NODE is = 0.
    * int EDGE_1S is 1.
    * int EDGE_2S is 2.
    * int MOVE is 3.
    * int DELETE is 4.
    */
   public void changeCursor(TypeElement typeElement) {
     this.typeElement = typeElement;
     if (null == typeElement) {
         setCursor(Cursor.getPredefinedCursor(13));
     } else switch (typeElement) {
           case NODE:
               setCursor(Cursor.getPredefinedCursor(1));
               break;
           case DELETE:
               setCursor(Cursor.getPredefinedCursor(1));
               break;
           case EDGE_1S:
               setCursor(Cursor.getPredefinedCursor(0));
               break;
           case EDGE_2S:
               setCursor(Cursor.getPredefinedCursor(0));
               break;
           default:
               setCursor(Cursor.getPredefinedCursor(13));
               break;
       }
   }
   /**
    * Change the point of a node
    * @param e mouse cursor event
    */
   private void changePoint(MouseEvent e) {
     if (this.graph.inBounds(e) != -1) {
       Node node = (Node)this.graph.getNodes().get(this.graph.inBounds(e));
       if (this.graph.getAuxNodes().size() != 2) {
         this.graph.getAuxNodes().add(node);
       } else {
         this.graph.getAuxNodes().set(1, node);
       }
     }
     else if (this.graph.getAuxNodes().size() == 2) {
       this.graph.getAuxNodes().remove(1);
     }
   }
   
   private void drawEdges(Graphics g)
   {
     for (Edge edge : this.graph.getEdges()) {
       g.setColor(edge.getColor());
       g.drawLine(edge.getNodeOne().getX() + 8, edge.getNodeOne().getY() + 8, edge.getNodeTwo().getX() + 8, edge.getNodeTwo().getY() + 8);
       int xWeight = Math.abs((edge.getNodeOne().getX() + 8 - edge.getNodeTwo().getX()) / 2 + edge.getNodeTwo().getX());
       int yWeight = Math.abs((edge.getNodeOne().getY() + 8 - edge.getNodeTwo().getY()) / 2 + edge.getNodeTwo().getY());
       g.setColor(Color.WHITE);
       g.drawString(edge.getWeight() + "", xWeight - 4, yWeight - 4);
     }
   }
   
   /**
    * Draws graph's arrows
    * @param g Graphics
    */
   private void drawArrow(Graphics g) {
     for (Edge edge : this.graph.getEdges()) {
       if (edge.getSense() == 1) {
         g.setColor(edge.getColor());
         int x1 = edge.getNodeOne().getX() + 8;
         int y1 = edge.getNodeOne().getY() + 8;
         int x2 = Math.abs((edge.getNodeOne().getX() - edge.getNodeTwo().getX()) / 2 + edge.getNodeTwo().getX() + 8);
         int y2 = Math.abs((edge.getNodeOne().getY() - edge.getNodeTwo().getY()) / 2 + edge.getNodeTwo().getY() + 8);
         double alpha = Math.atan2(y2 - y1, x2 - x1);
         int k = 15;
         int xa = (int)(x2 - k * Math.cos(alpha + 0.5D));
         int ya = (int)(y2 - k * Math.sin(alpha + 0.5D));
         int xb = (int)(x2 - k * Math.cos(alpha - 0.5D));
         int yb = (int)(y2 - k * Math.sin(alpha - 0.5D));
         int[] x = { xa, xb, x2 };
         int[] y = { ya, yb, y2 };
         g.fillPolygon(x, y, 3 );
       }
     }
   }

   public void editEdge(int[] selectedEdge) {
     if (selectedEdge.length == 0) {
       JOptionPane.showMessageDialog(null, "Seleccione una arista de la lista para editar el peso");
     } else { if (selectedEdge.length == 1)
         try {
           String auxWeight = JOptionPane.showInputDialog("Ingrese el peso para la arista");
           double weight = Double.parseDouble(auxWeight);
           if (weight <= 0.0D) {
             JOptionPane.showMessageDialog(null, "Ingrese un peso corecto para la arista por favor ");
             auxWeight = JOptionPane.showInputDialog("Ingrese el peso para la arista nuevamente");
             weight = Double.parseDouble(auxWeight);
           }
           if (weight <= 0.0D) {
             JOptionPane.showMessageDialog(null, "Ingreso de arista cancelado");
             return;
           }
           ((Edge)this.graph.getEdges().get(selectedEdge[0])).setWeight(weight);
           this.graph.updateEdgesComboBox();
           repaint();
         } catch (Exception e) { 
             JOptionPane.showMessageDialog(null, "Ingreso de arista cancelado"); }
             JOptionPane.showMessageDialog(null, "Seleccione solo una arista");
     }
   }
   
   private void drawAuxLine(Graphics g) {
     g.setColor(Color.RED);
     if (!this.graph.getAuxNodes().isEmpty()) {
       g.drawLine(((Node)this.graph.getAuxNodes().get(0)).getX() + 8, ((Node)this.graph.getAuxNodes().get(0)).getY() + 8, this.posXaux, this.posYaux);
     }
   }
   
   private void drawNodes(Graphics g) {
     for (Node node : this.graph.getNodes()) {
       if (node.getMarkedNode() == 0) {
         g.drawImage(this.greenNode, node.getX(), node.getY(), this);
       } else if (node.getMarkedNode() == 1) {
         g.drawImage(this.blueNode, node.getX(), node.getY(), this);
       } else {
         g.drawImage(this.yellowNode, node.getX(), node.getY(), this);
       }
       g.setColor(Color.WHITE);
       g.drawString(node.getName(), node.getX() - 4, node.getY() - 4);
     }
     for (Node node : this.graph.getAuxNodes()) {
       g.drawImage(this.redNode, node.getX(), node.getY(), this);
       g.setColor(Color.RED);
       g.drawString(node.getName(), node.getX() - 4, node.getY() - 4);
     }
   }
   
   public void cleanMap() { 
     this.graph.getEdges().clear();
     this.graph.getNodes().clear();
     this.graph.updateNodesComboBox();
   }
   
   public void solution(String nodeOne, String nodeTwo, JLabel label) { 
     Dijkstra dijkstra = new Dijkstra();
     this.graph.restartDijkstraGraph(label);
     dijkstra.execute(this.graph.searchNode(nodeOne));
     dijkstra.shortestPath(this.graph.searchNode(nodeTwo));
     double distance = 0.0D;
     for (Edge edge : this.graph.getEdges()) {
       if (edge.getColor() == Color.GREEN) {
         distance += edge.getWeight();
       }
     }
     if (distance == 0.0D) {
       label.setForeground(Color.red);
       label.setText("No existe un camino valido del nodo " + nodeOne + " al nodo " + nodeTwo);
     } else {
       label.setForeground(Color.WHITE);
       label.setText("El camino más corto entre " + nodeOne + " y " + nodeTwo + " es: " + distance);
     }
   }
   
   public void paintComponent(Graphics ga)
   {
     super.paintComponent(ga);
     Graphics2D g = (Graphics2D)ga;
     
     drawAuxLine(g);
     drawArrow(g);
     drawEdges(g);
     drawNodes(g);
   }
   
 
 
 
 
 
   public void mouseDragged(MouseEvent e)
   {
     if ((this.typeElement == typeElement.EDGE_1S) || (this.typeElement == typeElement.EDGE_2S)) {
       this.posXaux = e.getX();
       this.posYaux = e.getY();
       changePoint(e);
     }
     if ((this.typeElement == typeElement.MOVE) && 
       (this.index != -1)) {
       Node auxNode = (Node)this.graph.getNodes().get(this.index);
       auxNode.setX(e.getX());
       auxNode.setY(e.getY());
       this.graph.getNodes().set(this.index, auxNode);
     }
     
     repaint();
   }
   
 
 
   public void mouseMoved(MouseEvent e) {}
 
   public void mouseClicked(MouseEvent e) {}
 
   public void mousePressed(MouseEvent e)
   {
     if (this.typeElement == TypeElement.NODE) {
       this.graph.addNode(e);
     } else if (this.typeElement == TypeElement.DELETE) {
       //this.graph.(e);
     } else if ((this.typeElement == typeElement.EDGE_1S) || (this.typeElement == TypeElement.EDGE_2S)) {
       this.posXaux = e.getX();
       this.posYaux = e.getY();
       changePoint(e);
     } else {
       this.index = this.graph.inBounds(e);
       if (this.index != -1) {
         Node auxNode = (Node)this.graph.getNodes().get(this.index);
         this.graph.getNodes().set(this.graph.inBounds(e), auxNode);
       }
     }
     repaint();
   }
   
   public void mouseReleased(MouseEvent e)
   {
     if (this.typeElement == TypeElement.EDGE_1S) {
       this.graph.addEdge(e, 1);
       this.graph.getAuxNodes().clear();
     } else if (this.typeElement == TypeElement.EDGE_2S) {
       this.graph.addEdge(e, 2);
       this.graph.getAuxNodes().clear();
     } else if ((this.typeElement == TypeElement.MOVE) && 
       (this.index != -1)) {
       Node auxNode = (Node)this.graph.getNodes().get(this.index);
       this.graph.getNodes().set(this.graph.inBounds(e), auxNode);
     }
     
     repaint();
   }
   
   public void mouseEntered(MouseEvent e) {}
   
   public void mouseExited(MouseEvent e) {}
 }

