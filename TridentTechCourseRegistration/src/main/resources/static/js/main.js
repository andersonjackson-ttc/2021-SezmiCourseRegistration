//Global Scope Variable to Hold JSON Object and Parsed Text Data
var xmlhttp;
var majorId;
var currentUser = "";
var userName = "";

//Activate Event Listeners on Page Load
window.onload = function()
{
	//getCurrentUser();
	//document.getElementById('errorFooter').innerHTML = "hello";
    init();
};

//Initialize all Event Listeners
function init()
{
    loadMajors();
    document.getElementById('btnSubmit').addEventListener('click', selectMajor, false);
    getCurrentUser();
    setTimeout(getUserName,200);
    //getUserName();
}

//Get User Name
function getUserName()
{
  //Declare and Initialize New Ajax Object
    var x = new XMLHttpRequest();
           
      //Get Status
      x.onreadystatechange = function()
      {
          //Check if Status is Ready
          if (true)
          {
            //Store Returned JSON String in Global Variable
            userName = jQuery.parseJSON(x.responseText);
            document.getElementById('sezmiFooter').color = "red";
            document.getElementById('sezmiFooter').innerHTML = ("Welcome " + userName.first_name + " " + userName.last_name).fontcolor("black").fontsize(2.1);
          }      
       }
     
       //Create API Call to Random Number Website
       x.open("GET", '/student/' + currentUser.user);
       
       //Send API Call to Student Table
       x.send();
}

//Get Current User
function getCurrentUser()
{
  //Declare and Initialize New Ajax Object
    var x = new XMLHttpRequest();
           
      //Get Status
      x.onreadystatechange = function()
      {
          //Check if Status is Ready
          if (true)
          {
            //Store Returned JSON String in Global Variable
            currentUser = jQuery.parseJSON(x.responseText);
            //document.getElementById('sezmiFooter').innerHTML = "Welcome " + currentUser.user;
          }      
       }
     
       //Create API Call to Random Number Website
       x.open("GET", '/user/1');
       
       //Send API Call to Student Table
       x.send();
}

//Template Literal to Return Major Name and Major ID
function majorTemplate(major) 
{
    return `<option value='${major.major_id}'>${major.major}</option>`
}

//Template Literal to Return Course Name
function courseTemplate(major_requirements) 
{
    //Check if MajorID from User Selection is the same as MajorID in majorRequirements table
    if ((major_requirements.major_id).includes(majorId))
    {
		//Declare needed local variables
		let output = "";
		let courseList = major_requirements.course_id;
		let courses = "";
		let test = "";
		
		
		//Create Constant Array from splitting courselist String with comma delimiters
		const jamesCoursesArray = courseList.split(",");
		
		
		//Sort Array Alphabetically 
		jamesCoursesArray.sort();
		
		//For loop that accumulates output variable with dynamic HTML building of Table 
		for (i = 0; i < jamesCoursesArray.length; i++)
		{
		
			//Output Accumulator
			output += "<tr><td>";
			courses += jamesCoursesArray[i];
			test = jamesCoursesArray[i].substring(0,7);
			
			while (jamesCoursesArray[i+1] != null && jamesCoursesArray[i+1].includes(test))
			{
				courses += ", " + jamesCoursesArray[i+1];
				i++;
			}
			output += courses;
			output += `</td><td><input type='checkbox' value =${jamesCoursesArray[i]} /></td></tr>`;
			
			courses =  "";
		}
		//Return Accumulated Output 
		return output;
	}
}

//Load Drop Down ComboBox with a List of Majors
function loadMajors() 
{
            //Make a new API Request
            xmlhttp = new XMLHttpRequest();
            
            //Get Status
            xmlhttp.onreadystatechange = function() 
            {
                //Check if Status is Ready
                if (this.readyState == 4 && this.status==200) 
                {
                    //Parse into JSON
                    const majors = jQuery.parseJSON(xmlhttp.responseText);
                    
                    //Template Literal to Add all Majors into HTML Dynamically
                    document.getElementById('combo').innerHTML = `<option value='nah'>Select a Major</option>${majors.map(majorTemplate).join('')}`
                }
            };
            
            //Create API Call
            xmlhttp.open("GET", '/majors');
            
            //Send API Call
            xmlhttp.send();
}

