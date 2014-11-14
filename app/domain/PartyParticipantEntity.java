package domain;

import java.math.BigInteger;
import java.sql.Timestamp;

public class PartyParticipantEntity {
	private BigInteger partyId;
	private BigInteger userId;
	private Timestamp createTime;
	public BigInteger getPartyId() {
		return partyId;
	}
	public void setPartyId(BigInteger partyId) {
		this.partyId = partyId;
	}
	public BigInteger getUserId() {
		return userId;
	}
	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}
