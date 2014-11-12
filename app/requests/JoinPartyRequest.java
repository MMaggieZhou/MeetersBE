package requests;
import java.math.BigDecimal;
import java.math.BigInteger;
public class JoinPartyRequest {
	private BigInteger userId;
	private BigInteger partyId;
	
	public BigInteger getUserId(){
		return userId;
	}
	public void setUserId(BigInteger userId){
		this.userId=userId;
	}
	public BigInteger getPartyId(){
		return partyId;
	}
	public void setPartyId(BigInteger partyId){
		this.partyId=partyId;
	}

}