//Load Table with Required Courses
function loadCourses() 
{
            //Make a new API Request
            xmlhttp = new XMLHttpRequest();
            
            //Get Status
             xmlhttp.onreadystatechange = function() 
            {
                //Check if Status is Ready
                if (this.readyState == 4 && this.status==200) 
                {
                    //Parse into JSON
                    const courses = jQuery.parseJSON(xmlhttp.responseText);
                    
                    //test if user selected a REAL major
                    if (majorId != "nah")
                    {
						//Generate Table of Eligible courses dynamically into HTML page
                    	document.getElementById('courses').innerHTML = `<tr><th>Eligible Courses</th><th id="selectHeader">Select Completed Courses</th></tr>${courses.map(courseTemplate).join('')}`
						document.getElementById('submitClasses').innerHTML = '<button id="completedBtn">Submit Courses</button>'
						document.getElementById('completedBtn').addEventListener('click', pleaseWork, false);
					}
					//Clear Table from Page
					else
					{
						document.getElementById('courses').innerHTML = "";
					}
                }
            };
            
            //Create API Call
            xmlhttp.open("GET", '/major_requirements');
            
            //Send API Call
            xmlhttp.send();
}

//Get Major Selection and Store as a Variable
function selectMajor()
{
    var majorSelection = document.querySelector('#combo');
    majorId = majorSelection.value;
    loadCourses();
}

function pleaseWork()
    {
        let checkbox = document.querySelector('input[type="checkbox"]:checked');
        checkboxSelection = checkbox.value;
	    //checkboxSelection = checkbox.parentElement.textContent;
        alert(checkboxSelection);
        
        loadNewCourses();
        document.getElementById("completedBtn").style.display = "none";
    }
    

    
//if I can't add value, maybe I can store the json values and add them another way
function storeCourses()
{
    var selectedCourses = document.getElementById('courses');
    store.set(courses.innerHTML, null);
}

function loadNewCourses()
{
    //Make a new API Request
            xmlhttp = new XMLHttpRequest();
            
            //Get Status
             xmlhttp.onreadystatechange = function() 
            {
                //Check if Status is Ready
                if (this.readyState == 4 && this.status==200) 
                {
                    //Parse into JSON
                    const newCourses = jQuery.parseJSON(xmlhttp.responseText);
                    
                    //test if user selected a REAL major
                    if (majorId != "nah")
                    {
						//Generate Table of Eligible courses dynamically into HTML page
                    	document.getElementById('courses').innerHTML = `<tr><th>Remaining Courses</th></tr>${newCourses.map(newCourseTemplate).join('')}`
                        
					}
					//Clear Table from Page
					else
					{
						document.getElementById('courses').innerHTML = "";
					}
                }
            };
            
            //Create API Call
            xmlhttp.open("GET", '/majors', true);
            
            //Send API Call
            xmlhttp.send();
}


//only fill table with courses that weren't selected
function newCourseTemplate(major_requirements)
{
 
    //Check if MajorID from User Selection is the same as MajorID in majorRequirements table
    if ((major_requirements.major_id).includes(majorId))
    {
		//Declare needed local variables
		let output = "";
		let courseList = major_requirements.course_id;
		let courses = "";
		let test = "";
		
		//Create Constant Array from splitting courselist String with comma delimiters
		const jamesCoursesArray = courseList.split(",");
		
		//Sort Array Alphabetically 
		jamesCoursesArray.sort();
		
		//For loop that accumulates output variable with dynamic HTML building of Table 
		for (i = 0; i < jamesCoursesArray.length; i++)
		{
			//Output Accumulator
			output += "<tr><td>";
			courses += jamesCoursesArray[i];
			test = jamesCoursesArray[i].substring(0,7);
			
			while (jamesCoursesArray[i+1] != null && jamesCoursesArray[i+1].includes(test))
			{
				courses += ", " + jamesCoursesArray[i+1];
				i++;
			}
			output += courses;
			output += "</td></tr>";
			courses =  "";
		}
		//Return Accumulated Output 
		return output;
	}

}