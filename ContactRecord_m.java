package Model;

public class ContactRecord_m {
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String emailAddress;
	private String company;
	private String notes;
	
	
	public ContactRecord_m() {}
	public ContactRecord_m(String contactID, String firstName, String lastName, String phoneNumber, String emailAddress, String company, String Notes) {}
	
		private String contactID;
		public String getContactID() {
			return contactID;
		}
		public void setContactID(String contactID) {
			this.contactID = contactID;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public String getEmailAddress() {
			return emailAddress;
		}
		public void setEmailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
		}
		public String getCompany() {
			return company;
		}
		public void setCompany(String company) {
			this.company = company;
		}
		public String getNotes() {
			return notes;
		}
		public void setNotes(String notes) {
			this.notes = notes;
		}
		
		
	}
		