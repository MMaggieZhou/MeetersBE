package controllers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import requests.*;
import domain.*;

import org.apache.commons.lang3.StringUtils;

import play.Logger;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import utils.GeoHashUtil;

import com.fasterxml.jackson.databind.JsonNode;

import common.cache.LRUAuthToken;
import daos.*;

/**
 * @author Maggie
 * 
 */
public class PartyServiceREST extends Controller
{

    private static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    @SuppressWarnings("unused")
    @Transactional
    public static Result getMyParty()
    {

    	BigInteger id = null;
        String authToken = null;
        String latitude = null;
        String longitude = null;
        try
        {
            id = new BigInteger(request().getQueryString("userId"));
            latitude = request().getQueryString("lat");
            longitude = request().getQueryString("long");
            authToken = request().getHeader("AUTHTOKEN");
        }
        catch (Exception e)
        {
            Logger.error("Find my party request@ " + new Date().toGMTString() + "---->" + e.getMessage());
            // return badRequest((new InvalidJsonException(
            // "Not sure which exception to throw, but your http headeris invalid!")).toString());
        }
        if (id == null)
        {
            return forbidden("Missing user id!");
        }
        if (StringUtils.isBlank(authToken))
        {
            return unauthorized("Missing session token!");

        }
        BigInteger userId = null;
        try
        {
            userId = searchByAuthToken(authToken);
        }
        catch (Exception e)
        {
            return unauthorized(e.toString());
        }
        List<PartyEntity> partyEntities = null;
        if (userId == null)
        {
            return unauthorized("Token does not exist!");
        }

        if (userId.compareTo(id) != 0)
        {
            return forbidden("Token does not match with user id!"
                    + String.format("Token id is %s, but the userId is %s", id, userId));
        }

        try
        {
            partyEntities = new PartyDAO().searchByParticipant(id);
        }
        catch (Exception e)
        {
            Logger.error("Find party request@ " + new Date().toGMTString() + "---->" + e.toString());
            return internalServerError("Database access error!");
        }

        ArrayList<StartPartyResponse> parties = new ArrayList<StartPartyResponse>();
        for (PartyEntity partyEntity : partyEntities)
        {
            StartPartyResponse party = map(partyEntity);

            if (StringUtils.isNotBlank(longitude) && StringUtils.isNotBlank(latitude))
            {
                double lgt = Double.parseDouble(longitude);
                double ltt = Double.parseDouble(latitude);
                if (Math.abs(lgt) <= 180 && Math.abs(ltt) <= 90)
                {
                    double distance = GeoHashUtil.distance((double) partyEntity.getLatitude(),
                            (double) partyEntity.getLongitude(), ltt, lgt, "M");
                    party.setDistance(new BigDecimal(distance));
                     return badRequest("longitude or latitude out of range");
                }

            }
            parties.add(party);
        }
        GetMyPartyResponse getMyPartyResponse = new GetMyPartyResponse();
        getMyPartyResponse.setMyParties(parties);
        return ok(Json.toJson(getMyPartyResponse));
        
    }
    private static BigInteger searchByAuthToken(String authToken)
    {
        BigInteger userId;
        if ((userId = LRUAuthToken.getInstance().get(authToken)) == null)
        {
            SessionTokenEntity sessionToken = new SessionTokenDAO().retrieveSessionTokenByToken(UUID
                    .fromString(authToken));
            if (sessionToken != null)
            {
                userId = sessionToken.getUserId();
                LRUAuthToken.getInstance().put(sessionToken.getValue(), userId);
            }
        }
        return userId;
    }

    /*private static ViewPartyResponse viewByPartyId(BigInteger partyId)
    {
    	ViewPartyResponse viewPartyResponse = new ViewPartyResponse();
    	PartyDAO pd=new PartyDAO();
    	pd.searchByPartyId(partyId);
    	return viewByPartyId;
    	
    }*/
    
