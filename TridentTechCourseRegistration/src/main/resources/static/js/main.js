//Global Scope Variable to Hold JSON Object and Parsed Text Data
var xmlhttp;
var majorId;
var currentUser = "";
var userName = "";
var checkbox;
var checkboxSelection;
const courseListArray = new Array();
var student_id = "";

//Activate Event Listeners on Page Load
window.onload = function()
{
    init();
};

//Initialize all Event Listeners
function init()
{
    
    setTimeout(getCurrentUser,500); //load the current user into the footer
    setTimeout(getUserName,1000); //load the current user name
		loadMajors(); //load the majors (from the user if possible)
    document.getElementById('btnSubmit').addEventListener('click', selectMajor, false); //if the major isn't selected, add the new major
    setTimeout(patchCompletedCourses,2000);
}

 function patchMajorSelection()
 {
	xmlhttp = new XMLHttpRequest();
        
        xmlhttp.onreadystatechange = function()
        	{
				if(this.status==200)
				{
					;
				}
				/*else
				{
					alert("I'm not working....JEREMY");
				}*/
			}
			
		xmlhttp.open("PATCH", "/student/" + currentUser.user, true);
		
		xmlhttp.setRequestHeader("Content-Type", "application/json");
		
		xmlhttp.send(JSON.stringify({"major_id": majorId}));
}  

 function patchCompletedCourses(checkboxSelection)
 {
	xmlhttp = new XMLHttpRequest();
        
        xmlhttp.onreadystatechange = function()
        	{
				if(this.status==200)
				{
					;
				}
				/*else
				{
					alert("I'm not working....JEREMY");
				}*/
			}
			
		xmlhttp.open("PATCH", "/student/" + student_id + "/"  + checkboxSelection, true);
		
		xmlhttp.setRequestHeader("Content-Type", "application/json"); //Jeremy added 11/4/21
		
		xmlhttp.send();
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
            student_id = userName.id;
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
            document.getElementById('sezmiFooter').innerHTML = "Welcome " + currentUser.user;
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
    return `<option value='${major.major_id}'>${major.major_name}</option>`
}

//Template Literal to Return Course Name
function courseTemplate(course) 
{
    /*//Check if MajorID from User Selection is the same as MajorID in majorRequirements table
    if ((major_requirements.major_id).includes(majorId))
    {
		//Declare needed local variables
		let output = "";
		let courseList = courses.course_name;
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
			output = "<tr><td>";
			courses = jamesCoursesArray[i];
			//test = jamesCoursesArray[i].substring(0,7);
			
			//while (jamesCoursesArray[i+1] != null && jamesCoursesArray[i+1].includes(test))
			//{
				//courses += ", " + jamesCoursesArray[i+1];
				//i++;
			//}
			//output += courses;
			output += `</td><td><input type='checkbox' value =${jamesCoursesArray[i]} /></td></tr>`;
			
			courses =  "";
			return `<tr><td><input type='checkbox' value =${course.course_name} />${course.course_name}</td></tr>`;
		}
		//Return Accumulated Output 
		return "<tr><td>";
		return `<tr><td><input type='checkbox' value =${course.course_name} />${course.course_name}</td></tr>`;
	}*/
	return `<tr><td><input type='radio' name = 'radioBtn' value =${course.course_id} />${course.course_name}</td></tr>`;
}

function sectionTemplate(course)
{
	document.getElementById('sezmiFooter').innerHTML = course[0].course_id;
	return `course.availableSections[0].section_id`;
	//output += `</td><td><input type='checkbox' value =${jamesCoursesArray[i]} /></td></tr>`;
}

/*function sectionTemplate(course)
{
	for (i=0; i<course.availableSections.length; i++)
	{
		let output = "";
		let courseList = course;
		let courses = "";
		let test = "";
		
		courseListArray = courseList.split(",");
		courseListArray.sort();
		alert();
	}
	if 	((course).includes(course_id))
	{
		let output = "";
		let courseSectionList = section;
		let courses = "";
		let test = "";
		
		courseListArray = courseSectionList.split(",");
		
		courseListArray.sort();
		
		for (i=0; i<course.section_id.length; i++)
		{
		output += "<tr><td>";
		courses += courseListArray[i];
		test = courseListArray[i].substring(1,261);
		
		while (courseListArray[i+1] != null && courseListArray[i+1].includes(test))
		{
			courses += ", " + courseListArray[i+1];
			i++;
		}
		
		output += courses;
		output += `</td><td><input type='checkbox' value =${courseListArray[i]} /></td></tr>`;
			
		courses =  "";	
		}	
		return output;
	}
		
}*/

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
                    	document.getElementById('courses').innerHTML = `<tr><th>Select Completed Courses</th></tr>${courses.map(courseTemplate).join('')}`
						document.getElementById('submitClasses').innerHTML = '<button id="completedBtn">Submit Courses</button>'
						document.getElementById('showSections').innerHTML = '<button id="btnSection">Display Sections</button>'
						document.getElementById('showCompCourses').innerHTML = '<button id="btnCompletedCourses">Completed Courses</button>'
						document.getElementById('btnCompletedCourses').addEventListener('click', loadCompletedCourses, false);
						document.getElementById('completedBtn').addEventListener('click', pleaseWork, false);
						document.getElementById('btnSection').addEventListener('click', loadSections, false);
					}
					//Clear Table from Page
					else
					{
						document.getElementById('courses').innerHTML = "";
					}
                }
            };
            
            //Create API Call 											//this might be the culprit 11/4/2021
            xmlhttp.open("GET", '/majors/' + currentUser.user + '/courses');
            
            xmlhttp.setRequestHeader("Content-Type", "application/json"); //jeremy edit delete if not working
            
            //Send API Call
            xmlhttp.send();
}

