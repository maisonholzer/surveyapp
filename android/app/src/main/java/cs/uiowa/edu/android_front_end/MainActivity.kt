package cs.uiowa.edu.android_front_end

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log // see examples of Log below
import android.view.View
import android.widget.Button

// Log.v(tag,msg) for Verbose Level
// Log.d(tag,msg) for Debug Level
// Log.i(tag,msg) for Information Level
// Log.w(tag,msg) for Warning level
// Log.e(tag,msg) for Error level
// another kind of method: wtf(tag,msg)

class MainActivity : AppCompatActivity() {
    val tag = "CS2820"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(tag,"*** onCreate ***")

        val btnOpenActivity: Button = findViewById(R.id.button1)
        btnOpenActivity.setOnClickListener{
            val intent = Intent(this,SurveyInfoActivity :: class.java )
            startActivity(intent)
        }
    }



    // others to override: onPostCreate, onRestart, onStart, onResume,
    // onPostResume, onPause, onStop, onDestroy
    override fun onStart() {
        super.onStart()
        Log.v(tag,"*** onStart ***")
    }

    // handler for button press
    fun sendMessage(view: View) {
        Log.v(tag,"*** button press ***")
    }
}
