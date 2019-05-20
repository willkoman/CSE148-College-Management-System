package model;

public class College {

	public ClassroomBag crb;
	public CourseBag cb;
	public PersonBag pb;
	public TextbookBag tb;

	public College(ClassroomBag crb, CourseBag cb, PersonBag pb, TextbookBag tb) {
		this.cb = cb;
		this.crb = crb;
		this.pb = pb;
		this.tb = tb;
	}

}
