//Global Scope Variable to Hold JSON Object and Parsed Text Data
var student = ""; //declared global variable for student JH 11/12
//var xmlhttp;
var majorId;
var userEmail = "";
var userName = "";
var student_id = "";
var coursesList = ``;
//var preReqStatus;

//Activate Event Listeners on Page Load
window.onload = function()
{
    init();
};

//Initialize all Event Listeners
function init()
{
    //Load User Email 
    setTimeout(getUserEmail,500);
    
    //
    setTimeout(getUserName,1000);		
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
			}
			
		xmlhttp.open("PATCH", "/student/" + userEmail.user, true);
		
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
			}
			
		xmlhttp.open("PATCH", "/student/" + student_id + "/"  + checkboxSelection, true);
		
		xmlhttp.setRequestHeader("Content-Type", "application/json"); 
		
		xmlhttp.send();
}

 function patchSectionSelection(checkboxSelection)
 {
	xmlhttp = new XMLHttpRequest();
        
        xmlhttp.onreadystatechange = function()
        	{
				if(true)
				{
					document.getElementById('sezmiFooter').innerHTML = (checkboxSelection).fontcolor("black").fontsize(2.1);
				}
			}
			
		xmlhttp.open("PATCH", "/students/" + student_id + "/" + checkboxSelection, true);
		
		xmlhttp.setRequestHeader("Content-Type", "application/json");
		
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
          
          //alert("This should happen last " + userName.major_id); //this is where we can get the major id
          //call getMajorIdFromStudent to get the major id and display the relevant courses for their major
          getMajorIdFromStudent();
       }
     
       //Create API Call to Random Number Website
       x.open("GET", '/student/' + userEmail.user);
       
       //Send API Call to Student Table
       x.send();
}

//Get Current User
function getUserEmail()
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
            userEmail = jQuery.parseJSON(x.responseText);
            document.getElementById('sezmiFooter').innerHTML = "Welcome " + userEmail.user;
          }      
       }
     
       //Create API Call to Random Number Website
       x.open("GET", '/user/1');
       
       //Send API Call to Student Table
       x.send();
}

//the getMajorIdFromStudent function checks if the student has a major ID, and if so, uses it to display the relevant courses
//if NOT, will display the loadMajors dropdown menu.
function getMajorIdFromStudent()
{
	//if statement seeing if the user has logged in before to only display their courses
	//calls getStudentMajor function that returns the student major (or null if never entered)
		if (userName.major_id != "null")
		{
			loadMajorWithId();
			//setTimeout(loadOverLoadMajors(currentMajor), 5000);
			//loadCourses only and skip the rest (no major drop down list anymore)
			//loadCourses();
			//document.getElementById('btnSubmit').addEventListener('click', selectMajor, false); //if the major isn't selected, add the new major
		}
		//else they haven't signed up for a major so they need to do that. 
		else
		{
			loadMajors(); //load the majors from the database
    		document.getElementById('btnSubmit').addEventListener('click', selectMajor, false); //if the major isn't selected, add the new major
    		setTimeout(patchCompletedCourses,2000);
		}
}


//Template Literal to Return Major Name and Major ID
function majorTemplate(major) 
{
    return `<option value='${major.major_id}'>${major.major_id} - ${major.major_name}</option>`
}

//Template Literal to Return Course Name
function courseTemplateAvailable(course) 
{
	return `<tr><td><input type='radio' name = 'radioBtn' value =${course.course_id}/>${course.course_id} - ${course.course_name}</td></tr>`;
	//getPreReqStatus();
	//setPreReqStatus();
	//setTimeout(doNothing, 1000);
	/*if (getPreReqStatus() == "true")
	{
		return `<tr><td><input type='radio' name = 'radioBtn' value =${course.course_id} />TRUE-${preReqStatus}${course.course_id} - ${course.course_name}</td></tr>`;
	}
	else
	{
		return `<tr><td><input type='radio' name = 'radioBtn' value =${course.course_id} />FALSE-${preReqStatus}${course.course_id} - ${course.course_name}</td></tr>`;
	}*/
}

