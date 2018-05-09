package cs.uiowa.edu.android_front_end

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.request.JsonObjectRequest
import com.android.volley.toolbox.VolleyTickle
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import controller.*
import org.json.JSONObject


val surveyList: MutableMap<String, survey> = HashMap()
val questionList: MutableMap<String, question> = HashMap()
val person: MutableMap<String, String> = HashMap()

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

    fun getUser(P: MainActivity,name:String,pass:String): Any? {
        // change this URL to have the IP address of your machine
        // and make sure netdemo is running edu.uiowa.cs.NettyServer
        // in another shell before trying this
        val url = "http://192.168.0.2:8080/users/" + name
        val t = jsonop(P, JSONObject(), url)  // sending an empty JSON object
        //val default = Admin()
        if (t == null) {
            return null
        }//(name+" is not an registered user")}
        if (t?.length() == 3){
            Log.v("NetAccess:","this is a Client")
            if (t["pass"].toString() == pass ) {
                val mapper = Gson()
                val conversionType = object : TypeToken<Client>() {}.type
                val gotperson: Client = mapper.fromJson(t.toString(), conversionType)
                //val gotperson = Admin(t["username"] as String,
                // t["pass"] as String)
                Log.v("NetAccess:", "username is " + gotperson.username)
                Log.v("NetAccess:", "password is " + gotperson.pass)
                //if (gotperson is Admin){Log.v("netAccess","this user is Admin")}
                //if (gotperson !is Client){Log.v("netAccess","this user is Admin")}
                Log.v("NetAccess:", "survey is " + gotperson.surveys)
                return gotperson//t["pass"]as String
            }else{
                Log.v("NetAccess:","password is not correct")
                return false
            }

        }
        else {
            Log.v("NetAccess:", "this is a Admin")
            if(t["pass"].toString() == pass) {
                val mapper = Gson()
                val conversionType = object : TypeToken<Admin>() {}.type
                val gotperson: Admin = mapper.fromJson(t.toString(), conversionType)
                //val gotperson = Admin(t["username"] as String,
                // t["pass"] as String)
                Log.v("NetAccess:", "username is " + gotperson.username)
                Log.v("NetAccess:", "password is " + gotperson.pass)
                //if (gotperson is Admin){Log.v("netAccess","this user is Admin")}
                //if (gotperson !is Client){Log.v("netAccess","this user is Admin")}
                //Log.v("NetAccess:", "survey is " + gotperson.surveys)
                return gotperson//t["pass"]as String
            }
            else{
                Log.v("NetAccess:","password is not correct")
                return false
            }
        }


    }

    fun jsonop2(P: SignUpActivity, payload: JSONObject, url: String): JSONObject? {
        // see https://github.com/DWorkS/VolleyPlus for documentation on VolleyTickle
        val mRequestTickle = VolleyTickle.newRequestTickle(P as Context)
        val sendJson = JSONObject()
        val stringRequest= JsonObjectRequest(Request.Method.POST, url, sendJson,
                Response.Listener { },
                Response.ErrorListener { })
        mRequestTickle.add(stringRequest)
        val response = mRequestTickle.start()
        Log.v("statusCode:",response.statusCode.toString())
        if (response.statusCode == 200) {
            val data = VolleyTickle.parseResponse(response)
            Log.v("data: ", data)
            val fields = JSONObject(data)
            Log.v("fields:",fields.toString())
            return fields
        }
        return null
    }

    fun createClient(P: SignUpActivity,n:String,p: String):Response<Any> {

        val newUser = User(n,p)
        val url = "http://192.168.0.2:8080/users/create"
        val t = jsonop2(P, JSONObject(), url)
        val mapper = ObjectMapper()
        val jsonString = mapper.writeValueAsString(newUser)
        val mapper2 = Gson()
        val conversionType2 = object : TypeToken<Response<Any>>() {}.type
        val resp: Response<Any> = mapper2.fromJson(t.toString(), conversionType2)

        return resp
    }



    }
