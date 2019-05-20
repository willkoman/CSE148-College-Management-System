package model;

import java.io.Serializable;
import java.util.Random;

public class Classroom implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum RoomBuilding {
		Riverhead("R"), Islip("I"), Smithtown("S");
		public static RoomBuilding getRandomBuilding() {
			Random random = new Random();
			return values()[random.nextInt(values().length)];
		}
		String name;
		
		RoomBuilding(String prefix)
		{
			this.name=prefix;
		}
		
		public String getPrefix() {
			return name;
		}
	}

	String roomNumber;// First Letter of building followed by 3 digits
	Random rand = new Random();

	public RoomBuilding roomBuilding;
	boolean projector;
	int capacity;

	public Classroom(RoomBuilding roomBuilding, String roomNumber, boolean projector, int capacity) {
		this.roomBuilding = roomBuilding;
		this.roomNumber = roomNumber;
		this.projector = projector;
		this.capacity = capacity;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public void setRoomBuilding(RoomBuilding roomBuilding) {
		this.roomBuilding = roomBuilding;
	}

	public boolean isProjector() {
		return projector;
	}

	public void setProjector(boolean projector) {
		this.projector = projector;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public RoomBuilding getRoomBuilding() {
		return roomBuilding;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

}
