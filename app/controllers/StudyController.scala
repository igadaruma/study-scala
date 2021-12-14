package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
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

}
