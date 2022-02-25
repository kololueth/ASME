$(document).ready(function () {

	// Attach Listeners

	document.getElementById('firstVideo').addEventListener('ended', firstVideo, false);
  
	document.getElementById('secondVideo').addEventListener('ended', secondVideo, false);

	document.getElementById('thirdVideo').addEventListener('ended', thirdVideo, false);

	document.getElementById('fourthVideo').addEventListener('ended', fourthVideo, false);
	
	// Prevents contextmenu from displaying when right clicking on browser page
	document.addEventListener('contextmenu', event => event.preventDefault());
	
	
	$('#fourthVideo').show();
	$('#fourthVideo').trigger('play');

	applicant = new Object();
  

}); // End of Document.ready()


							////////////////////////
/////////////////////////////// EVENT HANDLERS ////////////////////////////
						////////////////////////


  function firstVideo(e) { 
  
		$('#getStarted').show(); 
		
	} // End of Handler

  function secondVideo(e) { 
  
		$('#USMap').show(); 
		$('#VTState').show(); 
		$('#VTText').show();
		
	} // End of Handler

  function thirdVideo(e) { 
  
		$('#ageSelect').show(); 
		$('#ageSelectButton').show(); 
		
	} // End of Handler

  function fourthVideo(e) { 
  
		$('#Apply').show();
		$('#ApplyUpper').show(); 
		$('#ViewSave').show(); 
		$('#ViewSaveUpper').show(); 
		$('#Email').show(); 
		$('#EmailUpper').show(); 
		$('#ClickHere').show(); 
	

	} // End of Handler


							////////////////////////
