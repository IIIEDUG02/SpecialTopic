# 資料表設計

## 支付系統

### 購物車紀錄
```sql
CREATE TABLE shopping_cart (
	id int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	uid int NOT NULL,
	cid int NOT NULL,
	UNIQUE (uid,cid),
    FOREIGN KEY (cid) REFERENCES db.dbo.class(cid),
    FOREIGN KEY (uid) REFERENCES db.dbo.members(uid)
);
```

### 綠界支付紀錄
```sql
CREATE TABLE db.dbo.ecpay_record (
	order_id varchar(255) NOT NULL PRIMARY KEY,
	uid int NOT NULL,
	cids varchar(255) NOT NULL,
	trade_amount int NOT NULL,
	rtn_msg varchar(255) NULL,
	order_date datetime NULL,
	payment_type varchar(255) NULL,
);
```



<script src="https://code.jquery.com/jquery-3.6.0.slim.js" integrity="sha256-HwWONEZrpuoh951cQD1ov2HUK5zA5DwJ1DNUXaM6FsY=" crossorigin="anonymous"></script>

<script>
$(document).ready(function() {
  $('h2').each(function(index) {
    $(this).html((index + 1) + '. ' + $(this).html());
  });
});
</script>