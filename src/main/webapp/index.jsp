<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html lang="en">
	
		<head>
		
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
			
			<link rel="shortcut icon" type="image/png" href="https://mindstamp-resources.s3-us-west-2.amazonaws.com/assets/images/color_white.png"/>   
			<link rel="stylesheet" href="./assets/css/bootstrap.min.css">
			<link rel="stylesheet" href="./assets/css/mainstyle.css">
			<link rel="stylesheet" href="./assets/css/ui_components.css">
			
			<title>ASME</title>
			 
		</head>
	
		<body>
		    
		    <section class="videoSection">
		
		         <div class="position-relative">  
				 
				
					<!-- VIDEO ELEMENTS -->
			     
					 	<!-- Ends showing Get Started button -->
						<video controls id="firstVideo" muted playsinline>  <source src="./assets/media/Mercer ASME Animation_rgb FULL 1x.mp4" type="video/mp4"> </video>
							
						<!-- Ends showing US Map --> 
						<video controls id="secondVideo" muted playsinline> <source src="./assets/media/Mercer ASME Animation_rgb FULL 2x.mp4" type="video/mp4"> </video>
							
						<!-- Ends showing blocks -->
						<video controls id="thirdVideo" muted playsinline> <source src="./assets/media/Mercer ASME Animation_rgb FULL 3x.mp4" type="video/mp4"> </video>
							
						<!-- Ends showing ASME LIFE DECISION GUIDE Apply Options -->          
						<video controls id="fourthVideo" muted playsinline> <source src="./assets/media/Mercer ASME Animation_rgb FULL 4x.mp4" type="video/mp4"> </video> 
						
						
						
					<!-- VIDEO ELEMENTS NO CONTROLS -->
			     
						<!-- Ends showing Get Started button 	
						<video id="firstVideo" muted playsinline>  <source src="./assets/media/Mercer ASME Animation_rgb FULL 1x.mp4" type="video/mp4"> </video>
							
						<!-- Ends showing US Map 
						<video id="secondVideo" class="video" muted  playsinline> <source src="./assets/media/Mercer ASME Animation_rgb FULL 2x.mp4" type="video/mp4"> </video>
							
						<!-- Ends showing blocks 
						<video id="thirdVideo" class="video" muted  playsinline> <source src="./assets/media/Mercer ASME Animation_rgb FULL 3x.mp4" type="video/mp4"> </video>
							
						<!-- Ends showing ASME LIFE DECISION GUIDE Apply Options         
						<video id="fourthVideo" class="video" muted  playsinline> <source src="./assets/media/Mercer ASME Animation_rgb FULL 4x.mp4" type="video/mp4"> </video>	-->
					
					
					
					<!-- NORMAL BACKEND BUTTONS -->
					
						<!-- End of first video -->
						<button id="getStarted" value="2" onclick="playVideo(this.value)">getStarted</button>
						
						<!-- End of second video --> <!-- setState function will call playVideo function -->
						<button id="USMap" value="ALL" onclick="setState(this.value)">USMap</button>
						<button id="VTState" value="VT" onclick="setState(this.value)">VTState</button>
						<button id="VTText" value="VT" onclick="setState(this.value)">VTText</button>
						
						<!-- End of third video --> <!-- setAge function will call polayVideo function -->
						<button id="ageSelectButton" onclick="setAge()">CONTINUE</button>
						
						<!-- End of fourth video -->
						<button id="Email" onclick="showEmailForm()">Email</button>
						<button id="EmailUpper" onclick="showEmailForm()">Email Upper</button>
						
						
						
					<!-- HYPERLINK BUTTONS (All hyperlink buttons are active after fourth video)-->
					
						<!-- Apply Buttons -->
						<a id="Apply" href="https://asme.nylinsure.com/?apply=forms&prods=G8700-1&campaign=5434310015307053071" target="_blank" >Apply</a>
						<a id="ApplyUpper"href="https://asme.nylinsure.com/?apply=forms&prods=G8700-1&campaign=5434310015307053071" target="_blank">Apply Upper</a>
						
						<!-- View / Save Buttons -->
						<a id="ViewSave" href="./assets/media/98401 ASME Life Decision Download.pdf" target="_blank" >View Save</a>
						<a id="ViewSaveUpper" href="./assets/media/98401 ASME Life Decision Download.pdf" target="_blank" >View Save Upper</a>
						
						<!-- Click Here -->
						<a id="ClickHere" href="https://asme.nylinsure.com/?apply=forms&prods=G8700-1&campaign=5434310015307053071" target="_blank">Click Here</a>
		            
					
					
					<!-- EMAIL FORM -->
						<div id="emailStep">
							<div  class="emailStep">
								<form>
									<div class="form-group">
										<label for="">Please enter your email address to receive this PDF</label>
										<input id="emailInput" type="email" class="form-control" placeholder="Enter Email Address" required>
									</div>
									<button type="button" onclick="sendEmail()">Submit</button>
								</form>
							</div>
						</div>
		
		
		
					<!-- MODAL -->
					
						<div id="forwardDialog"> 
							<div class="dialog-content">
							
								<p id="dialogText"></p>
								
								<button id="dialogForward"  onclick="forward()">OK</button>
								<button id="dialogClose" onclick="closeDialog()">Close</button>
								
							</div>
						</div>
					
					
					
					<!-- AGE OPTION CONTROL -->
					
						<select id="ageSelect">
		
							<option value="18">18</option>
							<option value="19">19</option>
							<option value="20">20</option>
							<option value="21">21</option>
							<option value="22">22</option>
							<option value="23">23</option>
							<option value="24">24</option>
							<option value="25">25</option>
							<option value="26">26</option>
							<option value="27">27</option>
							<option value="28">28</option>
							<option value="29">29</option>
							<option value="30">30</option>
							<option value="31">31</option>
							<option value="32">32</option>
							<option value="33">33</option>
							<option value="34">34</option>
							<option value="35">35</option>
							<option value="36">36</option>
							<option value="37">37</option>
							<option value="38">38</option>
							<option value="39">39</option>
							<option value="40">40</option>
							<option value="41">41</option>
							<option value="42">42</option>
							<option value="43">43</option>
							<option value="44">44</option>
							<option value="45">45</option>
							<option value="46">46</option>
							<option value="47">47</option>
							<option value="48">48</option>
							<option value="49">49</option>
							<option value="50">50</option>
							<option value="51">51</option>
							<option value="52">52</option>
							<option value="53">53</option>
							<option value="54">54</option>
							<option value="55">55</option>
							<option value="56">56</option>
							<option value="57">57</option>
							<option value="58">58</option>
							<option value="59">59</option>
							<option value="60">60</option>
							<option value="61">61</option>
							<option value="62">62</option>
							<option value="63">63</option>
							<option value="64">64</option>
							<option value="65">65</option>
							<option value="66">66</option>
							<option value="67">67</option>
							<option value="68">68</option>
							<option value="69">69</option>
							<option value="70">70</option>
							<option value="71">71</option>
							<option value="72">72</option>
							<option value="73">73</option>
							<option value="74">74</option>
							<option value="75">75</option>
							<option value="76">76</option>
							<option value="77">77</option>
							<option value="78">78</option>
							<option value="79">79</option>
							<option value="80">80</option>
							<option value="81">81</option>
							<option value="82">82</option>
							<option value="83">83</option>
							<option value="84">84</option>
							<option value="85">85</option>
							<option value="86">86</option>
							<option value="87">87</option>
							<option value="88">88</option>
							<option value="89">89</option>
							<option value="90">90</option>
							<option value="91">91</option>
							<option value="92">92</option>
							<option value="93">93</option>
							<option value="94">94</option>
							<option value="95">95</option>
							<option value="96">96</option>
							<option value="97">97</option>
							<option value="98">98</option>
							<option value="99">99</option>
							<option value="100">100</option>
							<option value="101">101</option>
							<option value="102">102</option>
							<option value="103">103</option>
							<option value="104">104</option>
							<option value="105">105</option>
							<option value="106">106</option>
							<option value="107">107</option>
							<option value="108">108</option>
							<option value="109">109</option>
							<option value="110">110</option>
							<option value="111">111</option>
							<option value="112">112</option>
							<option value="113">113</option>
							<option value="114">114</option>
							<option value="115">115</option>
							<option value="116">116</option>
							<option value="117">117</option>
							<option value="118">118</option>
							<option value="119">119</option>
							<option value="120">120</option>
		
					</select>
					
					
		
		        </div> <!-- position-relative class -->
				
		    </section> <!-- videoSection class -->
		    
		    
		    <script src="./assets/js/jquery-min.js"></script>
		    <script src="./assets/js/bootstrap.min.js"></script>
		    <script src="./assets/js/custom.js"></script>
			<script src="./assets/js/objects.js"></script>
			<script src="./assets/js/dialog.js"></script>
			
		</body>
	
	</html>