package cs.uiowa.edu.android_front_end

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SurveyInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_info)

        val btnOpenActivity: Button = findViewById(R.id.button2)
        btnOpenActivity.setOnClickListener{
            val intent = Intent(this,UserInfoActivity :: class.java )
            startActivity(intent)
        }
    }
}