function loadCompletedCourses()
{
	xmlhttp = new XMLHttpRequest();
	
	xmlhttp.onreadystatechange = function()
	{
		if (this.readyState == 4 && this.status==200) 
                {
					words = "<tr><th>Completed Courses</th></tr><tr><td>";
                    //Parse into JSON
                    const completedCourses = jQuery.parseJSON(xmlhttp.responseText);
                    for (i=0;i<completedCourses.length ;i++)
                    {
						if (i>0)
							{
								words += `, `;
							}
							words += `${completedCourses[i].course_name}`
							if (i == completedCourses.length -1)
							{
								words += "</td></tr>";
							}
					}
					document.getElementById('courses').innerHTML = words.fontcolor("white");
					
                }
            };
            
            //Create API Call
            xmlhttp.open("GET", '/student/' + currentUser.user + '/courses');
            
            //Send API Call
            xmlhttp.send();
}

function loadSections()
{
	xmlhttp = new XMLHttpRequest();
	let words = "";
            
            //Get Status
             xmlhttp.onreadystatechange = function() 
            {
                //Check if Status is Ready
               if (this.readyState == 4 && this.status==200) 
                {
					words = "<tr><th>Remaining Courses</th></tr>";
                    //Parse into JSON
                    const courseSection = jQuery.parseJSON(xmlhttp.responseText);
                    for (i=0;i<courseSection.length ;i++)
                    {
						//words +=`Course ID of ${courseSection[i].course_id}: `;
						words += `<tr><td><a id="openUp"><style="red>${courseSection[i].course_name}</a></td></tr><tr><td>`;
						//document.getElementById("openUp").addEventListener('click',alert("hi"), false);
					
						for (z=0;z<courseSection[i].availableSections.length;z++)
						{
							if (z>0)
							{
								words += `, `;
							}
							words += `<input type='radio' name='newRadioBtn' value =${courseSection[i].course_id} />${courseSection[i].availableSections[z].section_id}`
							if (z == courseSection[i].availableSections.length -1)
							{
								words += "</td></tr>";
							}
						}
					}
                    
                    document.getElementById('courses').innerHTML = words.fontcolor("white");
                    
                    //output += `</td><td><input type='checkbox' value =${jamesCoursesArray[i]} /></td></tr>`;
			
					//courses =  "";
					//return `<tr><td><input type='checkbox' value =${course.course_name} />${course.course_name}</td></tr>`;
					
                    /*var moreWords = "";
                    
                    //var words = JSON.stringify(courseSection);
                    for (i=0; i<courseSection.length; i++)
                    {
						var words = courseSection[i];
						for (z=0; z<availableSection.length; z++)
						{
							moreWords += `${courseSection[0].availableSections[0].section_id}`;
						}
						//var words += courseSection[i].;
						//document.getElementById('sezmiFooter').innerHTML = JSON.stringify(courseSection[0].availableSections[0].section_id);
					}
                    
                    //document.getElementById('sezmiFooter').innerHTML = `${courseSection[0].availableSection[0].section_id}`;
                    
                    //test if user selected a REAL major
                    for (i=0; i<courseListArray.length; i++) 
                    {
						if (courseListArray[i] != "nah")
						{
						//Generate Table of Eligible courses dynamically into HTML page
                    	document.getElementById('courses').innerHTML = `<tr><th>Eligible Courses</th><th id="selectHeader">Select Completed Courses</th></tr>${newCourses.map(sectionTemplate).join(',')}`
						document.getElementById('submitClasses').innerHTML = '<button id="completedBtn">Register Courses (dont press yet)</button>'
						document.getElementById('completedBtn').addEventListener('click', pleaseWork, false);
						}
					
					//Clear Table from Page
					else
					{
						document.getElementById('courses').innerHTML = "";
					}
					}*/
					
                }
            };
            
            //Create API Call
            xmlhttp.open("GET", '/majors/' + currentUser.user + '/courses');
            
            //Send API Call
            xmlhttp.send();
}

//Get Major Selection and Store as a Variable
function selectMajor()
{
	//use a get request the get the user name 
	/*xmlhttp.open("GET", "/student/" + currentUser.user, true);
		
		xmlhttp.setRequestHeader("Content-Type", "application/json");
		
		xmlhttp.send(JSON.stringify({"major_id": majorId}));*/
    var majorSelection = document.querySelector('#combo');
    majorId = majorSelection.value;
    
    setTimeout(patchMajorSelection,500);
    setTimeout(loadCourses,750);
    
}

function pleaseWork()
    {
		
		//var checkboxArray = [];
		
        checkbox = document.querySelector('input[type="radio"]:checked');
        //const checkboxArray = document.querySelectorAll("td[value]");
        /*for (var i= 0; i < checkbox.length; i++){
			checkboxArray.push(checkbox[i].value)
		}*/
        checkboxSelection = checkbox.value;
	    //checkboxSelection = checkbox.parentElement.textContent;
        //alert(checkboxSelection);
        patchCompletedCourses(checkboxSelection);
        postCourses();
        loadCourses();
        
        //document.getElementById("completedBtn").style.display = "none";
    }
    
 function postCourses()
 {
	xmlhttp = new XMLHttpRequest();
        
        xmlhttp.onreadystatechange = function()
        	{
				if(true)
				{
					;
				}
			}
			
		xmlhttp.open("POST", "/completedCourses", true);
		
		xmlhttp.setRequestHeader("Content-Type", "application/json");
		
		xmlhttp.send(JSON.stringify({"email": currentUser.value, "course_id": checkbox.value, "grade": "null", "term": "null"}));
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
    if ((student/currentUser.majorId).includes(majorId))
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