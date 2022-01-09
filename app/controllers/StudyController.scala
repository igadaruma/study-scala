package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 7. 組込クラスと高階関数とラムダ式
    // 7-4. Option 2

    // Option型により踏み込める準備が整いましたので、
    // 早速理解を深めていきましょう。
    // Optionに実装されている、私が選ぶ最重要メソッドランキングTOP3を紹介します。

    // ①getOrElse
    // 2-6では、StringからIntへの変換を行っていました。
    // その際は以下のように、toIntというStringのメソッドを使っておりました。
    val stringOne = "1"
    val one = stringOne.toInt
    // 2-6でも触れましたが、
    // toIntは変換できない文字列から呼び出せば、
    // 実行時エラーになってしまいます。
    // 言い換えると、"危険な"メソッドなのです。
    // なので、多くの場面ではtoIntOptionという、
    // より安全なメソッドを利用すべきだと思います。
    // toIntOptionは変換可能ならSomeにくるんだIntを返し、
    // 変換不可ならNoneを返してくれるという安全設計になっています。
    val some1: Option[Int] = stringOne.toIntOption // Some(1)
    val none: Option[Int] = "NOT INT".toIntOption // None // エラーにならない！

    // toIntOptionが返す型はOption[Int]型なので、
    // Intとして扱いたい場合は中身を取り出す必要がありました。
    // 7-1ではmatch式を利用しましたが、
    // もっとお手軽な、getOrElseメソッドを利用できる場面が多く存在します。
    // getOrElseは呼び出し元が、Someであれば中身の値を、
    // Noneであれば引数に指定した値を返してくれるものになっています。
    val some1Value = some1.getOrElse(0) // 1
    val noneValue = none.getOrElse(0) // 0

    // 復習になりますが、
    // match式で同じことをすると以下になります(none側は省略してます)。
    val some1ValueByMatch = some1 match {
      case Some(value) => value
      case None => 0
    }
    // getOrElseで書くととてもスッキリしますね！

    // ②map
    // Option型から、中身の値を取り出す前に、
    // 先に中身を変換したい場面があります。
    // 例えば、toIntOptionで取得した値が、
    // Someであれば、"値は数字で${value}でした。"
    // Noneであれば、"値は数字ではありませんでした。"
    // といった文字列を最終的に取得したいといった場合、
    // mapメソッドを使って以下のように書くことができます。
    val message1 = some1.map(value => s"値は数字で${value}でした。").getOrElse("値は数字ではありませんでした。")
    // message1 = "値は数字で1でした。"

    val message2 =
      none // こんな感じに改行して書いてもOKです。
        .map(value => s"値は数字で${value}でした。")
        .getOrElse("値は数字ではありませんでした。")
    // message2 = "値は数字ではありませんでした。"

    // ちょいとややこしいですね？
    // 詳しく解説していきます。

    // 前半部分に主題のmapメソッドが呼びだれています。
    // 引数に例のラムダ式が記述されています。mapメソッドは高階関数なわけです。
    // mapは、呼び出し元の実体がSomeであれば、
    // 中身の値を"引数で受け取った関数の引数"として処理し、
    // その結果を改めてSomeの中にいれて返してくれます。
    // Option[Int]型からmapを呼ぶ場合、
    // 引数の関数の引数の型は、Intになる点が大事なポイントです。

    // 逆に、呼び出し元がNoneであれば、それは値が無いわけなので、
    // 引数関数は無視されて、そのままNoneを返してくれる挙動となります。
    // 要約すると、"中身があれば変換し、無ければ何もしない"というメソッドです。
    // 変換の際は、中身の型が変わっても構いません。今回は、
    // Option[Int]だったものが、Option[String]に変換された形になります。

    // 【注意】
    // ややこしい話になりますが、たとえNoneを変換しようとして、
    // 処理が無視された場合であっても、型は変換されている点に注意が必要です。

    // その後、後半部分のgetOrElseが呼び出されて、
    // やりたかった処理が実現できるわけですね。

    // おなじみmatch式でも同等の処理が記述できます(none側は省略してます)。
    val message1ByMatch = some1 match {
      case Some(value) => s"値は数字で${value}でした。"
      case None => "値は数字ではありませんでした。"
    } // 値は数字で1でした。

    // ③flatMap
    // 例えば、文字列のListの先頭の値を取得して、
    // それがIntに変換できるなら、そのInt型の値を取得しますが、
    // 変換できない、または先頭の値がそもそも無いなら、0としたい場合を考えます。
    // 先頭の値の取得についても、Int変換についても、
    // 安全なheadOption(cf. 7-1), toIntOptionを利用することとします。

    // サンプルデータとして3つリストを用意しておきます。
    val list1: List[String] = List("123")
    val list2: List[String] = List("abc")
    val list3: List[String] = List()

    // まずは、getOrElse+mapでこれをやってみます。
    val list1HeadAsInt = list1
      .headOption // Option[String]型になります。
      .map(_.toIntOption) // 中のStringがOption[Int]に変換され、結果的にOption[Option[Int]]型になっています!・・ややこしい！
      .getOrElse(None) // 外側のOptionが外れて、Option[Int]型になります。
      .getOrElse(0) // 全てのOptionが外れて、Int型になります。これで完了です。
    // list1HeadAsInt = 123

    // 次にmatchでやってみます。
    val list2HeadAsInt = list2.headOption match {
      case Some(head) => head.toIntOption match { // match式の中にmatch式が入り込む入れ子構造になっています。
        case Some(n) => n
        case None => 0 // Noneケース①
      }
      case None => 0 // Noneケース②
    }
    // list2HeadAsInt = 0

    // 両者、だめということは決してありませんが、
    // Option[Option[Int]]といった入れ子型がでてきたり、
    // getOrElse、`case None`が2回出てきたり、
    // あまりスマートでありません。

    // そんなあなたにflatMapです。
    // ほぼ同じ内容をflatMapを使って書き直してみます。
    val list3HeadAsInt = list3
      .headOption // Option[String]型になります。
      .flatMap(_.toIntOption) // Option[Int]型になります。ここがミソ！
      .getOrElse(0) // Int型になります。完了！
    // list3HeadAsInt = 0

    // flatMapは、mapと同じく中身の変換を行うものですが、
    // 変換先の型がOptionになる場合に限り使用できます。
    // mapだと入れ子Optionになってしまうところを、flatMapでは、
    // 外側も内側もSomeであれば、Some値として、
    // どちらかがNoneであれば、None値として、
    // 入れ子を自動的に平坦化(flatに)してくれるバージョンです。

    // この3つのメソッド(特にmap, flatMap)は、
    // めちゃ大事です。いえ、めちゃめちゃ大事です。
    // しっかり押さえておきましょう！

    Ok(
      s"""
         |some1Value = $some1Value<br>
         |noneValue = $noneValue<br>
         |some1ValueByMatch = $some1ValueByMatch<br>
         |message1 = $message1<br>
         |message2 = $message2<br>
         |message1ByMatch = $message1ByMatch<br>
         |list1HeadAsInt = $list1HeadAsInt<br>
         |list2HeadAsInt = $list2HeadAsInt<br>
         |list3HeadAsInt = $list3HeadAsInt<br>
         |""".stripMargin).as(HTML)

  }

}
