package ug.code.gameofthroneshouses.app

import retrofit2.Call
import retrofit2.http.GET
import ug.code.gameofthroneshouses.model.House

interface RetrofitAPI {
    // as we are making get request
    // so we are displaying
    // GET as annotation.
    // and inside we are passing last parameter for our url.
    @GET("houses")
    fun getHouses(): Call<ArrayList<House>?>?
}