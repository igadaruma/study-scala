package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
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
      // 引数と`coins.`部分が上記ではなくなっています。

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

}
