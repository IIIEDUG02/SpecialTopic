photoDIR="./classphoto"
staticDIR="/var/lib/tomcat9/webapps/SpecialTopic/WEB-INF/classes/static/"

if [ -d "$photoDIR" ]; then
  mv "${photoDIR}" "${staticDIR}"
fi

videoDIR="./classvideo"
if [ -d "$videoDIR" ]; then
  mv "${videoDIR}" "${staticDIR}"
fi

memberphoto="./memberphoto"
if [ -d "$memberphoto" ]; then
  mv "${memberphoto}" "${staticDIR}"
fi


