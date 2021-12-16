package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 4. 繰り返しとコレクション
    // 4-1. List

    // これまでに、
    // ①上からシンプルに順番にプログラムが実行されていく処理
    // ②条件による分岐
    // を見てきたわけなのですが、お次は、
    // ③繰り返し・ループ処理
    // と呼ばれるものを見ていきます。

    // 実はこの①②③を利用すれば、理論上、
    // どんな理論的手続き(アルゴリズム)も表現できると言われてたりします。
    // つまるところ、プログラム大原則をマスターしてしまったことになります。
    // ささ。早速やりましょう。

    // と、言いたいところですが、
    // その前に、繰り返し処理をするにあたり、
    // それに対してとっても身近な存在である、
    // コレクション系の型をまずは見ていきます。

    // そもそもコレクションって何よ？ですが、
    // "複数の値を、1つの変数に寄せ集めるための型"
    // と捉えていただくのがいいかもしれません。
    // コレクション型といっても、たくさん種類があるのですが、
    // まずはその代表格であるListを見ていきます。
    // 以下のように作成します。
    val listInt: List[Int] = List(1, 2, 3)
    // `listInt`には1と2と3がぎゅっと詰まったような感じになっています。

    // 繰り返しとは直接関係無いのですが、
    // 参考までに、中の値を取り出す時は`(何番目)`というのをつけて取り出せます。
    // ただし！何番目というのの開始番号は0です。
    // 慣れないと間違えやすいので注意しましょう！
    val one = listInt(0) // 1
    val two = listInt(1) // 2
    val three = listInt(2) // 3
    // val four = listInt(3) // 【注意】値が無いところを指定すると実行時にエラーになります！
    
    // 次回繰り返しの処理にListを使っていきます！

    Ok(
      s"""
         |listInt = $listInt<br>
         |one = $one<br>
         |two = $two<br>
         |three = $three<br>
         |""".stripMargin).as(HTML)
  }

}
