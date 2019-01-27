package pack;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;


/*
 *Write a program that has 7 operations, your program should run until exit
operation is selected. The 7 operations are
@author Amit Pandit
@version 1.0
@since   12-03-2018
 */

/* Class : DiGraph
 * This class contains all the worker functions required to carry out different operations on Graph
 */
@SuppressWarnings({ "unused" })

public class DiGraph<T extends Comparable<T>>
{
	public enum State { UNVISITED, VISITED, COMPLETE };

	private ArrayList<Vertex> vertices;
	private ArrayList<Edge> edges;
	HashMap<T, T> trackList = new HashMap<T, T>();
	boolean cycle = false;

	// Constructor
	
	public DiGraph()
	{
		vertices = new ArrayList<>();
		edges = new ArrayList<>();
	}

	/*
	  * Below methods is used to create edge between two vertices.
	  * @param args : directions for an edge
	  * @return: unused
	  */
	public void add(T from, T to, int cost)
	{
		Edge temp = findEdge(from, to);
		if (temp != null)
		{
			// Update Existing edge cost.
			
			System.out.println("Edge " + from + "," + to + " already exists.The cost is updated.");
			temp.cost = cost;
		}
		else
		{
			Edge e = new Edge(from, to, cost);
			edges.add(e);
		}
	}

	/*
	  * Below methods is used to find given vertex in the list
	  * @param args : given vertex
	  * @return: vertex
	  */
	private Vertex findVertex(T v)
	{
		for (Vertex each : vertices)
		{
			if (each.value.compareTo(v)==0)
				return each;
		}
		return null;
	}

	/*
	  * Below methods is used to find given edge in the edge list
	  * @param args : given vertices
	  * @return: edge
	  */
	private Edge findEdge(Vertex v1, Vertex v2)
	{
		for (Edge each : edges)
		{
			if (each.from.equals(v1) && each.to.equals(v2))
			{
				return each;
			}
		}
		return null;
	}

	/*
	  * Below methods is used to find given edge in the edge list
	  * @param args : given directions
	  * @return: edge
	  */
	private Edge findEdge(T from, T to)
	{
		for (Edge each : edges)
		{
			if (each.from.value.equals(from) && each.to.value.equals(to))
			{
				return each;
			}
		}
		return null;
	}

	/*
	  * Below methods is used to clear the states of vertices.
	  * @param args : given vertices
	  * @return: unused
	  */
	private void clearStates()
	{
		for (Vertex each : vertices)
		{
			each.state = State.UNVISITED;
		}
	}

	/*
	  * Below methods is used to check if graph is connected
	  * @param args :unused
	  * @return: boolean
	  */
	public boolean isConnected()
	{
		for (Vertex each : vertices)
		{
			if (each.state != State.COMPLETE)
				return false;
		}
		return true;
	}

	// Performs a DFS
	
	public boolean DepthFirstSearch()
	{
		if (vertices.isEmpty()) return false;

		clearStates();

		Vertex root = vertices.get(0);
		if (root==null) return false;

		DepthFirstSearch(root);
		return isConnected();
	}

	private void DepthFirstSearch(Vertex v)
	{
		v.state = State.VISITED;
		System.out.println(v.value);

		for (Vertex each : v.outgoing)
		{
			if (each.state ==State.UNVISITED)
			{
				DepthFirstSearch(each);
			}
		}
		v.state = State.COMPLETE;
	}

	// Perform BFS

	public boolean BreadthFirstSearch()
	{
		if (vertices.isEmpty()) return false;
		clearStates();

		Vertex root = vertices.get(0);
		if (root==null) return false;

		Queue<Vertex> queue = new LinkedList<>();
		queue.add(root);
		
		root.state = State.COMPLETE;

		while (!queue.isEmpty())
		{

			root = queue.peek();
			System.out.println(root.value);
			for (Vertex each : root.outgoing)
			{

				if (each.state==State.UNVISITED)
				{
					each.state = State.COMPLETE;
					queue.add(each);
				}
			}
			queue.remove();
		}
		return isConnected();
	}

	public boolean BreadthFirstSearch(T v1)
	{
		if (vertices.isEmpty()) return false;
		clearStates();

		Vertex root = findVertex(v1);
		if (root==null) return false;

		Queue<Vertex> queue = new LinkedList<>();
		queue.add(root);
		root.state = State.COMPLETE;

		while (!queue.isEmpty())
		{
			root = queue.peek();
			for (Vertex each : root.outgoing)
			{

				if (each.state==State.UNVISITED)
				{
					each.state = State.COMPLETE;
					queue.add(each);
				}
			}
			queue.remove();
		}
		return isConnected();
	}

