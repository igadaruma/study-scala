package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 1. 変数とリテラル
    // 1-2. 文字列リテラル 

    // 既に登場していましたが、
    // `"`(二重引用符・ダブルクォーテーション)で囲まれた文字は、
    // "文字列"と呼ばれ、文字データを表しています。
    // このようなデータを直接的に表したものをリテラルと呼んだりします。
    // (※文字列以外のリテラルも存在します。今後紹介していきます。)
    // 一方で、変数名等は`"`は付けずにそのまま記載します。

    // 改めて、以下を見ると、
    // 変数messageに文字列を入れている処理が書かれています。
    var message = "こんちゃーす"

    // 前回は正しく、以下と記載していましたが、
    // Ok(message).as(HTML)

    // 以下のように、messageを`"`で囲ってしまうと、
    // 全然別の意味となり、変数messageとは関係なく、
    // "message"という文字列が表示されることになるので、
    // 別物だということを注意しておきましょう。
    Ok("message").as(HTML)
  }

}
