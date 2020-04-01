
public class Stop {
	private int StopId;
	private String Name;
	private String VehicleTypeId;
	private String NeighborStop;
	
	public Stop(int stopId, String name, String vehicleTypeId, String neighborStop) {
		super();
		StopId = stopId;
		Name = name;
		VehicleTypeId = vehicleTypeId;
		NeighborStop = neighborStop;
	}


	public int getStopId() {
		return StopId;
	}

	public void setStopId(int stopId) {
		StopId = stopId;
	}




	public String getName() {
		return Name;
	}




	public void setName(String name) {
		Name = name;
	}

	public String getVehicleTypeId() {
		return VehicleTypeId;
	}




	public void setVehicleTypeId(String vehicleTypeId) {
		VehicleTypeId = vehicleTypeId;
	}




	public String getNeighborStop() {
		return NeighborStop;
	}




	public void setNeighborStop(String neighborStop) {
		NeighborStop = neighborStop;
	}
}
