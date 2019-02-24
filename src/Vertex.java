
/**
 * A C-style struct definition of a Vertex to be used with
 * the Graph class.
 * <p>
 * The distance field is designed to hold the length of the
 * shortest unweighted path from the source of the traversal
 * <p>
 * The predecessor field refers to the previous field on
 * the shortest path from the source (i.e. the vertex one edge
 * closer to the source).
 * 
 *  This Vertex class was adapted from 
 *  <a href="https://www.cs.duke.edu/courses/cps100e/fall10/class/11_Bacon/src/Vertex.java">Vertex.java</a> by 
 *  by Jeff Forbes and Jeff Martin
 */
public class Vertex implements Comparable<Vertex> {
	/**
	 * label for Vertex
	 */
	public String name;  

	public Vertex(String v)
	{
		name = v;
	}

	/**
	 * The name of the Vertex is assumed to be unique, so it
	 * is used as a HashCode
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode()
	{
		return name.hashCode();
	}
	
	public String toString()
	{ 
		return name;
	}
	/**
	 * Compare on the basis of distance from source first and 
	 * then lexicographically
	 */
	public int compareTo(Vertex other)
	{
		return name.compareTo(other.name);
	}
	
	@Override
	public boolean equals(Object o){
		Vertex other = (Vertex) o;
		return name.equals(other.name);
	}
}