//Global Scope Variable to Hold JSON Object
var xmlhttp;

//Activate Event Listeners on Page Load
window.onload = function()
{
    init();
};

//Initialize all Event Listeners
function init()
{
    document.getElementById('btnSubmit').addEventListener('click', showMajor, false);
}

//Receive Json File and Populate Drop Down Menu FOR TESTING PURPOSES ONLY
function receiveJson() 
{
    //Test if Server is Ready and Available
    if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            var json = jQuery.parseJSON(xmlhttp);
            var output = "'<option>'";
            output += json + "'</option>'"
            document.getElementById('combo').innerHTML = output;
        }
}

//Get Major Selection and Store as a Variable
function selectMajor()
{
    var majorSelection = document.getElementById('major');
}

//Show Major Requirements
function showMajor()
{
    
}

function clearList()
{
    document.getElementById('combo').innerHTML = "";
    store.clear();
}