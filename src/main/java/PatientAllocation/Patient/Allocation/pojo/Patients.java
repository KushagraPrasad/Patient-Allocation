package PatientAllocation.Patient.Allocation.pojo;

public class Patients {
	private long id;
	private String name;
	private String city;
	private String email;
	private String phoneNumber;
	private String symptom;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public Patients(long id, String name, String city, String email, String phoneNumber, String symptom) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.symptom = symptom;
	}

	public Patients() {

	}

	@Override
	public String toString() {
		return "Patients [id=" + id + ", name=" + name + ", city=" + city + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", symptom=" + symptom + "]";
	}

}
