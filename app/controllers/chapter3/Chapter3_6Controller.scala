package controllers.chapter3

import play.api.mvc._

import javax.inject._

@Singleton
class Chapter3_6Controller @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def step6_1 = Action { _ =>
    // 6. クラス
    // 6-1. クラスって？

    // クラスというのは型だと思っていただいてほぼほぼ問題ナシです。
    // クラスは作れます。これはつまり型を自分で作れるということです。
    // といっても、難しく考える必要はありません。
    // 最近よく登場しているタプル型を思い出していただくと良いのですが、
    // あれと似たように、既存の型を組み合わせる形で定義します。
    // ただ、クラスの方が機能が多く、圧倒的に、拡張性・応用性が高く、
    // 現代のプログラミングの世界では、とてつもなく重要な要素の1つとなっています。

    // 前置きが長くなりましたが、
    // 早速前回やった硬貨の例をクラスでやってみます。
    // まずは(Int, Int, Int, Int, Int, Int)型を撲滅するために、
    // クラスを定義します。
    class Coins(val fiveHundred: Int,
                val oneHundred: Int,
                val fifty: Int,
                val ten: Int,
                val five: Int,
                val one: Int)
    // これでクラスができました。
    // クラスは`class クラス名(val フィールド名: 型)`という感じに書くことができます。
    // フィールドというのは"クラスが内包する変数"みたいなイメージで捉えてみてください。

    // 【補足事項】
    // クラス名の頭文字は大文字にするというのが一般的なので原則あわせましょう。
    // 逆に変数名・関数名は小文字から始めるのがScala言語では一般的です。
    // また英単語の区切りはスペース文字を使えないので、大文字にして表現することが多いです、
    // 例えば、`PenPineapple`といった記載方法です。
    // ・大文字から始まり、単語の区切りも大文字 → パスカルケース
    // ・小文字から始まり、単語の区切りが小文字 → キャメルケース
    // と呼ばれています。
    // 一方で、Scala界隈ではあまり登場しないですが、
    // ・単語区切りが`-`ハイフン(pen-pineapple) → ケバブケース
    // ・単語区切りが`_`アンダーバー(pen_pineapple) → スネークケース
    // と呼ばれています。この2つは頭が大文字になることがまずないので、
    // その違いによる呼び分けは、ほぼされてないと思われます(私は知りません)。
    // 【補足事項 終わり】

    // クラスはほぼ型と言いました。
    // クラスは定義しましたが、これは型を定義しただけですので、
    // 実際に変数に入れたり、関数の返り値などに利用する際には、
    // 値を作る必要があります。
    // クラスで定義した型の値を、インスタンスと呼ぶことがありますが、
    // インスタンスは`new`を使って以下のように生成できます。
    val coins = new Coins(1, 2, 3, 4, 5, 6)
    // タプルを作成するときの丸括弧の前に、newとクラス名をつける感じのイメージでしょうか。

    // ここからが重要です。

    // タプルと違い、クラスのフィールドには名前がつくので取得時にわかりやすくなります。
    // 例えば50円の枚数を取得する場合は以下となります。
    val numberOfFiftyYenCoin: Int = coins.fifty
    // タプルだと`coins._3`でしたが、意味のある表現に変わったので、
    // 読みやすくなったと感じませんでしょうか？
    // 説明が後付けなりましたが、`インスタンス.フィールド名`で、
    // そのフィールドの値が取得可能です。

    // さらにタプルとは違い、専用の型(クラス)名がつくのと、
    // それを自由に関数定義にも利用できるため、前回の500円→100円の両替関数は、
    // 次のように書き換えることができます。
    def exchange500For100(coins: Coins): Coins = {
      val exchanged100coins = coins.fiveHundred * 5
      new Coins(0, coins.oneHundred + exchanged100coins, coins.fifty, coins.ten, coins.five, coins.one)
    }

    // 使い方は全く同じです。
    val exchangedCoins = exchange500For100(coins)

    // いかがだったでしょう？
    // タプルを利用する時よりも、名前がつくことで、
    // 人の感覚に近い、表現でプログラムが書くことができることが、
    // 多少は伝わりましたでしょうか？

    // ただ、クラスはただ複数の型を組合わせるだけのものでは全くなく、
    // もっとたくさんできることがあるので、
    // 次回以降で、もう少しクラスを掘り下げて行きたいと思います。

