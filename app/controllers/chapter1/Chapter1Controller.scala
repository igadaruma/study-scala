package controllers.chapter1

import play.api.mvc._

import javax.inject._

@Singleton
class Chapter1Controller @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def index() = Action { implicit request: Request[AnyContent] =>
    // <- この記号2つより右の文字は"コメント"と呼ばれ、プログラムからは無視されます。
    // 書いてあってもなくても処理は同じ。ということなのです。

    // 【!! 注意 !!】
    // このプログラムは入門者の方への説明用に少し手抜き実装されています。
    // プロの現場ではNGな書き方です。どこが？等の詳細はいずれ説明します。たぶん... ヽ(〃v〃)ﾉ ♪

    // 画面で入力され送信されたフォームパラメータを取得しています。
    request.body.asFormUrlEncoded match { // match(マッチ)式と呼ばれる構文です。状況によって処理が分岐します。
      case Some(formParameters) => // フォームパラメーターが送信されていた場合はこっちの"case"にきます。
        // 左側のテキストボックスの値を取得しています。
        val left = formParameters("left").head
        // 右側のテキストボックスの値を取得しています。
        val right = formParameters("right").head
        // 左側のテキストボックスの値を数値の"型"に変換します。値が空なら0と扱っています。
        val leftAsNumber = if (left.isEmpty) 0 else left.toDouble
        // 右側のテキストボックスの値を数値の"型"に変換します。値が空なら0と扱っています。
        val rightAsNumber = if (right.isEmpty) 0 else right.toDouble
        // 足し算しています。
        val answer = leftAsNumber + rightAsNumber
        // 計算結果等をHTMLテンプレート(app/views/index.scala.html)に渡しています。
        Ok(views.html.index(leftAsNumber, rightAsNumber, answer))
      
      case None => // フォームパラメーターがな送信されていない場合(画面を初めて開く時等)はこっちの"case"にきます。
        // 計算結果等は無いので、0をHTMLテンプレート(app/views/index.scala.html)に渡しています。
        Ok(views.html.index(0, 0, 0))
    }
  }
}
