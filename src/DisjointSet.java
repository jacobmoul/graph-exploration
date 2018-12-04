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
