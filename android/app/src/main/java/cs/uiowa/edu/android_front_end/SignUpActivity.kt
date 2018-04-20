package cs.uiowa.edu.android_front_end

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

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
            val intent = Intent(this,UserInfoActivity :: class.java )
            startActivity(intent)
        }

    }
}
