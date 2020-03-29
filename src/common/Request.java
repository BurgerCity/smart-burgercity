package common;

public class Request {
	String operation_type;
	String table;
	String firstname;
	String lastname;
	int id ;
	
	public Request(/*String op*/) {
		/*operation_type = op;
		table = "";
		firstname = "";
		lastname = "";
		id = 0;*/
	}
	
	public String toString() {
		return "Operation Type : " + operation_type;
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
	}
}
