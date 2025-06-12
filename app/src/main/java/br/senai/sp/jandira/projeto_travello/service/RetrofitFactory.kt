package br.senai.sp.jandira.projeto_travello.service



import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private val BASE_URL = "http://10.107.144.21:8080/v1/travello/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getUserService(): UserService {
        return retrofit.create(UserService::class.java)
    }
    fun getLocationService(): LocationService {
        return retrofit.create(LocationService::class.java)
    }

    fun getTripService(): TripService {
        return retrofit.create(TripService::class.java)
    }


}