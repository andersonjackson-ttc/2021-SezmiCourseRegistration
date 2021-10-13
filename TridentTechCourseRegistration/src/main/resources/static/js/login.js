//Global Scope Variable to Hold JSON Object and Parsed Text Data
var xmlhttp;
var email;
var password;

//Activate Event Listeners on Page Load
window.onload = function() 
    {
    document.getElementById("btn-log-on").addEventListener("click", change, false);
    }
    function change() {
        
        email = document.getElementById("username-text-box").value;
        password = document.getElementById("password-text-box").value;
        
        if (email=="sezmi123@aol.com" & password=="goteam")
        window.location = "file://localhost/C:/Users/18436/Documents/GitHub/SezmiCourseRegistration/TridentTechCourseRegistration/src/main/resources/static/index.html";
        else
        alert("Username Not Found in Database, please click new user to set up account");
       
       // btn-log-on.disabled = true

    }

/*window.onload = function()
{
    init();
};

//Initialize all Event Listeners
function init()
{
   
    let button = document.querySelector(".btn-log-on")
     button.disabled = true
    //document.getElementsByClassName('btn-log-on').addEventListener('click', checkUser, false);
    //document.getElementsByClassName('btn-log-on').onclick = 
    //let button = document.getElementsByClassName('btn-log-on');
   // button.disabled=true;

}

function redirectToURL(btnId){
    if(btnId=="btn-log-off")
        button.disabled = true
        else
        window.location.replace("https://en.wikipedia.org/wiki/Main_Page");
    
}
function checkUser()
{
    
    //('btn-log-on').disabled
    //email = document.getElementById("username-text-box").value;
    //password = document.getElementById("password-text-box").value;
    //if (email="sezmi123@aol.com")
    //{
     //   btn-log-on.disabled = true; //setting button state to disabled

   //let input = document.querySelector("btn-log-on");
   //et button = document.querySelector(".btn-log-on");
   
   //button.disabled = true; //setting button state to disabled
   
       
    //}

}

//indow.navigate("index.html");
//Template Literal to Return email and password
//function emailTemplate(email) 
//{
//    return `<option value='${email.email}'>${email.password}</option>`
//}

//Create API Call
//xmlhttp.open("GET", '/student');
            
//Send API Call
//xmlhttp.send()*/