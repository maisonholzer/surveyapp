package cs.uiowa.edu.android_front_end

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button

class SurveySelectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_select)
/*
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
        } */
/*
        val radnames = listOf(R.id.radioButton1,R.id.radioButton2,R.id.radioButton3,R.id.radioButton4)
        val radarray = Array<RadioButton>(4) {it -> findViewById(radnames.get(it))}
        // initially make all radio buttons invisible
        radarray.forEach { it.setVisibility(View.INVISIBLE) }
        Log.v("radio button","set Invisible")
        val A = getJsonUserForLogin(this)

        // nice trick: https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/with-index.html
        for ((ind,title) in A.usermap.values.iterator().withIndex()) {
            radarray[ind].text = title
            Log.v("reset button","from map")
            radarray[ind].setVisibility(View.VISIBLE)
        }
*/
            val bNextActivity: Button = findViewById<Button>(R.id.bSurveySelectHome)

            bNextActivity.setOnClickListener {
                val A = NetAccess.getJsonUserForLogin(this)
                Log.v("json message",A.toString())
                val intent = Intent(this, UserInfoActivity::class.java)
                startActivity(intent)
            }
        }
}
