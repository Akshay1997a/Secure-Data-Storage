function onAction(){
    var email= document.forms['form']["email_id"].value;
    var pass = "1234";

    if(email == "Akshay"){
        document.getElementById("email_error").innerHTML=email;
        return true;
    }
    else{
        document.getElementById("email_error").innerHTML="Incorect Email Id";
        document.getElementById("password").innerHTML = "Incorrect Password!";
        //document.forms["form"]["email_id"].innerHTML = "";
        //document.forms['form']['password'].innerHTML = "";
        return false;
    }
}