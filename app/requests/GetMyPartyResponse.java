package requests;

import java.util.ArrayList;

public class GetMyPartyResponse
{
    ArrayList<StartPartyResponse> myParties;

    public ArrayList<StartPartyResponse> getMyParties()
    {
        return myParties;
    }

    public void setMyParties(ArrayList<StartPartyResponse> myParties)
    {
        this.myParties = myParties;
    }
}
