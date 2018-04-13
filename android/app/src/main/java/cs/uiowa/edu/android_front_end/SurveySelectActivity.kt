package cs.uiowa.edu.android_front_end

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SurveySelectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_select)

        val btnOpenActivity: Button = findViewById(R.id.button8)
        btnOpenActivity.setOnClickListener{
            val intent = Intent(this,Survey1Activity :: class.java )
            startActivity(intent)
        }
    }
}