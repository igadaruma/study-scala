package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 4. 繰り返しとコレクション
    // 4-3. 繰り返し処理のためのfor式 強化編その1 入れ子

    // 欲を出してもう少しループ処理の複雑な例にトライしてみましょう。
    // 今回は入れ子ループです。
    val one2nine = 1 to 9
    var kuku = ""
    for (i <- one2nine) {
      for (j <- one2nine) {
        kuku = kuku + s"$i * $j = ${i * j}<br>"
      }
    }
    
    // 結果が全てを物語ってくれますが、以下のようになります。
    // 1 * 1 = 1
    // 1 * 2 = 2
    // ...省略...
    // 9 * 8 = 72
    // 9 * 9 = 81
    
    // 外側のfor式の変数iが1~9へと変化していくわけですが、
    // そのループ処理の内側のfor式でも変数jが1~9へと変化していく形になり、
    
    // 1: 外側1回目のループ、内側1回目のループ
    // i = 1, j = 1
    // 2: 外側1回目のループ、内側2回目のループ
    // i = 1, j = 2
    // ...省略...
    // 80: 外側9回目のループ, 内側8回目のループ
    // i = 9, j = 8
    // 81: 外側9回目のループ, 内側8回目のループ
    // i = 9, j = 9
    
    // と、こんな感じで変数が変化していくわけです。ややこすぃ？
    // ちなみに、同じことを以下の書き方もできるので、覚えておくと便利です。
    var kuku2 = ""
    for (i <- one2nine; j <- one2nine) { 
      kuku2 = kuku2 + s"$i * $j = ${i * j}<br>"
    }
    // 見た目がキレイになります。イイネ！
    
    Ok(kuku).as(HTML)
  }
  
}
