package co.applylogic.android.interview.examples.example3

import android.app.IntentService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.Context
import android.os.Build
import androidx.annotation.NonNull
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import co.applylogic.android.interview.R
import co.applylogic.android.interview.examples.example3.api.ApiClient
import co.applylogic.android.interview.examples.example3.api.ApiInterface
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ACTION_FOO = "co.applylogic.android.interview.examples.example3.action.FOO"
private const val ACTION_BAR = "co.applylogic.android.interview.examples.example3.action.BAR"
private const val EXTRA_PARAM1 = "co.applylogic.android.interview.examples.example3.extra.PARAM1"
private const val EXTRA_PARAM2 = "co.applylogic.android.interview.examples.example3.extra.PARAM2"

class MyIntentService : IntentService("MyIntentService") {

    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val CHANNEL_ID = "my_channel_01"
            val channel = NotificationChannel(
                CHANNEL_ID,
                "iRiS Content update",
                NotificationManager.IMPORTANCE_LOW
            )

            channel.setSound(null, null)
            channel.enableLights(false)
            channel.enableVibration(false)

            try {
                (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
                    channel
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }

            val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Content update Service")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setOnlyAlertOnce(true)
                .setContentText("Content update in progress...").build()

            startForeground(1, notification)
        }
    }

    override fun onHandleIntent(intent: Intent?) {
        when (intent?.action) {
            ACTION_FOO -> {
                val param1 = intent.getStringExtra(EXTRA_PARAM1)
                val param2 = intent.getStringExtra(EXTRA_PARAM2)
                handleActionFoo(param1, param2)
            }
            ACTION_BAR -> {
                val param1 = intent.getStringExtra(EXTRA_PARAM1)
                val param2 = intent.getStringExtra(EXTRA_PARAM2)
                handleActionBar(param1, param2)
            }
        }
    }

    private fun handleActionFoo(baseURL: String, apiKey: String) {

        val client = ApiClient(this).getClient(baseURL)
        val apiService = client?.create<ApiInterface>(ApiInterface::class.java)
        //val call = apiService?.getPopular(apiKey)
        val call = apiService?.getPopularMovies(apiKey, 1)
        val response = call?.execute()
        if (response?.body() != null) {
            broadcastIntent(INTENT_FILTER, ACTION_FOO_INT, "SUCCESS")
        } else if (response?.errorBody() != null) {
            broadcastIntent(INTENT_FILTER, ACTION_FOO_INT, "API ERROR")
        }
    }

    private fun handleActionBar(baseURL: String, apiKey: String) {

        val client = ApiClient(this).getClient(baseURL)
        val apiService = client?.create<ApiInterface>(ApiInterface::class.java)
        //val call = apiService?.getPopular(apiKey)
        val call = apiService?.getPopularMovies(apiKey, 1)
        call?.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(@NonNull call: Call<ResponseBody>, @NonNull response: Response<ResponseBody>) {
                if (response.body() != null) {
                    broadcastIntent(INTENT_FILTER, ACTION_BAR_INT, "SUCCESS")
                } else if (response.errorBody() != null) {
                    broadcastIntent(INTENT_FILTER, ACTION_BAR_INT, "API ERROR")
                }
            }

            override fun onFailure(@NonNull call: Call<ResponseBody>, @NonNull t: Throwable) {
                broadcastIntent(INTENT_FILTER, ACTION_BAR_INT, "ERROR")
            }
        })
    }

    protected fun broadcastIntent(intentType: String, action: Int, value: String) {
        val intent = Intent(intentType)
        intent.putExtra("ACTION", action)
        intent.putExtra("VALUE", value)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }


    companion object {
        const val INTENT_FILTER: String = "co.applylogic.android.interview.examples.example3.action"
        const val ACTION_FOO_INT = 7000
        const val ACTION_BAR_INT = 7001

        @JvmStatic
        fun startActionFoo(context: Context, param1: String, param2: String) {
            val intent = Intent(context, MyIntentService::class.java).apply {
                action = ACTION_FOO
                putExtra(EXTRA_PARAM1, param1)
                putExtra(EXTRA_PARAM2, param2)
            }
            context.startService(intent)
        }

        @JvmStatic
        fun startActionBar(context: Context, param1: String, param2: String) {
            val intent = Intent(context, MyIntentService::class.java).apply {
                action = ACTION_BAR
                putExtra(EXTRA_PARAM1, param1)
                putExtra(EXTRA_PARAM2, param2)
            }
            context.startService(intent)
        }
    }
}
