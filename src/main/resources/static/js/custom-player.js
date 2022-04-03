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

function toggleFullScreen() {
	if (media.requestFullscreen) {
		media.requestFullscreen();
	} else if (media.mozRequestFullScreen) {
		media.mozRequestFullScreen();
	} else if (media.webkitRequestFullscreen) {
		media.webkitRequestFullscreen();
	} else if (media.msRequestFullscreen) {
		media.msRequestFullscreen();
	}
}

function windBackward(sec) {
	if (media.currentTime <= sec) {
		PauseOrStopMedia();
	} else {
		media.currentTime -= sec;
	}
}

function windForward(sec) {
	if (media.currentTime >= media.duration - sec) {
		PauseOrStopMedia();
	} else {
		media.currentTime += sec;
	}
}

function postRecord(duration, sumTime, ended) {
	$.ajax({
		type: "POST",
		url: "/SpecialTopic/ProgressRecord/api/9",
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

function changeVideoSrc(url) {
	media.src=url;
}

$.ajax({
	type: "Get",
	url: "/SpecialTopic/getCurListJson/api/" + $('input#cid').val(),
	dataType: "json",
	contentType: "application/json;charset=utf-8",
	success: function(data) {
		for (var i = 0 ; i < data.length ; i++) {
			let li = $("<li class='list-group-item' onclick=changeVideoSrc(\'" + data[i]['video_path'] + "\') >第" + data[i]['chapter'] + "章</li>")
			$('ul.list-group').append(li)
		}
	},
	error: function(xhr, status) {
		console.log(xhr);
		console.log(status);
	}
})