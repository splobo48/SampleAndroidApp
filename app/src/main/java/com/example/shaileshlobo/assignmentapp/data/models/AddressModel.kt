package com.example.shaileshlobo.assignmentapp.data.models

import android.location.Location

/**
 * Created by spl on 20/6/18.
 */
data class AddressModel(val street: String,
                        val suite: String,
                        val city: String,
                        val zipcode : String,
                        val geo: GeoModel)