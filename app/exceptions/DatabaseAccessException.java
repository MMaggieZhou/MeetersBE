/**
 * (C) 2014 theMobies, LLC.  All Rights Reserved.  Confidential Information of theMobies, LLC.
 */
package exceptions;

/**
 * Exception class used for situations when the request cannot be completed
 * because resource state does not allow it.
 */
public class DatabaseAccessException extends ApplicationRuntimeException
{
    private static final long serialVersionUID = 4472248776968374241L;

    public DatabaseAccessException()
    {
        super(ErrorCode.DATABASE_ACCESS_ERROR);
    }

    /**
     * @param errorCode
     *            - the product-specific error code
     */
    public DatabaseAccessException(ErrorCode errorCode)
    {
        super(errorCode);
    }

    /**
     * @param message
     *            - description of the exception
     */
    public DatabaseAccessException(String message)
    {
        super(message, ErrorCode.DATABASE_ACCESS_ERROR);
    }

    /**
     * @param message
     *            - description of the exception
     * @param errorCode
     *            - the product-specific error code
     */
    public DatabaseAccessException(String message, ErrorCode errorCode)
    {
        super(message, errorCode);
    }

    /**
     * @param message
     *            - description of the exception
     * @param debugInfo
     *            - developer message
     * @param errorCode
     *            - the product-specific error code
     */
    public DatabaseAccessException(String message, String debugInfo, ErrorCode errorCode)
    {
        super(message, debugInfo, errorCode);
    }

    /**
     * @param t
     *            - the root cause of the exception
     */
    public DatabaseAccessException(Throwable t)
    {
        super(null, ErrorCode.DATABASE_ACCESS_ERROR, t);
    }

    /**
     * @param message
     *            - description of the exception
     * @param t
     *            - the root cause of the exception
     */
    public DatabaseAccessException(String message, Throwable t)
    {
        super(message, t);
    }

    /**
     * @param message
     *            - description of the exception
     * @param errorCode
     *            - the product-specific error code
     * @param t
     *            - the root cause of the exception
     */
    public DatabaseAccessException(String message, ErrorCode errorCode, Throwable t)
    {
        super(message, errorCode, t);
    }

}
