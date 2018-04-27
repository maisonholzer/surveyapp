package BackEnd

import javax.validation.constraints.Null

interface BackEnd_API {

    fun newClient(name:String,password:String):User{
        val x = Client(name,password)
        x.surveys = getAllSurveys()
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

    fun getAllClientList(): HashMap<String, MutableMap.MutableEntry<String, Client>> {
        updateClientFile()
        val clientList: HashMap<String, MutableMap.MutableEntry<String, Client>> = HashMap()
        for (client in ClientList) {
            clientList.put(client.key, client)
        }
        return clientList
    }

    fun getAllAdminList(): HashMap<String, MutableMap.MutableEntry<String, Admin>> {
        updateAdminFile()
        val adminList: HashMap<String, MutableMap.MutableEntry<String, Admin>> = HashMap()
        for (admin in AdminList) {
            adminList.put(admin.key, admin)
        }
        return adminList
    }

    fun getAllUsersList(): HashMap<String, Any>{
        val userList: HashMap<String, Any> = HashMap()
        userList += getAllClientList()
        userList += getAllAdminList()
        return userList
    }

    fun getAllSurveys(): MutableMap<String,survey>{
        return surveyList
    }

    fun getAllQuestions(): MutableMap<String,question>{
        return questionList
    }

    fun updateUser(upUser: User){
        if (upUser is Client){
            ClientList.put(upUser.username,upUser)
            updateClientFile()
        }else {
            AdminList.put(upUser.username,upUser as Admin)
            updateAdminFile()
        }

    }

}