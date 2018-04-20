package cs.uiowa.edu.android_front_end

import com.android.volley.Response
import com.android.volley.request.StringRequest

class SignUpRequest(Name:String, Username: String, Year: String, Password: String, method: Int,
                    url: String?, listener: Response.Listener<String>?, errorListener: Response.ErrorListener?)
    : StringRequest(method, url, listener, errorListener) {

    val RequestURL:String = "https://webdev.cs.uiowa.edu/~changzhan/practice/register.php"
    val paramsMap = mutableMapOf<String,String>("Name" to Name, "Username" to Username, "Year" to Year, "Password" to Password)

    override fun getParams(): MutableMap<String, String> {
        return paramsMap
    }




}