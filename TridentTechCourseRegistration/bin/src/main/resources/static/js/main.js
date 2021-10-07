//Global Scope Variable to Hold JSON Object and Parsed Text Data
var xmlhttp;
var majorId;

//Activate Event Listeners on Page Load
window.onload = function()
{
    init();
};

//Initialize all Event Listeners
function init()
{
    loadMajors();
    document.getElementById('btnSubmit').addEventListener('click', selectMajor, false);
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
    if ((majorRequirements.majorId).includes(majorId))
    {
		//Declare needed local variables
		let output = "";
		let courseList = majorRequirements.james;
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
			test = jamesCoursesArray[i].substring(0,3);
			
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
                    	document.getElementById('courses').innerHTML = `<tr><th>Eligible Courses</th></tr>${courses.map(courseTemplate).join('')}`
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