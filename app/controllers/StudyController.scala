package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 3. 分岐
    // 3-5. match式でのガーーーード(if)！

    // match式はif式の強化版といった割に、今まで紹介した中で、
    // if式より強い力(ぱわぁ)は前回の型チェックぐらいでした。
    // そうです。まだ隠された力があります。
    // その1つがパターンガードです。名前は覚えなくていいです。
    // 具体例を見ていきましょう。
    val x = 15
    val message1 = x match {
      case n if n > 20 => s"20より大きいです。${n}です。"
      case n if n > 10 => s"10より大きいです。${n}です。" // このcaseに来ます。
      case n => s"${n}です。"
    }
    
    // という感じです`if Boolean型の式`という感じで指定することで、
    // Boolean型がtrueになる場合だけ。という条件が付きます。強いぞmatch式！
    
    // もちろん、今までのものと組み合わせることができます。
    val any: Any = "any" // わざとString型なのにAny型に格納。
    val message2 = any match {
      case n: Int if n > 10 => s"10より大きい整数です。${n}です。"
      case _: Int => "整数です。"
      case s: String => s"文字列です。'${s}'です。" // このcaseに来ます。
      case _ => "数字でも文字列でもない何かです。"
    }

    // 実はさらにあるのですが・・まだです。まだ秘密です🤐

    Ok(
      s"""
         |message1 = $message1<br>
         |message2 = $message2<br>
         |""".stripMargin).as(HTML)
  }

}
