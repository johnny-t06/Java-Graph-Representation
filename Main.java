
import java.util.*;


public class Main {

    // Run "java -ea Main" to run with assertions enabled (If you run
    // with assertions disabled, the default, then assert statements
    // will not execute!)
    
    public static void test1() {
		Graph g = new ListGraph();
		assert g.addNode("a");
		assert !g.addNode("a");
		assert g.addNode("q");
		assert g.addNode("t");

		assert g.hasNode("a");
		assert !g.hasNode("null");	// return false

		assert g.addEdge("a", "a");		//self edge

		assert g.hasEdge("a", "a");		//self check
		// assert !g.hasEdge("q", "n");	//return false
    }

	public static void addEdgethrow() {
		Graph g = new ListGraph();
		
		try {
			g.addEdge("b", "c");
		} catch (UnsupportedOperationException e) {
			// System.out.println("Caught " + e.getMessage());		//works
			assert true;
		}
	}

	
	public static void removeNodeBoth() {
		Graph g = new ListGraph();
		g.addNode("a");
		g.addNode("b");
		g.addNode("c");

		
		// g.print();
		assert !g.removeNode("d");
		assert g.removeNode("c");
		
		// g.print();

	}
    // public static void test2() {
	// Graph g = new ListGraph();
	// EdgeGraph eg = new EdgeGraphAdapter(g);
	// Edge e = new Edge("a", "b");
	// assert eg.addEdge(e);
	// assert eg.hasEdge(e);
    // }

	public static void removeEdgeBoth() {
		Graph g = new ListGraph();
		g.addNode("a");
		g.addNode("b");
		g.addNode("c");


		g.addEdge("a", "b");
		g.addEdge("b", "a");
		
		// g.print();
		assert !g.removeEdge("a", "c"); 	//asserts false (no edge there)
		assert g.removeEdge("a", "b");
		// System.out.println("Post Remove: ");
		// g.print();
	}
  
	public static void listNodes() {
		Graph g = new ListGraph();
		g.addNode("a");
		g.addNode("b");
		g.addNode("c");
		List<String> nodesCopy = g.nodes();

		// int size = nodesCopy.size();
		// System.out.println("Size: " + Integer.toString(size));

		// for (String x : nodesCopy) {
		// 	System.out.print(x + " ");
		// }
		// System.out.println();
	}

	public static void listNodesEmpty() {
		Graph g = new ListGraph();
		List<String> nodesCopy = g.nodes();
		
		// int size = nodesCopy.size();
		// System.out.println("Nodes Size: " + Integer.toString(size));

		// for (String x : nodesCopy) {
		// 	System.out.print(x + "~");
		// }
	}

	public static void succNonEmpty() {
		Graph g = new ListGraph();
		g.addNode("a");
		g.addNode("b");
		g.addNode("c");

		g.addEdge("a", "b");
		g.addEdge("a", "a");
		g.addEdge("c", "a");

		List <String> succCopy = g.succ("a");

		// int size = succCopy.size();
		// System.out.println("Size: " + Integer.toString(size));

		// for (String x : succCopy) {
		// 	System.out.print(x + " ");
		// }
		// System.out.println();
	}

	public static void succThrow() {
		Graph g = new ListGraph();
		
		try {
			g.succ("a");
		} catch (UnsupportedOperationException e) {
			// System.out.println("Caught " + e.getMessage());		//works
			assert true;
		}
		
	}

	public static void succEmpty() {
		Graph g = new ListGraph();
		g.addNode("a");
		List <String> succCopy = g.succ("a");

		// int succSize = succCopy.size();
		// System.out.println("SuccSize: " + Integer.toString(succSize));

		// for (String x : succCopy) {
		// 	System.out.print(x + " ");
		// }
		// System.out.println();
	}

	public static void predNonEmpty() {
		Graph g = new ListGraph();
		g.addNode("a");
		g.addNode("b");
		g.addNode("c");
		g.addNode("d");


		g.addEdge("a", "b");
		g.addEdge("a", "a");
		g.addEdge("c", "a");
		g.addEdge("d", "a");


		List <String> predCopy = g.pred("a");

		// int predSize = predCopy.size();
		// System.out.println("PredSize: " + Integer.toString(predSize));

		// for (String value : predCopy) {
		// 	System.out.print(value + " ");
		// }
		// System.out.println();
	}

