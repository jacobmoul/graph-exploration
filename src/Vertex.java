import java.util.ArrayList;

/**
 * Vertex class that is used in Graph.java
 *
 * @author Jacob Moul A13548393
 * @since 12/04/2018
 */
public class Vertex {

    private String name; // the name of this vertex
    private int x; // the x coordinates of this vertex on map
    private int y; // the y coordinates of this vertex on map


    public ArrayList<Edge> adjacentEdges; // the adjacent edges of this vertex

    private Vertex sentinel; // representative vertex used for Disjoint Set
    private int size; // size of set used in disjoint set

    /**
     * Constructor
     * @param name name of vertex
     * @param x x coordinate of vertex
     * @param y y coordinate of vertex
     */
    public Vertex(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        adjacentEdges = new ArrayList<>();

        this.sentinel = this;
        this.size = 1;
    }

    /**
     * Returns name of this vertex
     * @return name of vertex
     */
    public String getName() {
        return name;
    }

    /**
     * Returns x-coordinate of this vertex
     * @return x-coordinate of vertex
     */
    public int getX() {
        return x;
    }

    /**
     * Returns y-coordinate of this vertex
     * @return y-coordinate of vertex
     */
    public int getY() {
        return y;
    }


    /**
     * Return size of set (for which this vertex is a "representative"); used for DisjointSet
     * @return size of set
     */
    public int getSize() { return size; }

    /**
     * Increase size of set for DisjointSet
     * @param amount amount to increase by
     */
    public void increaseSize(int amount) {
        this.size += amount;
    }

    /**
     * Set representative of this vertex in DisjointSet.java
     * @param v vertex that will be sentinel
     */
    public void setSentinel(Vertex v) {
        this.sentinel = v;
    }

    /**
     * Find "root" or "representative" in DisjointSet.java
     * @return representative of this vertex
     */
    public Vertex getSentinel() { return sentinel; }

    /**
     * Compute Euclidean distance from this vertex to another vertex
     * @param o other vertex
     * @return Euclidean distance between the two
     */
    public double getDistanceTo(Vertex o) {
        double squareDis = Math.pow(x - o.getX(), 2) + Math.pow(y - o.getY(), 2);
        return Math.sqrt(squareDis);
    }

    /**
     * Add edge to list of adjacent edges
     * @param edge edge to be added
     */
    public void addEdge(Edge edge) {
        adjacentEdges.add(edge);
    }

    /**
     * Returns string representation of a vertex
     * @return string representation of vertex
     */
    public String toString() {
        return name + " (" + x + ", " + y + ")";
    }

}