package kamalnath.PojoPackage;

public class GetCourse{
	
	private String Url;
	private String services;
	private String expertise;
	private Courses Courses;
	private String instructor;
	private String linkedIn;
	/**
	 * @return the url
	 */
	public String getUrl() {
		return Url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		Url = url;
	}
	/**
	 * @return the services
	 */
	public String getServices() {
		return services;
	}
	/**
	 * @param services the services to set
	 */
	public void setServices(String services) {
		this.services = services;
	}
	/**
	 * @return the expertise
	 */
	public String getExpertise() {
		return expertise;
	}
	/**
	 * @param expertise the expertise to set
	 */
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	/**
	 * @return the courses
	 */
	public kamalnath.PojoPackage.Courses getCourses() {
		return Courses;
	}
	/**
	 * @param courses the courses to set
	 */
	public void setCourses(kamalnath.PojoPackage.Courses courses) {
		Courses = courses;
	}
	/**
	 * @return the instructor
	 */
	public String getInstructor() {
		return instructor;
	}
	/**
	 * @param instructor the instructor to set
	 */
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	/**
	 * @return the linkedIn
	 */
	public String getLinkedIn() {
		return linkedIn;
	}
	/**
	 * @param linkedIn the linkedIn to set
	 */
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	
	

}
