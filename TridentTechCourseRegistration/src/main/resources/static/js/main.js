//Global Scope Variable to Hold JSON Object and Parsed Text Data
var student = ""; //declared global variable for student JH 11/12
var index = 0;
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
    
    //Load User Name
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
					;
					//document.getElementById('sezmiFooter').innerHTML = (checkboxSelection).fontcolor("black").fontsize(2.1);
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
    let x = new XMLHttpRequest();
           
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
    let x = new XMLHttpRequest();
           
      //Get Status
      x.onreadystatechange = function()
      {
          //Check if Status is Ready
          if (true)
          {
            //Store Returned JSON String in Global Variable
            userEmail = jQuery.parseJSON(x.responseText);
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
}

//Template Literal to Return Course Name
function realCourseTemplate(course) 
{
	let radioButtonText = "";
	if (course.availabilty == "True")
	{
		radioButtonText = `input type='checkbox' name = 'checkBox${index}' `;
	}
	else
	{
		radioButtonText = `input type='checkbox' disabled = true name = 'checkBox${index}' `;
	}
	index++;
	return `<tr><td><${radioButtonText}value =${course.course_id}>${course.course_id} - ${course.course_name} - ${course.availabilty}</td></tr>`;	
}

function courseTemplateUnavailable(course) 
{
	return `<tr><td>Pre Req Not Met: ${course.course_id} - ${course.course_name}</td></tr>`;
}

function setPreReqStatus()
{
	if (preReqStatus == "true")
	{
		return `<tr><td><input type='radio' name = 'radioBtn' value =${course.course_id} />${preReqStatus}${course.course_id} - ${course.course_name}</td></tr>`;
	}
	else
	{
		return `<tr><td><input type='radio' name = 'radioBtn' value =${course.course_id} />${preReqStatus}${course.course_id} - ${course.course_name}</td></tr>`;
	}
}


function sectionTemplate(course)
{
	//document.getElementById('sezmiFooter').innerHTML = course[0].course_id;
	return `course.availableSections[0].section_id`;
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
					
					setTimeout(loadCourses, 1000);
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
	
						index = 0;
						
						//Show "Select Completed Courses Table"
						document.getElementById('courses').innerHTML = `<tr><th>Select Completed Courses</th></tr>${courses.map(realCourseTemplate).join('')}`
						
						//Show Array of New Buttons
                    	document.getElementById('submitClasses').innerHTML = '<button id="completedBtn">Submit</button>'
						document.getElementById('showSections').innerHTML = '<button id="btnSection">Display Sections</button>'
						document.getElementById('showCompCourses').innerHTML = '<button id="btnCompletedCourses">Completed Courses</button>'
						document.getElementById('showChosenSections').innerHTML = '<button id="btnChosenSections">Chosen Sections</button>'
						document.getElementById('removeSelection').innerHTML = '<button id="removeBtn">Remove</button>'
						
						//Add Appropriate Event Listeners
						document.getElementById('btnCompletedCourses').addEventListener('click', loadCompletedCourses, false);
						document.getElementById('completedBtn').addEventListener('click', setCourseSelection, false);
						document.getElementById('btnSection').addEventListener('click', loadSections, false);
						document.getElementById('btnChosenSections').addEventListener('click', loadChosenSections, false);
						document.getElementById('btnSubmit').innerHTML = 'Change Major';
						document.getElementById('loadCourses').innerHTML = '<button id="loadCoursesBtn">Courses Needed</button>';
						document.getElementById('loadCoursesBtn').addEventListener('click', loadCourses, false);
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
            
            document.getElementById('completedBtn').disabled = false;
            document.getElementById('removeBtn').disabled = true;
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

					index = 0;
                    coursesList += `${allCourses.map(realCourseTemplate).join('')}`;
                    
                 }
             }
             
            //Create API Call 											//this might be the culprit 11/4/2021
            xhr.open("GET", '/student/' + userEmail.user + '/all_courses');
            
            xhr.setRequestHeader("Content-Type", "application/json"); //jeremy edit delete if not working
            
            //Send API Call
            xhr.send();
 }//End of function to display courses with pre req met

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
							words += `<tr><td><input type = 'checkbox' name = 'courseDeletion${i}' id = 'deleteCourse' value ='${completedCourses[i].course_id}'>${completedCourses[i].course_id}, ${completedCourses[i].course_name}</tr></td>`
							if (i == completedCourses.length -1)
							{
								words += "</tr></td>";
							}
					}
					document.getElementById('courses').innerHTML = words.fontcolor("white");
					document.getElementById('removeBtn').addEventListener('click', removeCourseSelection, false);
                }
            };
            
            //Create API Call
            xmlhttp.open("GET", '/student/' + userEmail.user + '/courses');
            
            //Send API Call
            xmlhttp.send();
            //document.getElementById('loadCoursesBtn').addEventListener('click', loadCourses, false);
            document.getElementById('completedBtn').disabled = true;
            //document.getElementById('removeBtn').addEventListener('click', removeCourseSelection, false);
            document.getElementById('removeBtn').disabled = false;
}
//Loads all sections for student to choose from
function loadSections()
{
	xmlhttp = new XMLHttpRequest();
	let words = "";
    let corLength;       
            //Get Status
             xmlhttp.onreadystatechange = function() 
            {
                //Check if Status is Ready
               if (this.readyState == 4 && this.status==200) 
                {
					words = "<div id='sectionPage'><tr><th>Remaining Courses</th></tr>";
                    //Parse into JSON
                    const courseSection = jQuery.parseJSON(xmlhttp.responseText);
                    corLength = courseSection.length;
                    for (i=0;i<courseSection.length ;i++)
                    {
	     				let preReqNotMet = "";
						if (courseSection[i].availabilty == "False")
						{
							preReqNotMet = "Pre Reqs Not Met";		
						}
						
						
						//words +=`Course ID of ${courseSection[i].course_id}: `;
						words += `<tr><td><a><font color = black>${courseSection[i].course_id} - ${courseSection[i].course_name}</font>&nbsp;&nbsp;&nbsp;&nbsp;<font color = red>${preReqNotMet}</font></a></td></tr><tr><td><pre><div style="color:blue"><b>      Section            Term        Format         Duration        Schedule       Time              Instructor       Remaining Seats</b></div>`;
						//document.getElementById("openUp").addEventListener('click',alert("hi"), false);
					
						for (z=0;z<courseSection[i].availableSections.length;z++)
						{
							if (z>0)
							{
								words += `<br><div style="height:1px;background-color:gray;color:blue"><b></b></div>`;
							}
							
							let section = courseSection[i].availableSections[z].section_id;
							let courseFormat = courseSection[i].availableSections[z].course_format;
							let term = courseSection[i].availableSections[z].term;
							let remainingSpaces = courseSection[i].availableSections[z].remaining_spaces;
							let schedule = courseSection[i].availableSections[z].schedule;
							let duration = courseSection[i].availableSections[z].duration;
							let time = courseSection[i].availableSections[z].time;
							let instructor = courseSection[i].availableSections[z].instructor_id;
							let oneSpace = " ";
							let formatSpacing = "";
							let radioButton = "";
							let noRadioBtnFormatter = "";
							
							if (section.length >= 0 && section.length < 15)
							{
								formatSpacing = oneSpace.repeat(15 - section.length);
								section += formatSpacing;
							}
							
							if (courseFormat.length >=0 && courseFormat.length < 4)
							{
								formatSpacing = oneSpace.repeat(4 - courseFormat.length);
								courseFormat += formatSpacing;
							}
							
							if (term.length >=0 && term.length < 15)
							{
								formatSpacing = oneSpace.repeat(15 - term.length);
								term += formatSpacing;
							}
							
							if (remainingSpaces.length >=0 && remainingSpaces.length < 15)
							{
								formatSpacing = oneSpace.repeat(15 - remainingSpaces.length);
								remainingSpaces += formatSpacing;
							}
							
							if (schedule.length >=0 && schedule.length < 4)
							{
								formatSpacing = oneSpace.repeat(4 - schedule.length);
								schedule += formatSpacing;
							}
							
							if (duration.length < 22)
							{
								formatSpacing = oneSpace.repeat(22 - duration.length);
								duration += formatSpacing;
							}
							
							if (time.length < 16)
							{
								formatSpacing = oneSpace.repeat(16 - time.length);
								time += formatSpacing;
							}
							
							if (instructor.length < 19)
							{
								formatSpacing = oneSpace.repeat(19 - instructor.length);
								instructor += formatSpacing;
							}
							
							
							
							if (courseSection[i].availabilty == "True")
							{
								//radioButton = "input type='radio' name = 'newRadioBtn[ " + i + "]' ";
								//radioButton = "input type='radio' name = 'newRadioBtn' ";
								radioButton = "input type='radio' name = 'newRadioBtn" + i + "' ";
							}
							
							else
							{
								noRadioBtnFormatter = "   ";
							}
							words += `<pre><${radioButton}value =${section} />${noRadioBtnFormatter}<b>${section}</b>||<b> ${term}</b>||<b> ${courseFormat}</b>||<b> ${duration}</b>||<b> ${schedule}</b>||<b> ${time}</b>||<b> ${instructor}</b>||<b> ${remainingSpaces}</b>`
			
							if (z == courseSection[i].availableSections.length -1)
							{
								words += "</td></tr></div>";
							}
						}
						
					}
                    document.getElementById('courses').innerHTML = words.fontcolor("white");	
                    document.getElementById('completedBtn').addEventListener('click', setSectionSelection(corLength), false);
                }
            };
            
            //Create API Call
            xmlhttp.open("GET", '/majors/' + userEmail.user + '/courses');
            //xmlhttp.open("GET", '/courses/ENG-260/sections');
            
            //Send API Call
            xmlhttp.send();
            
            //document.getElementById('submitClasses').innerHTML = '<button id="">Submit Selection</button>'
            document.getElementById('completedBtn').addEventListener('click', setSectionSelection(corLength), false);
            //document.getElementById('loadCoursesBtn').addEventListener('click', loadCourses, false);
            document.getElementById('completedBtn').disabled = false;
            document.getElementById('removeBtn').disabled = true;
            
}
//Loads sections from the student_section table that student has chosen
function loadChosenSections()
{
	xmlhttp = new XMLHttpRequest();
	
	xmlhttp.onreadystatechange = function()
	{
		if (this.readyState == 4 && this.status==200) 
		    {
					sectionTableInfo = "<tr><th>Chosen Sections<br></th></tr><tr><td>";
				   //Parse into JSON
                    const chosenSections = jQuery.parseJSON(xmlhttp.responseText);
                   for (i=0;i<chosenSections.length ;i++)
                    {
						if (i>0)
							{
								sectionTableInfo += `<br>`;
							}
							let section = chosenSections[i].section_id;
							let term = chosenSections[i].term;
							let courseFormat = chosenSections[i].course_format;
							let duration = chosenSections[i].duration;
							let schedule = chosenSections[i].schedule;
							let time = chosenSections[i].time;
							let instructor = chosenSections[i].instructor_id;
							let remainingSpaces = chosenSections[i].remaining_spaces;
							let oneSpace = " ";
							let formatSpacing = "";
							
							if (section.length >= 0 && section.length < 15)
							{
								formatSpacing = oneSpace.repeat(15 - section.length);
								section += formatSpacing;
							}
							
							if (courseFormat.length >=0 && courseFormat.length < 4)
							{
								formatSpacing = oneSpace.repeat(4 - courseFormat.length);
								courseFormat += formatSpacing;
							}
							
							if (term.length >=0 && term.length < 15)
							{
								formatSpacing = oneSpace.repeat(15 - term.length);
								term += formatSpacing;
							}
							
							if (remainingSpaces.length >=0 && remainingSpaces.length < 15)
							{
								formatSpacing = oneSpace.repeat(15 - remainingSpaces.length);
								remainingSpaces += formatSpacing;
							}
							
							if (schedule.length >=0 && schedule.length < 4)
							{
								formatSpacing = oneSpace.repeat(4 - schedule.length);
								schedule += formatSpacing;
							}
							
							if (duration.length < 22)
							{
								formatSpacing = oneSpace.repeat(22 - duration.length);
								duration += formatSpacing;
							}
							
							if (time.length < 16)
							{
								formatSpacing = oneSpace.repeat(16 - time.length);
								time += formatSpacing;
							}
							
							if (instructor.length < 19)
							{
								formatSpacing = oneSpace.repeat(19 - instructor.length);
								instructor += formatSpacing;
							}
							
							//<pre><div style="height:1px;color:blue"><b>     Section                Term             Format              Duration               Schedule           Time                   Instructor              Remaining Seats</b></div><br>
							sectionTableInfo += `<pre><div style="height:1px;color:blue"><b>     Section                Term                Format           Duration                Schedule           Time                   Instructor              Remaining Seats</b></div><br><pre><input type = 'radio' name = 'testingName' id = 'deleteSection' value ='${section}'>${section}   ||   ${term}   ||   ${courseFormat}   ||   ${duration}   ||   ${schedule}   ||   ${time}   ||   ${instructor}   ||   ${remainingSpaces}`
							
							if (i == chosenSections.length -1)
							
							{
								sectionTableInfo += "</td></tr>";
							}
					}
					//document.getElementById('courses').innerHTML = sectionTableInfo.fontcolor("white");
					//document.getElementById('completedBtn').addEventListener('click', removeSectionSelection, false);
                }
            };
            
            //Create API Call
            xmlhttp.open("GET", '/student/' + userEmail.user + '/sections');
            
            //Send API Call
            xmlhttp.send();
            document.getElementById('courses').innerHTML = sectionTableInfo.fontcolor("white");
			document.getElementById('removeBtn').addEventListener('click', removeSectionSelection, false);
            //document.getElementById('loadCoursesBtn').addEventListener('click', loadCourses, false);
            document.getElementById('completedBtn').disabled = true;
            document.getElementById('removeBtn').disabled = false;
	
}