//Template Literal to Return Course Name
function realCourseTemplate(course) 
{
	return `<tr><td><${course.radioButton}value =${course.course_id}/>${course.course_id} - ${course.course_name}</td></tr>`;
}

function courseTemplateUnavailable(course) 
{
	//getPreReqStatus();
	//setPreReqStatus();
	//setTimeout(doNothing, 1000);
	/*if (getPreReqStatus() == "true")
	{
		return `<tr><td><input type='radio' name = 'radioBtn' value =${course.course_id} />TRUE-${preReqStatus}${course.course_id} - ${course.course_name}</td></tr>`;
	}
	else
	{
		return `<tr><td><input type='radio' name = 'radioBtn' value =${course.course_id} />FALSE-${preReqStatus}${course.course_id} - ${course.course_name}</td></tr>`;
	}*/
	//return `<tr><td>Pre Req Not Met: ${course.course_id} - ${course.course_name}</td></tr>`
	return `<tr><td>Pre Req Not Met: ${course.course_id} - ${course.course_name}</td></tr>`;
}

function doNothing()
{
	;
}
function setPreReqStatus()
{
	if (preReqStatus == "true")
	{
		return `<tr><td><input type='radio' name = 'radioBtn' value =${course.course_id} />TRUE-${preReqStatus}${course.course_id} - ${course.course_name}</td></tr>`;
	}
	else
	{
		return `<tr><td><input type='radio' name = 'radioBtn' value =${course.course_id} />FALSE-${preReqStatus}${course.course_id} - ${course.course_name}</td></tr>`;
	}
}


function sectionTemplate(course)
{
	document.getElementById('sezmiFooter').innerHTML = course[0].course_id;
	return `course.availableSections[0].section_id`;
	//output += `</td><td><input type='checkbox' value =${jamesCoursesArray[i]} /></td></tr>`;
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
                    //const currentMajor = jQuery.parseJSON(majorStuff);
                    
                    //if the user has a major assigned, add the major to the option menu, and then load the majors in the dropdown
                    if(userName.major_id != "null")
                    {
						document.getElementById('combo').innerHTML = `<option value='nah'>${majorStuff} - ${majorStuff}</option>${majors.map(majorTemplate)}`;
					}
					//else the major isn't loaded, so give the option for select major
					else
					{
						//Template Literal to Add all Majors into HTML Dynamically
                    document.getElementById('combo').innerHTML = `<option value='nah'>Select a Major</option>${majors.map(majorTemplate)}`;
					}
                        
                }
            };
            
            //Create API Call
            xmlhttp.open("GET", '/majors');
            
            //Send API Call
            xmlhttp.send();
}

//Load Drop Down ComboBox with a List of Majors
function loadOverLoadMajors(majorName) 
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
           
					document.getElementById('combo').innerHTML = `<option value='nah'>${majorName}</option>${majors.map(majorTemplate)}`;
					
					//if the user has a major selected, warn them that they are changing their major
					if(userName.major_id != "null")
					{
						//make the button send the alert 
						document.getElementById('btnSubmit').addEventListener('click', majorChangeAlert, false);
											
					}//end major change options
					
					//else the major hasn't been selected '
					else
					{
						//the major hasn't been selected or the user has chosen to change it, so allow the button to select the major
						document.getElementById('btnSubmit').addEventListener('click', selectMajor, false);
					}
					
					
					
					
					setTimeout(loadCourses(), 1000);
                }
            };
            
            //Create API Call
            xmlhttp.open("GET", '/majors');
            
            //Send API Call
            xmlhttp.send();
}


//LoadMajorWithId load the major given the user major_id
function loadMajorWithId()
{
		   //Make a new API Request
           var xhr = new XMLHttpRequest();
            
            //Get Status
            xhr.onreadystatechange = function() 
            {
                //Check if Status is Ready
                if (this.readyState == 4 && this.status==200) 
                {
                    //Parse into JSON
                    const majorStuff = jQuery.parseJSON(xhr.responseText);
                    
                    //Assign GLobal Variable Value
                    var currentMajor = `Your Current Major is ${majorStuff.major_id} - ${majorStuff.major}`;
                    
                    //setTimeout(loadCourses(), 3000);
                    setTimeout(loadOverLoadMajors(currentMajor),500);
                    //loadCourses();
					//document.getElementById('btnSubmit').addEventListener('click', selectMajor, false); //if the major isn't selected, add the new major
                    
                    //window.alert("The user's major is : " + majorStuff.major);
                    
                    //window.alert("The user's major is : " + majorStuff.major);
                    
                    //Template Literal to Add all Majors into HTML Dynamically
                    //document.getElementById('combo').innerHTML = `<option value='nah'>${majorStuff.major_id} - ${majorStuff.major}</option>$`;
                }
            };
            
            
            //Create API Call
            xhr.open("GET", '/majors/' + userName.major_id);
            
            //Send API Call
            xhr.send();
}

