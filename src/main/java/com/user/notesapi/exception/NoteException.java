package com.user.notesapi.exception;

public class NoteException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int errorCode;
	String errorMsg;
	
	public NoteException()
	{
		
	}
	
	public NoteException(int errorCode,String errorMsg)
	{
		this.errorCode=errorCode;
		this.errorMsg=errorMsg;
	}
	
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	

}