//Get Major Selection and Store as a Variable
function selectMajor()
{
    var majorSelection = document.querySelector('#combo');
    majorId = majorSelection.value;
    
    setTimeout(patchMajorSelection,500);
    setTimeout(loadCourses,750); 
}

function setCourseSelection()
    {
		let checkBoxSelectionString = "";
		let commaDelimiter = "";
			for (i=0;i<index;i++)
			{
				let checkbox = document.querySelector(`input[name="checkBox${i}"]:checked`);
				if(checkbox != null)
				{
					if (checkBoxSelectionString.length > 0)
					{
						commaDelimiter = ",";
					}
					checkBoxSelectionString += commaDelimiter + checkbox.value;
				}
			}
		//document.getElementById('sezmiFooter').innerHTML = (checkBoxSelectionString).fontcolor("black").fontsize(2.1)
		//window.Alert('hi');
        
        patchCompletedCourses(checkBoxSelectionString);
        setTimeout(loadCourses,500);
    }

function removeCourseSelection()
    {
		let checkBoxSelectionString = "";
		let commaDelimiter = "";
			for (i=0;i<15;i++)
			{
				let checkbox = document.querySelector(`input[name="courseDeletion${i}"]:checked`);
				if(checkbox != null)
				{
					if (checkBoxSelectionString.length > 0)
					{
						commaDelimiter = ",";
					}
					checkBoxSelectionString += commaDelimiter + checkbox.value;
				}
			}
		//document.getElementById('sezmiFooter').innerHTML = (checkBoxSelectionString).fontcolor("black").fontsize(2.1);
        
        //patchCompletedCourses(checkBoxSelectionString);
        deleteCourseSelection(checkBoxSelectionString);
        setTimeout(loadCompletedCourses,500);
    }
    
    
    
