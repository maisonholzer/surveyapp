package cs.uiowa.edu.android_front_end

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Survey3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey3)

        val btnOpenActivity: Button = findViewById(R.id.button6)
        btnOpenActivity.setOnClickListener{
            val intent = Intent(this,ThankyouActivity :: class.java )
            startActivity(intent)
        }
    }
}
