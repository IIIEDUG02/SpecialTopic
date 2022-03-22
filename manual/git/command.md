# Git 常用指令

#### 下載專案(只有一開始的時候會用到)

git clone https://github.com/IIIEDUG02/SpecialTopic [資料夾名稱(資料將會下載到資料夾內，可不填)]

```shell=
git clone https://github.com/IIIEDUG02/SpecialTopic
```

#### 更新Main Branch

因為我們是團隊開發，所以每天都會有人會push新的程式碼上來，每天至少執行一次，或push前系統有可能會要求使用前，

```shell=
git pull
```

#### 更新指定的Branch

```shell=
git pull origin other-branch
```

#### 回復到最新提交版本

```shell=
git reset --hard HEAD
```

#### 回復到上n個提交版本

```shell=
git reset --hard HEAD~n
```

#### 檢查目前有哪些檔案異動且未add、commit

```shell=
git status
```

#### 新增檔案至準備上傳(push)的暫存區

git add . 目前所有異動 git add [檔名] 針對指定檔案

```shell=
git add . 
```

#### 給準備上傳(push)的檔案添加註解（“註解”的雙引號“”可以不用，如果註解字串中有空格 就必須要有）

```shell=
git commit -m “註解”
```

#### 更新(上傳)至遠端專案

```shell=
git push
```

#### 新增Branch，並切換

```shell=
git checkout -b [Branch名稱(必填)]
```

#### 切換Branch

```shell=
git branch [Branch名稱(必填)]
```

#### 刪除Branch

```shell=
git branch -d [Branch名稱(必填)]
```

![](pictures/git.png)

- 如果已經了解指令的用途，但還不熟悉如何運用的可以搭配[故事](story.md)去了解

- 無法適應指令的同學可以參考[這個軟體(SourceTree)](https://www.sourcetreeapp.com)使用
