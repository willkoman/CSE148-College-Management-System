package utils;

import java.io.File;
import java.io.IOException;

import model.ClassroomBag;
import model.CourseBag;
import model.PersonBag;
import model.TextbookBag;

public class Setup {

	File tmpDir = new File("bin/output_data/bags.dat");
	NameFactory nf;
	ClassroomBag crb;
	ClassroomFactory crf;
	TextbookBag tb;
	TextbookFactory tf;
	CourseBag cb;
	PersonBag pb;
	PersonFactory pf;

	public void init() throws Exception {
		if (!tmpDir.exists()) {
			nf = new NameFactory();
			crb = new ClassroomBag(400);
			crf = new ClassroomFactory(crb, nf);
			tb = new TextbookBag(1100);
			tf = new TextbookFactory(tb, nf);
			cb = new CourseBag(1100);

			pb = new PersonBag(10000);
			pf = new PersonFactory(pb, nf, cb);

			nf.init();
			// initializes the name factory so all names are generated

			tf.importTextbooks(1000);
			crf.importClassrooms(200);
			CourseFactory cf = new CourseFactory(cb, crb);
			cf.importCourses(1000);
			// prepares essential school objects

			pf.insertStudents(2000);
			pf.insertFaculty(500);
			// generates the students and faculties
		} else {
			Importer importer = new Importer();
			crb = importer.importClassroomBag();
			tb = importer.importTextbookBag();
			cb = importer.importCourseBag();
			pb = importer.importPersonBag();

		}
	}

	public ClassroomBag importClassroomBag() {
		return crb;
	}

	public CourseBag importCourseBag() {
		return cb;
	}

	public PersonBag importPersonBag() {
		return pb;
	}

	public TextbookBag importTextbookBag() {
		return tb;
	}
}
