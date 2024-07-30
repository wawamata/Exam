package bean;

import java.io.Serializable;

public class Subject implements Serializable {
	/**
	 *
	 */
	private String cd;

	/**
	 * 氏名:String
	 */
	private String name;

	/**
	 * 所属校:School
	 */
	private School school;

	/**
	 * ゲッター、セッター
	 */
	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

}
