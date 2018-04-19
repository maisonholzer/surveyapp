package controller

import tornadofx.Controller
import tornadofx.Rest
import models.User

class localHost: Controller() {
    val api : Rest by inject()


/*
    init {
        api.baseURI = "http://localhost:8080/users/Iowa"

*/
    //checks credentials of user object
    fun login(user: User): Boolean {
        api.setBasicAuth(user.usernameProperty.value, user.passwordProperty.value)
        return api.get("user").consume().ok()
    }
}