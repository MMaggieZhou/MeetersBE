
package domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "party_participant")
@AssociationOverrides({ @AssociationOverride(name = "pk.party", joinColumns = @JoinColumn(name = "party_id")),
        @AssociationOverride(name = "pk.participant", joinColumns = @JoinColumn(name = "user_id")) })
public class PartyParticipant implements Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 6010326144902487759L;

    private PartyParticipantId pk = new PartyParticipantId();

    private Timestamp createdDateTime;

    @EmbeddedId
    public PartyParticipantId getPk()
    {
        return pk;
    }

    public void setPk(PartyParticipantId pk)
    {
        this.pk = pk;
    }

    @Transient
    public PartyEntity getParty()
    {
        return getPk().getParty();
    }

    public void setParty(PartyEntity party)
    {
        getPk().setParty(party);
    }

    @Transient
    public UserEntity getParticipant()
    {
        return getPk().getParticipant();
    }

    public void setParticipant(UserEntity participant)
    {
        getPk().setParticipant(participant);
    }

    @Column(name = "create_datetime")
    public Timestamp getCreatedDateTime()
    {
        return createdDateTime;
    }

    public void setCreatedDateTime(Timestamp createdDateTime)
    {
        this.createdDateTime = createdDateTime;
    }

}
