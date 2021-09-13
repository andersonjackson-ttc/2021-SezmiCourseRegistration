function helloTeam()
{ 
  const HELLO_ARRAY = ['Hello, Team Sezmi!', 'Hello, Jeremy!', 'Hello, Aaron!', 'Hello, Ariana!', 'Hello, Billy!', 'Hello, James!', 'Hello, NSA!'];
  let numHellos = parseInt(window.prompt("How many hellos would you like?"));
  
   //generate the hello messages at random
  for(let i=0; i < numHellos; i++)
  {
    alert(HELLO_ARRAY[Math.floor(Math.random() * 7)]);
  }
}//end helloTeam

helloTeam();
