# Scalaによるプログラミング👩‍💻入門(Windows版)

## 環境構築(プログラムを書く準備)

### 1. Chocolatey🍫のインストール

PowerShellで以下のコマンドを実行します。

```
Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))
```

### 2. Git, JDK, sbt, IntelliJのインストール

PowerShellで以下のコマンドを実行します。

```
choco install git adoptopenjdk8 sbt intellijidea-community
```

※`adoptopenjdk8`部分ですが、  
AdoptOpenJDKは現在はAdoptiumという名前に変更になっているようです。  
近い将来chocoコマンドの内容も変更になる可能性があります。

### 3. IntelliJを起動してScalaプラグインを追加

動画🎥で解説します。

## サンプルプログラムの確認

### 1. IntelliJからGit経由で取得

以下のURLから取得します。詳しくは動画🎥にて。

```
https://github.com/igadaruma/my-first-play
```

### 2. IntelliJのsbt shellから起動

以下のコマンドを実行します。詳しくは動画🎥にて。

```
run
```

起動後は以下のURLにアクセスして動作を確認できます。  
👉 [http://localhost:9000](http://localhost:9000)

## はじめてのプログラミング

### 1. 引き算に変えてみる。

`app/controllers/SampleController.scala`の30行目付近の以下を修正します。

```
// 足し算しています。
val answer = leftAsNumber + rightAsNumber
```

以下にしてみましょう。

* `足し` → `引き`
* `+` → `-`
    

```
// 引き算しています。
val answer = leftAsNumber - rightAsNumber
```

👉 [http://localhost:9000](http://localhost:9000)

### 2. 画面も調整してみる。

`my-first-play/app/views/index.scala.html`の15行目を修正します。

```
<input type="number" name="left"/> + <input type="number" name="right"/>
```

* `+` → `-`

```
<input type="number" name="left"/> - <input type="number" name="right"/>
```

同様に19行目を修正します。

```
@left + @right = @answer
```

* `+` → `-`

```
@left - @right = @answer
```

👉 [http://localhost:9000](http://localhost:9000)

## まとめ

プログラミング完全に理解した！

## 参考文献

### 利用したソフトウェアの公式ページ集

* [Chocolatey公式(英語)](https://chocolatey.org/)
* [Git公式(英語)](https://git-scm.com/)
* [Adoptium(JDK)公式(英語)](https://adoptium.net/)
* [sbt公式(英語)](https://www.scala-sbt.org/)
* [JetBrains公式(IntelliJ開発元)](https://www.jetbrains.com/ja-jp/)
