package co.applylogic.android.interview.examples.example2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager
import co.applylogic.android.interview.R

import kotlinx.android.synthetic.main.activity_example2.*
import kotlinx.android.synthetic.main.content_example2.*

class Example2Activity : AppCompatActivity() {

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, Example2Activity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example2)
        setSupportActionBar(toolbar)

        recyclerView.adapter = AdapterExample2(this)
        recyclerView.layoutManager = GridLayoutManager(this, 3)
    }

}
