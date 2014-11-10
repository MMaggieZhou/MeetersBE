/**
 * (C) 2014 TheMobies, LLC. All Rights Reserved. Confidential Information of TheMobies, LLC.
 */
package requests;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Aaron
 */
public class StartPartyResponse extends StartPartyRequest
{
    private BigInteger partyId;
    private BigDecimal distance;

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

}
