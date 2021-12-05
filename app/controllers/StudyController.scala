package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 1. 変数とリテラル
    // 1-4. 初めてのval 

    val message = "こんちゃーす"
    // val変数の値は変更できません。varとの違いはそれだけ。
    // なので以下を(コメントではない形で)記載するとエラーになります。
    // message = "はい。みなさん、こんばんは。"
    Ok(message).as(HTML)

    // え？じゃあvarの方が良くね？
    // 実はそうでもないんです。なぜかというとそれは秘密です🙊
    // 現時点では、私個人は、お仕事の時なら、
    // むしろvarは稀にしか使わないということだけお伝えしておきます・・！
    
    // valは値が変わらないので、
    // "変数"ではなく"定数"と呼ばれることがありそうですが、 
    // ここでは、varもvalも"変数"と呼んじゃいます。
  }

}
