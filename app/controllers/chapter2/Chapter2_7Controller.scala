package controllers.chapter2

import play.api.mvc._

import javax.inject._

@Singleton
class Chapter2_7Controller @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def chapter2_7_1 = Action { _ =>
    // 7. 組込クラスと高階関数とラムダ式
    // 7-1. Option

    // 今回は、Scalaプログラミングにおいて、
    // とても重要なOption型に入門していきます。
    // 初めはちょっと難しく感じると思いますが、
    // 慣れれば簡単なので頑張っていきましょう。

    // まずは、型構造を解説します。
    // Option型といいつつ、登場する型が3+1個あります。
    // 1つ目: Option型 
    // 2つ目: Some型
    // 3つ目: None型
    // (4つ目: Some型に含まれる値の型)
    // です。

    // Option型は6-7の抽象クラスになっています。
    // そしてSome型とNone型がその子クラスになっています。
    // 他に子クラスは存在しません。

    // Option型は、ざっくり表現すると、
    // "値が有るかもしれないし無いかもしれない型"です。
    // 子クラスの、Some型は"値がある場合"、None型は"値がない場合"に対応します。
    // よくわからないと思いますので、実際のソースを見ていきます。

    // 例としまして、前述の"4つ目"の型がInt型のケースを考えてみます。
    // 言い換えると、Int型の"値が有るかもしれないし無いかもしれない"という場面を想定します。
    // まず、4-1で登場したList型に登場してきてもらいます。
    val nonEmptyNums: List[Int] = List(1, 2, 3)
    val emptyNums: List[Int] = List() // 空です。

    // 次に、これらのリストの先頭の値を取得したかったとします。
    // nonEmptyNums(0) // `nonEmptyNums.head`というメソッドでも同じことができます。
    // emptyNums(0) // `emptyNums.head`というメソッドでも同じことができます。
    // とかけば目的が達成できそうではあるのですが、
    // emptyNumsは空なので、実行時にエラーが発生してしまいます。
    // より安全な実装をするための方法の1つとしてOption型を利用できます。
    // List型を受け取って先頭の値があればそれを返却するOption型を利用した関数の例が以下です。
    def headOption(nums: List[Int]): Option[Int] = {
      // ListのnonEmptyメソッドは値が1つでも入っていればtrueになります。
      if (nums.nonEmpty) Some(nums.head) // `Some(値)`でSome型の値が生成できます。
      else None // Noneは値を含まないのでシンプルに`None`とだけ書きます。
    }

    // 注目点としまして、返り値の型がOption[Int]型になっています。
    // 6-8の内容になりますが、子クラスは親クラスの変数や関数の返り値の型の値として利用できます。
    // また、Option自体は抽象クラスなので、
    // この関数から返却される実際の値はSome型かNone型だということになります。
    // この関数では、Listが空でないときにだけ`head`メソッドを呼び出しているので、
    // 実行時にエラーになることが無い、安全な実装になっています。
    // 実際に利用する時はmatchと組合せて以下のように使うことができます。
    val numsHead1 = headOption(nonEmptyNums)
    val numsHead1AsString = numsHead1 match {
      // Some(変数名)をcaseに指定することでSome型だった場合に変数に中身の値を格納できます。
      case Some(n) => s"numsHead1はSomeで${n}が入っていました。"
      case None => "numsHead1はNoneでした。"
    }

    val numsHead2 = headOption(emptyNums)
    val numsHead2AsString = numsHead2 match {
      case Some(n) => s"numsHead2はSomeで${n}が入っていました。"
      case None => "numsHead2はNoneでした。"
    }

    // match式で型をチェックしている分、
    // ただただheadを呼び出すよりは複雑になりますが、
    // 実行時にエラーにならない安全な実装というのは、
    // とてつもなく重要だと考えています。
    // "値が有るかもしれないし無いかもしれない"という場面はよく遭遇するため、
    // この状況を安全に実装できるOption型は重宝されているわけです。

    Ok(
      s"""
         |$numsHead1AsString<br>
         |$numsHead2AsString<br>
         |""".stripMargin).as(HTML)