	public static void predThrow() {
		Graph g = new ListGraph();
		
		try {
			g.pred("a");
		} catch (UnsupportedOperationException e) {
			// System.out.println("Caught " + e.getMessage());		//works
			assert true;
		}
		
	}

	public static void predEmpty() {
		Graph g = new ListGraph();
		g.addNode("a");
		List <String> predCopy = g.pred("a");

		// int predSize = predCopy.size();
		// System.out.println("PredSize: " + Integer.toString(predSize));

		// for (String x : predCopy) {
		// 	System.out.print(x + " ");
		// }
		// System.out.println();
	}

	public static void unionNonEmpty() {
		Graph g1 = new ListGraph();
		g1.addNode("a");
		g1.addNode("b");
		g1.addNode("c");
		g1.addEdge("a", "b");
		g1.addEdge("a", "c");
		
		Graph g2 = new ListGraph();
		g2.addNode("x");
		g2.addNode("y");
		g2.addNode("z");
		g2.addEdge("x", "y");
		g2.addEdge("y", "z");
		
		Graph union = g1.union(g2);
		
		// Check nodes
		assert union.hasNode("a");
		assert union.hasNode("b");
		assert union.hasNode("c");
		assert union.hasNode("x");
		assert union.hasNode("y");
		assert union.hasNode("z");
	
		// Check edges
		assert union.hasEdge("a", "b");
		assert union.hasEdge("a", "c");
		assert union.hasEdge("x", "y");
		assert union.hasEdge("y", "z");
	
		// Check nonexistent edges
		assert !union.hasEdge("a", "x");
		assert !union.hasEdge("b", "y");
		assert !union.hasEdge("c", "z");
	
	}
	public static void unionOverlap(){
		Graph g1 = new ListGraph();
		g1.addNode("a");
		g1.addNode("b");
		g1.addNode("c");
		g1.addEdge("a", "b");
		g1.addEdge("a", "c");
		
		Graph g2 = new ListGraph();
		g1.addNode("a");
		g1.addNode("b");
		g1.addNode("d");
		g1.addEdge("a", "b");
		g1.addEdge("b", "a");
		g1.addEdge("a", "d");


		Graph union = g1.union(g2);

		assert union.hasNode("a");
		assert union.hasNode("b");
		assert union.hasNode("c");
		assert union.hasNode("d");

		assert union.hasEdge("a", "b");
		assert union.hasEdge("a", "c");
		assert union.hasEdge("b", "a");
		assert union.hasEdge("a", "d");
	
		// Check nonexistent edges
		assert !union.hasEdge("a", "a");
		assert !union.hasEdge("b", "b");
		assert !union.hasEdge("c", "c");
	}

	public static void subGraphNonEmpty() {
		Graph g = new ListGraph();
		assert g.addNode("A");
		assert g.addNode("B");
		assert g.addNode("C");
		assert g.addNode("D");
		assert g.addEdge("A", "B");
		assert g.addEdge("A", "C");
		assert g.addEdge("C", "D");

		Set<String> nodes = new HashSet<>();
		nodes.add("A");
		nodes.add("B");
		nodes.add("C");
		nodes.add("E");

		Graph subgraph = g.subGraph(nodes);
		assert subgraph.hasNode("A");
		assert subgraph.hasNode("B");
		assert subgraph.hasNode("C");
		assert !subgraph.hasNode("D");
		assert !subgraph.hasNode("E");

		assert subgraph.hasEdge("A", "B");
		assert subgraph.hasEdge("A", "C");
		// assert !subgraph.hasEdge("C", "D");		
		//goes to exception, spec says do not need handle
	}

	public static void subGraphEmpty() {
		Graph g = new ListGraph();
		assert g.addNode("A");
		assert g.addNode("B");
		assert g.addNode("C");
		assert g.addNode("D");
		assert g.addEdge("A", "B");
		assert g.addEdge("A", "C");
		assert g.addEdge("C", "D");

		Set<String> nodes = new HashSet<>();


		Graph subgraph = g.subGraph(nodes);
		
		assert !subgraph.hasNode("A");
		assert !subgraph.hasNode("B");
		assert !subgraph.hasNode("C");
		assert !subgraph.hasNode("D");
		assert !subgraph.hasNode("E");

		// assert subgraph.hasEdge("A", "B");
		// assert subgraph.hasEdge("A", "C");
		// assert !subgraph.hasEdge("C", "D");		
		//goes to exception, spec says do not need handle
	}

