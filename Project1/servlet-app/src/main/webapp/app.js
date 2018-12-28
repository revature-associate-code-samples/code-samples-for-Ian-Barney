/**
 * 
 */
window.onload = function(){
	document.getElementById('info').addEventListener('click', loadInfo);
	document.getElementById('about').addEventListener('click', loadAbout);
}

function loadInfo(){
	var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4 && xhr.status == 200){
        	$('#view').html(xhr.responseText);
        	$('#getStarWars').on('click',getStarWars);
        }
    }
    xhr.open("GET", "info.view", true);
    xhr.send();
}
function loadAbout(){
	var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4 && xhr.status == 200){
        	$('#view').html(xhr.responseText);
        }
    }
    xhr.open("GET", "about.view", true);
    xhr.send();
}
function getStarWars(){
	  var xhr = new XMLHttpRequest();
	    xhr.onreadystatechange = function(){
	        if(xhr.readyState == 4 && xhr.status == 200){
	            var planet = JSON.parse(xhr.responseText);
	            console.log(planet.name);
	            var p = $(`<p>${planet.name}</p>`);
	        	$('#planet').append(p);
	        }
	    }
	    
	    xhr.open("GET", `https://swapi.co/api/planets/${Math.floor(Math.random() * 61) + 1}/`, true);
	    xhr.send();
	
}