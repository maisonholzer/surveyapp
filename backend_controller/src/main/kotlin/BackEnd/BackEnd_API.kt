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

    fun usernameAvailable(name:String): Boolean {
        return !ClientList.contains(name) && !AdminList.contains(name)
    }

    fun getAllClientList(): MutableList<MutableMap.MutableEntry<String, Client>> {
        updateClientFile()
        val clientList: MutableList<MutableMap.MutableEntry<String, Client>> = mutableListOf()
        for (client in ClientList) {
            clientList.add(client)
        }
        return clientList
    }

    fun getAllAdminList(): MutableList<MutableMap.MutableEntry<String, Admin>> {
        updateAdminFile()
        val adminList: MutableList<MutableMap.MutableEntry<String, Admin>> = mutableListOf()
        for (admin in AdminList) {
            adminList.add(admin)
        }
        return adminList
    }

    fun getAllUsersList(): HashMap<String, Any> {
        val userList: HashMap<String, Any> = HashMap()
        for (admin in getAllAdminList()) {
            userList.put(admin.key, admin.value)
        }
        for (client in getAllClientList()) {
            userList.put(client.key, client.value)
        }
        return userList
    }

    fun getAllSurveys(): HashMap<String, survey> {
        val survList: HashMap<String, survey> = HashMap()
        for (survey in surveyList) {
            survList.put(survey.key, survey.value)
        }
        return survList
    }

    fun getAllQuestions(): MutableMap<String, question>{
        val qList: HashMap<String, question> = HashMap()
        for (q in questionList) {
            qList.put(q.key, q.value)
        }
        return qList
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
    fun addSurveyQuestion(survey: survey , question :question){
        survey.addSurveyQuestion(question)
    }

    fun removeSurveyQuestion(survey: survey , question: question){
        survey.removeQuestion(question)
    }

    fun addQuestionAnswer(question:question, answer:String){
        question.addAnswer(answer)
    }

    fun removeQuestionAnswer(question:question, answer:String){
        question.removeAnswer(answer)
    }
}