package co.applylogic.android.interview.examples.example1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import co.applylogic.android.interview.R

import kotlinx.android.synthetic.main.activity_example1.*

class Example1Activity : AppCompatActivity() {

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, Example1Activity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example1)
        setSupportActionBar(toolbar)
    }

}
