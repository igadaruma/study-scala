package controllers.chapter3

import play.api.mvc._

import javax.inject._

@Singleton
class Chapter3_2Controller @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def step2_1 = Action { _ =>
    // 2. 型
    // 2-1. 初めての型

    // 全てのデータ(値)には"型"というのが存在しています。
    // 型というのは、とりあえず今は、データの形みたいなものと捉えてみてください。

    // 変数名等の右に`: 型名`という形で"型"を明示できます。
    // これは型アノテーションって言うんだってばよ。
    // 文字列は実はString型なので、以下のような表記ができます。
    val message: String = "こんちゃーす"
    Ok(message).as(HTML)

    // 「で？」と思うかもしれませんが、 
    // 気にせず次にいきましょう。
  }

  def step2_2 = Action { _ =>
    // 2. 型
    // 2-2. 演算子とString型の足し算

    val message1: String = "はい。" // String(文字列)型
    val message2 = "みなさん、こんばんは。" // String(文字列)型 (型アノテーションがなくても型がなくなることは、あーりません。)

    // String型とString型は足し算(?)できます。
    // 以下は、変数message1と変数message2を足し算したものを変数messageに入れてます。
    val message: String = message1 + message2

    // String型とString型を足すと、双方が結合された文字列になります。
    Ok(message).as(HTML) // "はい。みなさん、こんばんは。"

    // 今回`+`記号が出てきましたが、
    // こんな感じで、計算を行うための記号類を"演算子"と呼んだりします。
    // ちなみに変数に値を入れている`=`も代入演算子と呼ばれるような演算子の一種です。
    // (※今まで隠していましたが変数に値を入れることを"代入"といったりします。ひみつやで🤫)
  }

  def step2_3 = Action { _ =>
    // 2. 型
    // 2-3. 初めてのInt(整数)型

    // そろそろString以外の型もみていきましょう。そうしましょう。
    // 今回はInt(整数)型です。整数は非常によく使うことになると思います。

    val integer1: Int = 1 // 整数を書くとInt(整数)型のリテラル(データ)になります。
    val integer2 = 2 // 型アノテーションは省略できやす。(型がなくなるわけではないですよっと)。

    // ちなみに！
    // 以下のようにInt型の変数にString型を入れようとするとエラーになっちゃいます。
    // val integer: Integer = "Hello"
    // ※逆も同じです。

    // Int型とInt型だって、足し算できます。もちろんさ。
    val integer: Int = integer1 + integer2

    // ここで！
    // お馴染みの、Ok()の中に入る値は、実は、
    // String型は指定できますが、Int型はだめょという型制約があります。
    // つまり以下はエラーになります。
    // Ok(integer).as(HTML)

    // IntをStringに変換することでエラーを回避できます。
    Ok(integer.toString).as(HTML) // `.toString`をつけるとString型に転生できます。

    // この型による制約がミソです。今は、ミソ？ふーん。でOKです。
  }

  def step2_4 = Action { _ =>
    // 2. 型
    // 2-4. 初めてのDouble(小数)型

    // 整数をやったので今回は小数型です。
    // こちらもよく使うことになると思います。

    val double1: Double = 0.1 // Double(小数)型
    val double2 = 0.5 // 小数リテラルは、Double型として判定されます。
    val double: Double = double2 - double1 // 足し算はもうマスターしたので引き算してみましょう。

    Ok(double.toString).as(HTML) // Double型も`.toString`でString型に変身できます。
  }

  def step2_5 = Action { _ =>
    // 2. 型
    // 2-5. Int型とDouble型

    // IntとDoubleを四則演算した結果は、
    // より精度の高いDouble型になります。

    val int: Int = 2 // Int(整数)型
    val double: Double = 0.1 // Double(小数)型
    val result = int * double // `*`は掛け算です。

    Ok(result.toString).as(HTML)

    // 【注意】
    // この挙動(Double型になる)はScala限定の話です。
    // 他のプログラミング言語で同じ挙動になるとは限らないです。
  }

  def step2_6 = Action { _ =>
    // 2. 型
    // 2-6. String型とInt型

    val string: String = "10"
    val integer: Int = 2

    // String型とInt型を足すと文字列結合になります。おっと？
    val result1: String = string + integer // = "102" 

    // String型に`.toInt`をつけるとInt型に変換できます。
    val result2: Int = string.toInt + integer //  = 12

    // 以下は String型(result1) + String型(",") + Int型(12) で、最終的にString型になっている例です。
    val result: String = result1 + "," + result2 // "102,12"

    Ok(result).as(HTML)

    //【!注意!】
    // 以下のような整数になりえないString型の値を`toInt`するとエラーになってしまいます。
    // "数字じゃないょ".toInt  
    // より安全に変換するために`toIntOption`というのがありますが、まだ秘密です🤫

    //【参考】
    // `toDouble`,`toDoubleOption`等、他の型に変換できるやつもあります。

  }

  def step2_7 = Action { _ =>
    // 2. 型
    // 2-7. 文字列リテラルの強化版

    // すっかりお馴染みの`"`で囲った文字列リテラルには、
    // 便利な亜種や、特殊な記法がございます。
    // 使うことが多いものをいくつか紹介します。

    // ①s補完子つき
    // `s""`こんなので囲った文字の中では、
    // さらに`${}`という記法を利用できます。
    // この中では式を記述できまして、その計算結果が文字列リテラルに埋め込まれます。
    val sString = s"1 + 1 = ${1 + 1}" // "1 + 1 = 2"
    
    // 上では`${}`の中がInt型になっていますが、
    // 任意の型で`.toString`をs補完子さんがこっそり補完してくれています。

    // ②`"""`3連の2重引用符
    // この中では`"`が文字として使えたり、改行を含むことができます。
    // `"`だけのやとあかんかったんやで。
    val tripleString =
    """2重引用符(")が文字として使えます！
      改行もできちゃいます。"""

    // ③stripMargin
    // わかりづらいのですが、直前の例では、
    // 実は、2行目の"改行も..."の前部分のスペースも文字列として含まれてしまっています。
    // つまり"      改行もできちゃいます。"こうなっているわけです。
    // かといって、以下は見た目がガッタガタでださい・・・🙃
    val tripleString2 =
    """2重引用符(")が文字として使えます！
改行もできちゃいます。"""

    // と、そんな悩めるあなたには以下の書き方をどうぞ。
    val tripleString3 =
      """2重引用符(")が文字として使えます！
        |改行もできちゃいます。""".stripMargin
        
    // `|`という記号を2行目以降の行頭につけて、
    // 最後に`.stripMargin`をつけると、`|`とそれまでの半角スペースが消え去った文字列を取得できます。やったZE🌟

    // ④エスケープシーケンス
    // いつもの`"`だけで③と同じ内容を記述することもできるんやで。
    // そのためには、以下のようにmacだとバックスラッシュ・windowsだと円マーク(¥)+記号という表記を使います。
    val normalString = "2重引用符(\")が文字として使えます！\n改行もできちゃいます。"
    
    // こういった表記をエスケープシーケンスと言って、
    // 組み合わせる記号によって、いろんな特殊な文字を表現できます。
    // 他にもたくさんありますが、必要に応じて小出ししていきたいと思います。

    // ⑤s補完子と3連の組み合わせ
    Ok(
      s"""ブラウザで表示するためのHTMLは、
         |普通に改行しても改行されないのでbrタグを使う必要があります。<br>
         |ちなみに、以下のように変数名だけ単体で指定する場合は`{}`を省略できちゃいます。<br>
         |sString => "$sString"<br>
         |s補完子の機能で直感的に文字列を組み立てることができて嬉しいという話でした👻
         |""".stripMargin).as(HTML)

  }

  def step2_8 = Action { _ =>
    // 2. 型
    // 2-8. 基本型いろいろ

    // Scalaの基本型(言語に元から存在する型で最も基本的なもの)の一部を紹介しちゃいます。
    // 基本型以外にも、元から存在する型という意味では、もっと大量にあります。
    // それら全部はとても覚えられないので必要に応じて都度都度、勉強すればOKです。
    // ちなみに以下の基本型についても、
    // おそらくBoolean, Int(Long), Double, String以外はそれほど使わないで済むことが多いと思います。

    // 論理系
    val boolean: Boolean = true // 論理型です。trueかfalseしか値を持ちません。ブーリアンとよみます。
    // true ≒ YES
    // false ≒ NO
    // みたいな感じです(?)
    // で、それなに？という感じかもしれません。もうちょっとしたら詳しくでてきますので、今は気にせず。

    // 数値系(整数) 持てる値の範囲によって色々あります。
    val byte: Byte = 1 // 整数値型 -128 ~ 127 
    val short: Short = 2 // 整数値型 -32768 ~ 32767
    val int: Int = 3 // 整数値型 -2147483648 ~ 2147483647
    val long: Long = 4L // 整数値型 Lを数字リテラルにつけるとLong型になります。 -9223372036854775808 ~ 9223372036854775807
    // 数値系(小数) 
    val float: Float = 0.1f // 小数値型 小数リテラルにfをつけるとFloat型になります。Doubleより扱える値の範囲が狭いです。
    val double: Double = 0.1 // 少数値型
    // 何かしらの必要に迫られない限りは、広い範囲が扱える型を使えば良いとお考えくださいませ。
    // 型の範囲外の値を入れようとすると、大変なことになります(一周回って数が減ったり増えたり)💥

    // 文字系
    val char: Char = 'c' // 文字型。1文字だけの型です。`'`の記号で挟んだ文字は文字型リテラルです。
    val string: String = "string" // 文字列型

    Ok(
      s"""boolean = $boolean<br>
         |byte = $byte<br>
         |short = $short<br>
         |int = $int<br>
         |long = $long<br>
         |float = $float<br>
         |double = $double<br>
         |char = $char<br>
         |string = $string<br>
         |""".stripMargin).as(HTML)

  }

  def step2_9 = Action { _ =>
    // 2. 型
    // 2-9. 演算子色々

    // これまでも演算子はいくつか利用してきましたが、もうちょっと紹介しちゃいます。
    // 実は、実体はメソッドと呼ばれるものなのですが、今は秘密です🤫

    // 算数系🔢(以下は全部Int型ですが任意の数値系の型で利用できます)
    // 足し算
    val addition = 10 + 1 // 11
    // 引き算
    val subtraction = 10 - 1 // 9
    // 掛け算
    val multiplication = 10 * 2 // 20
    // 割り算(0で割り算するとエラーになるで。注意やで🥴)
    val division = 10 / 5 // 2 
    // 割り算のあまり
    val remainder = 10 % 3 // 1

    // 論理演算(Boolean型の結果を返すものです)
    // 等しい
    val eq1 = 1 == 1 // true  <- "1は1と等しい"は真
    val eq2 = 1 == 2 // false <- "1は2と等しい"は偽
    val eq3 = "a" == "a" // true  <- "aはaと等しい"は真
    val eq4 = "a" == "b" // false <- "aはbと等しい"は偽
    // 等しくない
    val ne1 = 1 != 2 // true  <- "1は2と等しくない"は真
    val ne2 = 1 != 1 // false <- "1は1と等しくない"は偽
    // 小なり
    val lt1 = 1 < 2 // true  <- "1は2より小さい"は真
    val lt2 = 2 < 2 // false <- "2は2より小さい"は偽
    val lt3 = "a1" < "a2" // true  <- 文字列の場合は名前の順です(日本語での利用はおすすめしません)。
    val lt4 = "b1" < "a1" // false  <- 文字列の場合は名前の順です(日本語での利用はおすすめしません)。
    // 小なりイコール
    val le1 = 2 <= 2 // true <- "2は2より小さいか等しい"は真
    val le2 = 3 <= 2 // false <- "3は2より小さいか等しい"は偽
    // 大なり
    val gt1 = 3 > 2 // true <- "3は2より大きい"は偽
    val gt2 = 2 > 2 // false <- "2は2より大きい"は偽
    // 大なりイコール
    val ge1 = 2 >= 2 // false <- "1は2より大きい"は偽
    val ge2 = 2 >= 2 // false <- "1は2より大きい"は偽
    // 否定(反転)
    val denial1 = !false // true   
    val denial2 = !true // false

    // 論理演算の組合せ
    // AND,かつ,両方とも
    val and1 = true && true // true
    val and2 = true && false // false
    val and3 = false && false // false
    val and4 = 1 == 1 && 1 != 2 // true "1は1と等しい かつ 1は2と等しくない"は真
    // OR,または,どっちか
    val or1 = true || true // true
    val or2 = true || false // true
    val or3 = false || false // false
    val or4 = 1 == 1 || 1 == 2 // true "1は1と等しい または 1は2と等しい"は真

    Ok("他にも計算はたくさんありますがJOJOにネ！").as(HTML)
  }

  def step2_10 = Action { _ =>
    // 2. 型
    // 2-10. 演算子の計算順序と括弧

    // 演算子には計算される順番のルールがあります。
    // 書いた順(左から)に計算されるというわけではありません。
    val number1 = 1 + 10 * 2 // 21 <- `10 * 2`が先に計算されます。

    // 丸括弧を使うとその中の計算を優先することができます！数学と一緒だね！
    val number2 = (1 + 10) * 2 // 22 <- `(1 + 10)`が先に計算されます。

    // 算術系の演算子の優先順位は、
    // 基本的に数学ルールと同じなので比較的わかりやすいのですが、
    // プログラミングの場合は、論理演算などが入り交じる可能性があります。
    // プログラミング言語によってもまちまちの優先順位のルールを、
    // 完璧にいつも覚えておくのは現実的ではないので、
    // ん？順番怪しいぞ？という時は丸括弧をどんどん使いましょう。
    val bool1 = (number1 > number2) && (10 + (1 * 2) < 15) // こんな感じで括弧in括弧も可能です。

    // `{}`の波括弧も同じように利用できます。
    val number3 = {
      1 + 10
    } * 2 // 22
    // しかし、波の場合は丸と異なり、
    // 複数の式を含めることができ、
    // "括弧内の最終行で行われた計算結果がその領域(ブロック)の値"になります。
    val number4 = {
      val one = 1
      val two = 2
      one + two // ここが`{}`内の最終行なので、このブロックの値となります。
    } + 1 // val number4 = 3 + 1 => 4 ということになります。
    // この挙動は今後の話でもよくでてきます。

    Ok(
      s"""
         |number1 = $number1<br>
         |number2 = $number2<br>
         |bool1 = $bool1<br>
         |number3 = $number3<br>
         |number4 = $number4<br>
         |""".stripMargin).as(HTML)
  }

}
