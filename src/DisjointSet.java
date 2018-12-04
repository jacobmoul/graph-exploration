/**
 * Class that implements DisjointSet data structure.
 *
 * @author Jacob Moul A13548393
 * @since 12/04/2018
 */
public class DisjointSet {

    public DisjointSet() {}

    public Vertex find(Vertex v) {
        
        // TODO
        while (v != v.getSentinel()) {
            v = v.getSentinel().getSentinel();
        }

        return v;
    }

    public void union(Vertex v1, Vertex v2) {
        
        // TODO
        if (v1.getSize() <= v2.getSize()) {
            v1.setSentinel(v2);
            v2.increaseSize(v1.getSize());
        } else {
            v2.setSentinel(v1);
            v1.increaseSize(v2.getSize());
        }
        
    }
}
