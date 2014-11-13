/**
 * (C) 2014 TheMobies, LLC. All Rights Reserved. Confidential Information of TheMobies, LLC.
 */
package requests;

/**
 * @author Maggie
 * 
 */
public class Point
{
    private double latitude;
    private double longitude;

    public Point(double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;

    }
}
