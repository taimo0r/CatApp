package com.taimoor.catapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil.load
import com.taimoor.catapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnFetchCat.setOnClickListener {
            fetchRandomCat()
        }

    }

    private fun fetchRandomCat() {


        RetrofitClient.instance.getRandomCatImage().enqueue(object : Callback<List<CatImageResponse>> {
            override fun onResponse(
                call: Call<List<CatImageResponse>>,
                response: Response<List<CatImageResponse>>
            ) {
                if (response.isSuccessful && response.body()?.isNotEmpty() == true) {
                    val catImageUrl = response.body()!![0].url
                    catImageUrl?.let {
                        binding.imgCat.load(it) {
                            crossfade(true)
                            placeholder(R.drawable.ic_launcher_foreground)
                            error(R.drawable.ic_launcher_background)
                        }
                    }
                } else {
                    Toast.makeText(this@MainActivity, "No cat image found!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<CatImageResponse>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed to load cat image: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}