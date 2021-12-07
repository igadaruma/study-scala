package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 2. 型
    // 2-10. 演算子の計算順序と括弧

    // 演算子には計算される順番のルールがあります。
    // 書いた順(左から)に計算されるというわけではありません。
    val number1 = 1 + 10 * 2 // 21 <- `10 * 2`が先に計算されます。

    // 丸括弧を使うとその中の計算を優先することができます！数学と一緒だね！
    val number2 = (1 + 10) * 2 // 22 <- `(1 + 10)`が先に計算されます。

    // 算術系の演算子の優先順位は、
    // 基本的に数学ルールと同じなので比較的わかりやすいのですが、
    // プログラミングの場合は、論理演算などが入り交じる可能性があります。
    // プログラミング言語によってもまちまちの優先順位のルールを、
    // 完璧にいつも覚えておくのは現実的ではないので、
    // ん？順番怪しいぞ？という時は丸括弧をどんどん使いましょう。
    val bool1 = (number1 > number2) && (10 + (1 * 2) < 15) // こんな感じで括弧in括弧も可能です。

    // `{}`の波括弧も同じように利用できます。
    val number3 = {1 + 10} * 2 // 22
    // しかし、波の場合は丸と異なり、
    // 複数の式を含めることができ、
    // "括弧内の最終行で行われた計算結果がその領域(ブロック)の値"になります。
    val number4 = {
      val one = 1
      val two = 2
      one + two // ここが`{}`内の最終行なので、このブロックの値となります。
    } + 1 // val number4 = 3 + 1 => 4 ということになります。
    // この挙動は今後の話でもよくでてきます。

    Ok(
      s"""
         |number1 = $number1<br>
         |number2 = $number2<br>
         |bool1 = $bool1<br>
         |number3 = $number3<br>
         |number4 = $number4<br>
         |""".stripMargin).as(HTML)
  }

}
