import java.io.*;
import java.util.*;

/**
 * The {@code Container} class is a generic container that can hold objects. It
 * provides methods for adding and removing objects, as well as querying the
 * size of the container.
 * */
public class Container {
	
	public void add(Object object) {
		
	}
	
	public Object remove() {
		return null;
	}
	
	public int getSize() {
		return 0;
	}
	
}

/**
 * The {@code Queue} class simulates a queue data structure, inserting and
 * removing data by the FIFO (first-in, first-out) rule. It stores orders with a
 * total amount above or equal to 1500 in the queue.
 *
 */
class Queue extends Container {
	
	private ArrayList<Order> queue;
	
	public Queue(String fileName) {
		queue = new ArrayList<Order>();
		List<String> orders = IOHandler.readFile(fileName);
		String[] cut = new String[5];
		int quantity;
		double price;
		for (int i = 0; i < orders.size(); i++) {
			cut = orders.get(i).split(", ");
			
			quantity = Integer.parseInt(cut[3]);
			price = Double.parseDouble(cut[4]);
			if (quantity*price >= 1500) {
				queue.add(new Order(Integer.parseInt(cut[0]), cut[1], cut[2], quantity, price));
			}
		}
	}
	
	@Override
	public void add(Object order) {
		if (order instanceof Order) {
			Order o = (Order) order;
			if (o.getTotalAmount() >= 1500) {
				queue.add(o);
			}
		}
	}
	
	@Override
	public Object remove() {
		Object o = queue.get(0);
		queue.remove(0);
		return o;
	}
	
	public Object top() {
		return queue.get(0);
	}
	
	@Override
	public int getSize() {
		return queue.size();
	}
	
	
}

/**
 * The {@code Stack} class simulates a stack data structure, inserting and
 * removing data by the FILO (first-in, last-out) rule. It stores orders with a
 * total amount below 1500 in the stack.
 *
 */
class Stack extends Container {
	
	private ArrayList<Order> stack;
	
	public Stack(String fileName) {
		stack = new ArrayList<Order>();
		List<String> orders = IOHandler.readFile(fileName);
		String[] cut = new String[5];
		int quantity;
		double price;
		for (int i = 0; i < orders.size(); i++) {
			cut = orders.get(i).split(", ");
			
			quantity = Integer.parseInt(cut[3]);
			price = Double.parseDouble(cut[4]);
			if (quantity*price < 1500) {
				stack.add(new Order(Integer.parseInt(cut[0]), cut[1], cut[2], quantity, price));
			}
		}
	}
	
	public void add(Object order) {
		if (order instanceof Order) {
			Order o = (Order) order;
			if (o.getTotalAmount() < 1500) {
				stack.add(0, o);
			}
		}
	}
	
	public Object remove() {
		Object o = stack.get(0);
		stack.remove(0);
		return o;
	}
	
	public Object top() {
		return stack.get(0);
	}
	
	public int getSize() {
		return stack.size();
	}
	
}

/**
 * The {@code IOHandler} class provides static methods for handling input and
 * output operations with files. It allows reading content from a file and
 * writing content to a file.
 *
 */
class IOHandler {
	
	static List<String> readFile(String fileName) {
		List<String> str = new ArrayList<String>();
		try {
			Scanner f = new Scanner(new File(fileName));
			while (f.hasNext()) {
				str.add(f.nextLine());
			}
			f.close();
		} catch(Exception e) {
			if (e instanceof FileNotFoundException) System.err.println("File not found");
			else if (e instanceof IOException) System.err.println("Error reading file");
		}
		return str;
	}
	
	static void writeFile(String fileName, List<String> content) {
		try {
			FileWriter n = new FileWriter(new File(fileName));
			BufferedWriter b = new BufferedWriter(n);
			
			for (int i = 0; i < content.size(); i++) {
				b.write(content.get(i));
				b.write("\n");
			}
			b.close();
		} catch(Exception e) {
			if (e instanceof FileNotFoundException) System.err.println("File not found");
			else if (e instanceof IOException) System.err.println("Error Writing to file");
		}
	}

	
}

/**
 * The {@code Order} class represents an order in the order processing system.
 * It encapsulates information about the order such as order ID, customer name,
 * product name, quantity, and unit price.
 *
 */
class Order {
	
	private String customerName;
	private int orderId;
	private String productName;
	private int quantity;
	private double unitPrice;
	
	public Order(int orderId, String customerName, String productName, int quantity, double unitPrice) {
		this.orderId = orderId;
		this.customerName = customerName;
		this.productName = productName;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}
	
	public double getTotalAmount() {
		return Math.round((quantity*unitPrice)*100)/100;
	}
	
	public String toString() {
		return String.format("[%d, %s, %s, %d, %.2lf]", orderId, customerName, productName, quantity, unitPrice);
	}
}