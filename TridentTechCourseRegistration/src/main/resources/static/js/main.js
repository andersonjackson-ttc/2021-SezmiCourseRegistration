//Global Scope Variable to Hold JSON Object and Parsed Text Data
var xmlhttp;
var text;

//Activate Event Listeners on Page Load
window.onload = function()
{
    init();
//    alert("Application Running");
    
};

//Initialize all Event Listeners
function init()
{
	//Make a new API Request
    xmlhttp = new XMLHttpRequest();
    loadMajors();
//    document.getElementById('btnSubmit').addEventListener('click', selectMajor, false);
    
//    document.getElementById('btnSubmit').addEventListener('click', showMajor, false);
}

//Load Drop Down ComboBox with a List of Majors
function loadMajors() 
{
    //For Loop for every Major
    for (let i = 0; i < 10; i++)
        {
            //Make a new API Request
            xmlhttp = new XMLHttpRequest();
            
            //Get Status
            xmlhttp.onreadystatechange = function() 
            {
                //Check if Status is Ready
                if (this.readyState == 4 && this.status == 200) 
                {
                    var json = jQuery.parseJSON(xmlhttp.responseText);
                    text = json.value.name;
                    var output = "<option value='";
                    output += i + "'>" + text + "</option>";
                    document.getElementById('combo').innerHTML += output;
                }
            };
            
            //Create API Call
//            xmlhttp.open("GET", 'http://api.icndb.com/jokes/random/', false);
            xmlhttp.open("GET", 'https://localhost:8080/products');
            
            //Send API Call
            xmlhttp.send();
        }
}