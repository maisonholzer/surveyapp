package cs.uiowa.edu.android_front_end

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
/*
        val radnames = listOf(R.id.radioButton1,R.id.radioButton2,R.id.radioButton3,R.id.radioButton4)
        val radarray = Array<RadioButton>(4) { it -> findViewById(radnames.get(it))}
        // initially make all radio buttons invisible
        //radarray.forEach { it.setVisibility(View.INVISIBLE) }
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        //Log.v("radio button","set Invisible")
        Log.v("radio 1",radioButton1.text.toString())*/
        //val RadioActivity1: RadioButton = findViewById<RadioButton>(R.id.radioButton1)

        //val A = netdemo(this,radioButton1.text.toString())

        //Log.v("check again:","check again")
        //Log.v("json should be:",A)
        //radioButton1.setVisibility(View.INVISIBLE)
        //radioButton2.text = A
        //radioButton2.text = A

        // nice trick: https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/with-index.html
        /*for ((ind,title) in A.usermap.values.iterator().withIndex()) {
            radarray[ind].text = title
            Log.v("reset button","from map")
            //radarray[ind].setVisibility(View.VISIBLE)
        }*/


            val bHomeActivity: Button = findViewById<Button>(R.id.bSurveySelectHome)
            bHomeActivity.setOnClickListener {

                val intent = Intent(this, UserInfoActivity::class.java)
                startActivity(intent)
            }
        val bNextActivity: Button = findViewById<Button>(R.id.button4)
        bNextActivity.setOnClickListener {

            val intent = Intent(this, ThankyouActivity::class.java)
            startActivity(intent)
        }
        }
}
