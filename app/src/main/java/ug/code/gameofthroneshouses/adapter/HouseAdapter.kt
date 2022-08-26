package ug.code.gameofthroneshouses.adapter


import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import ug.code.gameofthroneshouses.R
import ug.code.gameofthroneshouses.activities.DetailsActivity
import ug.code.gameofthroneshouses.model.House
import ug.code.gameofthroneshouses.roundImages.ShapeForm
import ug.code.gameofthroneshouses.roundImages.ShapeTextDrawable
import java.util.*

class HouseAdapter(private var courseList: ArrayList<House>) : RecyclerView.Adapter<HouseAdapter.CourseViewHolder>() {

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ): HouseAdapter.CourseViewHolder {
        // this method is use to inflate the layout file which
        // we have created for our recycler view.
        // on below line we are inflating our layout file.
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.house_item,
            parent, false
        )
        // at last we are returning our view
        // holder class with our item View File.
        return CourseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HouseAdapter.CourseViewHolder, position: Int) {
        // on below line we are setting data to our text view and our image view.
        val context: Context = holder.card.getContext()
        val house = courseList.get(position)
        holder.houseName.text = house.name
        holder.houseRegion.text = house.region
        holder.houseCoart.text = house.coatOfArms

        //Image Text
        val newName = house.name.replace("House ","")
        val rnd = Random()
        val color: Int = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        val drawable = ShapeTextDrawable(ShapeForm.ROUND, color = color, text = newName.toUpperCase().substring(IntRange(0, 1)))
        holder.houseImage.setImageDrawable(drawable)

        //Onclick
        holder.card.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("NAME", house.name)
            intent.putExtra("REGION", house.region)
            intent.putExtra("WORDS", house.words)
            intent.putExtra("COART", house.coatOfArms)
            context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        // on below line we are
        // returning our size of our list
        return courseList.size
    }

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // on below line we are initializing our views
        val houseName: TextView = itemView.findViewById(R.id.houseName)
        val houseRegion: TextView = itemView.findViewById(R.id.houseRegion)
        val houseCoart: TextView = itemView.findViewById(R.id.houseCoart)
        val houseImage: ImageView = itemView.findViewById(R.id.houseImage)
        val card: RelativeLayout = itemView.findViewById(R.id.card)

    }
}
