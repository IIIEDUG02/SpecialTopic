mv src/main/resources/application.properties src/main/resources/application.properties.bak
sed -e "/server.servlet.context-path=\/*/d" src/main/resources/application.properties.bak > src/main/resources/application.properties

sed -i 's/http:\/\/localhost:8080/https:\/\/iiiedug02.nilm.in/g' src/main/java/net/ddns/iiiedug02/controller/TradeController.java

sed -i 's/\${DEV_LOG_FILE_PATH}/\${DEPLOY_LOG_FILE_PATH}/g' src/main/resources/log4j2.xml
