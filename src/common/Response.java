package common;

import java.util.ArrayList;

public class Response {
	Boolean successfulOperation = true;
	String typeOperation;
	ArrayList<String> a = new ArrayList<String>();
	
	public Response() {
	}
	public Boolean getSuccessfulOperation() {
		return successfulOperation;
	}
	public void setSuccessfulOperation(Boolean successfulOperation) {
		this.successfulOperation = successfulOperation;
	}

	public ArrayList<String> getA() {
		return a;
	}
	public void setA(ArrayList<String> a) {
		this.a = a;
	}
	public String getTypeOperation() {
		return typeOperation;
	}

	public void setTypeOperation(String typeOperation) {
		this.typeOperation = typeOperation;
	}
}