//majorChangeAlert alerts the user that they are about to change their major
function majorChangeAlert()
{
	//declare variable for the message 
	var message = confirm("You are about to change your major. Are you sure you want to do that?");
	
	//if the message is true, show that the major has been selected. 
	if(message == true)
	{
		//alert that the use is trying to change their major
		window.alert("Your major has been changed!");
		//set the submit button to select the major in the combo box. 
		document.getElementById('btnSubmit').addEventListener('click', selectMajor, false);
		//call selectMajor to change the major
		selectMajor();
		
	}
	//else the major change has been aborted
	else
	{
		//alert the major cannot be changed. 
		window.alert("Your major has not been changed.");
	}
}//end majorChangeAlert

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
                    //if (true)
                    {
						document.getElementById('courses').innerHTML = `<tr><th>Select Completed Courses</th></tr>${courses.map(realCourseTemplate).join('')}`
						//create the course list. This will be appended in each pre-req method
						//coursesList = "<tr><th>Courses Needed</th></tr>";
						
						//displayAllCourses();
						
						//call function to display courses with pre reqs met
						//displayCoursesWithPreReqMet();
						//call function to display courses with pre reqs not met
						
						//having a window alert fixes the load errors
						//window.alert("does pausing fix the issue?");
						
						//displayCoursesWithPreReqNotMet();
						//window.alert("does pausing fix the issue?");
						
						
						//Generate Table of Eligible courses dynamically into HTML page
						//document.getElementById('btnSubmit').addEventListener('click', selectMajor, false); //if the major isn't selected, add the new major
						//USE FOR PRE REQ for (i=0;i<4;i++)
						//for (i=0;i<1;i++)
						//{
						//USE FOR PRE REQ  getPreReqStatus(courses,i);
						////}
						
						//let frank = getPreReqStatus();
						//document.getElementById('sezmiFooter').innerHTML = (frank).fontcolor("black").fontsize(2.1);
						//slowSetOfPreReq();
                    	/*if (preReqStatus == true)
                    	{
							document.getElementById('courses').innerHTML = `<tr><th>Select Completed Courses</th></tr>${courses.map(courseTemplate).join('')}`
						}
						else
						{
							document.getElementById('courses').innerHTML = `<tr><th>Select Completed Courses</th></tr>${courses.map(courseTemplateIfFalse).join('')}`
						}*/
                    	//document.getElementById('courses').innerHTML = `<tr><th>Select Completed Courses</th></tr>${courses.map(courseTemplate).join('')}`
                    	//document.getElementById('courses').innerHTML = coursesList;
                    	
                    	document.getElementById('submitClasses').innerHTML = '<button id="completedBtn">Submit Selection</button>'
						document.getElementById('showSections').innerHTML = '<button id="btnSection">Display Sections</button>'
						document.getElementById('showCompCourses').innerHTML = '<button id="btnCompletedCourses">Completed Courses</button>'
						document.getElementById('showChosenSections').innerHTML = '<button id="btnChosenSections">Chosen Sections</button>'
						document.getElementById('btnCompletedCourses').addEventListener('click', loadCompletedCourses, false);
						document.getElementById('completedBtn').addEventListener('click', setCourseSelection, false);
						document.getElementById('btnSection').addEventListener('click', loadSections, false);
						document.getElementById('btnChosenSections').addEventListener('click', loadChosenSections, false);
					}
					//Clear Table from Page
					else
					{
						//document.getElementById('courses').innerHTML = "";
					}
                }
            };
            
            //Create API Call 											//this might be the culprit 11/4/2021
            //xmlhttp.open("GET", '/majors/' + userEmail.user + '/courses');
            xmlhttp.open("GET", '/student/' + userEmail.user + '/all_courses');
            
            xmlhttp.setRequestHeader("Content-Type", "application/json"); //jeremy edit delete if not working
            
            //Send API Call
            xmlhttp.send();
}
//Function to print to table set of courses with pre-reqs met
function displayAllCourses()
{
	  //Make a new API Request
            xhr = new XMLHttpRequest();
            
            //Get Status
             xhr.onreadystatechange = function() 
            {
                //Check if Status is Ready
                if (this.readyState == 4 && this.status==200) 
                {
                    //Parse into JSON
                    const allCourses = jQuery.parseJSON(xhr.responseText);
                    //document.getElementById('courses').innerHTML = `<tr><th>Courses</th></tr>${coursesWithPreReqMet.map(courseTemplateAvailable).join('')}`
                    //coursesList += courseTemplateAvailable(coursesWithPreReqMet);
                   
                   
                   coursesList += `${allCourses.map(realCourseTemplate).join('')}`;
                    
                 }
                 
                 
             }
             
            //Create API Call 											//this might be the culprit 11/4/2021
            xhr.open("GET", '/student/' + userEmail.user + '/all_courses');
            
            xhr.setRequestHeader("Content-Type", "application/json"); //jeremy edit delete if not working
            
            //Send API Call
            xhr.send();
 }//End of function to display courses with pre req met


