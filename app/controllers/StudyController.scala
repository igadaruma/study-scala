package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 2. 型
    // 2-4. 初めてのDouble(小数)型

    // 整数をやったので今回は小数型です。
    // こちらもよく使うことになると思います。

    val double1: Double = 0.1 // Double(小数)型
    val double2 = 0.5 // 小数リテラルは、Double型として判定されます。
    val double: Double = double2 - double1 // 足し算はもうマスターしたので引き算してみましょう。
    
    Ok(double.toString).as(HTML) // Double型も`.toString`でString型に変身できます。
  }

}
