package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 3. 分岐
    // 3-2. 入れ子のif式

    // if ~ else if ~ elseは入れ子にできます。
    val message1 =
      if (5 < 10) { // 中にさらにif式を書けます。
        if (5 > 1) "5は10より小さい かつ 5は1より大きい です。"
        else "5は10より小さい かつ ５は1より大きくない です？"
      } else { // もちろんelseの中でも。
        if (5 < 3) "5は10より小さくなく かつ 5は3より小さい です？"
        else "5は10より小さくなく かつ 5は3より小さいもないです？"
      }
    // 2階層の例ですが、いくらでも階層は増やせます。
    // 階層が深くなると読むのは辛いですが・・😟
    
    // でもでも、
    // 2-9で紹介した論理演算を使って複数条件を一度に指定することも可能なので、
    // 状況によって使い分けます。
    val message2 =
      if (15 < 30 && 15 < 20) "15は30よりも小さい かつ 15は20よりも小さい です。"
      else "(15は30よりも小さい かつ 15は20よりも小さい) ということは無いです？"
    
    Ok(
      s"""
         |message1 = "$message1"<br>
         |message2 = "$message2"<br>
         |""".stripMargin).as(HTML)
  }

}
