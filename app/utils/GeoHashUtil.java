/**
 * (C) 2014 TheMobies, LLC. All Rights Reserved. Confidential Information of TheMobies, LLC.
 */
package utils;

import play.Logger;
import ch.hsr.geohash.GeoHash;

/**
 * @author Maggie
 * 
 */
public class GeoHashUtil
{
    public final static int NUM_CHAR = 10;

    public static String[] getAdjacentGeoHash(double latitude, double longitude, double distance)
    {
        int numChar = getNumCharacter(distance);
        String[] adjacents = new String[9];
        String geoHash = getGeohash(latitude, longitude);
        Logger.info("geohash = " + geoHash);
        System.arraycopy(getAdjacentGeoHash(geoHash, numChar), 0, adjacents, 0, 8);
        adjacents[8] = geoHash.substring(0, numChar);
        return adjacents;
    }
    
    
    public static String getGeohash(double latitude, double longitude)
    {
        return GeoHash.withCharacterPrecision(latitude, longitude, NUM_CHAR).toBase32();
    }

    public static String[] getAdjacentGeoHash(String geoHash, int numCharacter)
    {

        String[] adjacents = new String[8];

        GeoHash geohash = GeoHash.fromGeohashString(geoHash.substring(0, numCharacter));
        int i = 0;
        for (GeoHash gh : geohash.getAdjacent())
        {
            adjacents[i] = gh.toBase32();
            i++;
        }

        return adjacents;
    }

    private static int getNumCharacter(double distance)
    {
        double meter = distance * 1609.34;
        if (meter <= 0.6)
        {
            return 10;
        }
        else if (meter <= 3.71)
        {
            return 9;
        }
        else if (meter <= 19)
        {
            return 8;
        }
        else if (meter <= 118)
        {
            return 7;
        }
        else if (meter <= 610)
        {
            return 6;
        }
        else if (meter <= 3803)
        {
            return 5;
        }
        else if (meter <= 19545)
        {
            return 4;
        }
        else if (meter <= 123264)
        {
            return 3;
        }
        else if (meter <= 625441)
        {
            return 2;
        }
        else
        {
            return 1;
        }
    }

    /*
     * "M" is miles "K" is kilometers "N" is nautical miles
     */
    public static double distance(double lat1, double lon1, double lat2, double lon2, String unit)
    {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit.equals("K"))
        {
            dist = dist * 1.609344;
        }
        else if (unit.equals("N"))
        {
            dist = dist * 0.8684;
        }
        return (dist);
    }

    private static double deg2rad(double deg)
    {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad)
    {
        return (rad * 180.0 / Math.PI);
    }
}