//////////////////////////// ON CLICK FUNCTIONS ////////////////////////////
						////////////////////////


  function playVideo(video) {
	  
	  
		if(video == 2) { // video 2 ends show US Map
		  
			
		//	$('#getStarted').hide(); // invisible Button
		
			hideButtons();

			$('#secondVideo').css('z-index', '10');
			$('#secondVideo').fadeIn(300);
			$('#secondVideo').trigger('play');
			
			setTimeout(() => { $('#firstVideo').hide(); }, 500);
			$('#firstVideo').css('z-index', '5');
		 
	  } // End of if
	  
	  if(video == 3) { // video 3 ends showing blocks
		  
		//	$('#USMap').hide();
		//	$('#VTState').hide();
		//	$('#VTText').hide();

			hideButtons();

			$('#thirdVideo').css('z-index', '10');	
			$('#thirdVideo').fadeIn(300);
			$('#thirdVideo').trigger('play');

			setTimeout(() => { $('#secondVideo').hide(); }, 500);
			$('#secondVideo').css('z-index', '5');  
		 
	  } // End of if
	  
	  if(video == 4) { // video 4 ends showing apply buttons
		  
		//	$('#ageSelect').hide();
		//	$('#ageSelectButton').hide();

			hideButtons();

			$('#fourthVideo').css('z-index', '10');

			$('#fourthVideo').fadeIn(300);
			$('#fourthVideo').trigger('play');
		  
	//		setTimeout(() => { $('#thirdVideo').hide(); }, 500);
                        $('#thirdVideo').css('z-index', '5'); 
		  
	  } // End of if
	  
	  
  } // End of playVideo()


 function hideVideos() {



 }
  

 function hideButtons(){

	$('#getStarted').hide(); // invisible Button

	$('#USMap').hide();
        $('#VTState').hide();
        $('#VTText').hide();
	
	$('#ageSelect').hide();
        $('#ageSelectButton').hide();

	$('#Apply').hide();
        $('#ApplyUpper').hide(); 
        $('#ViewSave').hide(); 
        $('#ViewSaveUpper').hide(); 
        $('#Email').hide(); 
        $('#EmailUpper').hide(); 
        $('#ClickHere').hide(); 


 } // End of hide Buttons
  
  function setState(state) {
	  
	// 	applicant = new Object();
		
		applicant.state = state;
		applicant.email = "";
	
		$('#dialogText').text(dialogJSON[applicant.state]);
		
	
			if(applicant.state == "VT") {
				
				$('#USMap').hide(); 
				$('#VTState').hide(); 
				$('#VTText').hide();
				
				$('#forwardDialog').show(); // Further functions controlled by modal/dialog are in dialog.js
				
				delete applicant;
		
			} // End of if
			
			if(applicant.state == "ALL") {
				
			   $('#forwardDialog').show(); // Further functions controlled by modal/dialog are in dialog.js
		
			} // End of if
	  
  } // End of setState()
  
  
  function setAge(){
	  
		applicant.age = $('#ageSelect').val();
			  
		playVideo(4);
	  
  } // End of setAge()
  
  
  function showEmailForm() {
	  
		$('#Apply').hide();
		$('#ApplyUpper').hide(); 
		$('#ViewSave').hide(); 
		$('#ViewSaveUpper').hide(); 
		$('#Email').hide(); 
		$('#EmailUpper').hide(); 
		$('#ClickHere').hide(); 
	  

		$('#emailStep').fadeIn(1000);
	  
	  
  } // End of showEmailForm()


  function sendEmail() {


// Validate Email Input 	//

	 applicant.email = $('#emailInput').val();

	 regex = new RegExp(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/);

	 	if(regex.test(applicant.email)) {

			// alert("passes email test");

	 	} else {

			alert("not a valid email format");

			return;

		} // End of if/else


	 //  Create and set up http request //

	  var xhttp = new XMLHttpRequest();

	  // xhttp.open("POST", "http://216.137.177.126:8080/ASME/email", true);

	  xhttp.open("POST", "http://localhost:8080/ASME/email", true);

	
	  xhttp.setRequestHeader("Content-Type", "text/plain");

         // Event Handlers ///  

	  xhttp.onreadystatechange = function() {  // Success Response Action

		if (this.readyState == XMLHttpRequest.DONE && this.status == 200) {

			//  alert("ready state is OK");


			responseJSONObject = JSON.parse(this.responseText);


			if(responseJSONObject.hasOwnProperty("status") && responseJSONObject.hasOwnProperty("email")) {

				if(responseJSONObject.status == "confirmed") {

					alert("ASME Decision Life Guide sent to " + responseJSONObject.email);

					// alert("File sent to " + responseJSONObject.email + "\n\n\r JSON Object shown below: \n\r" + JSON.stringify(responseJSONObject));

				} else if(responseJSONObject.status == "error") {

					alert("There was an Error, the email was not sent: \n\r " + responseJSONObject.status_message);

				} else if (responseJSONObject.status == "UD") {

					alert("Unknown Issue");

				} // End of if/else if

			} else {

				alert("JSON key issue");

			}  // End of if/else


		} // End of if

	  }; // End of onreadystatechange


	// Event Handlers //

	xhttp.onerror = function() { alert("There was an error"); };

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// THE FOLLOWING EVENTS ARE USED FOR DEBUGGING -  DO NOT UNCOMMENT UNLESS DEBUGGING - DO NOT ERASE - Kolo Lueth 01-24-2022 //
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

 	 // xhttp.onload = function() { alert("on load"); };

     // xhttp.onabort = function() { alert("Operation Aborted"); };

     // xhttp.onloadstart = function() { alert("onload start"); };

	 // xhttp.onprogress = function(e) { alert("on progress"); };

	///////////////////////////////////////////// END OF DEBUGGING TOOLS ////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	 // Assembles Data and Sends to Server //

	requestJSONObject = JSON.parse("{\"function\":\"email\", \"email\":\"" + applicant.email + "\"}"); 

	xhttp.send(JSON.stringify(requestJSONObject));

	alert("Sending email to " + applicant.email);

  }  // End of sendEmail()

