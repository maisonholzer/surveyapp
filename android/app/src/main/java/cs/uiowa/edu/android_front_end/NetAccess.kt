package cs.uiowa.edu.android_front_end

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.request.JsonObjectRequest
import com.android.volley.toolbox.VolleyTickle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import controller.Admin
import controller.Client
import controller.question
import controller.survey
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
    /*
    fun getUser(P: MainActivity,name:String): String? {
        // change this URL to have the IP address of your machine
        // and make sure netdemo is running edu.uiowa.cs.NettyServer
        // in another shell before trying this
        val url = "http://192.168.0.2:8080/users/"+name
        val t = jsonop(P, JSONObject(), url)  // sending an empty JSON object
        if (t == null) {return (name+" is not an registered user")}

        //val gotperson = User(t["username"] as String,
        //       t["pass"] as String)
        Log.v("got person",t["pass"]as String)
        return t["pass"]as String

    }*/
    fun getUser(P: MainActivity,name:String): Any? {
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

        }
        else {
            Log.v("NetAccess:", "this is a Admin")
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
        /*
        try {

            val url = "http://192.168.0.2:8080/users/" + name
            val t = jsonop(P, JSONObject(), url)  // sending an empty JSON object
            //val default = Admin()
            if (t == null) {
                return null
            }//(name+" is not an registered user")}
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
        }catch (ex: Exception) {
            Log.v("NetAccess","user not an admin")
        }
        try {

            val url = "http://192.168.0.2:8080/users/" + name
            val t = jsonop(P, JSONObject(), url)  // sending an empty JSON object
            //val default = Admin()
            if (t == null) {
                return null
            }//(name+" is not an registered user")}
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
        }catch (ex: Exception) {
            Log.v("NetAccess","user not an Client")
        }
        return null */

    }
    /*
    fun getAllSurveys(P: MainActivity): AllSurveys {
        // change this URL to have the IP address of the Controller machine
        // and make sure Controller is running cs.uiowa.edu.Controller.NettyServer
        // in another shell before trying this
        val url = "http://192.168.0.2:8080/surveys/all"
        val t = jsonop(P,JSONObject(),url) // sending an empty JSON object
        val default = AllSurveys(mapOf<Int,String>())
        if (t == null) return null//default
        val mapper = Gson() // use Gson to convert JSONObject to Allsurveys
        val conversionType = object : TypeToken<AllSurveys>(){ }.type
        val converted: AllSurveys = mapper.fromJson(t.toString(),conversionType)
        Log.v(P.tag,converted + "server test success")
        return converted
    }*/
    fun jsonop2(P: SurveySelectActivity, payload: JSONObject, url: String): JSONObject? {
        // see https://github.com/DWorkS/VolleyPlus for documentation on VolleyTickle
        val mRequestTickle = VolleyTickle.newRequestTickle(P as Context)
        val sendJson = JSONObject()
        val stringRequest= JsonObjectRequest(Request.Method.GET, url, sendJson,
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
/*
    fun getJsonUserForLogin(P: SurveySelectActivity): Usertest {
        Log.v("Login:","Getting User")
        val url = "http://192.168.0.2:8080/users/han"
        val t = jsonop2(P,JSONObject(),url)
        val default = Usertest(mapOf<String,String>())
        Log.v("t:",t.toString())
        Log.v("length:",t?.length().toString())
        //if (t == null) return default
        val mapper = Gson() // use Gson to convert JSONObject to Allsurveys
        val conversionType = object : TypeToken<Usertest>(){ }.type
        val converted:Usertest = mapper.fromJson(t.toString(),conversionType)
        Log.v("result:",converted.toString())
        return converted
    }*/



    }
