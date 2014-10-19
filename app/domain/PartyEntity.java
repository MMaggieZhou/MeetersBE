
package domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "party")
public class PartyEntity implements Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 662159157694153832L;

    private BigInteger id;
    private UserEntity host;

    private Date date;
    private Time startTime;
    private Timestamp start;
    private Time endTime;
    private Timestamp end;
   
    private int duration;
    private String restaurant;
    // in dollar
    private double price;
    private StyleEnum style;
    private int numParticipant;
    private String theme;
    private int numFemale;
    private int numMale;
    private int numBise;
    private RankEnum rank;
    private boolean active;
    private float longitude;
    private float latitude;
    private String geohash;
    private Set<PartyParticipant> pp;
    private String type;
    private String otherInfo;

    private String addressLine1;

    private String addressLine2;

    private String addressLine3;

    private String addressLine4;

    private String addressLine5;

    private String cityName;

    private String postalCode;

    private String stateProvinceCode;

    private String countryCode;

    private String countyCode;

    private boolean isPrivateAddress;

    private boolean isDefault;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pk.party")
    public Set<PartyParticipant> getPp()
    {
        return pp;
    }

    public void setPp(Set<PartyParticipant> pp)
    {
        this.pp = pp;
    }

    @Id
    @GeneratedValue
    @Column(name = "party_id")
    public BigInteger getId()
    {
        return id;
    }

    public void setId(BigInteger id)
    {
        this.id = id;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public UserEntity getHost()
    {
        return host;
    }

    public void setHost(UserEntity host)
    {
        this.host = host;
    }

    @Column(name = "date")
    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    @Column(name = "start_time")
    public Time getStartTime()
    {
        return startTime;
    }

    public void setStartTime(Time startTime)
    {
        this.startTime = startTime;
    }

    @Column(name = "end_time")
    public Time getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Time endTime)
    {
        this.endTime = endTime;
    }

    @Column(name = "duration")
    public int getDuration()
    {
        return duration;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    @Column(name = "restaurant")
    public String getRestaurant()
    {
        return restaurant;
    }

    public void setRestaurant(String restaurant)
    {
        this.restaurant = restaurant;
    }

    @Column(name = "price")
    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    @Column(name = "style")
    @Enumerated(EnumType.STRING)
    public StyleEnum getStyle()
    {
        return style;
    }

    public void setStyle(StyleEnum style)
    {
        this.style = style;
    }

    @Column(name = "participant")
    public int getNumParticipant()
    {
        return numParticipant;
    }

    public void setNumParticipant(int numParticipant)
    {
        this.numParticipant = numParticipant;
    }

    @Column(name = "female")
    public int getNumFemale()
    {
        return numFemale;
    }

    public void setNumFemale(int numFemale)
    {
        this.numFemale = numFemale;
    }

    @Column(name = "male")
    public int getNumMale()
    {
        return numMale;
    }

    public void setNumMale(int numMale)
    {
        this.numMale = numMale;
    }

    @Column(name = "bisexual")
    public int getNumBise()
    {
        return numBise;
    }

    public void setNumBise(int numBise)
    {
        this.numBise = numBise;
    }

    @Column(name = "rank")
    @Enumerated(EnumType.ORDINAL)
    public RankEnum getRank()
    {
        return rank;
    }

    public void setRank(RankEnum rank)
    {
        this.rank = rank;
    }

    @Column(name = "active")
    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
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

    @Column(name = "theme")
    public String getTheme()
    {
        return theme;
    }

    public void setTheme(String theme)
    {
        this.theme = theme;
    }
    
    @Column(name = "start")
    public Timestamp getStart()
    {
        return start;
    }
    
    public void setStart(Timestamp start)
    {
        this.start = start;
    }
    @Column(name = "end")
    public Timestamp getEnd()
    {
        return end;
    }

    public void setEnd(Timestamp end)
    {
        this.end = end;
    }

    @Transient
    public ArrayList<UserEntity> getParticipants()
    {
        ArrayList<UserEntity> participants = null;
        if (pp != null && pp.size() > 0)
        {
            participants = new ArrayList<UserEntity>();
            for (PartyParticipant p : pp)
            {
                if (p.getParticipant() != null)
                {
                    participants.add(p.getParticipant());
                }
            }
        }
        return participants;

    }

    @Column(name = "type")
    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    @Column(name = "other_info")
    public String getOtherInfo()
    {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo)
    {
        this.otherInfo = otherInfo;
    }

    @Column(name = "addressLine1")
    public String getAddressLine1()
    {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1)
    {
        this.addressLine1 = addressLine1;
    }

    @Column(name = "addressLine2")
    public String getAddressLine2()
    {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2)
    {
        this.addressLine2 = addressLine2;
    }

    @Column(name = "addressLine3")
    public String getAddressLine3()
    {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3)
    {
        this.addressLine3 = addressLine3;
    }

    @Column(name = "addressLine4")
    public String getAddressLine4()
    {
        return addressLine4;
    }

    public void setAddressLine4(String addressLine4)
    {
        this.addressLine4 = addressLine4;
    }

    @Column(name = "addressLine5")
    public String getAddressLine5()
    {
        return addressLine5;
    }

    public void setAddressLine5(String addressLine5)
    {
        this.addressLine5 = addressLine5;
    }

    @Column(name = "cityName")
    public String getCityName()
    {
        return cityName;
    }

    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }

    @Column(name = "postalCode")
    public String getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    @Column(name = "stateProvinceCode")
    public String getStateProvinceCode()
    {
        return stateProvinceCode;
    }

    public void setStateProvinceCode(String stateProvinceCode)
    {
        this.stateProvinceCode = stateProvinceCode;
    }

    @Column(name = "countryCode")
    public String getCountryCode()
    {
        return countryCode;
    }

    public void setCountryCode(String countryCode)
    {
        this.countryCode = countryCode;
    }

    @Column(name = "countyCode")
    public String getCountyCode()
    {
        return countyCode;
    }

    public void setCountyCode(String countyCode)
    {
        this.countyCode = countyCode;
    }

    @Column(name = "isPrivateAddress")
    public boolean isPrivateAddress()
    {
        return isPrivateAddress;
    }

    public void setPrivateAddress(boolean isPrivateAddress)
    {
        this.isPrivateAddress = isPrivateAddress;
    }

    @Column(name = "isDefault")
    public boolean isDefault()
    {
        return isDefault;
    }

    public void setDefault(boolean isDefault)
    {
        this.isDefault = isDefault;
    }

}
