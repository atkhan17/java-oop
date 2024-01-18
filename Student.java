import java.util.*;

/**
 * An abstract class representing a student
 * that extends Comparable and includes methods and fields 
 * related to student information and courses. 
 * It also implements the Comparable interface to 
 * enable comparison between Student objects based on their IDs.
 * */
public abstract class Student implements Comparable {

	protected ArrayList<Course> courses;
	protected int id;
	protected String name;
	
	public abstract void addCourse(Course course) throws Exception;
	
	public abstract void dropCourse(Course course);
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Course> getCourses() {
		ArrayList<Course> c = new ArrayList<Course>();
		c.addAll(courses);
		Collections.sort(c);
		for (Course a : c) {
			System.out.println(a.name + ", ");
		}
		return courses;
	}
	
	@Override
	public int compareTo(Object object) {
		Student st = (Student) object;
		if (this.id == st.id) return 0;
		else if (this.id > st.id) return 1;
		else return -1;
	}	
}


/**
 * A class representing a registered student.
 * This class extends the abstract class Student and provides methods and properties
 * specific to registered students and their course registrations.
 */
class RegisteredStudent extends Student {

	public RegisteredStudent(int id, String name) {
		this.id = id;
		this.name = name;
		courses = new ArrayList<Course>();
	}
	
	@Override
	public void addCourse(Course course) throws Exception {
		if (!course.offered) throw new RegistrationException();
		else if (course.getPrerequisite() != null && !courses.contains(course.getPrerequisite())) throw new PrerequisiteException();
		else courses.add(course);
	}

	@Override
	public void dropCourse(Course course) {
		courses.remove(course);
	}
	
	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null)
			return false;
		if (getClass() != object.getClass())
			return false;
		RegisteredStudent other = (RegisteredStudent) object;
		return Objects.equals(name, other.name) && id == other.id && Objects.equals(courses, other.courses);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name, this.courses);
	}
}



/**
 * A class representing a course.
 * This class contains information about a course, including its name, prerequisite,
 * and whether it is currently offered.
 */
class Course implements Comparable{

	protected String name;
	protected Course prerequisite;
	protected boolean offered;
	
	public Course(String name, boolean offered) {
		this.name = name;
		this.offered = offered;
	}
	
	public Course(String name, Course prerequisite,  boolean offered) {
		this(name, offered);
		this.prerequisite = prerequisite;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Course getPrerequisite() {
		return this.prerequisite;
	}
	
	public void setPrerequisite(Course prerequisite) {
		this.prerequisite = prerequisite;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, offered, prerequisite);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(name, other.name) && offered == other.offered
				&& Objects.equals(prerequisite, other.prerequisite);
	}

	@Override
	public int compareTo(Object o) {
		Course c = (Course) o;
		return this.name.compareTo(c.name);
	}
}




/**
 * A custom exception class representing an exception related to course registration.
 * This exception can be used to handle registration-related errors.
 */
class RegistrationException extends Exception {

	public RegistrationException(String message) {
		super(message);
	}

	public RegistrationException() {
	}
	
}

/**
 * A custom exception class representing an exception related to course prerequisites.
 * This exception can be used to handle errors related to course prerequisites.
 */
class PrerequisiteException extends RegistrationException {

	public PrerequisiteException(String message) {
		super(message);
	}
	
	public PrerequisiteException() {
	}
}