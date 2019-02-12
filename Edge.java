/**
 * Edge class represents an edge of the graph (connection between 2 nodes)
 * Edges will be used with nodes to represent an entire graph
 * Note: These are undirected edges 
 * (which endpoint is endpoint1 and which is endpoint2 is completely arbitrary;
 * however it is important to keep track of them when using solve/pathfinder method of labyrinth class
 * @author fmoham26 Fawaz Mohammad
 *
 */
public class Edge {
	
	private Node endpoint1; //vertex(Node object) at one end of edge
	private Node endpoint2; //vertex(Node object) at other end of edge
	private String type; //whether edge is corridor, wall, thickwall, or metal wall. Different walls will require different resources to go through
	
	/**
	 * Constructor. Sets all instance variables of Edge class
	 * @param u first endpoint
	 * @param v second endpoint
	 * @param type whether edge is corridor, wall, thickwall, metalwall
	 */
	public Edge(Node u, Node v, String type){
		this.endpoint1 = u;
		this.endpoint2 = v;
		this.type = type;
		
	}
	
	/**
	 * get method for endpoint1
	 * @return endpoint1
	 */
	public Node firstEndpoint(){
		return this.endpoint1;
	}
	
	/**
	 * get method for endpoint2
	 * @return endpoint2
	 */
	public Node secondEndpoint(){
		return this.endpoint2;
	}
	
	/**
	 * get method for type
	 * @return type
	 */
	public String getType(){
		return this.type;
	}
	
	/**
	 * set method for type
	 * @param type
	 */
	public void setType(String type){
		this.type = type;
	}


}
