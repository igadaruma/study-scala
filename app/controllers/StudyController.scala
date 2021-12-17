package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 4. 繰り返しとコレクション
    // 4-5. for式サヨナラ!?

    // ここで衝撃的なことを申し上げます。
    // 実は・・・少なくとも私個人は、for式によるループ処理を、
    // 滅多に書きません。。。
    // ※全く無いとは言えませんが。

    // 誤解のないようにと思いますが、
    // ループ処理自体は多用するのですが、
    // 今まで使ってきたfor式のやり方ではあんまりやらないよ。
    // ということです。

    // じゃあどうしてるの？ですが、それは秘密です👍

    // ・・・だとさすがにビンタを頂戴しそうなので、
    // 以下にサンプルを書きますが、すみません、今はこれを理解するための、
    // 前提知識をお伝えできていませんので、
    // 今の段階では全くわからなくてOKです。
    // 一旦、雰囲気だけを見てもらえたらと思います。

    // その1. 4-2での、合計値を出していたパターン
    val sum = (1 to 3).sum

    // その2. 4-2での、文字abcを結合していたパターン
    val str = ('a' to 'c').mkString("")
    
    // その3. 4-4での、2の倍数だけの例
    val str2 = (1 to 9).filter(_ % 2 == 0).mkString("")

    // その4. 4-3での、九九計算していた例
    val kuku = (for {
      i <- 1 to 9
      j <- 1 to 9
    } yield s"$i * $j = ${i * j}").mkString("<br>")
    // あれ？for使ってるじゃん？と思いましたか？
    // はい。実は、forだけの構文は滅多に使わないのは本当ですが、
    // 親戚(?)のfor-yield式はよく使います。
    // 時が来れば詳細を解説しますので、お楽しみに！
    
    // しつこいですが、今は意味不明で構いません。
    // 今後のための布石のような位置付けですので、
    // 気にせず次に進みましょう！！

    Ok(
      s"""
         |sum = $sum<br>
         |str = $str<br>
         |str2 = $str2<br>
         |kuku = <br>
         |$kuku
         |""".stripMargin).as(HTML)
  }

}
