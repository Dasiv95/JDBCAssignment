package july13;

public class CustInfo {
	private String name;
	private String password;
	private String gender;
	private int age;
	private String email;
	
	
	
	@Override
	public String toString() {
		return "RegisterCustinfo [name=" + name + ", pwd=" + password + ", gender=" + gender + ", age=" + age + ", email="
				+ email + "]";
	}
	public CustInfo(String name, String pwd, String gender, int age, String email) {
		super();
		this.name = name;
		this.password = pwd;
		this.gender = gender;
		this.age = age;
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return password;
	}
	public void setPwd(String pwd) {
		this.password = pwd;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
