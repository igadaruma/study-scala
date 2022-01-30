package controllers.chapter2

import play.api.mvc._

import javax.inject._

@Singleton
class Chapter2_1Controller @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 今は細かいことは気にせず、これでブラウザに文字が出るんだと納得ください。約束だぞ🥺
    Ok("こんちゃーす").as(HTML)
  }

  def chapter2_1 = Action { _ =>
    // 1. 変数とリテラル
    // 1-1. 初めての変数(var)

    // ”変数"は値(データ)の入れ物📦みたいな感じです。
    // 変数にはそれぞれ名前がつけられるので、
    // その名前を使って後から中のデータを取り出すことができます。

    // 例えば以下のように書くと、変数を作って値を保持させることができます。
    // var 変数名 = 変数に入れる値
    var message = "こんちゃーす"

    // 作った変数(message)は以下のように使うことができます。
    Ok(message).as(HTML)

    // 【注意】
    // 変数名は数字から始まったり、日本語や、予約語と呼ばれる名前につかえないものがあります。
    // IntelliJ等でプログラムを書いている場合は、使えない名前だと、文字色が変わったりして、
    // 密かに教えてくれます。予約語については今は詳しく触れないですが、学習が進むにつれ、
    // 自ずとわかってくると思います。
  }
  
}
