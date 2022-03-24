# SQLServer Docker Container建立

### 1.下載SQL的Docker Image(CMD/Shell)

```shell=1
docker pull mcr.microsoft.com/mssql/server
```

### 2.建立Container(CMD/Shell)

- SA的密碼：<font color="red">aaaa</font>
- 對外連結Port：<font color="red">1555</font>
- 自動重啟

```shell=2
docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=aaaa" \
   -p 1555:1433  \
   -d \
   --restart=always \
   mcr.microsoft.com/mssql/server
   
```

### 3.連線資料庫

使用SSMS或其他連線程式

### 4.建立資料庫(SQL)

```sql=
CREATE DATABASE db;
```

### ５.建立login(SQL)

- 名稱：<font color="red">iiiedug02</font>
- 密碼：<font color="red">paSSwoRD</font>
- 預設資料庫：<font color="red">db</font>

```sql=2
CREATE LOGIN iiiedug02 
WITH PASSWORD=N'paSSwoRD', 
DEFAULT_DATABASE=db, 
CHECK_EXPIRATION=OFF, 
CHECK_POLICY=OFF
```

### 6.啟用登入iiiedug02(SQL)

```sql=7
ALTER LOGIN iiiedug02 ENABLE;
```

### 7.切換到資料庫(SQL)

```sql=8
USE db;
```

### 8.建立使用者iiiedug02(SQL)

```sql=9
CREATE USER iiiedug02 FOR LOGIN iiiedug02;
```

### 9.設定使用者iiiedug02為資料庫擁有者(SQL)

```sql=10
ALTER ROLE db_owner ADD MEMBER iiiedug02;
```
