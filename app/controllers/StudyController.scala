package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 2. 型
    // 2-2. 演算子とString型の足し算

    val message1: String = "はい。" // String(文字列)型
    val message2 = "みなさん、こんばんは。" // String(文字列)型 (型アノテーションがなくても型がなくなることはあーりません。)

    // String型とString型は足し算(?)できます。
    // 以下は、変数message1と変数message2を足し算したものを変数messageに入れてます。
    val message: String = message1 + message2
    
    // String型とString型を足すと、双方が結合された文字列になります。
    Ok(message).as(HTML) // "はい。みなさん、こんばんは。"
    
    // 今回`+`記号が出てきましたが、
    // こんな感じで、計算を行うための記号等を"演算子"と呼んだりします。
    // ちなみに変数に値を入れている`=`も代入演算子と呼ばれるような演算子の一種です。
    // (※今まで隠していましたが変数に値を入れることを"代入"といったりします。ひみつやで🤫)
  }

}
