<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html lang="zh-tw">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  	<title>Video player example</title>
  	<link rel="stylesheet" type="text/css" href="/SpecialTopic/css/style.css">
  </head>
  <body>
      <div class="player">
        <video controlsList="nodownload">
          <source src="/SpecialTopic/classvideo/sintel-short.mp4" type="video/mp4">
          <!-- fallback content here -->
        </video>
        <div class="controls">
          <button class="play" aria-label="play pause toggle">play</button>
          <div class="timer" style="display: inline-block;"><div></div><span aria-label="timer">00:00</span></div>
          <button class="rwd" aria-label="rewind">reward</button>
          <button class="fwd" aria-label="fast forward">foward</button>
          <button class="fullscreen" aria-label="fast forward">fullscreen</button>
        </div>
      </div>
    <script src="/SpecialTopic/js/jquery-3.6.0.js"></script>
    <script src="/SpecialTopic/js/custom-player.js"></script>
  </body>
</html>