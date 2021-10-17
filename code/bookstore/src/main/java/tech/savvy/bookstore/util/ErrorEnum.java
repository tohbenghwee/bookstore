package tech.savvy.bookstore.util;

public enum ErrorEnum {
	
	  BOOK_NOT_FOUND(100, "Book Not Found."),
	  BOOK_SAVE_ERROR(101, "Save Book Error"),
	  BOOK_UPDATE_ERROR(102, "Update Book Error."),
	  BOOK_DELETE_ERROR(103, "Delete Book Error."),
	  BOOK_ISBN_NOT_EXIST(104, "ISBN for the book don't exist."),
	  BOOK_ISBN_EXIST(105, "ISBN already existed."),
	  BOOK_NO_AUTHOR(106, "No Author for the book.");
	  private final int code;
	  private final String description;

	  private ErrorEnum(int code, String description) {
	    this.code = code;
	    this.description = description;
	  }

	  public String getDescription() {
	     return description;
	  }

	  public int getCode() {
	     return code;
	  }

	  @Override
	  public String toString() {
	    return code + ": " + description;
	  }
}
