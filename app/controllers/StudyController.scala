package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 2. 型
    // 2-1. 初めての型

    // 全てのデータ(値)には"型"というのが存在しています。
    // 型というのは、とりあえず今は、データの形みたいなものと捉えてみましょう。
    
    // 変数名等の右に`: 型名`という形で"型"を明示できます。型アノテーションって言うんだって。
    // 文字列は実はString型なので、以下のような表記ができます。
    val message: String = "こんちゃーす"
    Ok(message).as(HTML)
    
    // 「は？」「で？」と思うかもしれませんが、 
    // 気にせず次にいきましょう。
  }

}
