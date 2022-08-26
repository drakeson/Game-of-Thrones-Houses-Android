package ug.code.gameofthroneshouses.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ug.code.gameofthroneshouses.R
import ug.code.gameofthroneshouses.adapter.HouseAdapter
import ug.code.gameofthroneshouses.app.RetrofitAPI
import ug.code.gameofthroneshouses.databinding.ActivityMainBinding
import ug.code.gameofthroneshouses.model.House

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var courseRV: RecyclerView
    lateinit var loadingPB: ProgressBar
    lateinit var houseAdapter: HouseAdapter
    lateinit var houseList: ArrayList<House>
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        courseRV = findViewById(R.id.idRVCourses)
        loadingPB = findViewById(R.id.idPBLoading)

        houseList = ArrayList()
        getAllgetHouses()

    }

    private fun getAllgetHouses() {
        // on below line we are creating a retrofit
        // builder and passing our base url
        val retrofit = Retrofit.Builder()
            .baseUrl("https://anapioficeandfire.com/api/")
            // on below line we are calling add
            // Converter factory as Gson converter factory.
            // at last we are building our retrofit builder.
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // below line is to create an instance for our retrofit api class.
        val retrofitAPI = retrofit.create(RetrofitAPI::class.java)

        // on below line we are calling a method to get all the courses from API.
        val call: Call<ArrayList<House>?>? = retrofitAPI.getHouses()

        // on below line we are calling method to enqueue and calling
        // all the data from array list.
        call!!.enqueue(object : Callback<ArrayList<House>?> {
            override fun onResponse(
                call: Call<ArrayList<House>?>,
                response: Response<ArrayList<House>?>
            ) {
                if (response.isSuccessful) {
                    loadingPB.visibility = View.GONE
                    houseList = response.body()!!
                }

                // on below line we are initializing our adapter.
                houseAdapter = HouseAdapter(houseList)

                // on below line we are setting adapter to recycler view.
                courseRV.adapter = houseAdapter

            }

            override fun onFailure(call: Call<ArrayList<House>?>, t: Throwable) {
                // displaying an error message in toast
                Toast.makeText(this@MainActivity, "Fail to get the data..", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }


}