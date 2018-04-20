package cs.uiowa.edu.android_front_end

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Survey2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey2)

        val btnOpenActivity: Button = findViewById<Button>(R.id.button5)
        btnOpenActivity.setOnClickListener{
            val intent = Intent(this,Survey3Activity :: class.java )
            startActivity(intent)
        }
    }
}
