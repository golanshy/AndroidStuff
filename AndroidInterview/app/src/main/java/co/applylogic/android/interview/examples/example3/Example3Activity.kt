package co.applylogic.android.interview.examples.example3

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import co.applylogic.android.interview.R

import kotlinx.android.synthetic.main.activity_example3.*
import kotlinx.android.synthetic.main.content_example3.*
import java.lang.ref.WeakReference

class Example3Activity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, Example3Activity::class.java)
        }
    }

    private lateinit var mMessagesReceiver: MessageReceiverListener

    private inner class MessageReceiverListener : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.getIntExtra("ACTION", 0)
            val value = intent.getStringExtra("VALUE")
            val msg = Message()
            msg.what = action
            val b = Bundle()
            b.putString("VALUE", value)
            msg.data = b
            messagesDisplayHandler.sendMessage(msg)
        }
    }

    private val messagesDisplayHandler = MyHandler(this)

    private val TAG: String = "Example3Activity"

    private class MyHandler internal constructor(activity: Example3Activity) : Handler() {
        private val mActivity: WeakReference<Example3Activity> = WeakReference(activity)

        override fun handleMessage(msg: Message) {
            val activity = mActivity.get()
            val b = msg.data
            val value: String? = b.getString("VALUE")
            activity?.progressBar?.visibility = View.GONE
            when (msg.what) {
                MyIntentService.ACTION_FOO_INT -> {
                    Log.d(activity?.TAG, "ACTION_FOO_INT value: $value")
                }
                MyIntentService.ACTION_BAR_INT -> {
                    Log.d(activity?.TAG, "ACTION_BAR_INT value: $value")
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example3)
        setSupportActionBar(toolbar)

        progressBar.visibility = View.GONE

        val baseURL = "https://api.themoviedb.org/"
        val apiKey = "6529fbcde4ba3050af3b976c183a6f84"

        button1.setOnClickListener(View.OnClickListener {
            progressBar.visibility = View.VISIBLE
            MyIntentService.startActionFoo(this, baseURL, apiKey)
        })

        button2.setOnClickListener(View.OnClickListener {
            progressBar.visibility = View.VISIBLE
            MyIntentService.startActionBar(this, baseURL, apiKey)
        })
    }

    override fun onResume() {
        super.onResume()
        mMessagesReceiver = MessageReceiverListener()
        LocalBroadcastManager.getInstance(this).registerReceiver(
            mMessagesReceiver,
            IntentFilter(MyIntentService.INTENT_FILTER)
        )
    }

    override fun onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(
            mMessagesReceiver
        )
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
