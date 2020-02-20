package stelmaszyk.task2

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class Connector(val context: Context,val startProgressView:() -> Unit,val stopProgressView:() -> Unit, val showError:() -> Unit) {

    val TAG = "doGetRequestTAG"

    private var amountRequestQuestion = 0
    private val requestQueue= Volley.newRequestQueue(context)

    fun getRequestVolley(url : String, initElements:(JSONObject) -> Unit) {
        val req = StringRequest(Request.Method.GET, url,
            Response.Listener { response ->
                amountRequestQuestion--
                stopProgress()
                initElements(JSONObject(response))

            }, Response.ErrorListener {
                amountRequestQuestion--
                stopProgress()
                showError()

            })
        requestQueue.add(req)
        amountRequestQuestion++
        startProgress()
    }

    fun startProgress(){
        if(amountRequestQuestion > 0){
            startProgressView()
        }
    }

    fun stopProgress(){
        if(amountRequestQuestion <= 0){
            stopProgressView()
        }
    }

    fun cancelOperation(){
        requestQueue?.cancelAll(TAG)
    }

    companion object {

        private var INSTANCE: Connector? = null

        /**
         * Returns the single instance of this class, creating it if necessary.
         * @param tasksRemoteDataSource the backend data source
         * *
         * @param tasksLocalDataSource  the device storage data source
         * *
         * @return the [TasksRepository] instance
         */
        @JvmStatic fun getInstance(context: Context, startProgressView:() -> Unit, stopProgressView:() -> Unit, showError:() -> Unit): Connector {
            return INSTANCE ?: Connector(context, startProgressView, stopProgressView, showError)
                .apply { INSTANCE = this }
        }

        /**
         * Used to force [getInstance] to create a new instance
         * next time it's called.
         */
        @JvmStatic fun destroyInstance() {
            INSTANCE = null
        }
    }
}