//Function to print to table set of courses with pre-reqs met
function displayCoursesWithPreReqMet()
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
                    const coursesWithPreReqMet = jQuery.parseJSON(xmlhttp.responseText);
                    //document.getElementById('courses').innerHTML = `<tr><th>Courses</th></tr>${coursesWithPreReqMet.map(courseTemplateAvailable).join('')}`
                    //coursesList += courseTemplateAvailable(coursesWithPreReqMet);
                   
                   
                   coursesList += `${coursesWithPreReqMet.map(courseTemplateAvailable).join('')}`;
                    
                 }
                 
                 
             }
             
            //Create API Call 											//this might be the culprit 11/4/2021
            xmlhttp.open("GET", '/student/' + userEmail.user + '/course_prereq_true');
            
            xmlhttp.setRequestHeader("Content-Type", "application/json"); //jeremy edit delete if not working
            
            //Send API Call
            xmlhttp.send();
 }//End of function to display courses with pre req met

//Function to print to table set of courses with pre-reqs met
function displayCoursesWithPreReqNotMet()
{
	let courses = "";
	//Make a new API Request
            xmlhttp = new XMLHttpRequest();
            
            //Get Status
             xmlhttp.onreadystatechange = function() 
            {
                //Check if Status is Ready
                if (this.readyState == 4 && this.status==200) 
                {
					//declare header by editing coursesList
					coursesList += "<tr><th>Courses Without Prerequisites Met</th></tr>";
                    //Parse into JSON
                    const coursesWithPreReqNotMet = jQuery.parseJSON(xmlhttp.responseText);
                    //document.getElementById('courses').innerHTML = `${coursesWithPreReqNotMet.map(courseTemplateUnavailable).join('')}`
                    coursesList += `${coursesWithPreReqNotMet.map(courseTemplateUnavailable).join('')}`
                   
                 }
             }
            //Create API Call 											//this might be the culprit 11/4/2021
            xmlhttp.open("GET", '/student/' + userEmail.user + '/course_prereq_false');
            
            xmlhttp.setRequestHeader("Content-Type", "application/json"); //jeremy edit delete if not working
            
            //Send API Call
            xmlhttp.send();
            
}//End of function to display courses with pre req met
  

