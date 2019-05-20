package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ClassroomBag implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Classroom> classroomArray;
	int nElems;
	private Random rand = new Random();

	public ClassroomBag(int maxSize) {
		classroomArray = new ArrayList<Classroom>();
		nElems = classroomArray.size();
	}

	public void insert(Classroom classroom) {
		classroomArray.add(classroom);
	}

	public Classroom getByRoomNumber(String roomNumber) {
		for(Classroom c : classroomArray)
			if(c.getRoomNumber().equals(roomNumber))
				return c;
		return null;
	}
	
	public void deleteByRoomNumber(String roomNumber) {
		for (Iterator<Classroom> iterator = classroomArray.iterator(); iterator.hasNext(); ) {
			Classroom g = iterator.next();
			if (g.getRoomNumber().equals(roomNumber)) {
				iterator.remove();
			}
		}
	}
	public Classroom emitClassroom() {
		return classroomArray.get(rand.nextInt(classroomArray.size()));
	}

}
