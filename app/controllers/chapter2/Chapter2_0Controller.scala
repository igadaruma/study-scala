package controllers.chapter2

import play.api.mvc._

import javax.inject._

@Singleton
class Chapter2_0Controller @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def chapter2_0 = Action { _ =>
    // 今は細かいことは気にせず、
    // これでブラウザに文字が出るんだと納得ください。
    // 約束だぞ🥺
    Ok("ここにはHTMLを書きます。").as(HTML)
  }

}
