package com.example.shaileshlobo.assignmentapp.utils

import com.example.shaileshlobo.assignmentapp.data.models.AddressModel

/**
 * Created by spl on 20/6/18.
 */
object AppSpecificUtils{

    const val DOT = ".";
    const val apiKey: String ="pk" + DOT +
            "eyJ1Ijoic3Bsb2JvNDgiLCJhIjoiY2ppb3p0NXJ3MHBqZjNrcG83anE4dDkxMyJ9" +
            DOT +"NSpe_jqvuNGxRa4j1thJBg";
    const val SPLASH_TIME_OUT = 1000L
    const val COMMA = ", ";
    const val PINCODE_SEPARTOR = "-";

    fun getCompleteAddress(addr: AddressModel): String{
        val sb = StringBuilder();


        if (!addr.street.isEmpty()){
            sb.append(addr.street)
            sb.append(COMMA)
        }

        if (!addr.suite.isEmpty()){
            sb.append(addr.suite)
            sb.append(COMMA)
        }

        if (!addr.city.isEmpty()){
            sb.append(addr.city)
        }

        if (!addr.zipcode.isEmpty()){
            sb.append(PINCODE_SEPARTOR)
            sb.append(addr.zipcode)
        }
        return sb.toString();
    }

}