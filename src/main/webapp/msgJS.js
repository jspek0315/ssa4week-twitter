/**
 * 
 */
var baseURL = "http://localhost:8080"
function addTweet()
{

	var newMsg = document.getElementById('textBoxID').value;
	
	window.location.href = baseURL + '/SaveMSG' + '?User_Name=Bob&' + 'Msg=' + newMsg;

	updateHTML();
	alert("Finished Updating HTML");
}

function updateHTML()
{
//	//Get User ID
//	var userID = getUserID();
//	//Create URL 
//	var requestURL = generateURL(userID);
	var requestURL = baseURL + "/src/main/resources/JSON_File1.txt";
	
	alert("Entered updateHTML");
	
	//Get JSON and Update HTML
	var xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function() 
	{
		var msg2 = "Ready State: " + xhttp.readyState;
		msg2 += "\nStatus : " + xhttp.status;
		alert(msg2);
		if (xhttp.readyState === 4 ) //&& xhttp.status == 200) 
		{
		    var txtJSON = xhttp.responseText;
		    writeTextToHTML(txtJSON);
		    alert(txtJSON);
		}
	}
	
	xhttp.open("GET", requestURL, true);
	xhttp.send();
	alert("Sent Get Request");
}

function writeTextToHTML(txtJSON)
{
	//Convert JSON to JavaScript Object
	var jsObj = JSON.parse(txtJSON);
	
	//Remove Old Images 
	removePosts();
	
	alert("Entering Write Loop");
	
	//Loop through jsObj
	for(var i=0; i < jsObj.Message_List.length; i++)
	{
		var msg = jsObj.Message_List[i].msg;
		
		//Create image element
		var paragraphNode = document.createElement("p");
		paragraphNode.innerHTML = msg;
		alert(msg);
		document.getElementById('outputID').appendChild(paragraphNode);
	}
	
}

function removePosts()
{
	// Get the <ul> element with id="myList"
	var list = document.getElementById("outputID");

	// As long as <ul> has a child node, remove it
	while (list.hasChildNodes()) 
	{   
		list.removeChild(list.firstChild);
	}
}