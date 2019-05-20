/*
 * package model;
 * 
 * import java.io.File;
 * 
 * import utils.ClassroomFactory; import utils.CourseFactory; import
 * utils.Exporter; import utils.Importer; import utils.NameFactory; import
 * utils.PersonFactory; import utils.TextbookFactory;
 * 
 * public class Demo {
 * 
 * public static void main(String[] args) throws Exception { File tmpDir = new
 * File("bin/output_data/bags.dat"); NameFactory nf; ClassroomBag crb;
 * ClassroomFactory crf; TextbookBag tb; TextbookFactory tf; CourseBag cb;
 * PersonBag pb; PersonFactory pf;
 * 
 * if (!tmpDir.exists()) { nf = new NameFactory(); crb = new ClassroomBag(100);
 * crf = new ClassroomFactory(crb, nf); tb = new TextbookBag(1100); tf = new
 * TextbookFactory(tb, nf); cb = new CourseBag(1100);
 * 
 * pb = new PersonBag(5000); pf = new PersonFactory(pb, nf, cb);
 * 
 * nf.init(); // initializes the name factory so all names are generated
 * 
 * tf.importTextbooks(1000); crf.importClassrooms(50); CourseFactory cf = new
 * CourseFactory(cb, crb); cf.importCourses(1000); // prepares essential school
 * objects
 * 
 * pf.insertStudents(2000); pf.insertFaculty(500); // generates the students and
 * faculties } else { Importer importer = new Importer(); crb =
 * importer.importClassroomBag(); tb = importer.importTextbookBag(); cb =
 * importer.importCourseBag(); pb = importer.importPersonBag();
 * 
 * }
 * 
 * System.out.println(tb.emitTextbook().toString());
 * 
 * for (int i = 0; i <= 3; i++) { if (crb.classroomArray[i] != null)
 * System.out.print(crb.classroomArray[i].roomNumber); }
 * 
 * Person bb = pb.findByID("00000691"); if
 * (bb.getClass().isAssignableFrom(Student.class)) { Student s = (Student) bb;
 * System.out.println(s.toString()); s.miniStudentCourseBag.display(); } else if
 * (bb.getClass().isAssignableFrom(Faculty.class)) { Faculty s = (Faculty) bb;
 * System.out.println(s.toString()); s.miniFacultyCourseBag.display(); } else
 * System.out.println("Person Does Not Exist in Registry");
 * 
 * Exporter export = new Exporter(crb, cb, pb, tb); export.write();
 * 
 * } }
 */