//Global Scope Variable to Hold JSON Object and Parsed Text Data
var xmlhttp;
var firstName;
var lastName;
var email;
var password;


//Activate Event Listeners on Page Load
window.onload = function()
{
    init();
};

//Initialize all Event Listeners
function init()
{
    document.getElementById('btnSubmit').addEventListener('click',postToDatabase, false);
}

//Store User Input Values in Appropriate Variables
function storeUserInput()
{
    firstName = document.getElementById("firstname-text-box");
    lastName = document.getElementById("lastname-text-box");
    email = document.getElementById("email-text-box");
    password = document.getElementById("password-text-box");
}

function postToDatabase()
{
    //Create Array from User Input
    var elements = document.getElementsByClassName("formVal");
    
    //Declare and Initialize FormData Object
    var formData = new FormData(); 
    
    //For Loop that appends name and value pairs
    for(var i=0; i<elements.length; i++)
    {
        formData.append(elements[i].name, elements[i].value);
    }
    
    //Initialize XMLHttpRequest Object
    xmlhttp = new XMLHttpRequest();
    
    //Check that Server is Ready
    xmlHttp.onreadystatechange = function()
        {
            if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
            {
                alert(xmlhttp.responseText);
            }
        }
    
    //Request to Post Key Value Pairs
    xmlhttp.open("POST", "/student"); 
    //Send All Key Value Pairs to Database
    xmlhttp.send(formData); 
}

