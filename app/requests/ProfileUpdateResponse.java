package requests;
public class ProfileUpdateResponse extends LoginResponse{
	private boolean flag;
	
	public boolean getFlag(){
		return flag;
	}
	
	public void setFlag(boolean flag){
		this.flag=flag;
	}

}
