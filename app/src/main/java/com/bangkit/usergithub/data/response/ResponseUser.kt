package com.bangkit.usergithub.data.response

import com.bangkit.usergithub.data.User
import com.google.gson.annotations.SerializedName

data class ResponseUser(

	@field:SerializedName("total_count")
	val totalCount: Int? = null,

	@field:SerializedName("incomplete_results")
	val incompleteResults: Boolean? = null,

	@field:SerializedName("items")
	val items: List<User?>? = null
)
