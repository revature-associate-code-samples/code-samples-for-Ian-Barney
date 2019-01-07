/**
 * 
 */
window.onload = function (){
	loadHomeView();
}

function getReimbursements(){
	var table = document.getElementById("table");
	while (table.firstChild) {
	    table.removeChild(table.firstChild);
	}
	var aTable = document.getElementById("approvedTable");
	while (aTable.firstChild) {
	    aTable.removeChild(aTable.firstChild);
	}
	var dTable = document.getElementById("deniedTable");
	while (dTable.firstChild) {
	    dTable.removeChild(dTable.firstChild);
	}
	
	console.log("in function");
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			let reimbursements = JSON.parse(xhr.responseText);
			for(let r of reimbursements){
				appendToReimbursementList(r);
			}
		}
	}
	xhr.open("GET", "reimbursements");
	xhr.send();
}
function appendToReimbursementList(r){
	console.log(r);
	if(r.reimbStatus == "PENDING"){
		var td = 
			$(`<tr>
				<td>${r.reimbID}</td>
				<td>${r.reimbAmount}</td>
				<td id="statID${r.reimbID}">${r.reimbStatus}</td>
				<td>${r.reimbType}</td>
				<td>${r.submittedString}</td>
				<td>${r.reimbDescription}</td>
				<td>${r.reimbAuthor}</td>
				<td><button onclick="approve(${r.reimbID},1)">approve</button></td>
				<td><button onclick="approve(${r.reimbID},2)">deny</button></td>
			</tr>`);
		$('#table').append(td);
		}
	var td = 
		$(`<tr>
			<td>${r.reimbID}</td>
			<td>${r.reimbAmount}</td>
			<td id="statID${r.reimbID}">${r.reimbStatus}</td>
			<td>${r.reimbType}</td>
			<td>${r.submittedString}</td>
			<td>${r.reimbDescription}</td>
			<td>${r.reimbAuthor}</td>
			<td>${r.reimbResolver}</td>
			<td>${r.resolvedString}</td>
		</tr>`);
		if(r.reimbStatus == "APPROVED"){
			$('#approvedTable').append(td);
			}
		if(r.reimbStatus == "DENIED"){
			$('#deniedTable').append(td);
			}
		
}
function getUserReimbursements(){
	var table = document.getElementById("table");
	while (table.firstChild) {
	    table.removeChild(table.firstChild);
	}
	var aTable = document.getElementById("approvedTable");
	while (aTable.firstChild) {
	    aTable.removeChild(aTable.firstChild);
	}
	var dTable = document.getElementById("deniedTable");
	while (dTable.firstChild) {
	    dTable.removeChild(dTable.firstChild);
	}
	console.log("in function");
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			console.log(xhr.responseText);
			let reimbursements = JSON.parse(xhr.responseText);
			for(let r of reimbursements){
				appendToUserReimbursementList(r);
			}
		}
	}
	xhr.open("GET", "reimbursements");
	xhr.send();
}
function appendToUserReimbursementList(r){
	
	if(r.reimbStatus == "PENDING"){
		var td = 
			$(`<tr>
				<td>${r.reimbID}</td>
				<td>${r.reimbAmount}</td>
				<td id="statID${r.reimbID}">${r.reimbStatus}</td>
				<td>${r.reimbType}</td>
				<td>${r.submittedString}</td>
				<td>${r.reimbDescription}</td>
				<td>${r.reimbAuthor}</td>
			</tr>`);
	$('#table').append(td);
	}
	var td = 
		$(`<tr>
			<td>${r.reimbID}</td>
			<td>${r.reimbAmount}</td>
			<td id="statID${r.reimbID}">${r.reimbStatus}</td>
			<td>${r.reimbType}</td>
			<td>${r.submittedString}</td>
			<td>${r.reimbDescription}</td>
			<td>${r.reimbAuthor}</td>
			<td>${r.reimbResolver}</td>
			<td>${r.resolvedString}</td>
		</tr>`);
	if(r.reimbStatus == "APPROVED"){
		$('#approvedTable').append(td);
		}
	if(r.reimbStatus == "DENIED"){
		$('#deniedTable').append(td);
		}
	
}
function loadHomeView(){
	console.log("loading home");
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			// do things w response
			console.log(xhr.responseText);
			$('#view').html(xhr.responseText);
			$('#loginbtn').on('click', login);
		}
	}
	xhr.open("GET", "home.view", true);
	xhr.send();	
	
}
function loadPoolView(){
	console.log("test");
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			// do things w response
			console.log(xhr.responseText);
			console.log("before view is loaded")
			$('#view').html(xhr.responseText);
			console.log("after view is loaded");
			$('#loadTable').on('click', getReimbursements);
		}
	}
	xhr.open("GET", "reimbursement.view", true);
	xhr.send();	
	
}

function login(){
	var uName = $('#username').val();
	var pWord = $('#password').val();
	
	console.log(uName + " " + pWord);
	var obj = {
		username: uName,
		password: pWord
	};
	
	var toSend = JSON.stringify(obj);
	console.log(toSend);
	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function (){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			$('#newRe').on('click', newRequest);
		}
	}
	xhr.open("POST", "login");
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(toSend);
	
}

function newRequest(){
	
	var obj = {
			reimbAmount: $('#reAmount').val(),
			reimbTypeID: $('#reType').val(),
			reimbDescription: $('#reDes').val()
	}
	var a = $('#reAmount').val();
	var b = $('#reType').val();
	var c = $('#reDes').val();
	if(a==null||a=="",b==null||b=="",c==null||c==""){
		console.log($('#reAmount').val());
		console.log($('#reType').val());
		console.log($('#reDes').val());
		alert("You didn't enter enough information");
		return;
	}
	
	var toSend = JSON.stringify(obj);
	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status==200){
			
			getUserReimbursements();
		}
	}
	xhr.open("POST", "reimbursements", true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(toSend);
}

function approve(rID,statusID){
	console.log("approving");
	var obj = {
		id: rID,
		status: statusID
	};
	
	var toSend = JSON.stringify(obj);
	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function (){
		if(xhr.readyState == 4 && xhr.status == 200){
			getReimbursements();
		}
	}
	xhr.open("PUT", "reimbursements", true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(toSend);
}

function logout(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
		}
	}
	xhr.open("GET", "logout", true);
	xhr.send();	
}