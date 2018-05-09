package controller

open class User {
    var username: String
    var pass: String
    constructor(username: String, pass: String) {
        this.username = username; this.pass = pass
    }
    constructor() {
        this.username = ""; this.pass = ""
    }
}

class Admin : User {
    constructor(username: String, pass: String) {
        this.username = super.username; super.pass = pass
    }
    constructor() {
        this.username = ""; this.pass = ""
    }
}

class Client: User {
    var surveys = mutableMapOf<String,survey>()

    constructor(username: String, pass: String) {
        this.username = super.username; this.pass = super.pass; this.surveys = mutableMapOf()
    }
    constructor() {
        this.username = ""; this.pass = ""; this.surveys = HashMap()
    }


}

fun main(args: Array<String>) {

}