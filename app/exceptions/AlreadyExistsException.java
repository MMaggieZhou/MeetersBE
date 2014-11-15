/**
 * (C) 2014 theMobies, LLC.  All Rights Reserved.  Confidential Information of theMobies, LLC.
 */
package exceptions;


/**
 * Basic exception for notifying a client that a resource being created already
 * exists.
 */
public class AlreadyExistsException extends ApplicationRuntimeException
{
    private static final long serialVersionUID = 4833541455754883453L;

    // ArrayList<String> searchArguments = new ArrayList<String>();

    public AlreadyExistsException()
    {
        super(ErrorCode.NOT_FOUND_ERROR_CODE);
        // searchArguments = new ArrayList<String>();
    }

    public AlreadyExistsException(String message)
    {
        super(message, ErrorCode.CONFLICT_ERROR_CODE);
    }

    public AlreadyExistsException(String message, Throwable t)
    {
        super(message, ErrorCode.CONFLICT_ERROR_CODE, t);
    }

    public AlreadyExistsException(ErrorCode errorCode)
    {
        super(errorCode);
        // searchArguments = new ArrayList<String>();
    }

    public AlreadyExistsException(String message, ErrorCode errorCode)
    {
        super(message, errorCode);
        // searchArguments = new ArrayList<String>();
    }

    public AlreadyExistsException(String message, String debugInfo, ErrorCode errorCode)
    {
        super(message, null, errorCode);
    }

    public void addSearchArgument(String argName, String arg)
    {
        // searchArguments.add(String.format("%s = %s", argName, arg));
    }

    public String getDebugInfo()
    {
//        String debugInfo = "Search arguments:";
        // for (String value : searchArguments)
        // {
        // debugInfo += "\n";
        // debugInfo += value;
        // }

//        return debugInfo;
        return null;
    }
}
