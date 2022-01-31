package controllers.chapter2

import play.api.mvc._

import javax.inject._

@Singleton
class Chapter2_5Controller @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def chapter2_5_1 = Action { _ =>
    // 5. 関数
    // 5-1. 関数の無い世界

    // 急ですが、例えば、ゲーム等で人型のキャラクターがいたとします。
    // そのキャラクターが"前に移動"させるのに、以下の処理が必要だったとします。
    // 【注意】説明のために意味のないプログラムを書いています💩
    var state = ""
    state = "右足を上げる。"
    state = "右足を前に出す。"
    state = "右足を下げる。"
    state = "左足を上げる。"
    state = "左足を前に出す。"
    state = "左足を下げる。"

    // 上記はあくまでもイメージですが、
    // 実際のプログラミングでも、何かをしたい時に、
    // 細かく指示を書いていくことは多々発生します。
    // では、さらに、キャラクターが"右に移動"する処理が以下だったとします。
    state = "右足を上げる。"
    state = "右足を右に出す。"
    state = "右足を下げる。"
    state = "左足を上げる。"
    state = "左足を右に出す。"
    state = "左足を下げる。"

    // よし！ここまでの処理がかけたので、
    // 前・右・前と進む処理を書くぞ！となった場合。
    // 以下と書いていくのは、なんとなく地獄感を感じるのではないでしょうか？
    state = "右足を上げる。"
    state = "右足を前に出す。"
    state = "右足を下げる。"
    state = "左足を上げる。"
    state = "左足を前に出す。"
    state = "左足を下げる。"
    state = "右足を上げる。"
    state = "右足を右に出す。"
    state = "右足を下げる。"
    state = "左足を上げる。"
    state = "左足を右に出す。"
    state = "左足を下げる。"
    state = "右足を上げる。"
    state = "右足を前に出す。"
    state = "右足を下げる。"
    state = "左足を上げる。"
    state = "左足を前に出す。"
    state = "左足を下げる。"

    // プログラミングをしていますと、日常的に、
    // よりよくするための改善や、
    // だめなところを直すためのバグ・不具合修正を行います。

    // 例えば、"前に移動"する処理が、本当は、
    // "右足を上げる"前に、"左足で踏ん張る"が必要であり、
    // 同様に、"左足を上げる前に"、"右足で踏ん張る"が必要だったと"後から"判明した場合、
    // 上記のプログラムを修正するだけでも、
    // 書き間違えそう感は否めません。

    // この地獄から私達を救ってくれるのが、
    // そう。関数です。

    // 次回へ続く！

