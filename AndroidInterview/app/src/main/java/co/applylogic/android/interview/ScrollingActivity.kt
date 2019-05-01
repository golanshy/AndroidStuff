package co.applylogic.android.interview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import co.applylogic.android.interview.examples.example1.Example1Activity
import co.applylogic.android.interview.examples.example2.Example2Activity
import co.applylogic.android.interview.examples.example3.Example3Activity
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.content_scrolling.*

class ScrollingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)

        recyclerView.adapter = AdapterMain(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun onItemSelected(position: Int) {
        when (position) {
            0 -> {
                startActivity(Example1Activity.newIntent(this))
            }
            1 -> {
                startActivity(Example2Activity.newIntent(this))
            }
            2 -> {
                startActivity(Example3Activity.newIntent(this))
            }
            3 -> {
                //startActivity(Example4Activity.newIntent(this))
            }
            4 -> {
                //startActivity(Example5Activity.newIntent(this))
            }
            5 -> {
                //startActivity(Example6Activity.newIntent(this))
            }
            6 -> {
                //startActivity(Example7Activity.newIntent(this))
            }
            7 -> {
                //startActivity(Example8Activity.newIntent(this))
            }
        }
    }
}
