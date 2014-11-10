
package daos;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import play.Logger;
import play.db.jpa.JPA;
//import ch.hsr.geohash.GeoHash;
import exceptions.*;
import domain.*;
//import utils.GeoHashUtil;

public class SessionTokenDAO
{

    public void createOrUpdateSesstionToken(SessionTokenEntity sessionToken)
    {
        try
        {
            JPA.em().merge(sessionToken);
            Logger.info(String.format("save seesion token(%s) for user(%s)", sessionToken.getValue(),
                    sessionToken.getUserId()));
        }
        catch (IllegalArgumentException iae)
        {
            Logger.error(iae.getMessage());
            throw new DatabaseAccessException("Database access error!");
        }
        catch (Exception e)
        {
            // Handle errors for Class.forName
            Logger.error(e.getMessage());
            throw new DatabaseAccessException("Database access error!");
        }
    }

    public SessionTokenEntity retrieveSessionTokenByToken(UUID authToken)
    {
        if (authToken == null)
        {
            return null;
        }
        SessionTokenEntity sessionToken = null;

        List<?> results = JPA
                .em()
                .createQuery(
                        String.format(
                                "select sessionToken from SessionTokenEntity as sessionToken where sessionToken.value='%s'",
                                authToken)).getResultList();

        if (results == null || results.size() == 0)
        {
            return null;
        }
        sessionToken = (SessionTokenEntity) results.get(0);

        return sessionToken;
    }

    public SessionTokenEntity retrieveSessionTokenByUserId(BigInteger userId)
    {
        if (userId == null || userId.signum() == -1)
        {
            return null;
        }
        SessionTokenEntity sessionToken = null;

        List<?> results = JPA
                .em()
                .createQuery(
                        String.format(
                                "select sessionToken from SessionTokenEntity as sessionToken where sessionToken.userId='%s'",
                                userId.toString())).getResultList();

        if (results == null || results.size() == 0)
        {
            return null;
        }
        sessionToken = (SessionTokenEntity) results.get(0);

        return sessionToken;
    }

    public List<SessionTokenEntity> findAllLoggedInUsers()
    {

        List<SessionTokenEntity> sessionTokens = new ArrayList<SessionTokenEntity>();

        @SuppressWarnings("unchecked")
        List<SessionTokenEntity> results = (List<SessionTokenEntity>) JPA.em().createQuery(

        "select sessionToken from SessionTokenEntity as sessionToken ").getResultList();

        if (results == null || results.size() == 0)
        {
            return null;
        }
        sessionTokens.addAll(results);

        return sessionTokens;
    }

}
