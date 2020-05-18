package common;

import java.util.ArrayList;

public class Request {
	String operation_type;
	String table;
	ArrayList<String> a = new ArrayList<>();
	
	public ArrayList<String> getA() {
		return a;
	}
	public void setA(ArrayList<String> a) {
		this.a = a;
	}
	public String getOperation_type() {
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
}
