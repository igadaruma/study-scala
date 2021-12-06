package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 2. 型
    // 2-5. Int型とDouble型
    
    // IntとDoubleを四則演算した結果は、
    // より精度の高いDouble型になります。
    
    val int: Int = 2 // Int(整数)型
    val double: Double = 0.1 // Double(小数)型
    val result = int * double // `*`は掛け算です。
    
    Ok(result.toString).as(HTML)
    
    // 【注意】
    // この挙動(Double型になる)はScala限定の話です。
    // 他のプログラミング言語で同じ挙動になるとは限らないです。
  }

}
