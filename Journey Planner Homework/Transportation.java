import java.util.ArrayList;
import java.util.HashMap;

public class Transportation {
	
	public static ArrayList<Stop> stops = new ArrayList<>();
	public static ArrayList<Line> lines = new ArrayList<>();
	public static Graph transportation = new Graph();
	public static HashMap<String,Integer> distance= new HashMap<String,Integer>();
	
	
	public static Stop getStop(int Id) {

		for (int i = 0; i < stops.size(); i++) {

			if (stops.get(i).getStopId() == Id) {

				return stops.get(i);
			}
		}

		return null;
	}

	public static Line getLine(int Id) {

		for (int i = 0; i < lines.size(); i++) {

			if (lines.get(i).getLineId() == Id) {

				return lines.get(i);
			}
		}

		return null;
	}
	
	public static void createTransportation() {
		
		ArrayList<ArrayList<String>> line = FolderOperations.readLines(System.getProperty("user.dir") + "\\Line.txt");
		ArrayList<ArrayList<String>> trip = FolderOperations.readLines(System.getProperty("user.dir") + "\\Trip.txt");
		ArrayList<ArrayList<String>> distances = FolderOperations.readLines(System.getProperty("user.dir") + "\\Distance.txt");
		ArrayList<ArrayList<String>> stop = FolderOperations.readLines(System.getProperty("user.dir") + "\\Stop.txt");

		for (int i = 1; i < stop.size(); i++) {

			// Stop operations
			if (stop.size() > i) {
				Stop s = new Stop(Integer.parseInt(stop.get(i).get(0)), stop.get(i).get(1), stop.get(i).get(4),
						stop.get(i).get(5));
				stops.add(s);
				transportation.insertVertex(s);
			}

			// Line operations
			if (line.size() > i) {
				Line l = new Line(Integer.parseInt(line.get(i).get(0)), Integer.parseInt(line.get(i).get(1)),
						line.get(i).get(2), line.get(i).get(3));
				lines.add(l);
			}

		}

		for (int i = 1; i < distances.size() - 1; i++) {

			// Distance operations
			
			String OriginId = distances.get(i).get(0);
			String DestinationId = distances.get(i).get(1);
			int Distance = Integer.parseInt(distances.get(i).get(2));
			if(OriginId!=null && DestinationId!=null) {
			distance.put(OriginId+DestinationId, Distance);	
			}
		
		}

		for (int i = 1; i < trip.size() - 1; i++) {
				int LineId1 = Integer.parseInt(trip.get(i).get(0));
				int LineId2 = Integer.parseInt(trip.get(i + 1).get(0));
				int StopId1 = Integer.parseInt(trip.get(i).get(3));
				int StopId2 = Integer.parseInt(trip.get(i + 1).get(3));
				int direction =Integer.parseInt(trip.get(i).get(1));
				if (LineId1 == LineId2) {
					Vertex v1 = transportation.getVertexByStopId(StopId1);
					Vertex v2 = transportation.getVertexByStopId(StopId2);
					
					if(distance.get(StopId1+""+StopId2)!=null) {
						int d=distance.get(StopId1+""+StopId2);
						Edge e=transportation.insertEdge(v1, v2,d,direction);					
						e.getLine().add(getLine(LineId1));
					}
		
				}
		}

		for (int i = 0; i < stops.size(); i++) {
			Stop s = stops.get(i);

			if (s.getNeighborStop() == null)
				continue;
			String[] neighborstops = s.getNeighborStop().split("\\.");

			for (int j = 0; j < neighborstops.length; j++) {
				String[] a = neighborstops[j].trim().split(":");
				try {
					int id = Integer.parseInt(a[0]);
					int distance = Integer.parseInt(a[1]);
					Vertex v1=transportation.getVertexByStopId(s.getStopId());
					Vertex v2=transportation.getVertexByStopId(id);
					transportation.insertEdge(v1, v2, distance,0);
					transportation.insertEdge(v1, v2, distance,1);
				} catch (Exception e) {
					continue;
				}
			}

		}
	}
}
