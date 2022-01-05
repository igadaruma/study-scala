package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
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
    // 本来、上記のように書きますが、引数が1つだけの場合は以下のように書けるという特殊ルールがScalaにはあります。
    val addedCoins = coins1 + coins2
    val subtractedCoins = coins1 - coins2
    
    // すこーしだけHTMLにも色気を出しています。
    Ok(
      s"""
         |<h2>addedCoins</h2>
         |${addedCoins.html}
         |<hr>
         |<h2>subtractedCoins</h2>
         |${subtractedCoins.html}
         |""".stripMargin).as(HTML)

  }

}