	public static void connectedNonEmpty() {
		Graph g = new ListGraph();
		assert g.addNode("A");
		assert g.addNode("B");
		assert g.addNode("C");
		assert g.addNode("D");
		assert g.addEdge("A", "B");
		assert g.addEdge("B", "C");
		assert g.addEdge("A", "D");

		assert g.connected("A", "B");
		assert g.connected("A", "D");
		assert !g.connected("B", "D");
	}

	public static void connected2() {
		Graph g = new ListGraph();
		assert g.addNode("a");
		assert g.addNode("b");
		assert g.addNode("c");
		assert g.addEdge("a","b");
		assert g.addEdge("b","c");
	
		// G is only connected from a to c, and not c to a
		assert !g.connected("c", "a");
		assert g.connected("a", "c");
	}

	public static void connectedTest() {
		Graph g = new ListGraph();
		assert g.addNode("a");
		assert g.addNode("b");
		assert g.addNode("c");
		assert g.addNode("d");
		assert g.addEdge("a", "b");
		assert g.addEdge("a", "c");
		assert g.addEdge("c", "d");
		assert g.connected("a", "d");
		assert g.removeNode("c");
		assert !g.connected("a", "d");
	}

	public static void e_addEdge() {
		Graph g = new ListGraph();
		EdgeGraph eg = new EdgeGraphAdapter(g);
		Edge edge1 = new Edge("a", "b");
		// Edge emptyEdge = new Edge("", "");
		assert eg.addEdge(edge1);
		assert !eg.addEdge(edge1);

	}
	
	public static void e_hasNode() {
		Graph g = new ListGraph();
		EdgeGraph eg = new EdgeGraphAdapter(g);
		Edge edge1 = new Edge("a", "b");
		Edge edge2 = new Edge("b", "a");
		Edge edge3 = new Edge("c", "a");

		assert !eg.hasNode("a");		//checks if edges to node a, but none are added
		assert eg.addEdge(edge1);
		assert eg.addEdge(edge2);
		assert eg.addEdge(edge3);
		assert eg.hasNode("a");
		assert eg.hasNode("b");
		assert !eg.hasNode("d");
	}

	public static void e_hasEdge() {
		Graph g = new ListGraph();
		EdgeGraph eg = new EdgeGraphAdapter(g);
		Edge edge1 = new Edge("a", "b");
		Edge edge2 = new Edge("b", "a");
		Edge edge3 = new Edge("c", "a");
		
		assert !eg.hasEdge(edge1);
		assert !eg.hasEdge(edge3);

		assert eg.addEdge(edge1);
		assert eg.addEdge(edge2);
		assert eg.addEdge(edge3);

		assert eg.hasEdge(edge1);
		assert eg.hasEdge(edge2);
		assert eg.hasEdge(edge3);

	}

	public static void eg_removeEdge() {
		Graph g = new ListGraph();
		EdgeGraph eg = new EdgeGraphAdapter(g);
		Edge edge1 = new Edge("a", "b");
		Edge edge3 = new Edge("c", "a");

		assert !eg.removeEdge(edge1);
		
		assert eg.addEdge(edge1);
		assert eg.addEdge(edge3);

		assert eg.removeEdge(edge3);

		assert !eg.hasNode("c");	//node c is deleted because last node connection edge 3, is gone
		assert eg.hasNode("a");		//node a is good because it has edge1 connection
		
	}

	public static void eg_removeEdgeOther() {
		Graph g = new ListGraph();
		EdgeGraph eg = new EdgeGraphAdapter(g);
		Edge edge1 = new Edge("a", "b");
		Edge edge2 = new Edge("c", "d");
		Edge edge3 = new Edge("c", "a");


		assert !eg.removeEdge(edge1);
		
		assert eg.addEdge(edge1);
		assert eg.addEdge(edge2);
		assert eg.addEdge(edge3);

		assert eg.removeEdge(edge3);

		assert eg.hasNode("c");	
		assert eg.hasNode("a");	
	}
	public static void eg_outEdgesNonEmpty() {
		Graph g = new ListGraph();
		EdgeGraph eg = new EdgeGraphAdapter(g);
		Edge edge1 = new Edge("a", "b");
		Edge edge2 = new Edge("c", "d");
		Edge edge3 = new Edge("c", "a");

		assert eg.addEdge(edge1);
		assert eg.addEdge(edge2);
		assert eg.addEdge(edge3);
		List <Edge> outEdges = eg.outEdges("a");

		assert (outEdges.size() == 1);
		// for (Edge outEdge : outEdges) { 
		// 	System.out.print(outEdge.getDst() + " ");
		// }
		// System.out.println();

	}