function setSectionSelection(numberOfCourses)
    {
		return function()
		{
			let checkBoxSelectionString = "";
			let commaDelimiter = "";
			for (i=0;i<=numberOfCourses;i++)
			{
				let checkbox = document.querySelector(`input[name = "newRadioBtn${i}"]:checked`);
				if(checkbox != null)
				{
					if (checkBoxSelectionString.length > 0)
					{
						commaDelimiter = ",";
					}
					checkBoxSelectionString += commaDelimiter + checkbox.value;
				}
			}
        	patchSectionSelection(checkBoxSelectionString);
        	setTimeout(loadChosenSections, 950);
		}
    }

/*
//Sets up the course selection to be deleted
function removeCourseSelection()
{
	let checkbox = document.querySelector('input[type="radio"]:checked');
	let checkboxSelection = checkbox.value;
	deleteCourseSelection(checkboxSelection);
}  */

//template patch mapping used to delete selected course from a student saying they already have taken that course
function deleteCourseSelection(checkboxSelection)
{
	xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function()
	{
		if (true)
		{
			window.alert(`You have successfully removed ${checkboxSelection}`);
			//document.getElementById('sezmiFooter').innerHTML = (checkboxSelection).fontcolor("black").fontsize(2.1);
		}
	}
	
	xmlhttp.open("DELETE", "/student/" + student_id + "/" + checkboxSelection, true);
	xmlhttp.setRequestHeader("Content-Type", "application/json");
	xmlhttp.send();
}


