package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 3. 分岐
    // 3-7. ifとmatchの使い分け

    // matchがifを包括する機能を持つので、
    // matchを使うことを基準に考えつつも、
    // if(x == 1) a else b
    // といったシンプルな条件の場合は、match式だと大げさ(書くのが面倒)になるので、
    // 楽にかけるifを選べば良いかな？という感じです。

    Ok("結論：使い分けるべし。").as(HTML)
  }

}
