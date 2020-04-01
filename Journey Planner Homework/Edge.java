import java.util.ArrayList;

public class Edge {
	
	private Vertex start;
	private Vertex finish;
	private ArrayList<Line> line= new ArrayList<>();
	private int distance;
	private int direction;
	
	public Edge(Vertex start, Vertex finish, int distance, int direction) {
		super();
		this.start = start;
		this.finish = finish;
		this.distance = distance;
		this.direction = direction;
	}
	public Vertex getStart() {
		return start;
	}
	public void setStart(Vertex start) {
		this.start = start;
	}
	public Vertex getFinish() {
		return finish;
	}
	public void setFinish(Vertex finish) {
		this.finish = finish;
	}
	
	public ArrayList<Line> getLine() {
		return line;
	}
	public void setLine(ArrayList<Line> line) {
		this.line = line;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	
}

