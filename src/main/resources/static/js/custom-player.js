const media = $('video').get(0);
const controls = $('.controls');

const play = $('.play');
const stop = $('.stop');
const rwd = $('.rwd');
const fwd = $('.fwd');
const fsn = $('.fullscreen');

const timerWrapper = $('.timer');
const timer = $('.timer span');
const timerBar = $('.timer div');

var startTime;
var pauseTime;

play.click(playPauseMedia);
rwd.click(windBackward(3));
fwd.click(windForward(3));
fsn.click(toggleFullScreen);

media.addEventListener('timeupdate', setTime);
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

function setTime() {
	const minutes = Math.floor(media.currentTime / 60);
	const seconds = Math.floor(media.currentTime - minutes * 60);

	const minuteValue = minutes.toString().padStart(2, '0');
	const secondValue = seconds.toString().padStart(2, '0');

	const mediaTime = `${minuteValue}:${secondValue}`;
	timer.textContent = mediaTime;

	const barLength = timerWrapper.get(0).clientWidth * (media.currentTime / media.duration);
	timerBar.get(0).style.width = `${barLength}px`;
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
			console.log(data);
		},
		error: function(xhr, status) {
			console.log(xhr);
			console.log(status);
		}
	})
}