    private static StartPartyResponse map(PartyEntity partyEntity)
    {
        StartPartyResponse startPartyResponse = new StartPartyResponse();
        Address address = new Address();
        address.setAddressLine1(partyEntity.getAddressLine1());
        address.setAddressLine2(partyEntity.getAddressLine2());
        address.setAddressLine3(partyEntity.getAddressLine3());
        address.setAddressLine5(partyEntity.getAddressLine5());
        address.setAddressLine4(partyEntity.getAddressLine4());
        address.setCityName(partyEntity.getCityName());
        address.setCountryCode(partyEntity.getCountryCode());
        address.setCountyCode(partyEntity.getCountyCode());
        address.setDefault(partyEntity.isDefault());
        address.setPrivateAddress(partyEntity.isPrivateAddress());
        address.setPostalCode(partyEntity.getPostalCode());
        address.setStateProvinceCode(partyEntity.getStateProvinceCode());

        startPartyResponse.setAddress(address);

        Timestamp endTime = partyEntity.getEnd();
        startPartyResponse.setEndTime(endTime == null ? null : sdf.format(endTime));
        startPartyResponse.setLatitude(new Double(partyEntity.getLatitude()));
        startPartyResponse.setLongitude(new Double(partyEntity.getLongitude()));
        startPartyResponse.setNumOfPeople(partyEntity.getNumParticipant());
        startPartyResponse.setOtherInfo(partyEntity.getOtherInfo());
        startPartyResponse.setPrice(new BigDecimal(partyEntity.getPrice()));

        Timestamp startTime = partyEntity.getStart();
        startPartyResponse.setStartTime(startTime == null ? null : sdf.format(startTime));
        startPartyResponse.setTheme(partyEntity.getTheme());
        startPartyResponse.setUserId(partyEntity.getHost().getUserId());
        startPartyResponse.setVenues(partyEntity.getRestaurant());
        startPartyResponse.setPartyId(partyEntity.getId());

        return startPartyResponse;
    }

    private static PartyEntity map(StartPartyRequest startPartyRequest)
    {
        PartyEntity partyEntity = new PartyEntity();
        partyEntity.setHost(JPA.em().find(UserEntity.class, startPartyRequest.getUserId()));
        partyEntity.setRestaurant(startPartyRequest.getVenues());
        partyEntity.setNumParticipant(startPartyRequest.getNumOfPeople());
        partyEntity.setPrice(startPartyRequest.getPrice().doubleValue());
        partyEntity.setOtherInfo(startPartyRequest.getOtherInfo());
        partyEntity.setTheme(startPartyRequest.getTheme());
        partyEntity.setActive(true);
        try
        {
            partyEntity.setStart(parse(startPartyRequest.getStartTime()));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        try
        {
            partyEntity.setEnd(parse(startPartyRequest.getEndTime()));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        partyEntity.setLatitude(startPartyRequest.getLatitude().floatValue());
        partyEntity.setLongitude(startPartyRequest.getLongitude().floatValue());

        partyEntity.setAddressLine1(startPartyRequest.getAddress().getAddressLine1());
        partyEntity.setAddressLine2(startPartyRequest.getAddress().getAddressLine2());
        partyEntity.setAddressLine3(startPartyRequest.getAddress().getAddressLine3());
        partyEntity.setAddressLine4(startPartyRequest.getAddress().getAddressLine4());
        partyEntity.setAddressLine5(startPartyRequest.getAddress().getAddressLine5());
        partyEntity.setCityName(startPartyRequest.getAddress().getCityName());
        partyEntity.setCountryCode(startPartyRequest.getAddress().getCountryCode());
        partyEntity.setCountyCode(startPartyRequest.getAddress().getCountyCode());
        partyEntity.setPostalCode(startPartyRequest.getAddress().getPostalCode());
        partyEntity.setStateProvinceCode(startPartyRequest.getAddress().getStateProvinceCode());

        return partyEntity;
    }

    private static Timestamp parse(String time) throws ParseException
    {

        Date date = sdf.parse(time);
        return new Timestamp(date.getTime());
    }
}
