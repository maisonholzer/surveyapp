package cs.uiowa.edu.android_front_end

import org.json.JSONObject
import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.request.JsonObjectRequest
import com.android.volley.toolbox.VolleyTickle
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import edu.uiowa.cs.Controller.AllSurveys



object NetAccess {
    fun jsonop(P: MainActivity, payload: JSONObject, url: String): JSONObject? {
        // see https://github.com/DWorkS/VolleyPlus for documentation on VolleyTickle
        val mRequestTickle = VolleyTickle.newRequestTickle(P as Context)
        val sendJson = JSONObject()
        val stringRequest= JsonObjectRequest(Request.Method.GET, url, sendJson,
                Response.Listener { },
                Response.ErrorListener { })
        mRequestTickle.add(stringRequest)
        val response = mRequestTickle.start()
        Log.v(P.tag,response.statusCode.toString())
        if (response.statusCode == 200) {
            val data = VolleyTickle.parseResponse(response)
            Log.v(P.tag, data)
            val fields = JSONObject(data)
            return fields
        }
        return null
    }

    fun getAllSurveys(P: MainActivity): AllSurveys {
        // change this URL to have the IP address of the Controller machine
        // and make sure Controller is running edu.uiowa.cs.NettyServer
        // in another shell before trying this
        val url = "http://97.127.165.220:8080/surveys/all"
        val t = jsonop(P,JSONObject(),url) // sending an empty JSON object
        val default = AllSurveys(mapOf<Int,String>())
        if (t == null) return default
        val mapper = Gson() // use Gson to convert JSONObject to Allsurveys
        val conversionType = object : TypeToken<AllSurveys>(){ }.type
        val converted:AllSurveys = mapper.fromJson(t.toString(),conversionType)
        return converted
    }
}