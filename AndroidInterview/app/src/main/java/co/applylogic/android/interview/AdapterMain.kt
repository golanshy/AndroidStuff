package co.applylogic.android.interview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterMain(private val activity: ScrollingActivity) : RecyclerView.Adapter<MainViewHolder>() {

    private val items = ArrayList<String>()

    init {
        items.add("Example1")
        items.add("Example2")
        items.add("Example3")
        items.add("Example4")
        items.add("Example5")
        items.add("Example6")
        items.add("Example7")
        items.add("Example8")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val row = inflator.inflate(R.layout.view_row, parent, false)
        return MainViewHolder(row)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.title?.text = getTitle(position)
        holder.image?.setImageResource(getImageResource(position))
        holder.frame?.setOnClickListener { activity.onItemSelected(position) }
    }

    private fun getImageResource(position: Int): Int {
        return if (position % 2 == 0) R.drawable.black_rabbit else R.drawable.white_rabbit
    }

    private fun getTitle(position: Int): String? {
        return items[position]
    }
}

class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var frame: View? = view.findViewById(R.id.frame)
    var title: TextView? = view.findViewById(R.id.title)
    var image: ImageView? = view.findViewById(R.id.image)
}