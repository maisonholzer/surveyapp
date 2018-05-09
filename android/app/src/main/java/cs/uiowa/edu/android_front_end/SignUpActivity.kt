package cs.uiowa.edu.android_front_end

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    val tag = "SignUpPage"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val editTextYear:EditText = findViewById<EditText>(R.id.etYear)
        val editTextName:EditText = findViewById<EditText>(R.id.etName)
        val editTextUsername:EditText = findViewById<EditText>(R.id.etUsername2)
        val editTextPassword:EditText = findViewById<EditText>(R.id.etPassword)

        val bSignUpActivity: Button = findViewById<Button>(R.id.bSignUp)
        bSignUpActivity.setOnClickListener{
            val Name:String = etName.text.toString()
            val Username:String = etUsername2.text.toString()
            val Password:String = etPassword.text.toString()
            val Year:String = etYear.text.toString()
            /*
            val responseListener = Response.Listener<String> {
                override fun onRes
                //val jsonResponse = JSONObject(response)
                //val success:Boolean = jsonResponse.getBoolean("success")
            }
            val Request = SignUpRequest(Name, Username, Password, Year, responseListener)
            */
            bSignUpActivity.setOnClickListener{
                val intent = Intent(this,MainActivity :: class.java )
                Log.v(tag," Sign Up Success! Go back to main page to log in")
                startActivity(intent)
            }
        }

        val bSignUpNextActivity: Button = findViewById<Button>(R.id.buttonSignUpNext)
        bSignUpNextActivity.setOnClickListener{
            val intent = Intent(this,UserInfoActivity :: class.java )

            startActivity(intent)
        }

    }
}
