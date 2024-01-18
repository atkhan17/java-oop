import java.util.*;

public class SchoolManager {
	
	private MyList overtimeList;
	
	private MySet seminarRegistrationList;
	
	public SchoolManager() {
		overtimeList = new MyList();
		seminarRegistrationList = new MySet();
	}

	public void addToOvertimeList(Employee e) {
		overtimeList.add(e);
	}
	
	public void addToSeminarRegistrationList(Employee e) {
		seminarRegistrationList.add(e);
	}
	
	public int getOvertimeEmployeeCount() {
		return overtimeList.getSize();
	}
	
	public int getSeminarRegistrationsCount() {
		return seminarRegistrationList.getSize();
	}
	
	public String displayOvertimeList() {
		if (overtimeList.getSize() == 0) 
			return "Overtime List: No employees in the overtime list.";
		else 
			return "Overtime List: " + overtimeList.toString();
	}
	
	public String displaySeminarRegistrationList() {
		if (seminarRegistrationList.getSize() == 0)
			return "Registration List: No employees in the registration list.";
		else 
			return "Registration List: " + seminarRegistrationList.toString();
	}

	

}

/**
 * 
 * This class represents an employee in the school management system.
 * */
class Employee {

	private String name;
	private int age;
	private String employeeId;
	
	public Employee(String name, int age, String employeeId) {
		this.name = name;
		this.age = age;
		this.employeeId = employeeId;
	}
	
	public String getName() {
		return this.name;
	}
 	
	public int getAge() {
		return this.age;
	}
	
	public String getEmployeeId() {
		return this.employeeId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	@Override
	public String toString() {
		return "{" + this.name + " (ID: " + this.employeeId + ")}";
	}
	
	public int hashCode() {
		return this.age;
	}
	
	public boolean equals(Object obj) {
		if (obj == null) return false;
		
		if (this == obj) return true;
		
		if (obj instanceof Employee) {
			Employee e = (Employee) obj;
			if (this.name == e.name && this.age == e.age && this.employeeId == e.employeeId) return true;
		}
		
		return false;
	}
	
	
	

}

/**
 * 
 * This class represents a container that stores objects in an array-like
 * structure. */
class Container {
	/**
	 * The initial size of the container.
	 */
	protected final int INITIAL_SIZE = 10;

	/**
	 * The array that holds the objects.
	 */
	protected Object[] list;

	/**
	 * The number of elements currently stored in the container.
	 */
	protected int size;
	
	public void add(Object obj) {
		if (size >= list.length) {
			Object temp[] = new Object[list.length + 5];
			for (int i = 0; i < list.length; i++) {
				temp[i] = list[i];
			}
			list = temp;
		}
		list[size] = obj;
		size++;
	}
	
	public Object remove(Object obj) {
		boolean found = false;
		for (int i = 0; i < size; i++) {
			if (list[i].equals(obj) && !found) found = true;
			else if (found) {
				list[i-1] = list[i]; 
			}
		}
		if (found) {
			size--;
			return obj;
		} else return null;
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean isEmpty() {
		if (size == 0) return true;
		else return false;
	}
	
	public boolean contains(Object obj) {
		for (int i = 0; i < size; i++) {
			if (list[i].equals(obj)) return true;
		}
		return false;
	}
	
	public int countOccurrences(Object obj) {
		int count = 0;
		for (int i = 0; i < size; i++) {
			if (list[i].equals(obj)) count++;
		}
		return count;
	}
	
	@Override
	public String toString() {
		String temp = "[" + list[0];
		for (int i = 1; i < size; i++) {
			temp += ", " + list[i];
		}
		temp += "]";
		return temp;
	}
}

/**
 * This class simulates an ArrayList concept, where you can add unlimited number
 * of elements to the list.
 */
class MyList extends Container {
	
	public MyList() {
		list = new Object[INITIAL_SIZE];
		size = 0;
	}
	
	public void add(Object obj) {
		super.add(obj);
	}
	
	public Object remove(Object obj) {
		return super.remove(obj);
	}
	
	public Object get(int i) {
		return list[i];
	}
}

/**
 * This class simulates a custom set that stores unique elements.
 */
class MySet extends Container {
	
	public MySet() {
		this.list = new Object[INITIAL_SIZE];
		size = 0;
	}
	
	public void add(Object obj) {
		if (!this.contains(obj)) {
			super.add(obj);
		}
	}
	
	public Object remove(Object obj) {
		return super.remove(obj);
	}
}