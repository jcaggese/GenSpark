const subButton = document.getElementById("submit-btn")
const nameEl = document.getElementById("name")
const email = document.getElementById("email")
const reasons = document.getElementsByName("reason-radio")
const submission = document.getElementById("text-submission")

const form = document.querySelector("form")
form.onsubmit = function() {
    localStorage.clear();
    console.log("SUBMITTED");
    let input = `<li>Name: ${nameEl.value}</li>
                <li>Email: ${email.value}</li>
                <li>Reason: ${getReasonRadioVal()}</li>
                <li>Submission: ${submission.value}</li>`

    localStorage.setItem("submission", input)

    document.location.href="submission.html"

}

function submit() {
    localStorage.clear();
    console.log("SUBMITTED");
    let input = `<li>Name: ${nameEl.value}</li>
                <li>Email: ${email.value}</li>
                <li>Reason: ${getReasonRadioVal()}</li>
                <li>Submission: ${submission.value}</li>`

    localStorage.setItem("submission", input)

    document.location.href="submission.html"
}



function getReasonRadioVal() {
    for (let i = 0; i < reasons.length; i++) {
        if (reasons[i].checked) {
            let selector = `label[for=${reasons[i].id}]`
            return document.querySelector(selector).innerText
        }
    }
}