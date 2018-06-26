package com.example.shaileshlobo.assignmentapp.data.models

/**
 * Created by spl on 20/6/18.
 */
data class UserModel(val id:Long,
                     val name:String,
                     val email: String,
                     val address: AddressModel,
                     var isSelected: Boolean = false)