/**
 * (C) 2014 TheMobies, LLC. All Rights Reserved. Confidential Information of TheMobies, LLC.
 */
package requests;

/**
 * @author aaron_win7
 *
 */
public class LoginRequest
{
	
    private String loginAccount;
    private String password;
    private String regId;
    private Double latitude;
    private Double longitude;

    public Double getLatitude() {
    	return latitude;
    }
    public void setLatitude(Double latitude) {
    	this.latitude = latitude;
    }
    public Double getLongitude() {
    	return longitude;
    }
    public void setLongitude(Double longitude) {
    	this.longitude = longitude;
    }
    public String getLoginAccount()
    {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount)
    {
        this.loginAccount = loginAccount;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getRegId()
    {
        return regId;
    }

    public void setRegId(String regId)
    {
        this.regId = regId;
    }
}
