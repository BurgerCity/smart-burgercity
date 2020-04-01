package common;

public class Response {
	Boolean successfulOperation = true;
	String typeOperation;
	String select;
	String Connection_Status;
	
	public Response() {
	}
	public String getConnection_Status() {
		return Connection_Status;
	}

	public void setConnection_Status(String connection_Status) {
		Connection_Status = connection_Status;
	}
	public Boolean getSuccessfulOperation() {
		return successfulOperation;
	}
	public void setSuccessfulOperation(Boolean successfulOperation) {
		this.successfulOperation = successfulOperation;
	}
	public String getSelect() {
		return select;
	}
	public void setSelect(String select) {
		this.select = select;
	}

	public String getTypeOperation() {
		return typeOperation;
	}

	public void setTypeOperation(String typeOperation) {
		this.typeOperation = typeOperation;
	}
}
