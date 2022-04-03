

const media = document.querySelector('video');
const controls = document.querySelector('.controls');
const play = document.querySelector('.play');
const stop = document.querySelector('.stop');
const player = document.getElementById('player')
const dataSkip = document.querySelectorAll('[data-skip]');
const ranges = document.querySelectorAll('.player-sound');
const fsn = document.querySelector('.fullscreen');
const timerWrapper = document.querySelector('.timer');
const timer = document.querySelector('.timer span');
const timerBar = document.querySelector('.timer div');

var startTime;
var pauseTime;

media.removeAttribute('controls');
controls.style.visibility = 'visible';

play.addEventListener('click', playPauseMedia);

function playPauseMedia() {
  if(media.paused) {
    play.setAttribute('data-icon','u');
    media.play();
	startTime = new Date();
  } else {
    play.setAttribute('data-icon','P');
    media.pause();
	pauseTime = new Date();
	alert((pauseTime - startTime) / 1000);
  }
}

stop.addEventListener('click', stopMedia);
media.addEventListener('ended', stopMedia);

function stopMedia() {
  media.pause();
  media.currentTime = 0;
  play.setAttribute('data-icon','P');
}


let intervalFwd;
let intervalRwd;


fsn.addEventListener('click', toggleFullScreen);

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
// 快進
function skip() {
	media.currentTime += parseFloat(this.dataset.skip);
}
// 音量
function handleRangeUpdate() {
	media[this.name] = this.value;
}



dataSkip.forEach( button => button.addEventListener('click', skip) );

ranges.forEach(range => {
	range.addEventListener('input', handleRangeUpdate);
})

media.addEventListener('ended', end);

function end() {
	pauseTime = new Date();
	alert((pauseTime - startTime) / 1000);
	
	}

media.addEventListener('timeupdate', setTime);

function setTime() {
  const minutes = Math.floor(media.currentTime / 60);
  const seconds = Math.floor(media.currentTime - minutes * 60);

  const minuteValue = minutes.toString().padStart(2, '0');
  const secondValue = seconds.toString().padStart(2, '0');

  const mediaTime = `${minuteValue}:${secondValue}`;
  timer.textContent = mediaTime;

  const barLength = timerWrapper.clientWidth * (media.currentTime/media.duration);
  timerBar.style.width = `${barLength}px`;
}

rwd.classList.remove('active');
fwd.classList.remove('active');
clearInterval(intervalRwd);
clearInterval(intervalFwd);


