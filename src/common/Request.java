package common;

import java.util.ArrayList;

public class Request {
	String operation_type;
	String table;
	ArrayList<String> a = new ArrayList<>();
	private String date;
	private String date2;
	private String pos;


	
	public ArrayList<String> getA() {
		return a;
	}
	public void setA(ArrayList<String> a) {
		this.a = a;
	}
	/*String firstname;
	String lastname;
	int id ;
	Sensor poll = new Sensor();
	
	public Sensor getPoll() {
		return poll;
	}

	public void setPoll(Sensor poll) {
		this.poll = poll;
	}

	public Request() {
	}
	
	public String toString() {
		return "Operation Type : " + operation_type;
	}
	
	*/public String getOperation_type() {
		return operation_type;
	}
	public void setOperation_type(String operation_type) {
		this.operation_type = operation_type;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate2(String date2) {
		this.date2 = date2;
	}
	
	public String getDate2() {
		return date2;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}
	
	public String getPos() {
		return pos;
	}
	
	
	/*

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}*/
}
