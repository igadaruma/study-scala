package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 1. 変数とリテラル
    // 1-3. 変数値の上書き 

    var message = "こんちゃーす"
    // var変数の値は以下のように値を入れ替えられます。
    // 入れ替え時は"var"は記載しません。
    // ただ、それだけのことです。
    message = "はい。みなさん、こんばんは。"
    Ok(message).as(HTML)
  }

}
