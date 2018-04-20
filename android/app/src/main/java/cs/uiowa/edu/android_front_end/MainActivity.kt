package cs.uiowa.edu.android_front_end

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log // see examples of Log below
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import cs.uiowa.edu.android_front_end.NetAccess.getAllSurveys

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
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        setContentView(R.layout.activity_main)
        Log.d(tag,"*** onCreate ***")
        val radnames = listOf(R.id.radioButton6,R.id.radioButton7,R.id.radioButton8,R.id.radioButton9)
        val radarray = Array<RadioButton>(5) {it -> findViewById(radnames.get(it))}
        // initially make all radio buttons invisible
        val A = getAllSurveys(this)
        // nice trick: https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/with-index.html
        for ((ind,title) in A.surveymap.values.iterator().withIndex()) {
            radarray[ind].text = title
            radarray[ind].setVisibility(View.VISIBLE)
        }

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
