  function forward(){
	  
		$('#dialogText').text("");
		$('#forwardDialog').hide();
	  
			if(applicant.state == "VT"){
				
				window.open("https://www.asmeinsurance.com/?utm&utm_medium=referral&utm_campaign=98401-gltl&utm_content=life-decision-guide");
				
				$('#USMap').show(); 
				$('#VTState').show(); 
				$('#VTText').show();
			
			} // End of if
			
			if(applicant.state == "ALL" && applicant.email == "") {
				
				 playVideo(3);
		
			} // End of if
			
			if(applicant.email != "") {
				
				$('#emailStep').hide();
				
				$('#Apply').show();
				$('#ApplyUpper').show(); 
				$('#ViewSave').show(); 
				$('#ViewSaveUpper').show(); 
				$('#Email').show(); 
				$('#EmailUpper').show(); 
				$('#ClickHere').show(); 
				
			} // End of if
	  
  } // End of forwardVT()
  
    function closeDialog(){
		
		$('#dialogText').text("");
		$('#forwardDialog').hide();
		
			if(applicant.state == "VT"){
			  
				$('#USMap').show(); 
				$('#VTState').show(); 
				$('#VTText').show();
			
			} // End of if
	  
  } // End of forwardVT()