package cs.uiowa.edu.android_front_end

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.android.volley.Response
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject
import java.time.Year

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val editTextYear:EditText = findViewById<EditText>(R.id.etYear)
        val editTextName:EditText = findViewById<EditText>(R.id.etName)
        val editTextUsername:EditText = findViewById<EditText>(R.id.etUsername)
        val editTextPassword:EditText = findViewById<EditText>(R.id.etPassword)

        val bSignUpActivity: Button = findViewById<Button>(R.id.bSignUp)
        bSignUpActivity.setOnClickListener{
            val Name:String = etName.text.toString()
            val Username:String = etUsername.text.toString()
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