    Ok("クラス最高！").as(HTML)

  }

  def step6_2 = Action { _ =>
    // 6. クラス
    // 6-2. フィールド

    // クラスでは、クラスパラメータ(クラス定義のクラス名のすぐ後の丸括弧の中のフィールド群)に
    // 指定した値以外も、追加で値を保持することができます。
    // 例えば前回のCoinsに合計値を保持する変数を追加してみましょう。

    class Coins(val fiveHundred: Int,
                val oneHundred: Int,
                val fifty: Int,
                val ten: Int,
                val five: Int,
                val one: Int) { // 追加の`{}`括弧を書きます。
      // 中に追加のフィールドを定義できます。
      // 合計値を`sum`という名前で保持しています。
      // ※これはnewのタイミングで計算されます。
      val sum: Int = (500 * fiveHundred) + (100 * oneHundred) + (50 * fifty) + (10 * ten) + (5 * five) + one
    }

    // ※前回でも軽く触れましたが、クラスパラメータ内の変数も、
    // それ以外のクラス内の変数もあわせてフィールドと呼んだりします。
    // 他にもメンバ変数と表現したりもします。

    // 【補足事項】
    // より抽象的な呼び方になりますが、
    // 単に"メンバ"・"プロパティ"などと言ったりもします。
    // 厳密な言葉の定義はプログラミング言語や組織によって方言もあるので、
    // 一概には捉えにくいのですが、
    // 他のプログラマと話す際は、前後の文脈等から、
    // 『この人は今はこの言葉をフィールドに対して使ってるんだな。』
    // といった脳内変換が必要な場面も出てくるかもしれません。
    // もちろん逆に、相手が思う言葉の定義と違う使い方を自分がしている可能性もあるので、
    // 大事な場面では、誤解が発生しないように、注意して話す必要があるかもしれません。
    // 【補足事項 終わり】

    // クラスパラメータにないフィールドは、
    // newでインスタンスを生成する時には指定しませんが、
    // 値をとってくる時は同様に`.`を付けて参照します。
    // 例を見ていきましょう。
    val coins1 = new Coins(1, 1, 1, 1, 1, 1)
    val sumCoins1 = coins1.sum // `.`で参照
    // (500 * 1) + (100 * 1) + (50 * 1) + (10 * 1) + (5 * 1) + 1 = 666

    val coins2 = new Coins(2, 2, 2, 2, 2, 2)
    val sumCoins2 = coins2.sum
    // (500 * 2) + (100 * 2) + (50 * 2) + (10 * 2) + (5 * 2) + 2 = 1332

    // クラス定義とnewによるインスタンス生成の関係が、
    // 捉えづらい方も多いと思います。
    // 上記の例のように、coins1とcoins2は同じクラスからnewしていますが、
    // 指定されたクラスパラメータによって、中に保持している値は別ものになります。

    // 余計に混乱させてしまう可能性もありますが、
    // クッキーを焼く時の、金型がクラスで、
    // クッキー自体が、newで生成されるインスタンスのような感じです。
    // 金型が同じでも、クッキーは別ですし、
    // 生地によって、状態も異なるような感じです🍪

    Ok(
      s"""
         |sumCoins1 = $sumCoins1<br>
         |sumCoins2 = $sumCoins2<br>
         |""".stripMargin).as(HTML)

  }

  def step6_3 = Action { _ =>
    // 6. クラス
    // 6-3. メソッド

    // クラスでは、前回の変数(フィールド)に加えて、
    // 関数も中に含むことができます。
    // 前々回の`exchange500For100`関数のように、
    // クラスの中でなくても、当然定義できるのですが、
    // クラスの中に、"そのクラスと関係の深い"処理を含めておけば、
    // 読みやすいプログラムに仕上げられる場合があります。

    // こういったクラス内の関数を、
    // "メソッド"と呼びます。"メンバ関数"とも呼ばれたりします。
    // フィールドと同様に、他にも、
    // 単に"メンバ"・"プロパティ"などと呼ばれることもあるのでご注意ください。

    // 例として、`exchange500For100`をメソッドとして定義してみましょう。

    class Coins(val fiveHundred: Int,
                val oneHundred: Int,
                val fifty: Int,
                val ten: Int,
                val five: Int,
                val one: Int) {

      val sum: Int = (500 * fiveHundred) + (100 * oneHundred) + (50 * fifty) + (10 * ten) + (5 * five) + one

      // これです。
      def exchange500For100(): Coins = {
        val exchanged100coins = fiveHundred * 5
        new Coins(0, oneHundred + exchanged100coins, fifty, ten, five, one)
      }

      // 前々回の`exchange500For100`の定義は以下です。
      // 引数と`coins.`部分が上記では無くなっています。

      // def exchange500For100(coins: Coins): Coins = {
      //   val exchanged100coins = coins.fiveHundred * 5
      //   new Coins(0, coins.oneHundred + exchanged100coins, coins.fifty, coins.ten, coins.five, coins.one)
      // }

    }

    // クラス定義での`{}`の中で定義する以外は、
    // 表記上のルールは今までの関数と変わりません。

    // ただし、上記の例の通り、メソッドの場合は、
    // 同じクラス内の他フィールドやメソッドを`.`等をつけずに
    // 参照できるという特徴があります。
    // これのお陰で、処理の内容次第ですが表記を簡素化することができます。

    // メソッド使う例も見ていきましょう。
    // 基本的にはフィールドと同様ですが、"()"や引数を指定する形になります。
    val coins1 = new Coins(1, 2, 3, 4, 5, 6)
    val coins2 = coins1.exchange500For100()

    Ok(
      s"""
         |coins1.fiveHundred = ${coins1.fiveHundred}<br>
         |coins1.oneHundred = ${coins1.oneHundred}<br>
         |coins1.sum = ${coins1.sum}<br>
         |<br>
         |coins2.fiveHundred = ${coins2.fiveHundred}<br>
         |coins2.oneHundred = ${coins2.oneHundred}<br>
         |coins2.sum = ${coins2.sum}<br>
         |""".stripMargin).as(HTML)

  }

  def step6_4 = Action { _ =>
    // 6. クラス
    // 6-4. メソッド2

    // 今回は特に大きな新しい話はなく、
    // 追加の具体例と共に小ネタ等を紹介します。
    // Coinsクラスにいくつか別のフィールド・メソッドを定義します。

    class Coins(val fiveHundred: Int,
                val oneHundred: Int,
                val fifty: Int,
                val ten: Int,
                val five: Int,
                val one: Int) {

      val sum: Int = (500 * fiveHundred) + (100 * oneHundred) + (50 * fifty) + (10 * ten) + (5 * five) + one

      // 中身のデータをHTML形式の文字列にして表した変数
      val html: String = {
        s"""
           |500円玉: $fiveHundred 枚<br>
           |100円玉: $oneHundred 枚<br>
           |50円玉: $fifty 枚<br>
           |10円玉: $ten 枚<br>
           |5円玉: $five 枚<br>
           |1円玉: $one 枚<br>
           |<b>合計: $sum 円</b><br>
           |""".stripMargin
      }

      // 足し算メソッド(一部の記号はメソッド名として利用できます。)
      def +(c: Coins): Coins = {
        new Coins(
          fiveHundred + c.fiveHundred,
          oneHundred + c.oneHundred,
          fifty + c.fifty,
          ten + c.ten,
          five + c.five,
          one + c.one)
      }

      // 引き算メソッド(一部の記号はメソッド名として利用できます。)
      def -(c: Coins): Coins = {
        new Coins(
          fiveHundred - c.fiveHundred,
          oneHundred - c.oneHundred,
          fifty - c.fifty,
          ten - c.ten,
          five - c.five,
          one - c.one)
      }

    }

    // ここから使用例です。
    val coins1 = new Coins(10, 10, 10, 10, 10, 10)
    val coins2 = new Coins(1, 2, 3, 4, 5, 6)

    // val addedCoins = coins1.+(coins2)
    // 本来、上記のように書きますが、メソッド引数が1つだけの場合は以下のように書けるという特殊ルールがScalaにはあります。
    val addedCoins = coins1 + coins2
    val subtractedCoins = coins1 - coins2

    // そして今回はすこーしだけHTMLにも色気を出しています。
    Ok(
      s"""
         |<h2>addedCoins</h2>
         |${addedCoins.html}
         |<hr>
         |<h2>subtractedCoins</h2>
         |${subtractedCoins.html}
         |""".stripMargin).as(HTML)

  }

  def step6_5 = Action { _ =>
    // 6. クラス
    // 6-5. 組込型の演算子とメソッド

    // 以前よりInt型やString型を始めとした、
    // 色々な型を見てきました。その際、例えば、
    // Int型の足し算き算などを以下のように記載していたと思います。
    val number1 = 1 + 2

    // さて、前回Coinsには`+`というメソッドを定義して、
    // 上記と似た感じに利用していました。
    // 実は、Int型の`+`も逆に以下のように記載できます。
    val number2 = 1.+(2)

    // そうです？
    // 実は全ての型の演算子は、
    // 組込として初めから定義されてはいるものの、
    // 実体は各型(クラス)のメソッド(やフィールド)だったのです(ドーン！👻)

    // ただし`1`や`2`等のリテラルは、
    // Coinsと違って`new`してないですね？
    // 組込型の全てではないですが、
    // 組込型は利用頻度がとても多いものが多いので、
    // Scala言語が、特別にわかりやすい、
    // ある意味、特殊なインスタンス生成方法を用意しているのが、
    // 各リテラル表記だったのです。

    // ※全てのプログラミング言語が同じ仕組みというわけではないのでご注意ください。

    // 前回以下と記載していましたが、
    // > "そのクラスと関係の深い"処理を含めておけば、
    // > 読みやすいプログラムに仕上げられる場合があります。
    // 組み込みクラス・メソッドのこともおさらいしつつ、
    // クラス・メソッドのお陰で非常にわかりやすく記述できているというのが、
    // 多少でも体感・認識いただけると嬉しいなと思います。

    Ok("クラス最高！メソッド最高！").as(HTML)

  }

  def step6_6 = Action { _ =>
    // 6. クラス
    // 6-6. クラスの継承

    // クラスは継承(extends)と言われる機能があります。
    // ざっくりとしたイメージは、
    // あるクラスのフィールド・メソッドを、
    // 別のクラスが引き継ぐというものです。

    // 具体例を見ていきましょう。
    // まずは今まで同様のクラス定義です。

    // 鳥クラス。
    class Bird() {
      // 鳴き声です。今は空になっています。
      val chirping: String = ""

      // 鳴くことができます。
      def song() = s"$chirping。"

      // 飛ぶことができます。
      def fly() = "ぱたぱた。"
    }

    // 次が本番です。
    // 以下が鳥クラスを継承する、
    // すずめクラスです。
    class Sparrow() extends Bird {
      override val chirping: String = "ぴよぴよ"
    }

    // クラスを継承する時は、上記のように、
    // `extends 継承するクラス名`というのを記載します。
    // ちなみに、継承されたクラス同士に関して、
    // 今回だと、Birdは親(スーパー)クラス、Sparrowは子(サブ)クラスと表現されます。
    // ちなみに、Sparrowクラスをさらに継承させることも可能です。
    // この場合、親の親は先祖クラス、
    // 子の子は子孫クラスとかといった表現をする方が多いと思われます。

    // さらにoverrideというのがvalの前についています。
    // 親クラスがもっているものは、基本的にそのまま子クラスが使えるようになるのですが、
    // 継承する過程で、任意のフィールドやメソッドを子クラス側で上書きすることができます。
    // それをする際には、`override`というのをつけないとエラーになります。
    // ※これは意図せず間違えて上書きしてしまうことを抑制するためのプログラミング言語としての工夫だそうです。

    // それではこれらのクラスを使ってみましょう。
    // 親クラス(鳥)
    val bird = new Bird // クラスパラメータが空なら`()`を省略できます。
    val birdSong = bird.song()
    val birdFly = bird.fly()
    // 子クラス(すずめ)
    val sparrow = new Sparrow
    val sparrowSong = sparrow.song() // Sparrowのクラス定義では直接指定していませんが、Birdを継承しているので利用可能。 
    val sparrowFly = sparrow.fly() // 同上。

    Ok(
      s"""
         |birdSong = $birdSong<br>
         |birdFly = $birdFly<br>
         |sparrowSong = $sparrowSong<br>
         |sparrowFly = $sparrowFly<br>
         |""".stripMargin).as(HTML)

  }

  def step6_7 = Action { _ =>
    // 6. クラス
    // 6-7. 抽象クラス

    // 継承の話の続編で、抽象クラスというのを紹介いたします。
    // すこーし今回は高度なトピックです。
    // まず名前がピンと来ないと思います。おそらく今回の説明を聞いても、
    // 実際の利用シーンがイメージできないとは思うのですが、
    // とりあえず雰囲気だけをまずは把握いただけると幸いです。

    // ・”あるクラス”があり、
    // ・そのクラスを直接利用することは無く(または他のプログラマにさせたくなく)、
    // ・”あるクラス”を継承をさせた子クラスをインスタンス化して間接的に利用する(またはしてほしい)、
    // という場面で、"あるクラス"部分を抽象クラスにすると都合が良くなります。

    // 例として、前回のBirdクラスはそのまま使いたくないんだよな、
    // という状況だったとして、Birdクラスを抽象クラスに変更してみます。

    // 抽象クラスは、
    // ・newでインスタンス生成ができない
    // ・"抽象フィールド"や"抽象メソッド"を含めることができる
    // という特徴を持ちます。
    // 具体例をみていきましょう。

    // 親の鳥"抽象"クラス。
    abstract class Bird() {
      // 鳴き声フィールドです。
      // しかし、中身が空になっています。これが抽象フィールドです。
      val chirping: String

      // 歌うメソッドは定義があります。これはいつものメソッドです。
      def song() = s"$chirping。"

      // 飛ぶメソッドです。
      // しかし、中身が空になっています。これが抽象メソッドです。
      def fly(): String
    }

    // 子のぺんぎんクラス。
    class Penguin() extends Bird {
      // 親が持つ"抽象"メンバ(フィールド・メソッド)は、
      // 子クラスでは必ず定義(実装)する必要があります。
      val chirping: String = "ぶるぁぁぁぁ！"
      // ※例外として子クラスも抽象クラスなら話は別です。

      def fly(): String = "無理だよ"

      // 前回はしていませんでしたが、
      // 追加のフィールドやメソッドを追加することも可能です。
      def swim(): String = "すいすーい"
      // ※これは抽象クラス関係なしに可能です。
    }

    // 前回上書きの場合は、`override`というのがついていましたが、今回は無くなっています。
    // 実は付けても問題ないのですが、上書きする対象が抽象メンバであった場合は、
    // `override`を省略してもいいということになっています。

    // ではこれらのクラスを使ってみましょう。
    // 親クラス・・・は抽象クラスなので以下のように書くとエラーになります！
    // val bird = new Bird // NG

    // 子クラス(ぺんぎん)は抽象クラスではないので、今まで通りnewできます。
    // (抽象クラスの対義語として具象クラスがありますが、聞くのも言うのも機会が少ない印象です。)
    val penguin = new Penguin
    val penguinSong = penguin.song()
    val penguinFly = penguin.fly()
    val penguinSwim = penguin.swim()

    Ok(
      s"""
         |penguinSong = $penguinSong<br>
         |penguinFly = $penguinFly<br>
         |penguinSwim = $penguinSwim<br>
         |""".stripMargin).as(HTML)

  }

  def step6_8 = Action { _ =>
    // 6. クラス
    // 6-8. 型と継承と多態性

    // お馴染み"型"の話です。
    // 継承関係にある親子クラスの型に関して、
    // 少しばかし、ややこしい仕様があるので🤔
    // ここでその一部を解説します。

    // まずは説明のために、
    // 親子関係を持つクラスの定義です。

    // 鳥クラス(抽象)
    abstract class Bird() {
      val chirping: String // 抽象フィールド

      def fly(): String = "ぱたぱた"
    }

    // すずめクラス
    class Sparrow extends Bird() {
      val chirping: String = "ぴよぴよ"
    }

    // ぺんぎんクラス
    class Penguin() extends Bird {
      val chirping: String = "ぶるぁぁぁぁ！"

      override def fly(): String = "無理だよ" // 上書き

      def swim(): String = "すいすーい" // 追加
    }

    // それでは、、

    // ポイントその①
    // 子クラスは親(先祖)クラスの型の変数に格納できます。
    // 同様に、親クラス型が返り値型になっている関数の、
    // 返り値として指定できます。
    // 以下は変数の場合の例です。
    val bird1: Bird = new Sparrow
    val bird2: Bird = new Penguin

    // ポイントその②
    // 親(先祖)クラス型の変数に入っていたとしても、
    // メンバ(フィールド・メソッド)呼び出しを行った場合の処理は、
    // 実際の子クラスの処理結果になります。
    val bird1Song = bird1.fly() // "ぱたぱた"
    val bird2Song = bird2.fly() // "無理だよ"
    // ※chirpingも同様です。

    // 【補足事項】
    // これは覚えなくていいと思いますが、
    // こういった挙動を専門用語で多態性(ポリモーフィズム)と呼んだりします。
    // さらに細かくいうと、多態性の中でも、"サブタイプ多相"に分類されます。
    // 【補足事項 ここまで】

    // ポイントその③
    // 親(先祖)クラス型の変数に入ると、
    // "子で独自に追加されたメンバ"をそのまま参照することはできません。
    // Penguinでは`swim`が追加で定義されていますが、
    // bird2.swim()
    // はエラーになってしまいます💣🔥

    // ポイントその③'
    // swimを呼びたい場合はmatch式を使います。
    val bird2Swim = bird2 match {
      case b: Penguin => b.swim() // matchで型チェックが済んでるので、bはPenguin型として扱えます。
      case _ => "Penguin型ではないです。"
    }

    // もっと込み入った話題もありますが、
    // この③点と今までの話をおさえていれば、
    // おおよそ継承の基礎はマスターしていると言えると思います✨

    // ただ、今回の話だけですと、
    // これの何がうれしいの？ どこで使うの？
    // というのが、おそらくわからないと思います😭
    // もう少し後に解説する組込型の`Option`が、
    // 実際の応用例の1つになります。
    // 一旦は、そんなもんかぐらいで先に進んでみてください。

    Ok(
      s"""
         |bird1Song = $bird1Song<br>
         |bird2Song = $bird2Song<br>
         |bird2Swim = $bird2Swim<br>
         |""".stripMargin).as(HTML)
  }

  def step6_9 = Action { _ =>
    // 6. クラス
    // 6-9. Any型・Object型

    // 今回は番外編です。
    // よくわからんぞ？となった場合でも気にせず次に進んで問題ない内容です。

    // まずはAny型って何？という話です。
    // おそらく、もう忘れちゃっていることと思いますが、
    // 3-4にて登場していた型です。折角なのでぜひこの機会に復習してみてください。

    // さて、このAny型は、
    // 全ての型の先祖クラスである、という特別な存在です。
    // よって、ヴェルタースオリジナルです(スルーしてください)。
    // なんと自分で定義したクラスについても同じで、
    // Anyをextendsしていなくても勝手にそうなるのです。

    // つまり以下のように、
    // なんでもかんでもAny型の変数に入れることができます。
    // ※関数の戻り値の型としても同様です。

    class Something() // 自分で定義したクラス

    val someAsAny: Any = new Something() // Any型の変数に入る。
    val stringAsAny: Any = "string"
    val intAsAny: Any = 10
    val boolAsAny: Any = true

    // 一方で、Any型がでてきてしまうと、
    // match等を使って型を判定する必要が多いので、
    // 原則避けるべき型という捉え方が良いのではないかと思います。

    // そして、さらに、
    // 類似したObjectという型(クラス)というのも存在します。
    // 以下のように、おおよそAny型と同じなのですが、
    // 数値型や真偽型はObject型ではないという罠もあります。
    val someAsObj: Object = new Something() // 自分で定義したクラスは必ずObject型として扱えます。
    val stringAsObj: Object = "string"
    // val intAsObj: Object = 10 // エラー
    // val boolAsObj: Object = true // エラー

    // この違いからも予想できるかもですが、
    // AnyはObjectよりも上位なので、Object型の値をAny型の変数には入れられますが、
    // 逆はだめです。

    // Object型には`toString`メソッド等がいくつか組込実装されています。
    // なので、空っぽ定義のSomething型のインスタンスでも、
    // toStringが呼び出せてしまいます。
    // わお。
    val someAsString = (new Something()).toString

    // ただし、Object型のtoStringが望ましい文字列を返さない事の方が多いと思われますので、
    // むしろ、仮に同名のtoStringというメソッドを独自実装したい場合は、
    // `override`する必要があるという点に注意しましょう。

    class SomethingGreat() {
      // Object型で定義されているメンバはoverrideが必要
      override def toString(): String = "偉大だわぁ"

      // ちなみに、`super.メンバ`で親クラスのメンバを呼び出せます。
      def toStringByObject(): String = super.toString // Object型で実装されている`toString`を呼び出しています。
    }

    val sg = new SomethingGreat()
    val sgAsString = sg.toString()
    val sgAsStringByObject = sg.toStringByObject()

    Ok(
      s"""
         |sgAsString = $sgAsString<br>
         |sgAsStringByObject = $sgAsStringByObject<br>
         |""".stripMargin).as(HTML)
  }

}