	public static void eg_inEdgesNonEmpty() {
		Graph g = new ListGraph();
		EdgeGraph eg = new EdgeGraphAdapter(g);
		Edge edge1 = new Edge("a", "b");
		Edge edge2 = new Edge("b", "a");
		Edge edge3 = new Edge("c", "a");

		assert eg.addEdge(edge1);
		assert eg.addEdge(edge2);
		assert eg.addEdge(edge3);
		List <Edge> inEdges = eg.inEdges("a");
		assert (inEdges.size() == 2);
		// for (Edge inEdge : inEdges) { 
		// 	System.out.print(inEdges.getSrc() + " ");
		// }
		// System.out.println();
	}

	public static void eg_edgesNonEmpty() {
		Graph g = new ListGraph();
		EdgeGraph eg = new EdgeGraphAdapter(g);
		Edge edge1 = new Edge("a", "b");
		Edge edge2 = new Edge("b", "a");
		Edge edge3 = new Edge("c", "a");
		Edge edge4 = new Edge("a", "a");


		assert eg.addEdge(edge1);
		assert eg.addEdge(edge2);
		assert eg.addEdge(edge3);
		assert eg.addEdge(edge4);

		List <Edge> allEdges = eg.edges();
		assert (allEdges.size() == 4);
		// for (Edge edge : allEdges) { 
		// 	System.out.println("SRC: " + edge.getSrc() + " DST: " + edge.getDst());
		// }
		// System.out.println();
	}

	public static void eg_unionNonEmpty() {
		Graph g = new ListGraph();

		EdgeGraph eg = new EdgeGraphAdapter(g);
		Edge edge1 = new Edge("a", "b");
		Edge edge2 = new Edge("b", "a");
		Edge edge3 = new Edge("c", "a");
		Edge edge4 = new Edge("a", "a");

		assert eg.addEdge(edge1);
		assert eg.addEdge(edge2);
		assert eg.addEdge(edge3);
		assert eg.addEdge(edge4);

		Graph g2 = new ListGraph();

		EdgeGraph eg2 = new EdgeGraphAdapter(g2);
		Edge edge5 = new Edge("a", "b");		//same Edge
		Edge edge6 = new Edge("b", "a");		//same Edge
		Edge edge7 = new Edge("c", "d");		//new Edge to new Node
		Edge edge8 = new Edge("d", "d");		//New Edge

		assert eg2.addEdge(edge5);
		assert eg2.addEdge(edge6);
		assert eg2.addEdge(edge7);
		assert eg2.addEdge(edge8);

		EdgeGraph unioned = eg.union(eg2);
		
		List <Edge> allEdges = unioned.edges();
		assert (allEdges.size() == 6);
		// for (Edge edge : allEdges) { 
		// 	System.out.println("SRC: " + edge.getSrc() + " DST: " + edge.getDst());
		// }
		// System.out.println();

	}

    public static void main(String[] args) {
		test1();
		addEdgethrow();
		removeNodeBoth();
		removeEdgeBoth();
		listNodes();
		listNodesEmpty();
		succNonEmpty();
		succThrow();
		succEmpty();
		predNonEmpty();
		predThrow();
		predEmpty();
		unionNonEmpty();
		unionOverlap();
		subGraphNonEmpty();
		subGraphEmpty();
		connectedNonEmpty();
		connected2();
		connectedTest();
		e_addEdge();
		e_hasNode();
		e_hasEdge();
		eg_removeEdge();
		eg_removeEdgeOther();
		eg_outEdgesNonEmpty();
		eg_inEdgesNonEmpty();
		eg_edgesNonEmpty();
		eg_unionNonEmpty();
    }

}