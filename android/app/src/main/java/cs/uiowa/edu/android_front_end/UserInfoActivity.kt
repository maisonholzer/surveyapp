package cs.uiowa.edu.android_front_end

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class UserInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        //val editTextUsername: EditText = findViewById<EditText>(R.id.etUsername)
        //val editTextPassword: EditText = findViewById<EditText>(R.id.etPassword)

        val btnOpenActivity: Button = findViewById<Button>(R.id.button3)
        btnOpenActivity.setOnClickListener{
            val intent = Intent(this,SurveySelectActivity :: class.java )
            startActivity(intent)
        }
    }
}