//Function to change course availability based on preReqStatus
function getPreReqStatus(courses, i)
{
//Make API Request for Boolean value to check Pre Req status
			//var preReqStatus;
			//var coursesList = `<tr><th>Select Completed Courses</th></tr>`;
           	var tOrF = new XMLHttpRequest();
           	//Get Status
             tOrF.onreadystatechange = function() 
             {
					
                //Check if Status is Ready
                if (true) 
                {
                    //Parse into JSON
                    const preReqStatus = tOrF.responseText;
                     //const newCourses = jQuery.parseJSON(xmlhttp.responseText);
                    //document.getElementById('sezmiFooter').innerHTML = (`${courses[i].course_id}`).fontcolor("blue").fontsize(2.1);
                    document.getElementById('sezmiFooter').innerHTML = (preReqStatus).fontcolor("red").fontsize(2.1);
                    //coursesList += `<tr><td><input type='radio' name = 'radioBtn' value =${courses[i].course_id} />TRUE-${tOrF.responseText}${courses[i].course_id} - ${courses[i].course_name}</td></tr>`;
                    
                    if (preReqStatus)
                    //if(true)
                    
                    {
						window.alert("the pre-req status is " + preReqStatus);
						 document.getElementById('sezmiFooter').innerHTML = (preReqStatus).fontcolor("orange").fontsize(2.1);
						//document.getElementById('sezmiFooter').innerHTML = (`${courses[i].course_id}`).fontcolor("black").fontsize(2.1);
						//document.getElementById('courses').innerHTML = `<tr><th>Select Completed Courses</th></tr>${courses.map(courseTemplate).join('')}`
						//document.getElementById('sezmiFooter').innerHTML = (preReqStatus).fontcolor("red").fontsize(2.1);
						//coursesList += `<tr><td><input type='radio' name = 'radioBtn' value =${courses[i].course_id} />TRUE-${preReqStatus}${courses[i].course_id} - ${courses[i].course_name}</td></tr>`;
						coursesList += `<tr><td><input type='radio' name = 'radioBtn' value =${courses[i].course_id} />YES-${preReqStatus}${courses[i].course_id} - ${courses[i].course_name}</td></tr>`;
						//coursesList += `<tr><td><input type='radio' name = 'radioBtn' value =${preReqStatus}</td></tr>`;
						window.alert("is this line ever read?");
					}
					else 
					{
						 document.getElementById('sezmiFooter').innerHTML = (preReqStatus).fontcolor("blue").fontsize(2.1);
						//document.getElementById('sezmiFooter').innerHTML = (`${courses[i].course_id}`).fontcolor("black").fontsize(2.1);
						//document.getElementById('courses').innerHTML = `<tr><th>Select Completed Courses</th></tr>${courses.map(courseTemplateIfFalse).join('')}`
						coursesList += `<tr><td><input type='radio' name = 'radioBtn' value =${courses[i].course_id} />NO-${preReqStatus}${courses[i].course_id} - ${courses[i].course_name}</td></tr>`;
						window.alert("Is this line read first?");
					}
                    //preReqStatus = "hi billy";
                    //document.getElementById('sezmiFooter').innerHTML = (preReqStatus).fontcolor("black").fontsize(2.1);
                    //document.getElementById('courses').innerHTML = coursesList;
                }
             }
                 
                //Create API Call to get boolean value for Pre Req Status	-PLEASE DONT DELETE!										
            
           		
           		tOrF.open("GET", '/student/'+ student_id + "/" + courses[i].course_id +   '/course_prereq');          
                tOrF.setRequestHeader("Content-Type", "application/json"); 
            
            	//Send API Call
            	tOrF.send();
            	//return preReqStatus;
            	//END of preReqStatus
}



function returnTest()
{
	return "hi billy";
}

//getStudentMajor returns a String with the student major from the database
function getStudentMajor()
{
	
	//make a new API request
	xmlhttp = new XMLHttpRequest();

	//on successful return, do this function
	xmlhttp.onreadystatechange = function()
	{
		//if the status is ready
		if(this.readyState == 4 && this.status == 200)
		{
			
			//parse the JSON object to the global variable for the student 
			student = jQuery.parseJSON(xmlhttp.responseText);
			
			//assign the majorId from the student.major_id 
			majorId = student.major_id;
			
			//alert for testing
			window.alert(JSON.stringify(student));
			
			//alert for testing 
			window.alert("the majorId in getStudentMajor is : " + student.major_id);
		}//end of successful API status
	}//end of onreadystatechange function

	//create API call to GET the student logged in 
	xmlhttp.open("GET", "/student/" + userEmail.user + "/major");
	
	//test alert
	window.alert("current user: " + userEmail.user);

	//Send API Call
	xmlhttp.send();

}//end of getStudentMajor

