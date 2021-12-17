package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 4. 繰り返しとコレクション
    // 4-2. 繰り返し処理のためのfor式

    // 前回のリストとfor式を使って繰り返し処理の例をみていきましょう。
    val listInt = List(1, 2, 3)
    var sum = 0
    // 以下がfor式です。
    for (n <- listInt) { 
      sum = sum + n
    }
    // 以下のような記述になっています。
    // for (変数名 <- コレクション) { ループ処理 }
    
    // コレクションの値が順番に変数に入り、
    // ループ処理が行われる形になります。
    // 上記の例ではList(1, 2, 3)になっているので、
    
    // 1回目のループ処理
    // sum = sum + 1  // sumの値は1になります。
    // 2回目のループ処理
    // sum = sum + 2 // sumの値は3になります。
    // 3回目のループ処理
    // sum = sum + 3 // sumの値は6になります。
    
    // と、こうなるイメージです。
    // 別の例もみてみましょう。
    val listC = List('a', 'b', 'c')
    var str = ""
    for (s <- listC) {
      str = s"$str$s"
    }
    // 最終的にstr="abc"となります。
    
    // ちなみに今回のような、綺麗な連番や規則正しい文字であれば、
    // 上記のように、1つ1つ値を手書きしなくても、
    // 以下のように書く方法があるので、覚えておくと便利です。
    
    var sum2 = 0
    for (n <- 1 to 3) {
      sum2 = sum2 + n
    }
    
    var str2 = ""
    for (s <- 'a' to 'c') {
      str2 = s"$str2$s"
    }
    
    Ok(
      s"""
         |sum = $sum<br>
         |str = $str<br>
         |sum2 = $sum2<br>
         |str2 = $str2<br>
         |""".stripMargin).as(HTML)
    
    // `x to y`のように書いた時は、List型ではなく、
    // Range, NumericRange型であり、別のコレクションなのですが、
    // Listの親戚みたいなもん。ぐらいで捉えて頂いてOKです。
  }

}