//Sets up the section selection to be deleted
function removeSectionSelection()
{
	let checkbox = document.querySelector('input[type="radio"]:checked');
	let checkboxSelection = checkbox.value;
	deleteSectionSelection(checkboxSelection);
	
	//setTimeout(loadChosenSections,750);
	//loadChosenSections;
}

//BRAND NEW added 12/2
//template of patch mapping used to delete selected section
function deleteSectionSelection(checkboxSelection)
 {
	xmlhttp = new XMLHttpRequest();
        
        xmlhttp.onreadystatechange = function()
        	{
				if(this.status==200)
				{
					window.alert(`${checkboxSelection}has been removed successfully!`);
					loadChosenSections();
					//document.getElementById('sezmiFooter').innerHTML = (checkboxSelection).fontcolor("black").fontsize(2.1);
					//window.alert(`${checkboxSelection}has been removed!`);
					//setTimout(loadChosenSections,500);
					//loadChosenSections();
					
				}
			}
		
		
		xmlhttp.open("DELETE", "/students/" + student_id + "/" + checkboxSelection, true);
		
		xmlhttp.setRequestHeader("Content-Type", "application/json");
		
		xmlhttp.send();
		//window.alert('success');
		//setTimeout(loadChosenSections,1000);
		//window alert for testing
		//window.alert(checkboxSelection);
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
