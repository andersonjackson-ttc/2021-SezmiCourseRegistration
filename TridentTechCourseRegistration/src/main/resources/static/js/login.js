//Global Scope Variable to Hold JSON Object and Parsed Text Data
var xmlhttp;
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
    loadMajors();
    document.getElementById('btn-log-on').addEventListener('click', , false);
}
//Aaron Will Finish This By The Weekend of 10/08/2021