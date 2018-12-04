/**
 * Class that implements DisjointSet data structure.
 *
 * @author Jacob Moul A13548393
 * @since 12/04/2018
 */
public class DisjointSet {

    /**
     * Constructor.
     */
    public DisjointSet() {}

    /**
     * Finds the root of a given vertex
     * @param v vertex being "found"
     * @return the root of v
     */
    public Vertex find(Vertex v) {
        
        while (v != v.getSentinel()) {
            v = v.getSentinel().getSentinel();
        }

        return v;
    }

    /**
     * Merges two sets (may be the same set)
     * @param v1 vertex from first set
     * @param v2 vertex from second set
     */
    public void union(Vertex v1, Vertex v2) {
        
        Vertex root1 = find(v1);
        Vertex root2 = find(v2);
        if (root1.getSize() <= root2.getSize()) {
            root1.setSentinel(root2);
            root2.increaseSize(root1.getSize());
        } else {
            root2.setSentinel(root1);
            root1.increaseSize(root2.getSize());
        }
        
    }
}
