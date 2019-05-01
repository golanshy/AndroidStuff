package co.applylogic.android.interview.examples.example2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import co.applylogic.android.interview.R

class AdapterExample2(private val activity: Example2Activity) : RecyclerView.Adapter<MainViewHolder>() {

    private val items = ArrayList<Rabbit>()

    init {
        items.add(Rabbit(false))
        items.add(Rabbit(true))
        items.add(Rabbit(false))
        items.add(Rabbit(true))
        items.add(Rabbit(false))
        items.add(Rabbit(true))
        items.add(Rabbit(false))
        items.add(Rabbit(true))
        items.add(Rabbit(false))
        items.add(Rabbit(true))
        items.add(Rabbit(false))
        items.add(Rabbit(true))
        items.add(Rabbit(false))
        items.add(Rabbit(true))
        items.add(Rabbit(false))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val row = inflator.inflate(R.layout.image_view_row, parent, false)
        return MainViewHolder(row)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        if (getItem(position)?.isBlack!!)
            holder.image?.setImageResource(R.drawable.black_rabbit)
    }

    private fun getItem(position: Int): Rabbit? {
        return items[position]
    }
}

class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var image: ImageView? = view.findViewById(R.id.image)
}

class Rabbit(val isBlack: Boolean) {
}