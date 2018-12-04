/*
 * NAME: Jacob Moul
 * PID: A13548393
 */

import java.util.*;

/**
 * Class that creates a vertex-edge graph.
 *
 * @author Jacob Moul A13548393
 * @since 12/04/2018
 */
public class Graph {

    private HashMap<String, Vertex> vertices;
    private ArrayList<Edge> allUndirectedEdges;
    private ArrayList<Edge> resultMST;
    private boolean edgesGiven;

    /**
     * Constructor for Graph
     */
    public Graph(boolean edgesGiven) {
        
        this.vertices = new HashMap<>();
        this.allUndirectedEdges = new ArrayList<>();
        this.edgesGiven = edgesGiven;

    }

    /**
     * Adds a vertex to the graph. Throws IllegalArgumentException if given vertex
     * already exist in the graph.
     *
     * @param v vertex to be added to the graph
     * @throws IllegalArgumentException if two vertices with the same name are added.
     */
    public void addVertex(Vertex v) throws IllegalArgumentException {
        
        String key = v.getName();

        if (vertices.containsKey(key)) {
            throw new IllegalArgumentException();
        } else {
            vertices.put(key, v);
        }

    }

    /**
     * Gets a collection of all the vertices in the graph
     *
     * @return collection of all the vertices in the graph
     */
    public Collection<Vertex> getVertices() {

        return vertices.values();
    }

    /**
     * Adds a directed edge from vertex u to vertex v, Throws IllegalArgumentException if one of
     * the vertex does not exist. If edgesGiven is false, directly return at first.
     *
     * @param nameU name of vertex u
     * @param nameV name of vertex v
     * @param weight weight of the edge between vertex u and v
     * @throws IllegalArgumentException if one of the vertex does not exist
     */
    public void addEdge(String nameU, String nameV, Double weight) throws IllegalArgumentException {
        
        if (edgesGiven) {
            Vertex u = vertices.get(nameU);
            Vertex v = vertices.get(nameV);

            if (u == null || v == null) {
                throw new IllegalArgumentException();
            }

            Edge e = new Edge(u, v, weight);
            u.addEdge(e);
        } else {
            System.out.println("Graph fully connected; cannot add another edge.");
        }
    }

    /**
     * Adds an undirected edge between vertex u and vertex v by adding a directed
     * edge from u to v, then a directed edge from v to u. Then updates the allUndirectedEdges.
     * If edgesGiven is false, directly return at first.
     *
     * @param nameU name of vertex u
     * @param nameV name of vertex v
     * @param weight  weight of the edge between vertex u and v
     */
    public void addUndirectedEdge(String nameU, String nameV, double weight) {
        
        if (edgesGiven) {
            addEdge(nameU, nameV, weight);
            addEdge(nameV, nameU, weight);

            // add undirected edge to allUndirectedEdges
            Vertex u = vertices.get(nameU);
            Vertex v = vertices.get(nameV);
            Edge e = new Edge(u, v, weight);
            allUndirectedEdges.add(e);
        } else {
            System.out.println("Graph fully connected; cannot add another undirected edge.");
        }

    }

    /**
     * Calculates the euclidean distance for all edges in the graph and all edges in 
     * allUndirectedEdges. If edgesGiven is false, directly return at first.
     */
    public void computeAllEuclideanDistances() {

        if (edgesGiven) {
            int pow = 2;

            // compute distance of all adjacent edges of each vertex
            for (Vertex v : vertices.values()) {
                for (Edge e : v.adjacentEdges) {
                    Vertex s = e.getSource();
                    Vertex t = e.getTarget();

                    Double dist = s.getDistanceTo(t);

                    e.setDistance(dist);
                }
            }

            // compute distance of all undirected edges
            for (Edge e : allUndirectedEdges) {
                Vertex s = e.getSource();
                Vertex t = e.getTarget();

                Double dist = s.getDistanceTo(t);

                e.setDistance(dist);
            }
        } else {
            System.out.println("Euclidean distances already defined, cannot recompute.");
        }

    }

    /**
     * Populate all possible edges from all vertices in the graph. Only works when edgesGiven 
     * is false. If edgesGiven is true, directly return at first.
     */
    public void populateAllEdges() {
        int pow = 2;
        
        if (!edgesGiven) {
            Collection<Vertex> vCollection = getVertices();
            Vertex[] vertices = vCollection.toArray(new Vertex[vCollection.size()]);

            for (int i = 0; i < vertices.length; i++) {
                for (int j = 1; i + j < vertices.length; j++) {
                    // create undirected edge and compute its Euclidean distance, add to list
                    Vertex s = vertices[i];
                    Vertex t = vertices[i+j];
                    Double dist = s.getDistanceTo(t);
                    Edge e = new Edge(s, t, dist);
                    allUndirectedEdges.add(e);
                }
            }

        } else {
            System.out.println("Edges have been defined by user.");
        }
        
    }

    /**
     * Algorithm to find the Minimum Spanning Tree of this graph. Utilizes DisjointSet.java
     * @return the MST
     */
    public ArrayList<Edge> runKruskalsAlg() {
        // if resultMST is already computed, return the resultMST at first
        
        if (resultMST != null) {
            return resultMST;
        } else {
            if (!edgesGiven) {
                populateAllEdges();
            }
            resultMST = new ArrayList<>();
            DisjointSet ds = new DisjointSet();
            Collections.sort(allUndirectedEdges, Comparator.comparingDouble(e -> e.getDistance()));

            Iterator<Edge> iter = allUndirectedEdges.iterator();

            while (resultMST.size() < getVertices().size() - 1 && iter.hasNext()) {
                 Edge e = iter.next();
                 Vertex u = e.getSource();
                 Vertex v = e.getTarget();

                 if (ds.find(u) != ds.find(v)) {
                     ds.union(u, v);
                     resultMST.add(e);
                 }
            }

            return resultMST;
        }

    }
}
