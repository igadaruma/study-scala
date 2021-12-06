package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 2. 型
    // 2-6. String型とInt型

    val string: String = "10"
    val integer: Int = 2

    // String型とInt型を足すと文字列結合になります。おっと？
    val result1: String = string + integer // = "102" 

    // String型に`.toInt`をつけるとInt型に変換できます。
    val result2: Int = string.toInt + integer //  = 12
    
    // 以下は String型(result1) + String型(",") + Int型(12) で、最終的にString型になっている例です。
    val result: String = result1 + "," + result2 // "102,12"
    
    Ok(result).as(HTML)
    
    //【!注意!】
    // 以下のような整数になりえないString型の値を`toInt`するとエラーになってしまいます。
    // "数字じゃないょ".toInt  
    // より安全に変換するために`toIntOption`というのがありますが、まだ秘密です🤫

    //【参考】
    // `toDouble`,`toDoubleOption`等、他の型に変換できるやつもあります。

  }

}
