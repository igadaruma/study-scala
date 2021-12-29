package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 5. 関数
    // 5-4. 関数で複数の値を返したい？タプル型・・あるよ。

    // 関数の返り値は、値を1つしか返せないというルールがあります。
    // もう少し正確に表現すると、1つの型の値しか返せないというルールです。
    // ここで鋭すぎる方はぴーーーーーん！ぽーーーーん？と来たかもしれません。
    // 型が1つならば良いので、4-1等で登場したList等を使えば複数値を返せます。

    // 例えば、2つのInt型の引数を受け取って、
    // それを小さい順に並べた値を返す例を考えてみましょう。
    // これは以下のように書くことができます。

    def sort(a: Int, b: Int): List[Int] = {
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

}
