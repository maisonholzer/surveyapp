package controller

class survey {
    var title: String
    var questions: List<question>
    var sID: String

    constructor(title: String, questions: List<question>, sID: String) {
        this.title = title; this.questions = questions; this.sID = sID
    }

    constructor() {
        this.title = ""; this.questions = emptyList(); this.sID = ""
    }
}

class question {
    var text: String
    var answers: List<String>
    var qID: String

    constructor(text: String, answers: List<String>, qID: String) {
        this.text = text; this.answers = answers; this.qID = qID
    }

    constructor() {
        this.text = ""; this.answers = emptyList(); this.qID = ""
    }
}
