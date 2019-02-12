/**
 * Node class represents a vertex of the graph
 * Each node has a name (integer value) and a mark (t or f)
 * Nodes will be used with edges to represent a graph
 * @author fmoham26 (Fawaz Mohammad)
 *
 */
public class Node {
	
	private int name; //name of vertex. ID value
	private boolean mark; //whether this node has been marked or not. Used in solve/pathFinder methods of Labyrinth class
	
	/**
	 * constructor
	 * @param name
	 */
	public Node(int name){
		this.name = name;
	}
	
	/**
	 * sets mark of node
	 * @param mark what mark to set this mark to (T or F)
	 */
	public void setMark(boolean mark){
		this.mark = mark;
	}
	
	/**
	 * @return mark of node
	 */
	public boolean getMark(){
		return this.mark;
	}
	
	/**
	 * @return name of node
	 */
	public int getName(){
		return this.name;
	}

}
