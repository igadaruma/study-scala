package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 今は細かいことは気にせず、これでブラウザに文字が出るんだと納得ください。約束だぞ🥺
    Ok("こんちゃーす").as(HTML) 
  }
  
}
