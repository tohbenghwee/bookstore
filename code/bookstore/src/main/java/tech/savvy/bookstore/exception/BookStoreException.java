package tech.savvy.bookstore.exception;

public class BookStoreException extends RuntimeException {

	/**
	 * 
	 */
	 private static final long serialVersionUID = 1L;

	 private int errorCode = 0;
	 
	 public BookStoreException(String message) {
         super(message);
     }
	 
	 public BookStoreException(String message, int errorCode) {
         super(message);
         this.errorCode = errorCode;
     }

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
