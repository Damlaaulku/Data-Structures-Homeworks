
public class Line {
	private int LineId;
	private int LineNo;
	private String Name;
	private String VehicleTypeId;
	

	public Line(int lineId, int lineNo, String name, String vehicleTypeId) {
		LineId = lineId;
		LineNo = lineNo;
		Name = name;
		VehicleTypeId = vehicleTypeId;
	}


	public int getLineId() {
		return LineId;
	}


	public void setLineId(int lineId) {
		LineId = lineId;
	}


	public int getLineNo() {
		return LineNo;
	}


	public void setLineNo(int lineNo) {
		LineNo = lineNo;
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
	
	
}