	/*
	  * Below methods is used to find shortest path in the graph for a given vertex
	  * @param args : given vertice
	  * @return: edge
	  */
	private boolean Dijkstra(T v1)
	{
		if (vertices.isEmpty()) return false;

		// reset all vertices minDistance and previous
		resetDistances();

		// make sure it is valid
		Vertex source = findVertex(v1);
		if (source==null) return false;

		// set to 0 and add to heap
		source.minDistance = 0;
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.add(source);

		while (!pq.isEmpty())
		{
			//pull off top of queue
			Vertex u = pq.poll();

			// loop through adjacent vertices
			for (Vertex v : u.outgoing)
			{
				// get edge
				Edge e = findEdge(u, v);
				if (e==null) return false;
				// add cost to current
				int totalDistance = u.minDistance + e.cost;
				if (totalDistance < v.minDistance)
				{
					// new cost is smaller, set it and add to queue
					pq.remove(v);
					v.minDistance = totalDistance;
					// link vertex
					v.previous = u;
					pq.add(v);
				}
			}
		}
		return true;
	}

	/**
	 * Goes through the result tree created by the Dijkstra method
	 * and steps backward
	 */
	
	private List<String> getShortestPath(Vertex target)
	{
		List<String> path = new ArrayList<String>();

		// check for no path found
		if (target.minDistance==Integer.MAX_VALUE)
		{
			path.add("No path found");
			return path;
		}

		// loop through the vertices from end target 
		for (Vertex v = target; v !=null; v = v.previous)
		{
			path.add(v.value + " : cost : " + v.minDistance);
		}

		// flip the list
		Collections.reverse(path);
		return path;
	}

	/**
	 * for Dijkstra, resets all the path tree fields
	 */
	private void resetDistances()
	{
		for (Vertex each : vertices)
		{
			each.minDistance = Integer.MAX_VALUE;
			each.previous = null;
		}
	}

	public List<String> getPath(T from, T to)
	{
		boolean test = Dijkstra(from);
		if (test==false) return null;
		List<String> path = getShortestPath(findVertex(to));
		return path;
	}

	// string of vertices
	
	@Override
	public String toString()
	{
		String retval = "";
		for (Vertex each : vertices)
		{
			retval += each.toString() + "\n";
		}
		return retval;
	}

	/**
	 * list all the edges into a string
	 */
	public String edgesToString()
	{
		String retval = "";
		for (Edge each : edges)
		{
			retval += each + "\n";
		}
		return retval;
	}


	class Vertex implements Comparable<Vertex>
	{
		T value;

		// variables for Dijkstra Tree
		Vertex previous = null;
		int minDistance = Integer.MAX_VALUE;

		List<Vertex> incoming;
		List<Vertex> outgoing;
		State state;
		boolean color;
		

		/**
		 * Creates new Vertex with value T
		 */
		public Vertex(T value)
		{
			this.value = value;
			incoming = new ArrayList<>();
			outgoing = new ArrayList<>();
			state = State.UNVISITED;
			color=false;
		}

		@Override
		public int compareTo(Vertex other)
		{
			return Integer.compare(minDistance, other.minDistance);
		}

		/**
		 * Add Vertex to adjacent incoming list
		 */
		public void addIncoming(Vertex vert)
		{
			incoming.add(vert);
		}
		public void addOutgoing(Vertex vert)
		{
			outgoing.add(vert);
		}

		// Get string of Vertex with all it's incoming and outgoing adjacency

		@Override
		public String toString()
		{
			String retval = "";
			retval += "Vertex: " + value + " : ";
			retval += " In: ";
			for (Vertex each : incoming) retval+= each.value + " ";
			retval += "Out: ";
			for (Vertex each : outgoing) retval += each.value + " ";
			return retval;
		}
	}

	class Edge
	{
		Vertex from;
		Vertex to;
		int cost;

		public Edge(T v1, T v2, int cost)
		{
			from = findVertex(v1);
			if (from == null)
			{
				from = new Vertex(v1);
				vertices.add(from);
			}
			to = findVertex(v2);
			if (to == null)
			{
				to = new Vertex(v2);
				vertices.add(to);
			}
			this.cost = cost;

			from.addOutgoing(to);
			to.addIncoming(from);
		}

		@Override
		public String toString()
		{
			return "Edge From: " + from.value + " to: " + to.value + " cost: " + cost;
		}
	}
	
