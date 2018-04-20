package cs.uiowa.edu.android_front_end

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton

class SurveySelectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_select)

        val RadioActivity1: RadioButton = findViewById<RadioButton>(R.id.radioButton1)
        RadioActivity1.setOnClickListener{
            val intent = Intent(this,Survey1Activity :: class.java )
            startActivity(intent)
        }
        val RadioActivity2: RadioButton = findViewById<RadioButton>(R.id.radioButton2)
        RadioActivity2.setOnClickListener{
            val intent = Intent(this,Survey2Activity :: class.java )
            startActivity(intent)
        }
        val RadioActivity3: RadioButton = findViewById<RadioButton>(R.id.radioButton3)
        RadioActivity3.setOnClickListener{
            val intent = Intent(this,Survey3Activity :: class.java )
            startActivity(intent)
        }
        val RadioActivity4: RadioButton = findViewById<RadioButton>(R.id.radioButton4)
        RadioActivity4.setOnClickListener{
            val intent = Intent(this,Survey1Activity :: class.java )
            startActivity(intent)
        }
    }
}
