package controllers.chapter2

import play.api.mvc._

import javax.inject._

@Singleton
class Chapter2_0Controller @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def chapter2_0 = Action { _ =>
    // <- この記号から始まる行はコメントといって、
    // プログラムにはなんの影響も及ぼさないものになっています。
    
    // 今後もこのソースのようにサンプルプログラムと、
    // 色々説明を記載させていただきます。
    
    // まずここでは、意味不明だと思いますが、
    // 以下のような`Ok()`というプログラムで、
    // ブラウザにHTMLが返って画面に表示されるんだということをご納得くださいませ。約束だぞ🥺
    
    Ok("ここにはHTMLを書きます。").as(HTML)
  }

}
