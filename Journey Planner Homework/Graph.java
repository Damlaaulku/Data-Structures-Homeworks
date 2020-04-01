import java.util.ArrayList;

public class Graph {
	
	private ArrayList<Vertex> vertices;
	private ArrayList<Edge> edges;
	
	public Graph() {
		super();
		vertices=new ArrayList<Vertex>();
		edges=new ArrayList<Edge>();
	}
	
	public ArrayList<Vertex> getVertices() {
		return vertices;
	}
	public void setVertices(ArrayList<Vertex> vertices) {
		this.vertices = vertices;
	}
	public ArrayList<Edge> getEdges() {
		return edges;
	}
	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}
	
	public Vertex insertVertex(Stop s) {
		Vertex v = new Vertex(s);
		vertices.add(v);
		return v;
	}
	
	public Edge insertEdge(Vertex vertex1, Vertex vertex2, int distance, int direction) {
		Edge e = new Edge(vertex1, vertex2, distance,direction);
		edges.add(e);
		return e;
	}
	
//	public ArrayList<Edge> incidentEdges(Vertex v) {
//		ArrayList <Edge> list = new ArrayList<Edge>();
//		
//		for (int i = 0; i < edges.size(); i++) {
//			Edge edge=edges.get(i);
//			
//			if(edge.getStart()==v || edge.getFinish()==v) {
//				list.add(edge);
//			}
//		}
//		return list;
//	}
	
	public boolean areAdjacent(Vertex vertex1, Vertex vertex2) {
		
		for (int i = 0; i < edges.size(); i++) {
			Edge edge=edges.get(i);
			
			if(edge.getStart()==vertex1 && edge.getFinish()==vertex2 || edge.getStart()==vertex2 && edge.getFinish()==vertex1) {
				return true;
			}
		}
		return false;
	}
	
	public Vertex getVertexByStopId(int id) {
		
		for (int i = 0; i < vertices.size(); i++) {
			
			Vertex find = vertices.get(i);
			
			if(find.getStop().getStopId()==id) {
				return find;
			}		
		}
		return null;
	}
	
	public Edge getEdge(Vertex vertex1, Vertex vertex2) {
		
		for (int i = 0; i < edges.size(); i++) {
			Edge edge=edges.get(i);
			
			if(edge.getStart()==vertex1 && edge.getFinish()==vertex2 || edge.getStart()==vertex2 && edge.getFinish()==vertex1) {
				return edge;
			}
		}
		return null;
	}
	
}
