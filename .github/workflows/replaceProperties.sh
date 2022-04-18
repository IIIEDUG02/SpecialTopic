# 刪除 context-path
mv src/main/resources/application.properties src/main/resources/application.properties.bak
sed -e "/server.servlet.context-path=\/*/d" src/main/resources/application.properties.bak > src/main/resources/application.properties
# 修改TradeController.java，將locahost改成正式上線網址
sed -i 's/http:\/\/localhost:8080/https:\/\/iiiedug02.nilm.in/g' src/main/java/net/ddns/iiiedug02/controller/TradeController.java
# 修改Log檔存放位置
sed -i 's/\${DEV_LOG_FILE_PATH}/\${DEPLOY_LOG_FILE_PATH}/g' src/main/resources/log4j2.xml
# 修改資料庫網址，使用私人IP
sed -i 's/sql.nilm.in/10.104.0.3/g' src/main/resources/application.properties
