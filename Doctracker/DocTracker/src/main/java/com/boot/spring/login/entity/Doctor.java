package com.boot.spring.login.entity;



	import javax.persistence.CascadeType;
	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.JoinColumn;
	import javax.persistence.OneToOne;
	import javax.persistence.Table;

	@Entity
	@Table(name="doctor")
	public class Doctor {
		
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private int id;
		
		@Column(name="field")
		private String field;
		@Column(name="des")
		private String des;
		
		@Column(name="lng")
		private float lng;
		@Column(name="lat")
		private float lat;
		@OneToOne(cascade=CascadeType.ALL)
		@JoinColumn(name="user_id")
		private User user;

		public String getField() {
			return field;
		}
		public void setField(String field) {
			this.field = field;
		}
		public String getDes() {
			return des;
		}
		public void setDes(String des) {
			this.des = des;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public float getLng() {
			return lng;
		}
		public void setLng(float lng) {
			this.lng = lng;
		}
		public float getLat() {
			return lat;
		}
		public void setLat(float lat) {
			this.lat = lat;
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		
		public String getfName() {
			return user.getFirstName();
		}
		
		public String getlName() {
			return user.getLastName();
		}
		
		
		public Doctor() {
			
		}
		


}
