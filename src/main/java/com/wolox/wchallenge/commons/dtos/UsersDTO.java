package com.wolox.wchallenge.commons.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UsersDTO {

	private String id;
	private String name;
	private String username;
	private String email;
	private UsersAddressDTO address;
	private String phone;
	private String website;
	private UsersCompanyDTO company;
	
	@Data @NoArgsConstructor @AllArgsConstructor @ToString @Builder
	public static class UsersAddressDTO {
		private String street;
		private String suite;
		private String city;
		private String zipcode;
		private UsersAddressGeoDTO geo;
		
		@Data @NoArgsConstructor @AllArgsConstructor @ToString @Builder
		public static class UsersAddressGeoDTO {
			private String lat;
			private String lng;
		}
	}
	
	@Data @NoArgsConstructor @AllArgsConstructor @ToString @Builder
	public static class UsersCompanyDTO {
		private String name;
		private String catchPhrase;
		private String bs;
	}

}
