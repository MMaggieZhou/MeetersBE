
package domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;


@Embeddable
public class PartyParticipantId implements Serializable
{
    /**
     *
     */
    private static final long serialVersionUID = -7249704592544063221L;

    private PartyEntity party;

    private UserEntity participant;

    @ManyToOne
    public PartyEntity getParty()
    {
        return party;
    }

    public void setParty(PartyEntity party)
    {
        this.party = party;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public UserEntity getParticipant()
    {
        return participant;
    }

    public void setParticipant(UserEntity participant)
    {
        this.participant = participant;
    }

}
