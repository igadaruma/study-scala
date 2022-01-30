package controllers.chapter2

import play.api.mvc._

import javax.inject._

@Singleton
class Chapter2_3Controller @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def chapter2_3_1 = Action { _ =>
    // 3. 分岐
    // 3-1. if ~ else if ~ else

    // ここからです。
    // ここからいよいよプログラミング感がでてきます。
    // 状況によって処理を切り替える分岐をやっていきます！

    // 暫くは状況を固定した例ばかりになりますので、
    // 決まった道にしかいきません。少々つまらないかもですが何卒です。

    // 今回は分岐の代表格`if`です。日本語で`もし`です。
    // さらに`else`です。日本語で`そうでないなら・ちゃうかったら`です。
    // ifはいきなり書けますが、elseはifに続く形でのみ書くことができます。
    val message1 =
      if (1 == 1) "1は1と等しいです。" // if (Boolean型) trueの時の処理
      else "1は1と等しくないです？" // else falseの時の処理
    // message1 => (1 == 1)はtrueなので、"1は1と等しいです。"が入ります。

    // `else if`(そうでなくて、もし)というのもあります。
    val message2 =
      if (2 == 3) "2と3は等しいです？"
      else if (2 == 2) "2と2は等しいです。"
      else "2は2とも3とも等しくないです？"
    // message2 => `(2 == 3)`はfalseで、`(2 == 2)`はtrueなので、"2と2は等しいです。"が入ります。

    // if ~ (else if) ~ else はセットで利用します。
    // セットの中でどれか1つだけの分岐に入って処理されることになります。

    // 分岐内の処理が複数の式を使いたい場合は`{}`で囲ってあげます。
    // 前回の内容と同様に、`{}`ブロックの中では最終行の結果がブロックとしての値になります。
    val message3 =
    if (1 + 2 == 3) {
      val one = 1
      val two = 2
      s"$one + $two = ${one + two}" // この行の結果がif(true)の場合の結果になります。
    } else "1 + 2 = 3は間違いです？"

