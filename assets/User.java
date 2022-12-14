package com.myspace.userstore;

/**
 * This class was automatically generated by the data modeler tool.
 */

public class User implements java.io.Serializable {

	static final long serialVersionUID = 1L;

	@org.kie.api.definition.type.Label("The name of the user")
	private java.lang.String userName;
	private java.lang.Integer age;

	private java.lang.Boolean canPurchase;

	private java.lang.Boolean status;

	public User() {
	}

	public java.lang.String getUserName() {
		return this.userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.Integer getAge() {
		return this.age;
	}

	public void setAge(java.lang.Integer age) {
		this.age = age;
	}

	public java.lang.Boolean getCanPurchase() {
		return this.canPurchase;
	}

	public void setCanPurchase(java.lang.Boolean canPurchase) {
		this.canPurchase = canPurchase;
	}

	public java.lang.Boolean getStatus() {
		return this.status;
	}

	public void setStatus(java.lang.Boolean status) {
		this.status = status;
	}

	public User(java.lang.String userName, java.lang.Integer age,
			java.lang.Boolean canPurchase, java.lang.Boolean status) {
		this.userName = userName;
		this.age = age;
		this.canPurchase = canPurchase;
		this.status = status;
	}

}
