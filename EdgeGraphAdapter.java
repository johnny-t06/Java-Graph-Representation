/*
 * Johnny Tan
 * Edited: 02/16/2024
 * Purpose: Implements EdgeGraph interface with polymophmism emphasis on 
 * 			Graph and EdgeGraph
 */


import java.util.*;


public class EdgeGraphAdapter implements EdgeGraph {

	private Graph g;

	public EdgeGraphAdapter(Graph g) {
		this.g = g;
	}

	public boolean addEdge(Edge e) {
		String src = e.getSrc();
		String dst = e.getDst();

		
		if (!g.hasNode(src)) {
			g.addNode(src);
		}
		if (!g.hasNode(dst)) {
			g.addNode(dst);
		}
		if (g.hasEdge(src, dst)) {
			return false;
		}

		return g.addEdge(src, dst);
	}

	public boolean hasNode(String n) {
		if(!g.hasNode(n)) {
			return false;
		}


		for (String nodes : g.nodes()) {
			if (g.hasEdge(n, nodes) || g.hasEdge(nodes, n)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasEdge(Edge e) {
		String src = e.getSrc();
		String dst = e.getDst();

		if (!(g.hasNode(src) && g.hasNode(dst))) {
			return false;
		}
		return(g.hasEdge(src, dst));
	}

	public boolean removeEdge(Edge e) {
		if (!hasEdge(e)) {
			return false;
		}

		String src = e.getSrc();
		String dst = e.getDst();
		g.removeEdge(src, dst);
		
		List<String> srcSucc = g.succ(src);
		List<String> srcPred = g.pred(src);
		
		List<String> dstSucc = g.succ(dst);
		List<String> dstPred = g.pred(dst);
		
		
		if (srcSucc.size() == 0 && srcPred.size() == 0) {
			g.removeNode(src);
		}
		if (dstSucc.size() == 0 && dstPred.size() == 0) {
			g.removeNode(dst);
		}

		return true;
	}

	public List<Edge> outEdges(String n) {
		List<Edge> toEdges = new LinkedList<>(); 
		if (!g.hasNode(n)) {
			throw new UnsupportedOperationException();
		}

		List<String> succNodes = g.succ(n);
		
		
		for (String node : succNodes) {
			Edge newEdge = new Edge(n , node);
			toEdges.add(newEdge);
		}

		return toEdges;
	}

	public List<Edge> inEdges(String n) {
		List<Edge> toEdges = new LinkedList<>(); 
		List<String> predNodes = g.pred(n); 

		if (!g.hasNode(n)) {
			throw new UnsupportedOperationException();
		}
		
		for (String node : predNodes) {
			Edge newEdge = new Edge(node, n);
			toEdges.add(newEdge);
		}

		return toEdges;
	}	

	public List<Edge> edges() {
		List <String> allNodes = g.nodes();
		List <Edge> allEdges = new LinkedList<>();

		for (String eachNode : allNodes) {
			List <String> eachSuccList = g.succ(eachNode);
			for (String eachSucc : eachSuccList) {
				Edge newEdge = new Edge(eachNode, eachSucc); 
				allEdges.add(newEdge);
			}
		}

		return allEdges;
	}

	public EdgeGraph union(EdgeGraph g) {
		Graph newGraph = new ListGraph();
		EdgeGraph newEdgeGraph = new EdgeGraphAdapter(newGraph);
		List <Edge> thisEdges = edges();
		List <Edge> otherEdges = g.edges();

		//add eachedge from this EdgeGraph
		for (Edge eachEdge : thisEdges) {
			newEdgeGraph.addEdge(eachEdge);
		}

		//add eachEdge from other EdgeGraph 
		for (Edge eachEdge : otherEdges) {
			newEdgeGraph.addEdge(eachEdge);
		}
		
		return newEdgeGraph;
	}

	public boolean hasPath(List<Edge> l) {
		if (l.size() == 0) {
			return false;
		}
		String DSTcheck = l.get(0).getSrc();
		for (Edge eachEdge : l) {
			if (!hasEdge(eachEdge)) {
				return false;
			}
			if (!DSTcheck.equals(eachEdge.getSrc())) {
				throw new BadPath();
			}
			DSTcheck = eachEdge.getDst();
		}
		return true;
	}

}
