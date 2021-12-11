package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 3. 分岐
    // 3-4. 分岐と型

    // また型です。型はすごく大事です。型の話です。
    // 分岐の際、それぞれの分岐で結果の値が存在することになります。
    // 今までの例では、分岐しても、それぞれが同じ型になるようになっていました。ふふ。
    // そうなると、その型が最終的な分岐式の型になります。
    // 何いってんの？というと、
    val message: String = // 分岐先でどっちもStringなのでmessageの型もString
      if (true) "trueだよ。" // こっちString(文字列)
      else "trueじゃないよ。" // こっちだってString(文字列)
    // ということです。

    // じゃあ違った場合はどうなるのかというと、Any型というものになります。
    val any: Any = // 両者の型が違うのでAnyという`曖昧な型`になります。曖昧なのでInt型でもString型でも入れる事ができるという感じ。
      if (true) 1 // こっちInt(整数)
      else "1" // こっちString(文字列)
    // 違ったら必ずAny型になるわけではないのですが、今は秘密です🤫

    // んで？と思うかもしれません。
    // 先の例では、anyの実際の値は1(Int型)となるわけなのですが、
    // 変数anyの型がIntではなくAnyになってしまうので、、
    // val answer = any + 10
    // 上記のような計算をしようとするとエラーになります・・。
    // Any型とInt型を足す方法とか知らねーよ。と怒られちゃうのです。
    // なんで！と思うかもしれませんが。Scalaだとこうなのです。これこそ安心なのです。なぜか？それは秘密です🤫
    // ※言語によっては計算できちゃったりします。それは怖いのです🥺

    // 分岐したときは全ルートで同じ型にする！
    // というのを原則にしましょう。原則ネ。
    // 今回if式の例でしたが、match式も同じです。
    
    // ただ、Any型になってしまっても、
    // 実は前回、隠していたmatch式の、
    // さらなる力(ぱわぁ)を使えば以下のようにこの状況を解決することができます。
    val answer = any match {
      case n: Int => // こんな感じで型アノテーションをつけてあげることで、`その型だったら`という条件がつきます。
        n + 10 // ここでは、intはInt型だと判定されたcase内なので、足し算できるZO！
      case _ => 0  // Intじゃない時にエラーにならないように、型アノテーション無し版のcaseも書いておきましょう。
    }
    // さらっと出てきましたが、
    // 実はすごく大事なぱわぁなので、
    // また詳しく触れます。

    Ok(
      s"""
         |message = $message<br>
         |any = $any<br>
         |answer = $answer<br>
         |""".stripMargin).as(HTML)
  }

}
