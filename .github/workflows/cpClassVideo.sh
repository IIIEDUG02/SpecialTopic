DIR="/var/lib/tomcat9/webapps/SpecialTopic/WEB-INF/classes/static/classvideo"
if [ -d "$DIR" ]; then
  mv "${DIR}" ./
fi