function loadCompletedCourses()
{
	xmlhttp = new XMLHttpRequest();
	
	xmlhttp.onreadystatechange = function()
	{
		if (this.readyState == 4 && this.status==200) 
                {
					words = "<tr><th>Completed Courses</th></tr>";
                    //Parse into JSON
                    const completedCourses = jQuery.parseJSON(xmlhttp.responseText);
                    for (i=0;i<completedCourses.length ;i++)
                    {
						if (i>0)
							{
								words += `<br>`;
							}
							words += `<tr><td>${completedCourses[i].course_id}, ${completedCourses[i].course_name}</tr></td>`
							if (i == completedCourses.length -1)
							{
								words += "</tr></td>";
							}
					}
					document.getElementById('courses').innerHTML = words.fontcolor("white");
					
                }
            };
            
            //Create API Call
            xmlhttp.open("GET", '/student/' + userEmail.user + '/courses');
            
            //Send API Call
            xmlhttp.send();
}
//Loads all sections for student to choose from
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
						words += `<tr><td><a id="openUp"><style="red>${courseSection[i].course_id} - ${courseSection[i].course_name}</a></td></tr><tr><td>`;
						//document.getElementById("openUp").addEventListener('click',alert("hi"), false);
					
						for (z=0;z<courseSection[i].availableSections.length;z++)
						{
							if (z>0)
							{
								words += `<br>`;
							}
							//All below if & else statments involve formating the output so that it is pretty
							if ((courseSection[i].availableSections[z].term).length == 13)
							{
								if((courseSection[i].availableSections[z].remaining_spaces).length > 1)
								{
									if(courseSection[i].availableSections[z].schedule == "MWF" || courseSection[i].availableSections[z].schedule == "TTH" ||  courseSection[i].availableSections[z].schedule == "MTWTHF")
									{
										words += `<pre><${courseSection[i].radioButton}value =${courseSection[i].availableSections[z].section_id} />${courseSection[i].availableSections[z].section_id} || ${courseSection[i].availableSections[z].course_format} || ${courseSection[i].availableSections[z].term} || ${courseSection[i].availableSections[z].remaining_spaces} || ${courseSection[i].availableSections[z].schedule}     || ${courseSection[i].availableSections[z].duration} || ${courseSection[i].availableSections[z].time}   || ${courseSection[i].availableSections[z].instructor_id}`
										//words += `<pre><input type='radio' name='newRadioBtn' value =${courseSection[i].availableSections[z].section_id} />${courseSection[i].availableSections[z].section_id} || ${courseSection[i].availableSections[z].course_format} || ${courseSection[i].availableSections[z].term} || ${courseSection[i].availableSections[z].remaining_spaces} || ${courseSection[i].availableSections[z].schedule}     || ${courseSection[i].availableSections[z].duration} || ${courseSection[i].availableSections[z].time}   || ${courseSection[i].availableSections[z].instructor_id}`
									}
									else
									{
										words += `<pre><${courseSection[i].radioButton}value =${courseSection[i].availableSections[z].section_id} />${courseSection[i].availableSections[z].section_id} || ${courseSection[i].availableSections[z].course_format} || ${courseSection[i].availableSections[z].term} || ${courseSection[i].availableSections[z].remaining_spaces} || ONLINE || ${courseSection[i].availableSections[z].duration} || ${courseSection[i].availableSections[z].time} || ${courseSection[i].availableSections[z].instructor_id}`
										//words += `<pre><input type='radio' name='newRadioBtn' value =${courseSection[i].availableSections[z].section_id} />${courseSection[i].availableSections[z].section_id} || ${courseSection[i].availableSections[z].course_format} || ${courseSection[i].availableSections[z].term} || ${courseSection[i].availableSections[z].remaining_spaces} || ONLINE || ${courseSection[i].availableSections[z].duration} || ${courseSection[i].availableSections[z].time} || ${courseSection[i].availableSections[z].instructor_id}`
									}
									
									
								}
			
								else
								{
									if(courseSection[i].availableSections[z].schedule == "MWF" || courseSection[i].availableSections[z].schedule == "TTH" ||  courseSection[i].availableSections[z].schedule == "MTWTHF")
									{
										words += `<pre><${courseSection[i].radioButton}value =${courseSection[i].availableSections[z].section_id} />${courseSection[i].availableSections[z].section_id} || ${courseSection[i].availableSections[z].course_format} || ${courseSection[i].availableSections[z].term} || ${courseSection[i].availableSections[z].remaining_spaces}  || ${courseSection[i].availableSections[z].schedule}    || ${courseSection[i].availableSections[z].duration} || ${courseSection[i].availableSections[z].time}    || ${courseSection[i].availableSections[z].instructor_id}`
									}
									else
									{
										words += `<pre><${courseSection[i].radioButton}value =${courseSection[i].availableSections[z].section_id} />${courseSection[i].availableSections[z].section_id} || ${courseSection[i].availableSections[z].course_format} || ${courseSection[i].availableSections[z].term} || ${courseSection[i].availableSections[z].remaining_spaces}  || ONLINE || ${courseSection[i].availableSections[z].duration} || ${courseSection[i].availableSections[z].time} || ${courseSection[i].availableSections[z].instructor_id}`
									}
									
								}
								
							}
							else
							{
								if((courseSection[i].availableSections[z].remaining_spaces).length > 1)
								{
									if(courseSection[i].availableSections[z].schedule == "MWF" || courseSection[i].availableSections[z].schedule == "TTH" ||  courseSection[i].availableSections[z].schedule == "MTWTHF")
									{
										words += `<pre><${courseSection[i].radioButton}value =${courseSection[i].availableSections[z].section_id} />${courseSection[i].availableSections[z].section_id} || ${courseSection[i].availableSections[z].course_format} || ${courseSection[i].availableSections[z].term}   || ${courseSection[i].availableSections[z].remaining_spaces} || ${courseSection[i].availableSections[z].schedule}    || ${courseSection[i].availableSections[z].duration} || ${courseSection[i].availableSections[z].time}    || ${courseSection[i].availableSections[z].instructor_id}`
									}
									else
									{
										words += `<pre><${courseSection[i].radioButton}value =${courseSection[i].availableSections[z].section_id} />${courseSection[i].availableSections[z].section_id} || ${courseSection[i].availableSections[z].course_format} || ${courseSection[i].availableSections[z].term}   || ${courseSection[i].availableSections[z].remaining_spaces} || ONLINE || ${courseSection[i].availableSections[z].duration} || ${courseSection[i].availableSections[z].time} || ${courseSection[i].availableSections[z].instructor_id}`
									}
									
								}
								else
								{
									if(courseSection[i].availableSections[z].schedule == "MWF" || courseSection[i].availableSections[z].schedule == "TTH" ||  courseSection[i].availableSections[z].schedule == "MTWTHF")
									{
										words += `<pre><${courseSection[i].radioButton}value =${courseSection[i].availableSections[z].section_id} />${courseSection[i].availableSections[z].section_id} || ${courseSection[i].availableSections[z].course_format} || ${courseSection[i].availableSections[z].term}   || ${courseSection[i].availableSections[z].remaining_spaces}  || ${courseSection[i].availableSections[z].schedule}    || ${courseSection[i].availableSections[z].duration} || ${courseSection[i].availableSections[z].time}   || ${courseSection[i].availableSections[z].instructor_id}`
									}
									else
									{
										words += `<pre><${courseSection[i].radioButton}value =${courseSection[i].availableSections[z].section_id} />${courseSection[i].availableSections[z].section_id} || ${courseSection[i].availableSections[z].course_format} || ${courseSection[i].availableSections[z].term}   || ${courseSection[i].availableSections[z].remaining_spaces}  || ONLINE || ${courseSection[i].availableSections[z].duration} || ${courseSection[i].availableSections[z].time} || ${courseSection[i].availableSections[z].instructor_id}`
									}
								}
								
							}
							//words += `<pre><input type='radio' name='newRadioBtn' value =${courseSection[i].course_id} />${courseSection[i].availableSections[z].section_id}  ||   ${courseSection[i].availableSections[z].course_format}  ||   ${courseSection[i].availableSections[z].term}          ||   ${courseSection[i].availableSections[z].instructor_id}`
							if (z == courseSection[i].availableSections.length -1)
							{
								words += "</td></tr>";
							}
						}
					}
                    document.getElementById('courses').innerHTML = words.fontcolor("white");	
                }
            };
            
            //Create API Call
            xmlhttp.open("GET", '/majors/' + userEmail.user + '/courses');
            //xmlhttp.open("GET", '/courses/ENG-260/sections');
            
            //Send API Call
            xmlhttp.send();
            
            //document.getElementById('submitClasses').innerHTML = '<button id="">Submit Selection</button>'
            document.getElementById('completedBtn').addEventListener('click', setSectionSelection, false);
}
//Loads sections from the student_section table that student has chosen
function loadChosenSections()
{
	xmlhttp = new XMLHttpRequest();
	
	xmlhttp.onreadystatechange = function()
	{
		if (this.readyState == 4 && this.status==200) 
		    {
					sectionTableInfo = "<tr><th>Chosen Sections</th></tr>";
				   //Parse into JSON
                    const chosenSections = jQuery.parseJSON(xmlhttp.responseText);
                   for (i=0;i<chosenSections.length ;i++)
                    {
						if (i>0)
							{
								sectionTableInfo += `<br>`;
							}
							sectionTableInfo += `<tr><td><pre>${chosenSections[i].section_id}   ||   ${chosenSections[i].course_format}   ||   ${chosenSections[i].term}   ||   ${chosenSections[i].remaining_spaces}   ||   ${chosenSections[i].schedule}   ||   ${chosenSections[i].duration}   ||   ${chosenSections[i].time}   ||   ${chosenSections[i].instructor_id}</tr></td>`
							if (i == chosenSections.length -1)
							
							{
								sectionTableInfo += "</tr></td>";
							}
					}
					document.getElementById('courses').innerHTML = sectionTableInfo.fontcolor("white");
					
					
                }
            };
            
            //Create API Call
            xmlhttp.open("GET", '/student/' + userEmail.user + '/sections');
            
            //Send API Call
            xmlhttp.send();
	
}


