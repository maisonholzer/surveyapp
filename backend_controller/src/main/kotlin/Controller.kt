/** This is a demonstration of converting objects to JSON
and converting objects to JSON: that is, we serialize
objects into JSON strings, and de-serialize JSON strings
into objects **/

/* Switched to fit username/password */

package edu.uiowa.cs

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken



// two classes used for this demonstration program
class User1 {
    val username:String
    val password:String
    val surveys:List<String>?
    constructor(username:String,password:String,surveys:List<String>?) {
        this.username = username; this.password = password; this.surveys = surveys
    }
    constructor() {
        this.username = ""; this.password = ""; this.surveys = null
    }
    override fun toString() = "player(username=$username,password=$password)"
}
class AllUsers {
    val username: String;
    val new: Boolean;
    val users: List<User>

    constructor(username: String, new: Boolean, users: List<User>) {
        this.username = username; this.new = new; this.users = users
    }
    constructor() {
        this.username = ""; this.new = false; this.users = listOf<User>()
    }
    override fun toString(): String {
        var r = "team(username=$username,new=$new,users=["
        for (p in users) {
            r += p.toString() + ","
        }
        return r + "])"
    }
}

fun JAXteamToJson():String {
    val olaf = User("Olaf","123")//listOf("survey1"))
    val sven = User("Sven","78922")//listOf("survey1", "survey2"))
    val bears = AllUsers("Bears",true,listOf(olaf,sven))
    // using Jackson 2 library for object to Json
    val mapper = ObjectMapper()
    val bearsString = mapper.writeValueAsString(bears)
    println("Bears object in Jackson JSON is $bearsString")
    return bearsString
}

fun JAXjsonToTeam(jsonstring: String): AllUsers {
    val mapper = ObjectMapper()
    val what = mapper.readValue(jsonstring,AllUsers::class.java)
    println("Result of decoding in Jackson: $what")
    return what as AllUsers
}

fun GteamToJSON():String {
    val gufi = User("Gufi","hey")
    val gita = User("Gita","hello")
    val zinc = AllUsers("Zinc",true,listOf(gufi,gita))
    // using Gson library for object to Json
    val mapper = Gson()
    val zincString = mapper.toJson(zinc)
    println("Zinc object in Gson JSON is $zincString")
    return zincString
}

fun GjsonToTeam(jsonstring:String): AllUsers {
    val mapper = Gson()
    val conversionType = object:TypeToken<AllUsers>(){ }.type
    val converted:AllUsers = mapper.fromJson(jsonstring,conversionType)
    println("Result of decoding in Gson is $converted")
    return converted
}

fun main(args: Array<String>) {
    // demonstrate Jackson 2 conversions (see printed output when run)
    JAXjsonToTeam(JAXteamToJson())
    GjsonToTeam(GteamToJSON())
}

