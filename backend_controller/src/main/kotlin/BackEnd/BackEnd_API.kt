package BackEnd

import javax.validation.constraints.Null

interface BackEnd_API {

    fun newClient(name:String,password:String):User{
        val x = Client(name,password)
        ClientList.put(x.username,x)
        updateClientFile()
        return x
    }

    fun newAdmin(name:String,password:String):Admin{
        val x = Admin(name,password)
        AdminList.put(x.username,x)
        updateAdminFile()
        return x
    }

    fun GetUser(name:String):User?{
        if (ClientList.contains(name)) return ClientList.get(name)
        else if (AdminList.contains(name)) return AdminList.get(name)
        else return null
    }

    fun usernameAvailable(name:String): Boolean{
        return !ClientList.contains(name) && !AdminList.contains(name)
    }

    fun getAllSurveys(): MutableMap<String,survey>{
        return surveyList
    }

    fun getAllQuestions(): MutableMap<String,question>{
        return questionList
    }

}