    Ok("関数最高！").as(HTML)
  }

  def chapter2_5_2 = Action { _ =>
    // 5. 関数
    // 5-2. 関数のある世界

    // 関数。なんといい響きなんでしょうか(謎)。
    // 関数はすごく雑に言い表しますと、
    // "処理の塊に名前をつけて、再利用できるようにしたもの。"
    // です。

    // 前回の"前に移動"・"右に移動"する処理を、
    // 関数にしてみると以下のような形になります。

    def moveToFront(): Unit = {
      var state = ""
      state = "右足を上げる。"
      state = "右足を前に出す。"
      state = "右足を下げる。"
      state = "左足を上げる。"
      state = "左足を前に出す。"
      state = "左足を下げる。"
    }

    def moveToRight(): Unit = {
      var state = ""
      state = "右足を上げる。"
      state = "右足を右に出す。"
      state = "右足を下げる。"
      state = "左足を上げる。"
      state = "左足を右に出す。"
      state = "左足を下げる。"
    }

    // 関数を定義する方法の1つが上述例の通り、
    // 以下の書き方になります。
    // def 関数名(引数): 関数が返す型 = { 処理 }

    // `引数`は次回解説しますので一旦お忘れを。

    // 処理部分が`{}`で囲まれていますが、
    // これは2-10でやったブロックと同じです。
    // 1行だけなら`{}`は省略可能で、
    // 最終行がそのブロックの値になるというのも同じです。

    // 今回、初登場のUnitという型名が"関数の返す型"として指定されていますが、
    // このUnitというのは特殊な型で、
    // "値が無い"ことを表す型になっています。
    // 今回、どちらの関数も最終行は変数に値を入れる処理になっていますが、
    // 代入演算子`=`は変数に値を入れて、それで終わりで、
    // 式の結果としてはUnitということになっていますので、
    // 関数の結果としてもUnitとなる形です。

    // 説明が長くなっていますが、
    // もう1つ大事なポイントとしまして、
    // 関数は定義しても、呼び出さなければ何も動かないという特徴があります。
    // 呼び出す際には、関数名を改めて書く形になります。
    // 前回同様に、"前・右・前と進む処理"をやりたい場合は以下となります。
    moveToFront()
    moveToRight()
    moveToFront()

    // 処理の塊に対して、意味のある名前がついたので、
    // 何をしているのかが、非常にわかりやすくなったのではないでしょうか？

    // さらに、修正が必要になった時も、
    // 例えば"前に進む"処理は、"moveToFront"関数の中に一度だけ書かれているだけなので、
    // 一箇所修正すればそれで済むことになります。

    // YES.これがヘブンです。

    Ok("関数最高！！").as(HTML)
  }

  def chapter2_5_3 = Action { _ =>
    // 5. 関数
    // 5-3. 関数の引数

    // 前回の関数は、何も値が返ってこないものでした(Unit型の値を返しているとも言えます)。
    // 今回は呼び出す時に、なんらかの値を渡して(関数に渡す値のことを引数(ひきすう)といいます)、
    // かつ、値も返ってくる例をみていきます。

    // 以下の例はInt(整数)型の引数を2つ指定して、
    // 与えられた引数の内、大きい方の数字を返してくれる関数になります。

    def larger(a: Int,
               b: Int): Int = {
      // 引数の値は関数内でvalで宣言した変数のように扱えます。
      if (a >= b) a else b
    }

    // 使う際は以下のようになります。
    // 返り値を変数に入れています。

    val larger1 = larger(10, 100) // 100
    val larger2 = larger(-10, -100) // -10

    // 引数に指定する値は、数が足りなくても、多くても、
    // 型があわなくても、エラーになるので注意しましょう💣

    Ok(
      s"""
         |larger1 = $larger1<br>
         |larger2 = $larger2<br>
         |""".stripMargin).as(HTML)

    // ち・な・み・に💝
    // 今回実装したlarger関数ですが、
    // 実は`Math.max`という組込関数がはじめから用意されていて、
    // 以下のように利用できます。
    // Math.max(10, 100)

    // 他にも、便利な関数がたくさん初期から用意されているので、
    // 実際にプログラムを書いていく中では、
    // 作ろうとしている関数が既に存在しないかを、
    // 調べてから作る方が多くの場合に幸せになれると思います(ぐぐって調べる事が多くなると思います)。
    // ※プログラミングの練習目的にあえて自分で作るというのはグッドです🍀

    // また、Scala本体が用意していなくても、
    // オープンソース(ソースコードが一般的に公開されている)の
    // ライブラリ(便利な関数の集合体みたいなイメージ)も、
    // 世界の有志達のお陰で、たくさん存在していますので、これが活用できる場面を多々あります。
    // ライブラリの追加方法などについても、
    // 少し先になりますが、ご紹介する予定です。たぶん🐣

  }

  def chapter2_5_4 = Action { _ =>
    // 5. 関数
    // 5-4. 関数で複数の値を返したい？タプル型・・あるよ。

    // 関数の返り値は、値を1つしか返せないというルールがあります。
    // もう少し正確に表現すると、1つの型の値しか返せないというルールです。
    // ここで鋭すぎる方はぴーーーーーん！ぽーーーーん？と来たかもしれません。
    // 型が1つならば良いので、4-1等で登場したList等を使えば複数値を返せます。

    // 例えば、2つのInt型の引数を受け取って、
    // それを小さい順に並べた値を返す例を考えてみましょう。
    // これは以下のように書くことができます。

    def sort(a: Int,
             b: Int): List[Int] = {
      if (a <= b) List(a, b) else List(b, a)
    }

    val sorted = sort(2, 1) // List(1, 2)

    // ただ、List等のコレクション型は、
    // 返り値の型だけを見ても、値がいくつ返ってくるのかがわからないという点や、
    // もし返したい値の型が別々だった場合にList[Any]のような扱いにくい型が登場してしまうことになります。
    // ※Any型については3-4を参照ください。
    // List(1, "s")みたいに書くと、これはList[Any]型になります。

    // ようやく本題ですが、型が別々になる時はタプル型というのが便利です。
    // 例えば、漢数字を1つ引数として、
    // それをInt型にしたものとひらがな読みしたものを、
    // セットにして返却する関数は、以下のように書けます。

    def kanjiNumberToIntAndKana(kanjiNumber: Char): (Int, String) = {
      kanjiNumber match {
        case '一' => (1, "いち")
        case '二' => (2, "に")
        case '三' => (3, "さん")
        case _ => (-1, "非対応の文字が入力されました。")
      }
    }

    // タプル型は任意の型を丸括弧でカンマ区切りにして表現します。
    // 上記例の返り値の型は`(Int, String)`ですが、
    // Int型とString型の組合せの型ということになります。
    // 実際の値も同様に`(1, "いち")`の例のように丸括弧でそれぞれの型の値を指定します。
    // 受け取る側は以下のようになります。

    val numberAndKana = kanjiNumberToIntAndKana('一')
    val number1 = numberAndKana._1 // １つ目の値を取得できます。
    val kana1 = numberAndKana._2 // 2つ目の値を取得できます。

    // さらに以下のように分解する方法もあります。
    val (number2, kana2) = kanjiNumberToIntAndKana('二')
    // 一度タプルで受け取った変数から上記の分解も可能です。
    val numberAndKana3 = kanjiNumberToIntAndKana('三')
    val (number3, kana3) = numberAndKana3

    // なお、今回は値が2つのタプルの例を出しましたが、
    // 最大22個まで値を連ねることが可能です。
    // ※Scala3からは22の制約はなくなり無制限に連ねられるそうです。

    Ok(
      s"""
         |sorted = $sorted<br>
         |(number1, kana1) = ($number1, $kana1)<br>
         |(number2, kana2) = ($number2, $kana2)<br>
         |(number3, kana3) = ($number3, $kana3)<br>
         |""".stripMargin).as(HTML)

    // ば・い・ざ・うぇい💝
    // 例示した小さい順の並び替え関数ですが、
    // これも組込の仕組みで、以下のような書き方ができます。
    // List(a, b).sorted
    // 逆に大きい順も、以下と書けたり、
    // List(a, b).sorted.reverse
    // 少ーし難易度の高い表現を利用して、
    // List(a, b).sortBy(-_)
    // であったり、さらには、
    // List(a, b).sortWith(_ > _)
    // という書き方もできます。
    // まだ意味不明・ワケワカメだと思いますが、
    // 近い将来にはご理解いただけるようになっているはずです。
  }

  def chapter2_5_5 = Action { _ =>
    // 5. 関数
    // 5-5. タプル型・・・だと厳しい？

    // 引き続きタプル型を使った例を見てみます。
    // お金をイメージいただき、
    // 硬貨の種類毎の枚数をタプル型で表現してみるとします。
    // 例えば左から大きい順に、500・100・50・10・5・1円玉の枚数としてタプル型を定義したとします。
    // 以下のような感じです。
    val coins: (Int, Int, Int, Int, Int, Int) = (1, 2, 3, 4, 5, 6)
    // 上記の例は、
    // 500円玉が1枚
    // 100円玉が2枚
    // ・・・省略・・・
    // 1円玉が6枚
    // というデータを変数に入れたようなイメージです。

    // この型のデータを引数に受け取り、
    // 500円玉を全て100円玉に両替するような関数を考えてみましょう。
    // 返り値は、両替した後の枚数になった同じ型の値を返します。
    // 以下のような感じです。
    def exchange500For100(coins: (Int, Int, Int, Int, Int, Int)): (Int, Int, Int, Int, Int, Int) = {
      val exchanged100coins = coins._1 * 5
      (0, coins._2 + exchanged100coins, coins._3, coins._4, coins._5, coins._6)
    }

    // 使用例はこんな感じ。
    val exchangedCoins = exchange500For100(coins)

    // ででで、まぁこれでも問題はないのですが、
    // (Int, Int, Int, Int, Int, Int)
    // この型は、みるだけでは、ただただIntが6つ並んでいるタプル型なので、
    // これが硬貨の枚数だという情報は、先に説明をもらわないと初見では解読しづらいと私は感じます。
    // さらにこの後のプログラムで、例えば、50円玉の枚数を取得しようとした場合に、
    // coins._3
    // という記載になりますが、50円玉が何番目かを頭で考えて記載する時に、
    // とても間違えやすそうにも感じます。

    // プログラムは書いた本人ですら、細かいことはすぐ忘れてしまうことが多いので、
    // 長く利用する・大きなプログラムでは特に、読みやすい・予想しやすいというのが重要になってきます。
    // プロが仕事で作成するプログラムは、数年以上使われるものも多いと思いますし、
    // チームで開発することが大半で、開発人員が途中入れ替わることも多々あります。
    // なので！プロが書くプログラムならば、
    // 開発の後継者(未来の自分も含めた)の人が、なるべく読みやすく書くべきと、私は考えています。
    // 初学者の方にプロの話を持ち出すのは、無粋かもしれませんが、
    // ぜひ意識していただきたいポイントだと思いましたので、少し長めに文章を入れてみました。

    // では今回の例を読みやすくするのはどうするの？ですが、
    // その1つの方法として、次回から登場する"クラス"というのを利用します。

    // 次回に続きます。

    Ok(
      s"""
         |coins = $coins<br>
         |exchangedCoins = $exchangedCoins<br>
         |""".stripMargin).as(HTML)

  }

}