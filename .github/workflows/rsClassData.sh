photoDIR="./classphoto"
if [ -d "$photoDIR" ]; then
  mv "${photoDIR}" /var/lib/tomcat9/webapps/SpecialTopic/WEB-INF/classes/static/
fi

videoDIR="./classvideo"
if [ -d "$videoDIR" ]; then
  mv "${videoDIR}" /var/lib/tomcat9/webapps/SpecialTopic/WEB-INF/classes/static/
fi