	public boolean callcycle(int v)
	{
	
		Vertex root = vertices.get(v);
		if(isCyclic(root)) {
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	
	private boolean isCyclic(Vertex v)
	{
		
		for (Vertex each : vertices)
		{
		  if (isCyclicUtil(each)) 
          return true; 
		}
  
        return false; 
	}
	
	public boolean isCyclicUtil(Vertex v)
	{
	
		if (trackList.containsValue(v.value)) 
		{
            return true; 
		}
		
        if (v.state == State.VISITED) 
            return false; 
              
        v.state = State.VISITED; 
  
        trackList.put(v.value, v.value);
       // List<Integer> children = adj.get(i); 
          
        for (Vertex each : v.outgoing)
        {
            if (isCyclicUtil(each)) 
                return true; 
        }
                  
        
        trackList.remove(v.value);
            //recStack[i] = false; 
  
        return false; 
	}
	
	public boolean Bipartite(int givenVertex)
	{
		if (vertices.isEmpty()) return false;

		clearStates();
		// get first node
		Vertex root = vertices.get(givenVertex);
		
		//System.out.println("vertices"+vertices.);
		
		if (root==null) return false;

		// call recursive function
		if(BipartiteDFS(root)) {
			return true;
		}else {
			return false;
		}
		
		//return isConnected();
	}
	
	private boolean BipartiteDFS(Vertex v)
	{
		
		v.state = State.VISITED;
		// loop through neighbors
		
		//System.out.println("DFS on"+v.value);
		for (Vertex each : v.outgoing)
		{
			//System.out.print("outoing"+each.value);
			if (each.state ==State.UNVISITED)
			{
				//System.out.println("unvisited"+each.value);
				each.state = State.VISITED;
				each.color = !v.color;
				if(BipartiteDFS(each) == false)
				{
					return false;
				}
				else if(each.color == v.color) {
					return false;
				}
			}
		}
		v.state = State.COMPLETE;
		return true;
	}
	


	
      	
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{

		// create graph
		@SuppressWarnings("rawtypes")
		DiGraph graph = new DiGraph();
		int v, edge, from, to, weight;
		String ip, task;
		@SuppressWarnings("resource")
		Scanner keyboard= new Scanner(System.in);
		
		System.out.println("Enter the number of vertices in the graph");
		v=keyboard.nextInt();
		System.out.println("Enter the number of edges in the graph");
		edge=keyboard.nextInt();
		
		for (int i=1; i<=edge; i++) {
			System.out.println("Total number of edges given :"+(graph.edges.size()-1));
			System.out.println("Enter source vertex");
			from=keyboard.nextInt();
			System.out.println("Enter destination vertex");
			to=keyboard.nextInt();
			System.out.println("Enter cost of the edge");
			weight=keyboard.nextInt();
			graph.add(from, to, weight);			
		}

		
		System.out.println(graph);
		System.out.println(graph.edgesToString());
		
		do {
			   System.out.println("\nChoose any operation from below graph operations\n");
			   System.out.println("1.Perform DFS Traversal on the Graph");
			   System.out.println("2.Perform BFS Traversal on the Graph");
			   System.out.println("3.Find Shortest Path");
			   System.out.println("4.Detect if there is a Cycle in the graph");
			   System.out.println("5.Check if the graph is Bipartite or not");
			   System.out.println("6.Check if Graph is a tree or not");
			   System.out.println("Select the operation [1-6] : ");
			   task=keyboard.next();
			   switch(task) {
			   case "1":
				   System.out.println("DFS Traversal :");
				   graph.DepthFirstSearch();
				   break;
				   
			   case "2":
					System.out.println("BFS Traversal :");
					graph.BreadthFirstSearch();
					System.out.println();
					break;
					
			   case "3":
					System.out.println("Enter the source vertex from where you want to find shortest path");
					String givenVertex = keyboard.next();
					int vertex = Integer.parseInt(givenVertex);
					
					for (int j=1; j<v; j++) {
						List <String> path = graph.getPath(vertex,j);
						int count=0;
						for(String each : path) {
							count++;
							if (count==path.size()) {
								System.out.println(each);
							}
					
						}		
					}
					break;
					
			   case "4":
			        if(graph.callcycle(0)) 
			            System.out.println("Graph contains cycle"); 
			        else
			            System.out.println("Graph doesn't contain cycle");
			        break;
			   
			   case "5":
				   if(graph.Bipartite(0))
					{
						System.out.println("Graph is Bipartite");
					}
					else
					{
						System.out.println("Graph is not Bipartite");
					}
				   break;
				   
			   case "6":
					if(v - 1 == edge){
						if(graph.DepthFirstSearch())
						{
							//graph.isCycle(treeVertex);
							if(!graph.callcycle(0)) {
							System.out.println("Graph is Tree");
						
							}
						}
					}
				else
				{
					System.out.println("Graph is not Tree");
				}
				break;
				
				default: 
					System.out.println("Invalid Task Index. Please select the valid Task ID.."); 
					break; 				   
			   }
			   System.out.println("\nDo you want to continue (Type y or n) \n");
			   ip=keyboard.next();
		}while (ip.equalsIgnoreCase("Y"));		
	}
}