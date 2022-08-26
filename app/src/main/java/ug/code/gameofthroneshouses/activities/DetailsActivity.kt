package ug.code.gameofthroneshouses.activities

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import ug.code.gameofthroneshouses.R
import ug.code.gameofthroneshouses.databinding.ActivityDetailsBinding
import ug.code.gameofthroneshouses.roundImages.ShapeForm
import ug.code.gameofthroneshouses.roundImages.ShapeTextDrawable
import java.util.*

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val houseName: TextView = findViewById(R.id.houseName)
        val houseRegion: TextView = findViewById(R.id.houseRegion)
        val houseWords: TextView = findViewById(R.id.houseWords)
        val houseCoart: TextView = findViewById(R.id.houseCoart)
        val houseImage: ImageView = findViewById(R.id.houseImage)

        houseName.text = intent.getStringExtra("NAME")
        houseRegion.text = "Region: " + intent.getStringExtra("REGION")
        houseWords.text = "Words: " + intent.getStringExtra("WORDS")
        houseCoart.text = "Coat Of Arms: " + intent.getStringExtra("COART")


        val newName = intent.getStringExtra("NAME")?.replace("House ","")
        val rnd = Random()
        val color: Int = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        val drawable =
            newName?.toUpperCase()?.let {
                ShapeTextDrawable(ShapeForm.ROUND, color = color, text = it
                    .substring(IntRange(0, 1)))
            }
        houseImage.setImageDrawable(drawable)
    }

}