package bean;



public class USER_property {
	  private String email;
	  private String fullname;
	  private String birthday;
	  private String address;
	  private String password;
	  private String NIC;
	  private int PM;

	  public String get_email() {return email;}
	  public void set_email(String email) {this.email = email;}
	  
	  public String get_name(){return fullname;}	
	  public void set_name(String fullname){this.fullname=fullname;}
	  
	  public String get_birthday() {return birthday;}
	  public void set_birthday(String birthday) {this.birthday = birthday;}
	  
	  public String get_address() {return address;}
	  public void set_address(String address) {this.address = address;}
	  
	  public String get_password() {return password;}
	  public void set_password(String password) {this.password = password;}
	  
	  public String get_NIC() {return NIC;}
	  
	  
	  public void set_NIC(String NIC) {this.NIC = NIC;}
	  
  public int get_PM() {return PM;}
	  
	  
	  public void set_PM(int PM) {this.PM = PM;}
	  public USER_property() {}
	  public USER_property(String email,String password,String NIC,int PM,String fullname) {
		  super();
		  this.email=email;
		  this.password=password;
		  this.NIC=NIC;
		  this.PM=PM;
		  this.fullname=fullname;
	  }
	  
	  public USER_property(String email,String password,String fullname,String birthday,String address,String NIC) 
	  {
	this.email=email;
	  this.fullname=fullname;
	  this.birthday=birthday;
	  this.address=address;
	  this.password=password;
	  this.NIC=NIC;}
	  
	  
}
