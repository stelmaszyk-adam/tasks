package stelmaszyk.task2

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    val url = "https://postman-echo.com/get?foo1=bar1&foo2=bar2"
    val url1 = "https://jsonplaceholder.typicode.com/todos/1"
    val url2 = "https://reqres.in/api/users?page=2"
    val url3 = "https://httpbin.org/get"
    val url4 = "https://jsonplaceholder.cypress.io/todos/4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var connector = Connector(this, ::startProgressBar, ::stopProgressBar, ::showError)

        connector.getRequestVolley(url, ::initPostManView)
        connector.getRequestVolley(url1, ::initPostManView1)
        connector.getRequestVolley(url2, ::initPostManView2)
        connector.getRequestVolley(url3, ::initPostManView3)
        connector.getRequestVolley(url4, ::initPostManView4)

    }

    fun visibleText(view : Int)
    {
        arrayOf(textView0, textView1, textView2, textView3, textView4).forEach { textView -> textView.visibility = view }
    }

    fun initPostManView(jsonObject: JSONObject)
    {
        textView0.setText("Args: "+jsonObject.get("args").toString())
    }

    fun initPostManView1(jsonObject: JSONObject)
    {
        textView1.setText("Title: "+jsonObject.get("title").toString())
    }

    fun initPostManView2(jsonObject: JSONObject)
    {
        textView2.setText("Total pages: "+jsonObject.get("total_pages").toString())
    }

    fun initPostManView3(jsonObject: JSONObject)
    {
        textView3.setText("Origin: "+jsonObject.get("origin").toString())
    }

    fun initPostManView4(jsonObject: JSONObject)
    {
        textView4.setText("Completed: "+jsonObject.get("completed").toString())
    }

    fun startProgressBar()
    {
        progressBar.visibility = View.VISIBLE
        visibleText(View.GONE)
    }

    fun stopProgressBar()
    {
        progressBar.visibility = View.GONE
        Toast.makeText(this, "Finished download", Toast.LENGTH_LONG).show()
        visibleText(View.VISIBLE)
    }

    fun showError()
    {
        Toast.makeText(this, "Somthing went wrong", Toast.LENGTH_LONG).show()
    }

}
