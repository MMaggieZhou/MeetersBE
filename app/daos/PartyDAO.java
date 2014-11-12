package daos;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import java.util.Date;
import java.text.SimpleDateFormat;

import domain.*;
import play.Logger;
import play.db.jpa.JPA;
import exceptions.*;
//import ch.hsr.geohash.GeoHash;

/**
 * @author Maggie
 *
 */
public class PartyDAO
{
    @SuppressWarnings("unchecked")
    public ArrayList<PartyEntity> searchByParticipant(BigInteger userId)
    {
        try
        {
            List<?> results = JPA
                    .em()
                    .createQuery(
                            "select distinct party " + "from PartyParticipant pp "
                                    + "right outer join pp.pk.party as party"
                                    + " where party.active = true and pp.pk.participant.id =" + userId.toString()
                                    + " ORDER BY party.start ASC").getResultList();
            return (ArrayList<PartyEntity>) results;
        }
        catch (Exception e)
        {
            Logger.error(e.getMessage());
            throw new DatabaseAccessException("Database access error!");
        }
    }
    
    public boolean joinParty(BigInteger userId, BigInteger partyId)
    {
    	boolean res=false;
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String date=df.format(new Date());
    	try
    	{
    		res=JPA.em().createQuery(
    				"insert party_participant values ("+partyId+", "+userId+", "+date).getResultList();
    		return res;
    	}
    	catch (Exception e)
        {
            Logger.error(e.getMessage());
            throw new DatabaseAccessException("Database access error!");
        }
    }
    
    public boolean dropParty(BigInteger userId, BigInteger partyId)
    {
    	boolean res=false;
    	try
    	{
    		res=JPA.em(
    				"delete from party_participant where party_id="+partyId+" and user_id="+userId).createQuery().getResultList();
    	}
    	catch (Exception e)
        {
            Logger.error(e.getMessage());
            throw new DatabaseAccessException("Database access error!");
        }
    }

}