    Ok(
      s"""
         |message1 = "$message1"<br>
         |message2 = "$message2"<br>
         |message3 = "$message3"<br>
         |""".stripMargin).as(HTML)
  }

  def chapter2_3_2 = Action { _ =>
    // 3. 分岐
    // 3-2. 入れ子のif式

    // if ~ else if ~ elseは入れ子にできます。
    val message1 =
      if (5 < 10) { // 中にさらにif式を書けます。
        if (5 > 1) "5は10より小さい かつ 5は1より大きい です。"
        else "5は10より小さい かつ ５は1より大きくない です？"
      } else { // もちろんelseの中でも。
        if (5 < 3) "5は10より小さくなく かつ 5は3より小さい です？"
        else "5は10より小さくなく かつ 5は3より小さいもないです？"
      }
    // 2階層の例ですが、いくらでも階層は増やせます。
    // 階層が深くなると読むのは辛いですが・・😟

    // でもでも、
    // 2-9で紹介した論理演算を使って複数条件を一度に指定することも可能なので、
    // 状況によって使い分けます。
    val message2 =
    if (15 < 30 && 15 < 20) "15は30よりも小さい かつ 15は20よりも小さい です。"
    else "(15は30よりも小さい かつ 15は20よりも小さい) ということは無いです？"

    Ok(
      s"""
         |message1 = "$message1"<br>
         |message2 = "$message2"<br>
         |""".stripMargin).as(HTML)
  }

  def chapter2_3_3 = Action { _ =>
    // 3. 分岐
    // 3-3. match式

    // match式はif式の強化版みたいな存在です。
    // 以下はシンプルな例です。
    val n = 2
    val message1 = n match {
      case 1 => "nは1です。" // このcaseには来ません。
      case 2 => "nは2です。" // このcaseに来ます。
      case 3 => "nは3です。" // このcaseには来ません。
    }

    // match式はif式と違って`{}`を使わなくても、
    // そのcase内で複数の行を書くことができます。
    // この場合もそのcase内の最終行の結果が最終的な値となります。
    val y = "Y"
    val message2 = y match {
      case "X" => // このcaseには来ません。
        val yIsX = "yはXです？"
        yIsX // ここがこのcaseの値

      case "Y" => // このcaseに来ます。
        val yIsY = "yはYです。"
        yIsY // ここがこのcaseの値
    }

    // 【注意】
    // match式のcaseの全てに当てはまらないと・・
    // エラーが発生しちゃいます💣🔥 
    // そんな時のために"それ以外"というのを`_`で記述できます。

    val z = "Z"
    val message3 = z match {
      case "X" => "zはXです？" // このcaseには来ません。
      case "Y" => "zはYです？" // このcaseには来ません。
      case _ => "zはZでもYでもないです。" // このcaseに来ます。
    }

    // ちなみに`_`以外の任意の名前を記述することで、
    // その値をcase内で利用することができます。
    val message4 = z match {
      case "X" => "zはXです？" // このcaseには来ません。
      case "Y" => "zはYです？" // このcaseには来ません。
      case something => s"zはXでもYでもなく${something}です。" // このcaseに来ます。
      // ↑ 名前は自由につけられます。
    }

    // 【注意】
    // caseは順番も大事です。
    // 全部に当てはまらないとエラーになる一方で、
    // 上のcaseから順番に確認され、
    // 最初に当てはまったcase以外は無視されるので、
    // 以下のように書くと2個目以降のcaseは意味ナッシングです。
    val x = "X"
    val message5 = x match {
      case something => s"xは何かです。${something}です。" // `_`や任意の変数名には全てマッチするのでこのcaseに来ます。
      case "X" => "xはXです。" // このcaseには来ることはできません！
      case "Y" => "xはYです？" // このcaseには来ることはできません！
    }

    Ok(
      s"""
         |message1 = $message1<br>
         |message2 = $message2<br>
         |message3 = $message3<br>
         |message4 = $message4<br>
         |message5 = $message5<br>
         |""".stripMargin).as(HTML)

  }

  def chapter2_3_4 = Action { _ =>
    // 3. 分岐
    // 3-4. 分岐と型

    // また型です。型はすごく大事です。型の話です。
    // 分岐の際、それぞれの分岐で結果の値が存在することになります。
    // 今までの例では、分岐しても、それぞれが同じ型になるようになっていました。ふふ。
    // そうなると、その型が最終的な分岐式の型になります。
    // 何いってんの？というと、
    val message: String = // 分岐先でどっちもStringなのでmessageの型もString
      if (true) "trueだよ。" // こっちString(文字列)
      else "trueじゃないよ。" // こっちだってString(文字列)
    // ということです。

    // じゃあ違った場合はどうなるのかというと、Any型というものになります。
    val any: Any = // 両者の型が違うのでAnyという`曖昧な型`になります。曖昧なのでInt型でもString型でも入れる事ができるという感じ。
      if (true) 1 // こっちInt(整数)
      else "1" // こっちString(文字列)
    // 違ったら必ずAny型になるわけではないのですが、今は秘密です🤫

    // んで？と思うかもしれません。
    // 先の例では、anyの実際の値は1(Int型)となるわけなのですが、
    // 変数anyの型がIntではなくAnyになってしまうので、、
    // val answer = any + 10
    // 上記のような計算をしようとするとエラーになります・・。
    // Any型とInt型を足す方法とか知らねーよ。と怒られちゃうのです。
    // なんで！と思うかもしれませんが。Scalaだとこうなのです。これこそ安心なのです。なぜか？それは秘密です🤫
    // ※言語によっては計算できちゃったりします。それは怖いのです🥺

    // 分岐したときは全ルートで同じ型にする！
    // というのを原則にしましょう。原則ネ。
    // 今回if式の例でしたが、match式も同じです。

    // ただ、Any型になってしまっても、
    // 実は前回、隠していたmatch式の、
    // さらなる力(ぱわぁ)を使えば以下のようにこの状況を解決することができます。
    val answer = any match {
      case n: Int => // こんな感じで型アノテーションをつけてあげることで、`その型だったら`という条件がつきます。
        n + 10 // ここでは、intはInt型だと判定されたcase内なので、足し算できるZO！
      case _ => 0 // Intじゃない時にエラーにならないように、型アノテーション無し版のcaseも書いておきましょう。
    }
    // さらっと出てきましたが、
    // 実はすごく大事なぱわぁなので、
    // また詳しく触れます。

    Ok(
      s"""
         |message = $message<br>
         |any = $any<br>
         |answer = $answer<br>
         |""".stripMargin).as(HTML)
  }

  def chapter2_3_5 = Action { _ =>
    // 3. 分岐
    // 3-5. match式でのガーーーード(if)！

    // match式はif式の強化版といった割に、今まで紹介した中で、
    // if式より強い力(ぱわぁ)は前回の型チェックぐらいでした。
    // そうです。まだ隠された力があります。
    // その1つがパターンガードです。名前は覚えなくていいです。
    // 具体例を見ていきましょう。
    val x = 15
    val message1 = x match {
      case n if n > 20 => s"20より大きいです。${n}です。"
      case n if n > 10 => s"10より大きいです。${n}です。" // このcaseに来ます。
      case n => s"${n}です。"
    }

    // という感じです`if Boolean型の式`という感じで指定することで、
    // Boolean型がtrueになる場合だけ。という条件が付きます。強いぞmatch式！

    // もちろん、今までのものと組み合わせることができます。
    val any: Any = "any" // わざとString型なのにAny型に格納。
    val message2 = any match {
      case n: Int if n > 10 => s"10より大きい整数です。${n}です。"
      case _: Int => "整数です。"
      case s: String => s"文字列です。'${s}'です。" // このcaseに来ます。
      case _ => "数字でも文字列でもない何かです。"
    }

    // 実はさらにあるのですが・・まだです。まだ秘密です🤐

    Ok(
      s"""
         |message1 = $message1<br>
         |message2 = $message2<br>
         |""".stripMargin).as(HTML)
  }

  def chapter2_3_6 = Action { _ =>
    // 3. 分岐
    // 3-6. 変数の範囲(スコープ)

    // 2-10にて`{}`によるブロックの話がありまして、
    // if式でも`{}`ブロックを用いていました。
    // さらにmatch式では`{}`ブロックではないですが、
    // `case ? =>`というような記述の後に、
    // 複数行の式を記述できるという話もありました。

    // このようなブロック領域の内側で、
    // 変数を作った(宣言した)場合に、実は、
    // 外側の領域ではその変数を使えない、
    // というルールがあります！なんだって！

    val outside1 = {
      val inside1 = s"ブロックの中で宣言"
      inside1 // ← このブロックの値
    }
    val outside2 = outside1 // 大丈夫だ。問題ない。
    // val inside2 = inside1 // 💣💥 inside1はブロックの中で作られたので外側からは使えない！

    // 一方で、外側で作られた変数を、内側で見ることはできます。
    val outside3 = {
      outside2 // 大丈夫だ。問題ない。
    } // ※1行だけで演算もないので、ブロックにする意味がない例ですが・・🐣

    // これはブロックが入れ子になっても、
    // 同じようにルールが適応されます。

    // 最後に少しややこしい例をあげておしまいです。
    // コメントだらけで見づらいですが、内容的には、
    // ・外側の変数は内側で使える。
    // ・ブロック内で宣言された変数はそのブロックが終了してしまえば、それ以降は使えない。
    // というだけでござります。

    val scope1 = 1
    if (true) { // scope1が見えるブロック開始です。
      val scope2 = 2
      if (true) { // scope1,2が見えるブロック開始です。
        val scope3 = 3
        // ここでscope3はさよなら。
      } else { // scope1,2が見えるブロック開始です。scope3は見えません。
        val scope4 = 4
        // ここでscope4はさよなら。
      }
      // ここでscope2はさよなら。
    } else { // scope1が見えるブロック開始です。scope2,3,4は見えません。
      val scope5 = 5
      if (true) { // scope1,5が見えるブロック開始です。
        val scope6 = 6
        // ここでscope6はさよなら。
      } else { // scope1,5が見えるブロック開始です。scope6は見えません
        val scope7 = 7
        // ここでscope7はさよなら。
      }
      // ここでscope5がさよなら。
    }

    Ok(
      s"""
         |outside1 = $outside1<br>
         |outside2 = $outside2<br>
         |outside3 = $outside3<br>
         |""".stripMargin).as(HTML)

    //【補足】
    // 外側で既に宣言されている変数と同じ名前で、
    // 内側のブロックで重ねて宣言した場合は、
    // 内側にいる間は、外側の変数が隠れたような形で、内側で宣言したものが優先されます。
    // ブロックの外に出たら、元の外側の変数が見えるようになります。
    // ややこしいので、基本は避けるようにしたほうがいいかなと思います。

    // 例）
    // val x = "x"
    // val innerX = {
    //    val x = "えっくす" 
    //    x // このブロックが終わるまではxは"えっくす"になります。
    // }
    // // 外に出たらxは元の"x"に戻ります。innerXは"えっくす"です。
    // ややこしネ！

  }

  def chapter2_3_7 = Action { _ =>
    // 3. 分岐
    // 3-7. ifとmatchの使い分け

    // matchがifを包括する機能を持つので、
    // matchを使うことを基準に考えつつも、
    // if(x == 1) a else b
    // といったシンプルな条件の場合は、match式だと大げさ(書くのが面倒)になるので、
    // 楽にかけるifを選べば良いかな？という感じです。

    Ok("結論：使い分けるべし。").as(HTML)
  }
  
}
