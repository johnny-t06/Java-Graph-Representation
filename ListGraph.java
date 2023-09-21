import java.util.*;

public class ListGraph implements Graph {
    
    private HashMap<String, LinkedList<String>> nodes = new HashMap<>();
    
    public void print() {
        for (String key : nodes.keySet()) { // iterate over key of HashMap
            System.out.print(key + ":"); 
            for (String value : nodes.get(key)) { // iterate through elements of nodes.get("a")
                System.out.print(value + " "); 
            }
		    System.out.println();

        }
		System.out.println();
    }
    public boolean addNode(String n) {

        if (hasNode(n)) {
            return false;
        }
        nodes.put(n, new LinkedList<String>());
        return true;


    }

    public boolean addEdge(String n1, String n2) {
        if(!(hasNode(n1) && hasNode(n2))) {
	        throw new UnsupportedOperationException();
        }

        if (nodes.get(n1).contains(n2)) {
            return false;
        }

        nodes.get(n1).add(n2);
        return true;

    }

    public boolean hasNode(String n) {
        return nodes.containsKey(n);
    }
    


    public boolean hasEdge(String n1, String n2) {
        if (!(hasNode(n1) && hasNode(n2))) {
	        throw new UnsupportedOperationException();
        }
        return nodes.get(n1).contains(n2);
    }

    public boolean removeNode(String n) {
        if (!hasNode(n)) {
            return false;
        }
        
        for (String key : nodes.keySet()) { // iterate over key of HashMap
            for (String value : nodes.get(key)) { // iterate through elements of nodes.get("a")
                if (n == value) {
                    nodes.get(key).remove(value);
                }
            }
        }

        nodes.remove(n);
        return true;
    }

    public boolean removeEdge(String n1, String n2) {
        if(!(hasNode(n1) && hasNode(n2))){
	        throw new UnsupportedOperationException();
        }

        for (String value : nodes.get(n1)) {
            if (n2 == value) {
                nodes.get(n1).remove(value);
                return true;
            }
        }
        return false;
    }

    public List<String> nodes() {
        List<String> allNodes= new LinkedList<>();
        for (String key : nodes.keySet()) {
            allNodes.add(key);
        }
        return allNodes;

    }

    public List<String> succ(String n) {
	    if (!hasNode(n)) {
	        throw new UnsupportedOperationException();
        }
        List<String> allsucc = new LinkedList<>();
        for (String key : nodes.get(n)) {
            allsucc.add(key);
        }
        return allsucc;
    }

    public List<String> pred(String n) {
        if (!hasNode(n)) {
	        throw new UnsupportedOperationException();
        }
        List<String> allpred = new LinkedList<>();
        for (String key : nodes.keySet()) {
            for (String value : nodes.get(key)) {
                if (value == n) {
                    allpred.add(key);
                }
            }
        }
        return allpred;
    }

    public Graph union(Graph g) {
        Graph newgraph = new ListGraph();
        //add all nodes
        for (String key : nodes.keySet()) {         
            newgraph.addNode(key);
        }
        //add all edges between nodes
        for (String key : nodes.keySet()) {      
            for (String value : nodes.get(key)) {
                newgraph.addEdge(key, value);
            }
        }

        List <String> gNodes = g.nodes();

        //add all of g's nodes to newgraph
        for (String eachNode : gNodes) {       
            if (!newgraph.hasNode(eachNode)) {
                newgraph.addNode(eachNode);
            }
        }
        for (String eachNode : gNodes) {
            List <String> gSucc = g.succ(eachNode);
            for (String eachSucc : gSucc) {
                if (!newgraph.hasEdge(eachNode, eachSucc)) {
                    newgraph.addEdge(eachNode, eachSucc);
                }
            }
            
        }
        return newgraph;

    }

    public Graph subGraph(Set<String> nodes) {
        Graph newgraph = new ListGraph();

        for (String key : nodes) {
            if (this.nodes.containsKey(key)) {
                newgraph.addNode(key);
            }
        }

        for (String key : newgraph.nodes()) {
            for (String value : this.nodes.get(key)) {
                if (newgraph.hasNode(value)) {
                    newgraph.addEdge(key, value);
                }
            }
        }
        
        return newgraph;

	    // throw new UnsupportedOperationException();

    }

    public boolean connected(String n1, String n2) {
	    throw new UnsupportedOperationException();
    }
}
