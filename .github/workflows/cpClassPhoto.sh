DIR="/var/lib/tomcat9/webapps/SpecialTopic/WEB-INF/classes/static/classphoto"
if [ -d "$DIR" ]; then
  mv "${DIR}" ./
fi