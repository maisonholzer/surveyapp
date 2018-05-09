package cs.uiowa.edu.android_front_end

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.widget.Button

class UserInfoActivity : AppCompatActivity() {


    //var editTextUsername2: EditText = findViewById<EditText>(R.id.etUsername2)
    //var editTextPassword2: EditText = findViewById<EditText>(R.id.etPassword2)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        //val A = NetAccess.getUser(MainActivity(), MainActivity()).toString(), editTextPassword.text.toString())
        //editTextUsername2.text = findViewById<EditText>(R.id.etUsername)

        //val editTextUsername: EditText = findViewById<EditText>(R.id.etUsername)
        //val editTextPassword: EditText = findViewById<EditText>(R.id.etPassword)

        val btnOpenActivity: Button = findViewById<Button>(R.id.button3)
        btnOpenActivity.setOnClickListener{
            val intent = Intent(this,SurveySelectActivity :: class.java )
            startActivity(intent)
        }
    }
}
