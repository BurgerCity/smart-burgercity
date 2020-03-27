package client;

public class Request {
	
	public class Insert {
		private String firstname;
		private String lastname;
		
		Insert() {}
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
		
	}
	
	public class Delete {
		private int id;
		Delete() {}
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
	}
	
	public class Select {
		private String table;
		Select(){}
		public String getTable() {
			return table;
		}

		public void setTable(String table) {
			this.table = table;
		}
	}

	public class Update {
		private String firstname;
		private String lastname;
		private int id;
		
		Update() {}
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

}
