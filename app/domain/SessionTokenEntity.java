/**
 * (C) 2014 TheMobies, LLC. All Rights Reserved. Confidential Information of TheMobies, LLC.
 */
package domain;

import java.math.BigInteger;
import java.sql.Time;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

/**
 * Aaron
 */
@Entity
@Table(name = "session_token")
public class SessionTokenEntity
{

    private String value;
    private BigInteger userId;
    private Time expirationTime;
    private float longitude;
    private float latitude;
    private String geohash;
    private String gcmId;

    @Id
    @Column(name = "value")
    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    @Column(name = "expiration_time")
    public Time getExpirationTime()
    {
        return expirationTime;
    }

    public void setExpirationTime(Time expirationTime)
    {
        this.expirationTime = expirationTime;
    }

    @Column(name = "longitude")
    public float getLongitude()
    {
        return longitude;
    }

    public void setLongitude(float longitude)
    {
        this.longitude = longitude;
    }

    @Column(name = "latitude")
    public float getLatitude()
    {
        return latitude;
    }

    public void setLatitude(float latitude)
    {
        this.latitude = latitude;
    }

    @Column(name = "geohash")
    public String getGeohash()
    {
        return geohash;
    }

    public void setGeohash(String geohash)
    {
        this.geohash = geohash;
    }

    @Column(name = "gcmId")
    public String getGcmId()
    {
        return gcmId;
    }

    public void setGcmId(String gcmId)
    {
        this.gcmId = gcmId;
    }

    @ForeignKey(name = "user_id")
    @Column(name = "user_id")
    public BigInteger getUserId()
    {
        return userId;
    }

    public void setUserId(BigInteger userId)
    {
        this.userId = userId;
    }

}
