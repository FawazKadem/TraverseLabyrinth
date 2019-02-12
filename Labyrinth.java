/**
 * Labyrinth class representing labyrinth to be traversed
 * Graph will be used to store labyrinth and find a solution for it
 * author: fmoham26 (Fawaz Mohammad)
 */
import java.io.*;
import java.util.*;

public class Labyrinth {


	private int width; //number of rooms in each row
	private int length; //number of rooms in each column
	private int brickBombs; //number of brickBombs available to get through walls
	private int acidBombs; //number of acidbombs available to get through metal walls



	private Node entrance; //starting vertex
	private Node exit; //destination vertex 

	private Graph labGraph = null; //graph representing labyrinth
	
	private Stack<Node> solution; //stack to store solution nodes. instance variable bc used in solve and solve helper method

	/**
	 * Constructs labyrinth using input file
	 * Reads file and recognizes meaning of characters to create matching labyrinth
	 * @throws GraphException
	 * @throws LabyrinthException 
	 * @throws IOException 
	 * 
	 */
	public Labyrinth(String inputFile) throws GraphException, LabyrinthException, IOException {

		int verticesFound = 0;

		BufferedReader in = null;
		String line;

		String fileName = inputFile;

		try {
			// opening input file

			in = new BufferedReader(new FileReader(fileName));

			line = in.readLine(); //removes scale factor
			line = in.readLine();
			this.width = Integer.parseInt(line);
			line = in.readLine();
			this.length = Integer.parseInt(line);
			line = in.readLine();
			this.brickBombs = Integer.parseInt(line);
			line = in.readLine();
			this.acidBombs = Integer.parseInt(line);


			labGraph = new Graph(length * width); //creates graph
			
			

			// fill the graph

			for (int i = 0; i < (length + length - 1); i++) {
				line = in.readLine();

				// go through each character in a line

				for (int j = 0; j < (width + width - 1); j++) {
					char legendValue = line.charAt(j);

					

					if (legendValue == 'b') {
						
						this.entrance = this.labGraph.getNode(verticesFound); 
						
						verticesFound++;
					} else if (legendValue == 'x') {
						this.exit = this.labGraph.getNode(verticesFound);
						
						verticesFound++;
					} else if (legendValue == '+') {
						verticesFound++;
					} else if (legendValue == ' ') {

						// horizontal intersections
					} else if (legendValue == 'h' || legendValue == 'H' || legendValue == 'm' || legendValue == '-') {
						
						if (legendValue == 'h'){
						labGraph.insertEdge(labGraph.getNode(verticesFound-1), labGraph.getNode(verticesFound), "wall");
						}
						if (legendValue == 'H'){
							labGraph.insertEdge(labGraph.getNode(verticesFound-1), labGraph.getNode(verticesFound), "thickWall");
						}
						if (legendValue == 'm'){
							labGraph.insertEdge(labGraph.getNode(verticesFound-1), labGraph.getNode(verticesFound), "metalWall");
						}
						if (legendValue == '-'){
							labGraph.insertEdge(labGraph.getNode(verticesFound-1), labGraph.getNode(verticesFound), "corridor");
						}						
						
						
						//vertical intersections
					} else if (legendValue == 'v' || legendValue == 'V' || legendValue == 'M' || legendValue == '|') {
						
						if (legendValue == 'v'){
							labGraph.insertEdge(labGraph.getNode(verticesFound - width + (j/2)), labGraph.getNode(verticesFound + (j/2)), "wall");
						}
						if (legendValue == 'V'){
							labGraph.insertEdge(labGraph.getNode(verticesFound - width + (j/2)), labGraph.getNode(verticesFound + (j/2)), "thickWall");
						}
						if (legendValue == 'M'){
							labGraph.insertEdge(labGraph.getNode(verticesFound - width + (j/2)), labGraph.getNode(verticesFound + (j/2)), "metalWall");
						}
						if (legendValue == '|'){

							labGraph.insertEdge(labGraph.getNode(verticesFound - width + (j/2)), labGraph.getNode(verticesFound + (j/2)), "corridor");
						}

					} 

				}

			}
			
			

		}

		//exception if any errors with reading file
		catch (IOException e) {
			throw new LabyrinthException("Error with file");
			
		}
		
		//close reader
		finally {
			in.close();
		}

	}
	
	/**
	 * get method for graph representing this labyrinth
	 * @return graph of edges/vertices
	 * @throws LabyrinthException if graph hasn't been constructed
	 */
	public Graph getGraph() throws LabyrinthException{
		if (this.labGraph == null){
			throw new LabyrinthException("Graph is undefined");
		}
		return this.labGraph;
	}
	
	
	
	
	
	/**
	 * Uses pathfinder method to return a solution if it exists
	 * Returns null if no solution exists
	 * @return
	 * @throws GraphException
	 */
	public Iterator solve() throws GraphException{
		solution = new Stack<Node>(); //stack to store solution
	
		if (pathfinder(this.entrance,this.exit)){
			return solution.iterator();
		}

		return null;
		
	}
	
	
	/**
	 * Solve helper method
	 * Modifies solution stack to store a path of vertices to the exit
	 * @param u input node (entrance for first call)
	 * @param d destination node (exit)
	 * @return true if solution exists, false otherwise
	 * @throws GraphException
	 */
	private boolean pathfinder(Node u, Node d) throws GraphException{
		u.setMark(true);
		solution.push(u);
		Iterator incident; 
		
		Edge nextEdge; 
		
		if (u.getName() == d.getName()){return true;};
		
		incident = labGraph.incidentEdges(u); //iterator storing all incidentEdges of node u
		
		while (incident.hasNext()){
			nextEdge = (Edge) incident.next();
			
			Node otherEndpoint; //vertex connected to u through an edge
			
			if (nextEdge.firstEndpoint() == u)
			{
				otherEndpoint = nextEdge.secondEndpoint();
			} else {
				otherEndpoint = nextEdge.firstEndpoint();
			}
			
			if (otherEndpoint.getMark() != true){ //check if vertex has already been interested
				
				if (nextEdge.getType() == "corridor"){
					if (pathfinder(otherEndpoint, d) == true) {return true;}
				}
				
				if (nextEdge.getType() == "wall"){
					if (brickBombs >= 1){
						brickBombs--;
						if (pathfinder(otherEndpoint, d) == true) {return true;}
					}
				}
				if (nextEdge.getType() == "thickWall"){
					if (brickBombs >= 2){
						brickBombs -= 2;
						if (pathfinder(otherEndpoint, d) == true) {return true;}
					}
				}
				
				if (nextEdge.getType() == "metalWall"){
					if (acidBombs >= 1){

						acidBombs--;
						if (pathfinder(otherEndpoint, d) == true) {return true;}
					}
				}
				
				
			}
			
		}
		
		Node removedNode = solution.pop();
		if (solution.isEmpty()){return false;} //if solution is empty, there is no solution
		
		removedNode.setMark(false); //unmark node
		Edge connectingEdge = labGraph.getEdge(removedNode, solution.peek()); //last edge crossed to get to removed node
		
		
		//add back bombs if the node removed required a bomb to get to it
		if (connectingEdge.getType() == "wall"){brickBombs++;} 
		if (connectingEdge.getType() == "thickWall"){brickBombs += 2;} 
		if (connectingEdge.getType() == "metalWall"){acidBombs++;} 
		
		
		return false;
		
	}

}

















