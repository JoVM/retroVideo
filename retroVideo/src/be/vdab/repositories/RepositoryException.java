package be.vdab.repositories;

public class RepositoryException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * Catch SQL exceptions
	 * 
	 * @param cause
	 */
	public RepositoryException(Throwable cause) {
		super(cause);

	}

}
