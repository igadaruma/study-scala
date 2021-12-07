package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 2. 型
    // 2-9. 演算子色々

    // これまでも演算子はいくつか利用してきましたが、もうちょっと紹介しちゃいます。
    // 実は、実体はメソッドと呼ばれるものなのですが、今は秘密です🤫

    // 算数系🔢(以下は全部Int型ですが任意の数値系の型で利用できます)
    // 足し算
    val addition = 10 + 1 // 11
    // 引き算
    val subtraction = 10 - 1 // 9
    // 掛け算
    val multiplication = 10 * 2 // 20
    // 割り算(0で割り算するとエラーになるで。注意やで🥴)
    val division = 10 / 5 // 2 
    // 割り算のあまり
    val remainder = 10 % 3 // 1

    // 論理演算(Boolean型の結果を返すものです)
    // 等しい
    val eq1 = 1 == 1 // true  <- "1は1と等しい"は真
    val eq2 = 1 == 2 // false <- "1は2と等しい"は偽
    val eq3 = "a" == "a" // true  <- "aはaと等しい"は真
    val eq4 = "a" == "b" // false <- "aはbと等しい"は偽
    // 等しくない
    val ne1 = 1 != 2 // true  <- "1は2と等しくない"は真
    val ne2 = 1 != 1 // false <- "1は1と等しくない"は偽
    // 小なり
    val lt1 = 1 < 2 // true  <- "1は2より小さい"は真
    val lt2 = 2 < 2 // false <- "2は2より小さい"は偽
    val lt3 = "a1" < "a2" // true  <- 文字列の場合は名前の順です(日本語での利用はおすすめしません)。
    val lt4 = "b1" < "a1" // false  <- 文字列の場合は名前の順です(日本語での利用はおすすめしません)。
    // 小なりイコール
    val le1 = 2 <= 2 // true <- "2は2より小さいか等しい"は真
    val le2 = 3 <= 2 // false <- "3は2より小さいか等しい"は偽
    // 大なり
    val gt1 = 3 > 2 // true <- "3は2より大きい"は偽
    val gt2 = 2 > 2 // false <- "2は2より大きい"は偽
    // 大なりイコール
    val ge1 = 2 >= 2 // false <- "1は2より大きい"は偽
    val ge2 = 2 >= 2 // false <- "1は2より大きい"は偽
    // 否定(反転)
    val denial1 = !false // true   
    val denial2 = !true // false

    // 論理演算の組合せ
    // AND,かつ,両方とも
    val and1 = true && true     // true
    val and2 = true && false    // false
    val and3 = false && false   // false
    val and4 = 1 == 1 && 1 != 2 // true "1は1と等しい かつ 1は2と等しくない"は真
    // OR,または,どっちか
    val or1 = true || true     // true
    val or2 = true || false    // true
    val or3 = false || false   // false
    val or4 = 1 == 1 || 1 == 2 // true "1は1と等しい または 1は2と等しい"は真
    
    Ok("他にも計算はたくさんありますがJOJOにネ！").as(HTML)
  }

}
