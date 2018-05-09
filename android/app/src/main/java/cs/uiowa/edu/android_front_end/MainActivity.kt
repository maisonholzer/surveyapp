package cs.uiowa.edu.android_front_end

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import cs.uiowa.edu.android_front_end.NetAccess.getUser
import kotlinx.android.synthetic.main.activity_main.*

//import cs.uiowa.edu.android_front_end.NetAccess.getAllSurveys

// Log.v(tag,msg) for Verbose Level
// Log.d(tag,msg) for Debug Level
// Log.i(tag,msg) for Information Level
// Log.w(tag,msg) for Warning level
// Log.e(tag,msg) for Error level
// another kind of method: wtf(tag,msg)

class MainActivity : AppCompatActivity() {
    val tag = "CS2820"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        setContentView(R.layout.activity_main)
        Log.d(tag,"*** onCreate ***")

        /*val radnames = listOf(R.id.radioButton1,R.id.radioButton2,R.id.radioButton3,R.id.radioButton4)
        val radarray = Array<RadioButton>(5) {it -> findViewById(radnames.get(it))}
        // initially make all radio buttons invisible
        val A = getAllSurveys(this)
        // nice trick: https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/with-index.html
        for ((ind,title) in A.surveymap.values.iterator().withIndex()) {
            radarray[ind].text = title
            radarray[ind].setVisibility(View.VISIBLE)
        }*/

        val editTextUsername: EditText = findViewById<EditText>(R.id.etUsername)
        var editTextPassword: EditText = findViewById<EditText>(R.id.etPassword)
        var login : CardView = findViewById(R.id.cardViewLogin)

        val LoginActivity: CardView = findViewById<CardView>(R.id.cardViewLogin)
        LoginActivity.setOnClickListener{
            Log.v("MainActivity", "Typed username "+ editTextUsername.text.toString())
            var pass = true
            val A = getUser(this,editTextUsername.text.toString())

            //Log.v("MainActivity:", "User is")
            if (A == null){//(editTextUsername.text.toString() + " is not an registered user")){
                //pass = false
                //login.isEnabled = false
                NoUser.text = editTextUsername.text.toString() + " is not an registered user"//A.toString()
                Log.v("MainActivity","Log in fail, can't find this name in both admin and user")
            }
            else{
                NoUser.text = " Log In Success "
                Log.v("MainActivity","Log in success")
                val intent = Intent(this,UserInfoActivity :: class.java )
                startActivity(intent)}





        }
        val SignupLink : TextView = findViewById<TextView>(R.id.tvSignUpHere)

        SignupLink.setOnClickListener{
            val intent = Intent(this,SignUpActivity :: class.java )
            Log.v(tag,"Go to Sign Up Click")
            startActivity(intent)
        }

    }



    // others to override: onPostCreate, onRestart, onStart, onResume,
    // onPostResume, onPause, onStop, onDestroy
    override fun onStart() {
        super.onStart()
        Log.v(tag,"*** onStart ***")
    }

    // handler for button press
    fun sendMessage(view: View) {
        Log.v(tag,"*** button press ***")
    }
}