//Get Major Selection and Store as a Variable
function selectMajor()
{
	//use a get request the get the user name 
	/*xmlhttp.open("GET", "/student/" + userEmail.user, true);
		
		xmlhttp.setRequestHeader("Content-Type", "application/json");
		
		xmlhttp.send(JSON.stringify({"major_id": majorId}));*/
    var majorSelection = document.querySelector('#combo');
    majorId = majorSelection.value;
    
    setTimeout(patchMajorSelection,500);
    setTimeout(loadCourses,750);
    
}

function setCourseSelection()
    {
		
		//var checkboxArray = [];
		
        var checkbox = document.querySelector('input[type="radio"]:checked');
        //const checkboxArray = document.querySelectorAll("td[value]");
        /*for (var i= 0; i < checkbox.length; i++){
			checkboxArray.push(checkbox[i].value)
		}*/
        var checkboxSelection = checkbox.value;
	    //checkboxSelection = checkbox.parentElement.textContent;
        //alert(checkboxSelection);
        patchCompletedCourses(checkboxSelection);
        //postCourses();
        setTimout(loadCourses, 500);
        
        //document.getElementById("completedBtn").style.display = "none";
    }
    
function setSectionSelection()
    {
		
		//var checkboxArray = [];
		
        var checkbox = document.querySelector('input[type="radio"]:checked');
        //const checkboxArray = document.querySelectorAll("td[value]");
        /*for (var i= 0; i < checkbox.length; i++){
			checkboxArray.push(checkbox[i].value)
		}*/
        var checkboxSelection = checkbox.value;
	    //checkboxSelection = checkbox.parentElement.textContent;
        //alert(checkboxSelection);
        patchSectionSelection(checkboxSelection);
        loadSections();
        
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
		
		xmlhttp.send(JSON.stringify({"email": userEmail.value, "course_id": checkbox.value, "grade": "null", "term": "null"}));
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
    if ((student/userEmail.majorId).includes(majorId))
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
