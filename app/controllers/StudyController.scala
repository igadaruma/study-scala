package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 2. 型
    // 2-3. 初めてのInt(整数)型

    // そろそろString以外の型もみていきましょう。そうしましょう。
    // 今回はInt(整数)型です。整数は非常によく使うことになると思います。
    
    val integer1: Int = 1 // 整数を書くとInt(整数)型のリテラル(データ)になります。
    val integer2 = 2 // 型アノテーションは省略できやす。(型がなくなるわけではないですよっと)。
    
    // ちなみに！
    // 以下のようにInt型の変数にString型を入れようとするとエラーになっちゃいます。
    // val integer: Integer = "Hello"
    // ※逆も同じです。

    // Int型とInt型だって、足し算できます。もちろんさ。
    val integer: Int = integer1 + integer2
    
    // ここで！
    // お馴染みの、Ok()の中に入る値は、実は、
    // String型は指定できますが、Int型はだめょという型制約があります。
    // つまり以下はエラーになります。
    // Ok(integer).as(HTML)
    
    // IntをStringに変換することでエラーを回避できます。
    Ok(integer.toString).as(HTML) // `.toString`をつけるとString型に転生できます。
    
    // この型による制約がミソです。今は、ふーん。でOKです。
  }

}
