package common;

public class Response {
	Boolean successfulOperation = true;
	String typeOperation;
	String select;
	
	public Response() {
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