    // ちなみにちなみに、
    // 今回実装したheadOption関数は、
    // 実はListの組込メソッドとして既に用意されていまして、
    // 名前は同じheadOptionです。
    // nonEmptyNums.headOption
    // みたいに使えます。
    // このメソッドは、今回実装したList[Int]型専用ではなく、
    // 任意の型を含むListで利用できます。
  }

  def chapter2_7_2 = Action { _ =>
    // 7. 組込クラスと高階関数とラムダ式
    // 7-2. 高階関数

    // 前回のOption型をもっと掘り下げて解説したいのですが、
    // その話をするための準備としまして、
    // 高階関数の話をしなければなりません。
    // そしてさらに、高階関数を説明するために、
    // 関数自体を変数や引数として利用できるという話をさせていただきます。

    // 何をいっているかわかりませんね？
    // 百聞は一見に如かずですので、具体例を見ていきましょう。
    // まずは関数を普通に定義してみましょう。
    // 引数の値に1を足した数を返す関数の例です。
    def add1(a: Int): Int = a + 1

    // 実はこれとほぼ等価な、次のような別の書き方があります。
    val addOne = (a: Int) => a + 1
    // この`(引数定義) => { 処理の中身 }`といった書き方は、
    // ”ラムダ式”と呼ばれています。
    // 括弧`{}`は処理が1行だけなら通常の関数定義と同様に省略可能です。
    // ラムダ式は"関数を返す"専用構文で、上記であれば、
    // 変数`addOne`にラムダ式で作った関数を入れている、という状態です。
    // 共に全く同じように関数処理を呼び出すことが可能です。
    val tenAdd1 = add1(10) // 11
    val tenAddOne = addOne(10) // 11

    // さて、ここで少々、興味深い話題があります。
    // 関数を作って返すラムダ式が存在し、関数が変数に入るという事実はつまり・・、
    // "関数の引数に、関数を指定することもできる" ということです。
    // これは、ややこしくなってきましたね？

    // 前述の`addOne`ですが、変数には必ず型がありますので、
    // 関数にも型があることになります。
    // 型アノテーションで明示すると以下のようになっています。
    val addOneWithTypeAnnotation: Int => Int = addOne
    // ちなみにdefで定義した関数を、改めて変数に格納することもできます。
    val add1WithTypeAnnotation: Int => Int = add1
    // 関数の型は `=>`よりも、
    // 左が、関数の引数の型を表し、
    // 右が、関数の返り値の型を表しています。
    // 引数が複数の場合は、括弧がついて以下のような形になります。
    // (Int, Int) => Int
    // 上記の例はInt型の引数を2つとって、Int型を返す関数の型です。

    // 関数の型定義がわかったところで、
    // 関数を引数にとる関数の例を出してみます。
    // 以下は、第1引数にIntの値をとり、
    // その値に対して、第2引数の関数を2回実行した値を返す例です。
    def twice(n: Int,
              f: Int => Int): Int = {
      // 以下、2重括弧になっていて、ややこしく感じるかもしれませんが、
      // まず、nを内側の関数fが引数にとって処理した結果を、
      // さらに外側のfが引数としてとって、 再度処理するという順番になります。
      f(f(n))
    }

    // これを使う場合はこんな感じ。
    val tenAdd1Twice = twice(10, add1) // 12 // def由来でも、
    val tenAddOneTwice = twice(10, addOne) // 12 // ラムダ式由来でもOKです。
    // さらにいえば、defやvalで関数に名前をつけずに、
    // いきなりラムダ式を引数に指定することもできます。
    val tenAdd1TwiceByLambda = twice(10, (n: Int) => n + 1)

    // このtwice関数のように、関数を引数に取る関数は、
    // 高階関数と呼ばれています。
    // 高階関数は、抽象度が高い仕組みなので、
    // ちょっと難しかったかもしれません。
    // ただ、その分とても、強力な武器になりますし、
    // 組込型のメソッドにも非常によく使われています。
    // Option・Listのメソッドにも頻出します。
    // そしてこれらのメソッドの利用頻度もとても高いので、
    // ぜひ理解していきましょう。

    Ok(
      s"""
         |tenAdd1 = $tenAdd1<br>
         |tenAddOne = $tenAddOne<br>
         |tenAdd1Twice = $tenAdd1Twice<br>
         |tenAddOneTwice = $tenAddOneTwice<br>
         |tenAdd1TwiceByLambda = $tenAdd1TwiceByLambda<br>
         |""".stripMargin).as(HTML)

    // 【補足事項①】
    // 関数を変数や引数に指定ができることを、
    // "関数が第一級値である"と表現されることがあります。
    // 覚えなくていいと思います。
    // プログラミング言語によっては、これができないものもあります。
    // 【補足事項① ここまで】

    // 【補足事項②】
    // twiceの第2引数の名前はfを指定していましたが、
    // これは、関数が英語でFunctionなので、その頭文字をとった形でした。
    // 変数や引数の名前は、それが何を意味しているかを理解しやすい名前にするのが良いのですが、
    // 高階関数の引数における関数は、それが関数であるということぐらいの意味しか言い表せない場合が多いため、
    // 単純に`f`といった簡素な名前がつけられることが慣習上多くなっています。
    // 引数に関数が複数ある場合は、アルファベット順に、f -> g -> hみたいに命名されることが多いです。
    // 【補足事項② ここまで】
  }

  def chapter2_7_3 = Action { _ =>
    // 7. 組込クラスと高階関数とラムダ式
    // 7-3. ラムダ式の省略記法

    // 高階関数の引数に直接ラムダ式指定する際に、
    // 記述を省エネできる記法があります。
    // 便利なので利用する方も多いので読み書きできるようにしておきましょう。

    // 前回同様にtwice高階関数に登場してもらいましょう。
    def twice(n: Int,
              f: Int => Int): Int = f(f(n))

    // まずは最も基本的な形が以下です。 
    val r1_1 = twice(10, (n: Int) => n + 1)

    // ここから、twice側の引数の定義(Int => Int)より、
    // 引数の型があきらかなので、ラムダ式から引数の型を省略することができます。
    val r1_2 = twice(10, (n) => n + 1)

    // さらに、これは引数が1つの場合に限りますが、
    // `()`も省略できます。
    val r1_3 = twice(10, n => n + 1)

    // さらに、これで最後ですが、
    // ラムダ式の処理部分にて、引数を利用するのが1回だけの場合に限りますが、
    // 引数部分を省略して、引数にあたる部分を`_`と記載することができます。
    val r1_4 = twice(10, _ + 1)

    // "引数を利用するのが1回だけ"という条件があるので、
    // 例えば、以下のようなラムダ式は`_`記法はできません。
    twice(10, n => n + n)


    // 次に、引数が2つだった場合も見ていきます。
    // 例として、以下のような、第1引数の値を、
    // 2つ引数を持つ第2引数の関数に受け渡した結果を返す高階関数に登場してもらいます。
    def sameArg(n: Int,
                f: (Int, Int) => Int): Int = f(n, n)

    // 同様にだんだん省略していきます。
    val r2_1 = sameArg(10, (n1: Int, n2: Int) => n1 + n2)
    val r2_2 = sameArg(10, (n1, n2) => n1 + n2) // 引数の型の省略
    val r2_3 = sameArg(10, _ + _) // 1つ目の`_`が第1引数、2つ目が第2引数を表します。

    // 以上です！
    // 慣れないうちは、何度もわからなくなると思いますが、
    // 繰り返し勉強しているうちに、自然に読み書きできるようになりますので、
    // めげずに頑張ってみてください！
    // 慣れてさえしまえば、むしろ、この省略記法がとても楽に感じられるはずです！

    Ok(
      s"""
         |r1_1 = $r1_1<br>
         |r1_2 = $r1_2<br>
         |r1_3 = $r1_3<br>
         |r1_4 = $r1_4<br>
         |<br>
         |r2_1 = $r2_1<br>
         |r2_2 = $r2_2<br>
         |r2_3 = $r2_3<br>
         |""".stripMargin).as(HTML)

  }

  def chapter2_7_4 = Action { _ =>
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

  def chapter2_7_5 = Action { _ =>
    // 7. 組込クラスと高階関数とラムダ式
    // 7-5. List 2

    // Optionは一旦これぐらいにして、
    // 次は、Listのメンバから、よく使うもの(の一部)を、
    // 追加で紹介したいと思います。
    // 高階関数・・あるよ！

    // 【補足事項】
    // 覚えようとしなくてOKです。
    // よく使うものは、自然と書いているうちに覚えられますし、
    // 忘れたらその時復習すればよいので、
    // 「へー。そんなのが用意されてるのかー。よくわからんけど便利そうだなー。」
    // ぐらいで気楽に眺めていきましょう。

    // 以下、サンプルソースの変数には、
    // 型アノテーション`: 型名`をつけていますが、
    // 省略しても全く構わないものです。
    // メソッドの挙動を理解するのに、
    // 書いていたほうがわかりやすいかもと思い記述しています。
    // 【補足事項 ここまで】

    // サンプルデータです。
    val list1: List[Int] = List(1, 2, 3, 4, 5)

    // ①::
    // まずはListに値を追加するメソッドです。以下をご覧ください。
    val list1Add0: List[Int] = 0 :: list1 // List(0, 1, 2, 3, 4, 5)
    // 実はちょっとこれはかなり変則的な記法なのですが、
    // あまり深く考えずに`追加する値 :: 追加先リスト`と書けば、
    // リストの先頭に値が追加されたものが取得できると捉えてみてください。
    // list1自体は変化しません。新しい変数に入れ直す必要がございます。
    // 詳細が気になる人は、以下の補足事項を確認ください。

    // 【補足事項】
    // これは通常構文で書き直すと以下になります。
    // val list1Add0 = list1.::(0)
    // は？という感じですね。ひっくり返ってますね。
    // 6-4では"+"という記号を使ったメソッドを定義しましたが、
    // これは":"記号を使ったメソッドになっています。
    // そして、特殊ルールなのですが、":"記号で名前が終わるメソッドは、
    // メソッド呼び出し元と、引数の順番を入れ替えて、"."を省略した形で記載しても良いという、
    // スーパー変則的なルールがあります。
    // なぜこんなルールがあるかというと、
    // まさにこのListの値を追加するメソッドためと言って良いと思われます。
    // 実はListは、"コンピュータ目線"で、値を先頭にくっつけるのは楽な仕事ですが、
    // 後ろにくっつけるのは大変、というデータ構造になっています(言い換えると時間がかかる)。
    // これが何故かは省略しますが、この性質のために、
    // メソッドとしても、要素を1つ追加したい場合のメソッドは先頭に繋げるものしか組込みでは用意されていません。
    // 値を先頭につけるので、`追加する値 :: 追加先のリスト`の形になっていた方が、
    // 直感的にわかりやすいネ！ということです。
    // 【補足事項 ここまで】

    // ②:::
    // とはいえ、やっぱり後ろにつけたい時だってあります。
    // List同士でないと使えないという制約はありますが、
    // ":"が3つ版を使えば、後ろにつけることもできます。
    val list1Add6: List[Int] = list1 ::: List(6) // List(1, 2, 3, 4, 5, 6)

    // ③reverse
    // また、Listの中身の順番をひっくり返すこともできますので、
    // ②の例だと":::"を使えばいいと思いますが、同じことが以下のようにもかけます。
    val list1Add6WithReverse: List[Int] = (6 :: list1.reverse).reverse // List(1, 2, 3, 4, 5, 6)
    // list1.reverse = List(5, 4, 3, 2, 1)
    // 値追加系+αはこれで終わりです。

    // ④isEmpty, nonEmpty
    // 次は真偽型(Boolean)を返す系を見ていきます。
    // まずは、リストが空かそうでないかをチェックするものです。
    val isList1Empty: Boolean = list1.isEmpty // false // 値が空ならtrue
    val isNotList1Empty: Boolean = list1.nonEmpty // true // 値が空でないならtrue

    // ⑤contains 
    // Listの中に引数の値が入っていればtrueを返してくれます。
    val contains1: Boolean = list1.contains(1) // true // 1があればtrue
    val contains6: Boolean = list1.contains(6) // false // 6があればtrue

    // ⑥exists
    // 高階関数です。containsの上位互換です(ただしcontainsでできるときはcontainsでOKです)。
    // 引数関数には、リストの値が順番に1つずつ渡されて実行されます。
    // 引数関数の返り値は真偽値型にする必要がありますが、
    // 引数関数の実行結果が1つでもtrueになれば、existsメソッドの返り値はtrueになります。
    val existsLessThan3: Boolean = list1.exists(_ < 3) // true // 3より小さい数があればtrue
    val existsLargerThan10: Boolean = list1.exists(_ > 10) // false // 10より大きい数があればtrue

    // ⑦forall
    // 高階関数です。existsと似た動作ですが、
    // リストの中身が"全部"trueになれば、
    // 返り値がtrueになるところが異なります。
    val forAllLessThan3: Boolean = list1.forall(_ < 3) // false // 全部3より小さければtrue
    val forAllLargerThan0: Boolean = list1.forall(_ > 0) // true // 全部0より大きければtrue
    // 真偽値系はここまでです。

    // ⑧find
    // 高階関数です。Optionが返ってきます。
    // existsと同じように引数に真偽値を返す関数を指定しますが、
    // 先頭の値から順番に実行され、
    // 最初にtrueになったものがSome値として返されます。
    // みつからなければNoneです。ビバOption!
    val findLessThan3: Option[Int] = list1.find(_ < 3) // Some(1) // 3より小さい数で最初に見つかったもの。
    val findLargerThan10: Option[Int] = list1.find(_ > 10) // None // 10より大きい数で最初に見つかったもの。

    // ⑨filter
    // 高階関数です。findと似た感じですが、
    // 引数関数の返り値がtrueになったものを"全て"、
    // "新しいList"として返す点が異なります。(※list1自体は変わりません。)
    // 見つからない場合は空のListが返ります。
    val filterLessThan3: List[Int] = list1.filter(_ < 3) // List(1, 2) // 3より小さい数に絞ったリスト。
    val filterLargerThan10: List[Int] = list1.filter(_ > 10) // List() // 10より大きい数に絞ったリスト。

    // ⑩applyOrElse
    // 4-1では任意の位置の値を取得するのに単純に`(n)`を使って取り出していました。
    // 注意点としては、0始まりなので、以下のような感じでした。
    // list1(0) // 先頭の値 
    // list1(1) // 2番目の値
    // しかしながら、以前紹介したとおり、
    // 値がない番号を指定してしまうと実行時エラーになってしまいます。
    // list1(10) // エラー！💣🔥
    // つまり危険な処理を書いていることになります。
    // 間違いなく値があることが明白なところでこれを利用するのは良いのですが、
    // そうではない場合、applyOrElseを使うことができます。
    // applyOrElseは高階関数です。
    // 第1引数として取得したい位置に加え、
    // 第2引数として関数を指定します。
    // 引数関数の引数は、第1引数の値(何番目か)が渡されて、
    // 戻り値はListの値の型に原則あわせる形になります(Any型になってしまうので)。
    val applyOrElse1 = list1.applyOrElse(1, (n: Int) => 0) // 値があるので関数は実行されず`2`になります。
    val applyOrElse10 = list1.applyOrElse(10, (n: Int) => 0) // 値がないので関数が実行されて`0`になります。

    // 【補足事項】
    // 上記の例は引数関数の引数"n"は使ってないので、
    // list1.applyOrElse(1, (_: Int) => 0)
    // といった書き方もできます。
    // また、すごく勘の良い方は、
    // "引数関数の引数の型って省略しないの？"と思われるかもしれません。
    // これは少々ややこしい言語仕様上の制約の問題なので、詳細は省略しますが、
    // (※ちゃんと説明できないというのが本音です🙃)
    // "applyOrElseの時は引数関数の引数型が省略できない"と丸覚えください。

    // また、`list1(0)`という表記は実はScalaの省略記法の1つで、
    // 省略しない場合は`list.apply(0)`という形になります。
    // ただの`値()`は実はapplyメソッドの呼び出しだったのでぃす。
    // 【補足事項 ここまで】

    // ⑪map
    // ここからは変換系になります。
    // Optionでも同名のメソッドがありましたが、こちらも似た動作をします。
    // Listの構造を保ちつつ、
    // 引数にとった関数の実行結果を新しいリストの値として変換します。
    val mapAdd10: List[Int] = list1.map(_ + 10) // List(11, 12, 13, 14, 15) // 中身を全部+10したリスト。

    // ⑫flatMap
    // またしてもOptionでも見たやつです。
    // Optionでは変換後の入れ子Optionを勝手に平坦化してくれる挙動でした。
    // Listも同じで、Listの中身の値を、Listに変換した場合、
    // List[List[中身の型]]みたいな入れ子Listになってしまうのを、
    // List[中身の型]に平坦化してくれます。
    val flatMapDouble: List[Int] = list1.flatMap(n => List(n, n)) // List(1, 1, 2, 2, 3, 3, 4, 4, 5, 5) // 中身の要素を2倍個にしたリスト。
    // mapで同じ引数で呼ぶと以下が返ってきます。
    // List(List(1, 1), List(2, 2), List(3, 3), List(4, 4), List(5, 5))

    // ⑬mkString
    // リストを文字列に変換するメソッドです。
    // 2種の呼び出し方があります。
    // 1つ目は文字列を1つだけ引数として指定した場合です。
    val csv: String = list1.mkString(",") // "1,2,3,4,5" 
    // 引数に指定した文字列が各値の間に入る形で文字列が完成します。
    // 2つ目は文字列を3つ指定する場合です。
    val explain: String = list1.mkString("list1の値はList(", ",", ")です。") // "list1の値はList(1,2,3,4,5)です。"
    // 第1引数が先頭につき、
    // 第2引数が各値の間に入り、
    // 第3引数が末尾につく形で文字列が完成します。

    // 【補足事項】
    // こういった同じメソッドなのに、
    // 2種類以上の引数パターンを持つメソッド(関数)は、
    // "オーバーロード"されていると表現できます。
    // 【補足事項 ここまで】

    // 数が多くてお腹いっぱいかもしれません。
    // 今は"こんなことができる"という雰囲気を、
    // おおまかに捉えていただけたらOKです。
    // 細かいことは実践的な演習の中で掴んでいきましょう。

    Ok(
      s"""
         |<h3>①::</h3>
         |list1Add0 ＝ ${list1Add0}<br>
         |<h3>②:::</h3>
         |list1Add6 ＝ ${list1Add6}<br>
         |<h3>③reverse</h3>
         |list1Add6WithReverse ＝ ${list1Add6WithReverse}<br>
         |<h3>④isEmpty, nonEmpty</h3>
         |isList1Empty ＝ ${isList1Empty}<br>
         |isNotList1Empty ＝ ${isNotList1Empty}<br>
         |<h3>⑤contains</h3>
         |contains1 ＝ ${contains1}<br>
         |contains6 ＝ ${contains6}<br>
         |<h3>⑥exists</h3>
         |existsLessThan3 ＝ ${existsLessThan3}<br>
         |existsLargerThan10 ＝ ${existsLargerThan10}<br>
         |<h3>⑦forall</h3>
         |forAllLessThan3 ＝ ${forAllLessThan3}<br>
         |forAllLargerThan0 ＝ ${forAllLargerThan0}<br>
         |<h3>⑧find</h3>
         |findLessThan3 ＝ ${findLessThan3}<br>
         |findLargerThan10 ＝ ${findLargerThan10}<br>
         |<h3>⑨filter</h3>
         |filterLessThan3 ＝ ${filterLessThan3}<br>
         |filterLargerThan10 ＝ ${filterLargerThan10}<br>
         |<h3>⑩applyOrElse</h3>
         |applyOrElse1 = ${applyOrElse1}<br>
         |applyOrElse10 = ${applyOrElse10}<br>
         |<h3>⑪map</h3>
         |mapAdd10 ＝ ${mapAdd10}<br>
         |<h3>⑫flatMap</h3>
         |flatMapDouble ＝ ${flatMapDouble}<br>
         |<h3>⑬mkString</h3>
         |csv ＝ ${csv}<br>
         |explain ＝ ${explain}<br>
         |""".stripMargin).as(HTML)

  }

  def chapter2_7_6 = Action { _ =>
    // 7. 組込クラスと高階関数とラムダ式
    // 7-6. Map

    // 第2章の最後に紹介するのはMapです。
    // 次のチャプターで出てくるのでここで紹介します。
    // これはListのようなコレクション系の型の1つになります。
    // Listと比較して、
    // 値に加えて、値に対するキー(名前のような？タグのような？)ものをセットに保持するのが特徴です。
    // Listの中から値を取る際は、applyOrElseなどで何番目かを指定していました。
    // Mapでは代わりに、キーを指定して値を取得します。
    // 逆にMapはListと違い、入れた順番というのは保持されないので注意です。

    // まずは具体例を見ていきましょう。
    // 果物に対する色を保持するイメージのMap例です。
    val fruitColors: Map[String, String] = Map(
      "りんご" -> "赤",
      "ばなな" -> "黄",
      "ぶどう" -> "紫"
    )
    // `キー -> 値`というのをセットにして、
    // 値をカンマ区切りで指定します。

    // 【補足事項】
    // `->`というのが初めてでてきましたが、
    // これはタプル型の値が２つのものを生成するものになっています。
    // ですので、以下と書いても同じことになります。
    // Map(("りんご", "赤"), ("ばなな", "黄"), ("ぶどう", "紫"))
    // `->`は"キーに対する値"というイメージがしやすいため、
    // Mapのためにこの記法が用意されているんだと思われます。
    // 【補足事項おわり】

    // 次は、このMapの値から値をとってくる例です。
    // りんごの色を取得したい場面というイメージでしょうか。
    val appleColor: Option[String] = fruitColors.get("りんご") // Some("赤")
    // すっかりお馴染みの？Option型が返ってくる安全設計メソッドです。
    // fruitColors("りんご")
    // といった危険設計のapplyメソッドもあります。

    // なんとなくMapの特徴はわかっていただけたかな？と思います。
    // 上記の例ではキーも値もStringですが、両者とも任意の型を利用できます。
    // 例えば以下はキーがStringで値がIntの例です。
    val numEnglish: Map[String, Int] = Map("one" -> 1, "two" -> 2)
    // 値がListになったりもOKです。

    // では次にMapのメソッドを少し紹介です。

    // ①+, ++
    // 値を追加する系です。
    // `+`は1つのペアを追加、
    // `++`は別のMapと結合です。
    // 原則追加するペアはキー・値ともに型を揃えます。
    val newFruitColors1 = fruitColors + ("レモン" -> 10)
    val newFruitColors2 = fruitColors ++ Map("レモン" -> "黃", "ライム" -> "緑")

    // ②getOrElse
    // 前述のgetはOptionで結果が返ってきますが、
    // こちらは、なかった場合の値を引数を指定することで、
    // 安全なまま、Option無しで値を受け取れます。
    val peachColor: String = fruitColors.getOrElse("もも", "未登録") // "もも"のキーは無いので、"未登録"となります。

    // ③keys
    // キーだけを集めたIterable型(実体はSet型)のコレクションを受け取ります。
    // 性質が異なりますが、現時点ではListのようなものと捉えてみてください。
    val fruits: Iterable[String] = fruitColors.keys
    // Iterable型はtoListメソッドでList型に変換も可能です。
    val fruitsList: List[String] = fruitColors.keys.toList
    // ※順番は入れた順とは限りません！

    // ④values
    // こちらは値だけを集めたIterable型のコレクションを受け取ります。
    val colors: Iterable[String] = fruitColors.values
    // ※順番は入れた順とは限りません！

    // ⑤map
    // またしても変換用のmapが登場です。
    // ただし、キーと値の２つがあるので、
    // 引数高階関数の引数はタプル型で2つの値を持ち、
    // 返り値もタプル型で2つ値を返します。
    // 以下は値に"色"というのを末尾に付け加える変換をする例です。
    val fruitColors2 = fruitColors.map(fruitAndColor => fruitAndColor._1 -> s"${fruitAndColor._2}色")

    Ok(
      s"""
         |fruitColors = ${fruitColors}<br>
         |appleColor = ${appleColor}<br>
         |numEnglish = ${numEnglish}<br>
         |<h3>①+, ++</h3>
         |newFruitColors1 = ${newFruitColors1}<br>
         |newFruitColors2 = ${newFruitColors2}<br>
         |<h3>②getOrElse</h3>
         |peachColor = ${peachColor}<br>
         |<h3>③keys</h3>
         |fruits = ${fruits}<br>
         |fruitsList = ${fruitsList}<br>
         |<h3>④values</h3>
         |colors = ${colors}<br>
         |<h3>⑤map</h3>
         |fruitColors2 = ${fruitColors2}<br>
         |""".stripMargin).as(HTML)

    // ここまでで第2章の内容は終わりです！！
    // もしかすると、まだ理解が怪しいかもと思う部分があるかもしれませんが、
    // ぜひ第3章に進んでしまい、第3章の内容でわからない部分がでてきたら、
    // その時復習してみるという形で学習を進めてもらえたらと考えております。
    // それでは次章で会いましょう。

  }

}
