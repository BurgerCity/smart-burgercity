package common;

public class Response {
	private Boolean successfulOperation = true;
	private String typeOperation;
	private String select;
	
	public Response(Boolean b, String t) {
		successfulOperation = b;
		typeOperation = t;
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
