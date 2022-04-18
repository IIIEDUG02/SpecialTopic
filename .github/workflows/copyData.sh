photoDIR="/var/lib/tomcat9/webapps/SpecialTopic/WEB-INF/classes/static/classphoto"
if [ -d "$photoDIR" ]; then
  mv "${photoDIR}" ./
fi

videoDIR="/var/lib/tomcat9/webapps/SpecialTopic/WEB-INF/classes/static/classvideo"
if [ -d "$videoDIR" ]; then
  mv "${videoDIR}" ./
fi

memberphoto="/var/lib/tomcat9/webapps/SpecialTopic/WEB-INF/classes/static/memberphoto"
if [ -d "$memberphoto" ]; then
  mv "${memberphoto}" ./
fi