
/**
 * Represents an undirected graph
 * has methods to:
 * 		insert and access edges
 * 		find incident edges of a given vertex
 * 		access a node
 * 		check if two vertices are adjacent (connected by an edge)
 * 
 * Implemented using an adjacency matrix
 * @author fmoham26 Fawaz Mohammad
 *
 */

import java.util.ArrayList;
import java.util.Stack;
import java.util.Iterator;
public class Graph {
	
	private Node[] vertices; //array to store all vertices
	private Edge[][] adjacencyMatrix; //adjacency matrix to implement graph/keep track of edges
	private int vertexCount; //keeps track of # of vertices
	
	/**
	 * Constructor method to initialize an empty graph
	 * Initializes vertices, assumes no edges
	 * @param n number of vertices
	 */
	public Graph(int n){
		this.vertexCount = n;
		this.vertices = new Node[n];
		this.adjacencyMatrix = new Edge[n][n];
		
		for (int i = 0;i < n; i++){
			vertices[i] = new Node(i);
		}
		
		
	}
	
	/**
	 * inserts an edge between two given nodes
	 * @param u
	 * @param v
	 * @param edgeType
	 * @throws GraphException if node DNE or if there is an edge between the 2 nodes already
	 */
	public void insertEdge(Node u, Node v, String edgeType) throws GraphException{
		
		if (0 > u.getName() ||  u.getName() > this.vertexCount - 1){
			throw new GraphException("First Endpoint DNE");
		}
		
		if (0 > v.getName() ||  v.getName() > this.vertexCount - 1){
			throw new GraphException("Second Endpoint DNE");
		}
		
		if ((this.adjacencyMatrix[u.getName()][v.getName()]) != null){
			throw new GraphException("Edge Already Exists");
		}
		
		Edge edgeToAdd = new Edge(u,v,edgeType);
		Edge edgeToAdd_Sym = new Edge(v,u,edgeType); //symmetrical edge to add since graph is undirected
		
		this.adjacencyMatrix[u.getName()][v.getName()] = edgeToAdd;
		this.adjacencyMatrix[v.getName()][u.getName()] = edgeToAdd_Sym;
		
		
		
	}
	
	/**
	 * access node by index number/node's name
	 * @param name
	 * @return node with inputted name
	 * @throws GraphException if node DNE
	 */
	public Node getNode(int name) throws GraphException{
		if (0 > name ||  name > this.vertexCount - 1){
			System.out.println(Thread.currentThread().getStackTrace());
			throw new GraphException("Node DNE");
		}
		
		return vertices[name];
		
	}
	
	/**
	 * Creates a stack of all edges attached to input node
	 * returns iterator of this stack
	 * @param u
	 * @return iterator of all edges attached to this node or null if vertex has no edges
	 * @throws GraphException if u is not a node of the graph
	 */
	public Iterator incidentEdges (Node u) throws GraphException{
		
		if (0 > u.getName() ||  u.getName() > this.vertexCount - 1){
			throw new GraphException("Node DNE");
		}
		
		Stack<Edge> edges = new Stack<Edge>(); //stack to store all incident edges
		
		for (int i = 0; i < this.vertexCount; i++){ //iterates over all vertices to find adjacent vertices
			
			if (this.adjacencyMatrix[u.getName()][i] != null){
				edges.push(this.adjacencyMatrix[u.getName()][i]);
			}
			
		}
		
		
		if (edges.size() > 0){
			return edges.iterator();
		} else {
			return null;
		}
		
		
		
	}
	
	/**
	 * gets edge between two input nodes
	 * @param u
	 * @param v
	 * @return edge between two input nodes
	 * @throws GraphException if no edge between u or v, or if u or v DNE
	 */
	public Edge getEdge(Node u, Node v) throws GraphException{
		
		if (0 > u.getName() ||  u.getName() > this.vertexCount - 1){
			throw new GraphException("First Endpoint DNE");
		}
		
		if (0 > v.getName() ||  v.getName() > this.vertexCount - 1){
			throw new GraphException("Second Endpoint DNE");
		}
		
		if ((this.adjacencyMatrix[u.getName()][v.getName()]) == null){
			throw new GraphException("Edge Doesn't Exist");
		}
		
		return this.adjacencyMatrix[u.getName()][v.getName()];
		
		
	}
	
	/**
	 * returns whether or not u and v are adjacent
	 * @param u
	 * @param v
	 * @return true if u and v have edge between them, false otherwise
	 * @throws GraphException if u or v DNE
	 */
	boolean areAdjacent(Node u, Node v) throws GraphException{
		if (0 > u.getName() ||  u.getName() > this.vertexCount - 1){
			throw new GraphException("First Endpoint DNE");
		}
		
		if (0 > v.getName() ||  v.getName() > this.vertexCount - 1){
			throw new GraphException("Second Endpoint DNE");
		}
		
		if ((this.adjacencyMatrix[u.getName()][v.getName()]) == null){
			return false;
		}
		else{
			return true;
		}
		
	}

}
