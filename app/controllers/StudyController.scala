package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
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

}
