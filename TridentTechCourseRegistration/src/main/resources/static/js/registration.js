//Global Scope Variable to Hold JSON Object and Parsed Text Data
var xmlhttp;
var firstName = "";
var lastName = "";
var email = "";
var password = "";
var trueOrFalse = false;
var emailArrayString;
var userInputBlank = false;
var salt = "";

//Activate Event Listeners on Page Load
window.onload = function()
{
    //Get Salt Value
    getSalty();
    
	//Load Array of Emails from Database
	loadEmailsFromDatabase();
	
	//Call Initialization Function
    init();
};

//Initialize all Event Listeners
function init()
{
	//Listen for Button Clicking
    document.getElementById('btnSubmit').addEventListener('click',validateUserInput, false);
}

//Get Random Salt
function getSalty()
{
  //Declare and Initialize New Ajax Object
    var xhr = new XMLHttpRequest();
           
      //Get Status
      xhr.onreadystatechange = function()
      {
          //Check if Status is Ready
          if (this.readyState == 4 && this.status==200)
          {
            //Store Returned JSON String in Global Variable
            salt = xhr.responseText;
            salt = salt.substring(1,6);
          }      
       }
     
       //Create API Call to Random Number Website
       xhr.open("GET", 'https://random.justyy.workers.dev/api/random/?cached&n=5&x=7');
       
       //Send API Call to Student Table
       xhr.send();
}

//Vaidate User
function validateUserInput()
{
	//Store Global Variables with User Input Values
    firstName = document.getElementById("firstname-text-box");
    lastName = document.getElementById("lastname-text-box");
    email = document.getElementById("email-text-box");
    password = document.getElementById("password-text-box");
    hashPassword = md5(salt + password.value);
    
    //Call Function to Check if Email Already Exists
    doesEmailAlreadyExist();
    
    //Check If User did not input anything into email text box
    if (userInputBlank)
    {
		document.getElementById('errorFooter').innerHTML = "Please Fill in All Empty Text Fields";
	}
	
	//Check If email that user input is already in the database
	else if (trueOrFalse)
	{
		document.getElementById('errorFooter').innerHTML = email.value + " already exists!";
	}
	
	//Else Create a new student record from user input values
	else
	{
		postToDatabase();
	}  
}
	
//Load Emails From Database into Memory
function loadEmailsFromDatabase()
{
 		//Declare and Initialize New Ajax Object
    	var xhr = new XMLHttpRequest();
            
      	//Get Status
      	xhr.onreadystatechange = function() 
        {
	
         	//Check if Status is Ready
         	if (this.readyState == 4 && this.status==200) 
         	{
       			//Store Returned JSON String in Global Variable
       			emailArrayString = xhr.responseText;
     		}    		  	
     	}
     	
     //Create API Call to Student Table
     xhr.open("GET", '/student');
        
     //Send API Call to Student Table
     xhr.send();
}


function doesEmailAlreadyExist()
{
	//Reset Value of Global Variables to Default
	trueOrFalse = false;
	userInputBlank = false;
	
	//Create a Parsed JSON Array from String Value held within global variable of "emailArrayString"
	const emails = jQuery.parseJSON(emailArrayString);
	
	//Check if user did not enter anything into email textbox
	if(email.value == "" || password.value == "" || firstName.value == "" || lastName.value == "")
	{
		userInputBlank = true;
	}

	//For loop that checks if user input email already exists within database and that the user input is not empty
    for (i=0; i < emails.length; i++)
    {
		if ((emails[i].email) == (email.value))
    	{
			trueOrFalse = true;
		}
	}	
}

//Writes a new student record to the student database table
function postToDatabase()
{   
    //Create Array from User Input
    //var elements = document.getElementsByClassName("formVal");
    
    //Declare and Initialize FormData Object
    //var formData = new FormData(); 
    
    //For Loop that appends name and value pairs
    /*for(var i=0; i<elements.length; i++)
    {
        formData.append(elements[i].name, elements[i].value);
    }*/
    
    //Initialize XMLHttpRequest Object
    xmlhttp = new XMLHttpRequest();
    
    //Check that Server is Ready
    xmlhttp.onreadystatechange = function()
        {
            if(true)
            {
				//Success Message
				document.getElementById('errorFooter').innerHTML = "Success!";
				//Open Login Page
				window.location = "/login";
            }
            else
            {
				//Tell User that there was a problem
				document.getElementById('errorFooter').innerHTML = "This DID NOT Work!";
            }
        }
    
    //Request to Post Key Value Pairs
    xmlhttp.open("POST", "/student", true); 
    
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    //Send All Key Value Pairs to Database
    xmlhttp.send(JSON.stringify({"email": email.value, "last_name": lastName.value, "first_name": firstName.value, "salt": salt, "password": hashPassword, "major_id": "null"})); 
}

