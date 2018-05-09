package BackEnd

import javax.validation.constraints.Null

interface BackEnd_API {

    fun newClient(name:String,password:String):User{
        println("BackEnd_API-newClient function")
        val x = Client(name,password)
        x.surveys = getAllSurveys()
        ClientList.put(x.username,x)
        updateClientFile()
        return x
    }

    fun newAdmin(name:String,password:String):Admin{
        println("BackEnd_API-newAdmin function")
        val x = Admin(name,password)
        AdminList.put(x.username,x)
        updateAdminFile()
        return x
    }

    fun GetUser(name:String):User?{
        println("BackEnd_API-GetUser function")
        if (ClientList.contains(name)) return ClientList.get(name)
        else if (AdminList.contains(name)) return AdminList.get(name)
        else return null
    }

    fun usernameAvailable(name:String): Boolean {
        println("BackEnd_API-usernameAvailable function")
        return !ClientList.contains(name) && !AdminList.contains(name)
    }

    fun getAllClientList(): MutableList<MutableMap.MutableEntry<String, Client>> {
        println("BackEnd_API-getAllClientList function")
        updateClientFile()
        val clientList: MutableList<MutableMap.MutableEntry<String, Client>> = mutableListOf()
        for (client in ClientList) {
            clientList.add(client)
        }
        return clientList
    }

    fun getAllAdminList(): MutableList<MutableMap.MutableEntry<String, Admin>> {
        println("BackEnd_API-newAdmin function")
        updateAdminFile()
        val adminList: MutableList<MutableMap.MutableEntry<String, Admin>> = mutableListOf()
        for (admin in AdminList) {
            adminList.add(admin)
        }
        return adminList
    }

    fun getAllUsersList(): HashMap<String, Any> {
        println("BackEnd_API-getAllUsersList function")
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
        println("BackEnd_API-getAllSurveys function")
        val survList: HashMap<String, survey> = HashMap()
        for (survey in surveyList) {
            survList.put(survey.key, survey.value)
        }
        return survList
    }

    fun getAllQuestions(): MutableMap<String, question>{
        println("BackEnd_API-getAllQuestions function")
        val qList: HashMap<String, question> = HashMap()
        for (q in questionList) {
            qList.put(q.key, q.value)
        }
        return qList
    }

    fun updateUser(upUser: User){
        println("BackEnd_API-updateUser function")
        if (upUser is Client){
            ClientList.put(upUser.username,upUser)
            updateClientFile()
        }else {
            AdminList.put(upUser.username,upUser as Admin)
            updateAdminFile()
        }

    }

    fun addQuestionToSurvey(survey: survey , question :question) {
        println("BackEnd_API-addQuestionToSurvey function")
        if (!survey.questions.contains(question)) survey.addSurveyQuestion(question)
    }

    fun removeQuestionFromSurvey(survey: survey , question: question){
        println("BackEnd_API-removeQuestionFromSurvey function")
        if (survey.questions.contains(question)) survey.removeQuestion(question)
    }

    fun addAnswerToQuestion(question:question, answer:String){
        println("BackEnd_API-addAnswerToQuestion function")
        if (!question.answers.contains(answer)) question.addAnswer(answer)
    }

    fun removeAnswerFromQuestion(question:question, answer:String){
        println("BackEnd_API-removeAnswerFromQuestion function")
        if (question.answers.contains(answer)) question.removeAnswer(answer)
    }

    fun getResultsOfSurvey(sId: String):ResultSummary{
        println("BackEnd_API-getResultsOfSurvey function")
        return summaryList.getValue(sId)
    }

    fun addNewResultsForSurvey(results: SurveyResults){
        println("BackEnd_API-addNewResultsForSurvey function")
        getResultsOfSurvey(results.surveyID).updateSummaryResults(results)
    }
}