package cs.uiowa.edu.android_front_end

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ThankyouActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thankyou)

        val btnOpenActivity: Button = findViewById(R.id.button7)
        btnOpenActivity.setOnClickListener{
            val intent = Intent(this,SurveySelectActivity :: class.java )
            startActivity(intent)
        }
    }
}
