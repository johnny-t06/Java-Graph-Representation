
import java.util.*;

public class EdgeGraphAdapter implements EdgeGraph {

	private Graph g;

	public EdgeGraphAdapter(Graph g) {
		this.g = g;
	}

	public boolean addEdge(Edge e) {
		String src = e.getSrc();
		String dst = e.getDst();

		
		if (!hasNode(src)) {
			g.addNode(src);
		}
		if (!hasNode(dst)) {
			g.addNode(dst);
		}
		if (g.hasEdge(src, dst)) {
			return false;
		}

		return g.addEdge(src, dst);
	}

	public boolean hasNode(String n) {
		for (String nodes : g.nodes()) {
			if(g.hasEdge(n, nodes) || g.hasEdge(nodes, n)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasEdge(Edge e) {
		String src = e.getSrc();
		String dst = e.getDst();
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

		for (String node : predNodes) {
			Edge newEdge = new Edge(node, n);
			toEdges.add(newEdge);
		}

		return toEdges;
	}	

	public List<Edge> edges() {
		throw new UnsupportedOperationException();
	}

	public EdgeGraph union(EdgeGraph g) {
		throw new UnsupportedOperationException();
	}

	public boolean hasPath(List<Edge> e) {
		throw new UnsupportedOperationException();
	}

}
