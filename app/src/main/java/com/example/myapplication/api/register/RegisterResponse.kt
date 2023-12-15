package com.example.myapplication.api.register

import com.example.myapplication.api.models.DefaultResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RegisterResponse: DefaultResponse(){
	@SerializedName("data")
	@Expose
	var data: RegisterResponse? = null

	class RegisterResponse {
		@SerializedName("user")
		@Expose
		var user: User? = null

		@SerializedName("userData")
		@Expose
		var userData: UserData? = null

		class User {
			@SerializedName("account_id")
			@Expose
			var accountId: String? = null

			@SerializedName("full_name")
			@Expose
			var fullName: String? = null

			@SerializedName("role")
			@Expose
			var role: String? = null

			@SerializedName("picture_profile_file")
			@Expose
			var pictureProfileFile: String? = null
		}

		class UserData(
			@SerializedName("address")
			@Expose
			val address: String? = null,

			@SerializedName("full_name")
			@Expose
			val fullName: String? = null,

			@SerializedName("province")
			@Expose
			val province: String? = null,
			
			@SerializedName("phone")
			@Expose
			val phone: String? = null,

			@SerializedName("city")
			@Expose
			val city: String? = null,

			@SerializedName("user_id")
			@Expose
			val userId: Int? = null,

			@SerializedName("id")
			@Expose
			val id: Int? = null,

			@SerializedName("picture_profile_file")
			@Expose
			val pictureProfileFile: String? = null
		)

	}
}