/**
 * (C) 2014 TheMobies, LLC. All Rights Reserved. Confidential Information of TheMobies, LLC.
 */
package common.cache;

import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.util.LinkedHashMap;

//import common.exception.NotCreatedException;

/**
 * Aaron
 */
public class LRUAuthToken
{
    private static LRUAuthToken instance;
    private static LinkedHashMap<String, BigInteger> authTokenMap = null;
    private int capacity;
    private static final Object mapLock = new Object();

    private LRUAuthToken(int capacity)
    {
        authTokenMap = new LinkedHashMap<String, BigInteger>(capacity, 0.72f, true);
        this.capacity = capacity;
    }

    public static LRUAuthToken getInstance(int capacity)
    {
        if (capacity < 100)
        {
            throw new InvalidParameterException("The AuthToken cache must over 100");
        }
        capacity = (int) (capacity * 1.34) + 1;
        if (instance == null)
        {
            synchronized (LRUAuthToken.class)
            {
                if (instance == null)
                {
                    instance = new LRUAuthToken(capacity);
                }
            }
        }
        return instance;
    }

    public static LRUAuthToken getInstance()
    {
        if (instance == null)
        {
            //throw new NotCreatedException("The AuthToken cache is not created!", null);
        }
        return instance;
    }

    public BigInteger get(String key)
    {
        BigInteger userId = null;

        synchronized (mapLock)
        {
            if (authTokenMap.containsKey(key))
            {

                userId = authTokenMap.get(key);
                authTokenMap.remove(key);
                authTokenMap.put(key, userId);

            }
        }
        return userId;
    }

    public void put(String key, BigInteger userId)
    {
        synchronized (mapLock)
        {
            if (authTokenMap.containsKey(key))
            {
                authTokenMap.remove(key);
            }
            else if (authTokenMap.size() == capacity)
            {
                String firstKey = authTokenMap.keySet().iterator().next();
                authTokenMap.remove(firstKey);
            }
            authTokenMap.put(key, userId);

        }
    }
}
