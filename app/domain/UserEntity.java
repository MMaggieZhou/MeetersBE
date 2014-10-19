
package domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "users")
public class UserEntity implements Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 3929183834410903304L;

    private BigInteger userId;

    private String nickname;

    private String email;

    private String gender;

    private String phone;

    private String firstname;

    private String lastname;

    private String password;

    private Set<PartyParticipant> pp;

    private float longitude;
    private float latitude;
    private String geohash;
    private Set<PartyEntity> hostedParties;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "host")
    public Set<PartyEntity> getHostedParties()
    {
        return hostedParties;
    }

    public void setHostedParties(Set<PartyEntity> hostedParties)
    {
        this.hostedParties = hostedParties;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.participant")
    public Set<PartyParticipant> getPp()
    {
        return pp;
    }

    public void setPp(Set<PartyParticipant> pp)
    {
        this.pp = pp;
    }

    @Column(name = "nickname")
    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    @NotNull
    @NotEmpty
    @Column(name = "email", unique = true)
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Column(name = "gender")
    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    @Column(name = "phone", unique = true)
    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    @Column(name = "firstname")
    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    @Column(name = "lastname")
    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    @NotNull
    @NotEmpty
    @Column(name = "password")
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
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

    @Id
    @GeneratedValue
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
