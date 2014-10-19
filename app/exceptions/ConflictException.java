/**
 * (C) 2014 theMobies, LLC.  All Rights Reserved.  Confidential Information of theMobies, LLC.
 */
package exceptions;

/**
 * Exception class used for situations when the request cannot be completed
 * because resource state does not allow it.
 */
public class ConflictException extends ApplicationRuntimeException
{
    private static final long serialVersionUID = 4472248776968374242L;

    public ConflictException()
    {
        super(ErrorCode.RESOURCE_CONFLICT_ERROR_CODE);
    }

    /**
     * @param errorCode
     *            - the product-specific error code
     */
    public ConflictException(ErrorCode errorCode)
    {
        super(errorCode);
    }

    /**
     * @param message
     *            - description of the exception
     */
    public ConflictException(String message)
    {
        super(message, ErrorCode.RESOURCE_CONFLICT_ERROR_CODE);
    }

    /**
     * @param message
     *            - description of the exception
     * @param errorCode
     *            - the product-specific error code
     */
    public ConflictException(String message, ErrorCode errorCode)
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
    public ConflictException(String message, String debugInfo, ErrorCode errorCode)
    {
        super(message, debugInfo, errorCode);
    }

    /**
     * @param t
     *            - the root cause of the exception
     */
    public ConflictException(Throwable t)
    {
        super(null, ErrorCode.RESOURCE_CONFLICT_ERROR_CODE, t);
    }

    /**
     * @param message
     *            - description of the exception
     * @param t
     *            - the root cause of the exception
     */
    public ConflictException(String message, Throwable t)
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
    public ConflictException(String message, ErrorCode errorCode, Throwable t)
    {
        super(message, errorCode, t);
    }

}
