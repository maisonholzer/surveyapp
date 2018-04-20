package cs.uiowa.edu.android_front_end

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Survey2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey2)

        val btnHomeActivity: Button = findViewById<Button>(R.id.bSurvey2Home)
        btnHomeActivity.setOnClickListener{
            val intent = Intent(this,UserInfoActivity :: class.java )
            startActivity(intent)
        }

        val btnNextActivity: Button = findViewById<Button>(R.id.bSurvey2Next)
        btnNextActivity.setOnClickListener{
            val intent = Intent(this,ThankyouActivity :: class.java )
            startActivity(intent)
        }
    }
}
