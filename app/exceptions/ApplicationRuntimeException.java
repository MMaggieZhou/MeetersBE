/**
 * (C) 2012 theMobies, LLC.  All Rights Reserved.  Confidential Information of theMobies, LLC.
 */
package exceptions;

/**
 * {@code ApplicationRuntimeException} is the superclass of those exceptions
 * that can be thrown during the normal operation of the system.
 */
public class ApplicationRuntimeException extends RuntimeException
{
    private static final long serialVersionUID = -2238425715382259296L;
    private ErrorCode errorCode;
    private Object debugInfo;

    public ApplicationRuntimeException(ErrorCode errorCode)
    {
        this.errorCode = errorCode;
    }

    public ApplicationRuntimeException(String message)
    {
        super(message);
    }

    public ApplicationRuntimeException(String message, ErrorCode errorCode)
    {
        super(message);
        this.errorCode = errorCode;
    }

    public ApplicationRuntimeException(String message, String debugInfo, ErrorCode errorCode)
    {
        super(message);
        this.errorCode = errorCode;
        this.debugInfo = debugInfo;
    }

    public ApplicationRuntimeException()
    {
        super();
    }

    public ApplicationRuntimeException(Throwable t)
    {
        super(t);
    }

    public ApplicationRuntimeException(Throwable t, ErrorCode errorCode)
    {
        super(t);
        this.errorCode = errorCode;
    }

    public ApplicationRuntimeException(String message, Throwable t)
    {
        super(message, t);
    }

    public ApplicationRuntimeException(String message, ErrorCode errorCode, Throwable t)
    {
        super(message, t);
        this.errorCode = errorCode;
    }

    /**
     * Get the error code of the exception.
     *
     * @return a {@link Integer} object represented the error code of the
     *         exception
     */
    public ErrorCode getErrorCode()
    {
        return errorCode;
    }

    public Object getDebugInfo()
    {
        return this.debugInfo;
    }

    public void setDebugInfo(Object debugInfo)
    {
        this.debugInfo = debugInfo;
    }

    @Override
    public String toString()
    {
        return String.format(" {\"errorCode\": \"%s\", \"debugInfo\": %s,\"message\": \"%s\"}", this.getErrorCode()
                .getCode(), this.getDebugInfo(), this.getMessage());
    }
}