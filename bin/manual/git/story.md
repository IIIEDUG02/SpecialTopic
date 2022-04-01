# Git Branch
小時候大家應該都有通過，農夫掉了一把鐵斧頭🪓到池塘裡，然後池塘出現一位女神問農夫，你掉的是金🪓？還是銀🪓？然後農夫就面臨了2選1的選擇題，但是身為程序猿的我們是不做選擇的，我們可以透過建立分歧點（Branch ）去體驗選金斧頭會怎樣？選銀斧頭回怎樣？而且我們還可以<font size="24" color="red">全都要！！！</font>

### 其實git就是一個可以跨平行宇宙時間機器(但是只能回到過去，及返回到現在，就像<font color="red">復仇者聯盟要去取回寶石的情況</font>)
![](http://e3f49eaa46b57.cdn.sohucs.com/c_pad,w_640,h_360,blur_80/2020/5/3/8/5/MTAwMTM1XzE1ODg0NjQzMzc4OTc=.jpg)
<hr>

故ㄘㄠ事ㄗㄨㄛˋ演ㄌㄧㄡˊ進ㄔㄥˊ，如下

<hr>

農夫(我們)開始準備新<font color="red">ㄓㄨˇ</font>的<font color="red">ㄒㄧㄢˋ</font>工<font color="red">ㄐㄩˋ</font>作<font color="red">ㄑㄧㄥˊ(main)</font>
```shell=1
git init
### 或是
git clone [github_url]
```
路過一個池子<font color="red">(main)</font>
```shell=4
# 下列指令請依作業系統不同使用
touch a.路過一個池子    #unix-like
echo.> a.路過一個池子   #windows
```
b.一不小心，鐵斧頭掉池子裡了<font color="red">(main)</font>
```shell=7
touch b.鐵斧頭掉池子裡了
echo.> b.鐵斧頭掉池子裡了   #windows
```
這時候拿出我們的時光機器Git
並且記錄時間點<font color="red">(main)</font>
```shell=9
git add .
git commit -m "鐵斧頭掉池子裡了"
```
這時候女神從池子裡浮出來，問你是掉金斧頭？還是銀斧頭？

因為我們兩邊的劇情都嘗試看看，所以我們要先建立一個分支<font color="red">(main)</font>
```shell=11
git checkout -b "選金斧頭"
```
記錄時間點<font color="blue">(選金斧頭)</font>
```shell=12
touch c1.選金斧頭
git add .
git commit -m "選金斧頭"
```
拿到金斧頭後，剛好強盜在一旁看到，稍後就把你洗劫一空了<font color="blue">(選金斧頭)</font>
```shell=15
touch d1.被搶劫了
echo.> d1.被搶劫了
#################
git add .
git commit -m "被搶劫了"
```
於是我們就拿出時光機器Git，讓時間返回到被搶之前<font color="blue">(選金斧頭)</font>
```shell=20
git reset XXXX --hard
```
然後發現即使可以時間返回，但仍然找不到方法打贏強盜，那我們就決定選擇拿銀斧頭
(因為Main的時間線還在選斧頭前，所以先回到平行宇宙的時間點)<font color="blue">(選金斧頭)</font>
```shell=21
git checkout main
```
我們要再建立一個選銀斧頭的分支<font color="red">(main)</font>
```shell=22
git checkout -b "選銀斧頭"
touch c2.選銀斧頭
```
記錄時間點<font color="green">(選銀斧頭)</font>
```shell=24
git add .
git commit -m "選銀斧頭"
```
結果遇到小偷，被扒得一乾二凈<font color="green">(選銀斧頭)</font>
```shell=26
touch d2.遇到小偷
echo.> d2.遇到小偷
###################
git add .
git commit -m "遇到小偷"
```
心情不美麗的我們，只好又拿出時光機．．．<font color="green">(選銀斧頭)</font>
```shell=31
git reset XXXX --hard
```
似乎怎麼選都不好，回到鐵斧掉水池的main線<font color="green">(選銀斧頭)</font>
```shell=32
git checkout main
```
結果女神因為你誠實的告訴是掉鐵斧頭，所以女神就就幫你排除了強盜及小偷<font color="red">(main)</font>
```shell=33
touch c3.選鐵斧
echo.> c3.選鐵斧
##################
git add .
git commit -m "選鐵斧"
```
有時光機怎麼可能滿足於此？於是我們就把平行世界的金銀斧頭都拿過來了！！！
```shell=38
git merge "選銀斧頭"
git merge "選金斧頭"
```


簡單來說，就像是遊戲的存檔/讀檔機制，但是Git可以把不同存檔的東西給合併
故事大概就是這樣了，操作的過程當中，留意一下資料夾檔案的變化，會比較好瞭解git的操作喔～～


###### tags: `IIIEDU期末專題` `Git`
