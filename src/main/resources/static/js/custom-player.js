const media = $('video').get(0);


var startTime;
var pauseTime;

media.addEventListener('play', playMedia);
media.addEventListener('pause', PauseOrStopMedia);
media.addEventListener('ended', PauseOrStopMedia);

function playMedia() {
	startTime = new Date();
	media.play();
	console.log("startTime" + startTime);
}

function PauseOrStopMedia() {
	pauseTime = new Date();
	media.pause();
	postRecord(media.duration, (pauseTime - startTime) / 1000, media.ended);
	console.log((pauseTime - startTime) / 1000);
}

function playPauseMedia() {
	if (media.paused) {
		media.play();
	} else {
		media.pause();
	}
}

function postRecord(duration, sumTime, ended) {
	$.ajax({
		type: "POST",
		url: "/SpecialTopic/ProgressRecord/api/"+$('input#cuid').val(),
		//dataType: "json",
		data: {
			"duration": duration,
			"sumTime": sumTime,
			"ended": ended
		},
		//contentType: "application/json;charset=utf-8",
		success: function(data) {
			console.log("not login");
		},
		error: function(xhr, status) {
			console.log(xhr);
			console.log(status);
		}
	})
}

function changeVideoSrc(url,cuid) {
	media.src=url;
	$('input#cuid').attr("value",cuid)
	var cuidchapter = $("#li"+cuid).html();
	console.log(cuidchapter);
	$("#chapter1").html(cuidchapter);
}

$.ajax({
	type: "Get",
	url: "/SpecialTopic/class/curriculums/api/" + $('input#cid').val(),
	dataType: "json",
	contentType: "application/json;charset=utf-8",
	success: function(data) {
		for (var i = 0 ; i < data.length ; i++) {
			let li = $("<li id='li"+data[i]['cuid']+"' class='list-group-item' onclick=changeVideoSrc(\'" + data[i]['video_path'] + "\',"+data[i]['cuid']+") >" + data[i]['chapter'] + "</li>")
			$('ul.list-group').append(li)
			

			
		}
	},
	error: function(xhr, status) {
		console.log(xhr);
		console.log(status);
	}
})
