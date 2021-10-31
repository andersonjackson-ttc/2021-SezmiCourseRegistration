//Global Scope Variable to Hold JSON Object and Parsed Text Data
var xmlhttp;
var inputEmail = "null";
var password = "";
var isThisTrue = false;
var testUserName;
var isThisBlank = false;

//Activate Event Listeners on Page Load
window.onload = function()
{
	init();
};

//Initialize all Event Listeners
function init()
{
	document.getElementById("btn-log-on").addEventListener("click", compareUser, false);
	document.getElementById("btn-log-on").addEventListener("click", putToDatabase, false);
}

//Template Literal to Return username and password
function emailTemplate(login) 
{
    return `<option value='${login.email}'>${login.password}</option>`
}

function compareUser()
    {
 		//Make a new API Request
    	xmlhttp = new XMLHttpRequest();
            
      	//Get Status
      	xmlhttp.onreadystatechange = function() 
        {
	
         	//Check if Status is Ready
         	if (this.readyState == 4 && this.status==200) 
         	{
				isThisTrue = false;
				isThisBlank = false;
				
       			//Parse into JSON
       			const students = jQuery.parseJSON(xmlhttp.responseText);
				
				inputEmail = document.getElementById("username-text-box").value;
    			password = document.getElementById("password-text-box").value;
    			
    			if (inputEmail == "" || password == "")
    			{
					isThisBlank = true;
				}
    			for (i=0; i < students.length; i++)
    			{
					if ((students[i].email) == (inputEmail) && inputEmail != "" && (students[i].password) == (md5(students[i].salt + password)) && password != "")
    				{
						isThisTrue = true;
					}
				}	
				if (isThisBlank)
				{
					document.getElementById('errorFooter').innerHTML = "Please Fill in All Empty Text Fields";
				}
				else if (isThisTrue)
				{
					//Display Success
					document.getElementById('errorFooter').innerHTML = "Success!";
					//Open Main Page
					window.location = "/mainpage";
				}
				else
				{
					document.getElementById('errorFooter').innerHTML = "Email and/or Password is incorrect.";
				}
     		}    		  	
     	}

     //Create API Call
     xmlhttp.open("GET", '/student');
            
     //Send API Call
     xmlhttp.send();
     }
     
     function putToDatabase()
{   
    //Initialize XMLHttpRequest Object
    x = new XMLHttpRequest();
    
    //Check that Server is Ready
    x.onreadystatechange = function()
        {
            if(true)
            {
				//Success Message
				inputEmail = document.getElementById("username-text-box").value;
				document.getElementById('errorFooter').innerHTML = "Success!";
            }
            else
            {
				//Tell User that there was a problem
				document.getElementById('errorFooter').innerHTML = "This DID NOT Work!";
            }
        }
    
    //Request to Post Key Value Pairs
    x.open("PUT", "/user/1", true); 
    
    x.setRequestHeader("Content-Type", "application/json");
    //Send All Key Value Pairs to Database
    x.send(JSON.stringify({"id": "1", "user": inputEmail})); 
}

    