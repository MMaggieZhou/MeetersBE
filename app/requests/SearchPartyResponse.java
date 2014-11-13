package requests;



import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by Mengqi on 11/12/2014.
 */
public class SearchPartyResponse implements Serializable {
    private boolean join;
    private String theme;
    private Double longitude;
    private Double latitude;
    private BigInteger partyId;
    private BigDecimal distance;

    public boolean getJoin() {
        return join;
    }

    public void setJoin(boolean join) {
        this.join = join;
    }
    public BigInteger getPartyId()
    {
        return partyId;
    }

    public void setPartyId(BigInteger partyId)
    {
        this.partyId = partyId;
    }

    public BigDecimal getDistance()
    {
        return distance;
    }

    public void setDistance(BigDecimal distance)
    {
        this.distance = distance;
    }

    public String getTheme()
    {
        return theme;
    }

    public void setTheme(String theme)
    {
        this.theme = theme;
    }

    public Double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(Double longitude)
    {
        this.longitude = longitude;
    }

    public Double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(Double latitude)
    {
        this.latitude = latitude;
    }

}
