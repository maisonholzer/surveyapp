package models

import javafx.beans.property.SimpleStringProperty
import tornadofx.ViewModel

class User {
    val usernameProperty = SimpleStringProperty()
    val passwordProperty = SimpleStringProperty()
}

class UserModel(val user: User) : ViewModel() {
    val username = bind {user.usernameProperty}
    val password = bind {user.passwordProperty}
}