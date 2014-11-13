/**
 * (C) 2014 TheMobies, LLC. All Rights Reserved. Confidential Information of TheMobies, LLC.
 */
package requests;

import java.sql.Timestamp;
import java.util.Date;

import utils.GeoHashUtil;

/**
 * @author Maggie
 * 
 */
public class Criteria
{

    private String[] geohash;
    private int[] distance;

    public Criteria(NearbySearchRequest request)
    {
        Point point = new Point(request.getLatitude(), request.getLongitude());
        DistanceEnum distance = DistanceEnum.values()[request.getDistanceOption()];

        this.distance = distance.getRange();
        this.setGeohash(GeoHashUtil.getAdjacentGeoHash(point.getLatitude(), point.getLongitude(),
                distance.getRange()[1]));
    }

    public void setGeohash(String[] geohash)
    {
        this.geohash = geohash;
    }

    public String[] getGeohash()
    {
        return geohash;
    }

    public void setDistance(int[] distance)
    {
        this.distance = distance;
    }

    public int[] getDistance()
    {
        return distance;
    }
    public String toString()
    {
        return String.format("geohash (%s,%s)    ", geohash[0], geohash[1])
                + String.format("distance (%s,%s)    ", distance[0], distance[1]
                );

    }
}
