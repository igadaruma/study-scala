package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
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

}
