package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Classroom;
import model.Classroom.RoomBuilding;
import model.ClassroomBag;

public class ClassroomFactory {
	NameFactory nf;
	ClassroomBag cb;
	List<String> roomNumbers = new ArrayList<String>();

	public ClassroomFactory(ClassroomBag cb, NameFactory nf) {
		this.nf = nf;
		this.cb = cb;
		init();
	}

	public void init() {
		for (int o = 0; o < 3; o++) {
			ArrayList<Integer> list = new ArrayList<Integer>();

			for (int i = 1; i < 200; i++) {
				list.add(new Integer(i));
			}
			if (o == 0)
				for (int f : list) {
					roomNumbers.add("R" + String.format("%03d%n",f+100));
				}
			if (o == 1)
				for (int f : list) {
					roomNumbers.add("S" + String.format("%03d%n",f+100));
				}
			if (o == 2)
				for (int f : list) {
					roomNumbers.add("I" + String.format("%03d%n",f+100));
				}
		}
	}

	public Classroom emitClassroom() {
		Random rand = new Random();
		Classroom cs;
		int temp = rand.nextInt(roomNumbers.size());
		if (roomNumbers.get(temp).charAt(0) == 'R')
			cs = new Classroom(RoomBuilding.Riverhead, roomNumbers.get(temp),rand.nextBoolean(),rand.nextInt(30)+20);
		else if (roomNumbers.get(temp).charAt(0) == 'S')
			cs = new Classroom(RoomBuilding.Smithtown, roomNumbers.get(temp),rand.nextBoolean(),rand.nextInt(80)+20);
		else
			cs = new Classroom(RoomBuilding.Islip, roomNumbers.get(temp),rand.nextBoolean(),rand.nextInt(30)+20);
		roomNumbers.remove(temp);
		return cs;

	}

	public void importClassrooms(int amt) {
		for (int i = 0; i < amt; i++) {
			cb.insert(emitClassroom());
		}
	}

}
