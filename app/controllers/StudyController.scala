package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 3. 分岐
    // 3-6. 変数の範囲(スコープ)

    // 2-10にて`{}`によるブロックの話がありまして、
    // if式でも`{}`ブロックを用いていました。
    // さらにmatch式では`{}`ブロックではないですが、
    // `case ? =>`というような記述の後に、
    // 複数行の式を記述できるという話もありました。

    // このようなブロック領域の内側で、
    // 変数を作った(宣言した)場合に、実は、
    // 外側の領域ではその変数を使えない、
    // というルールがあります！なんだって！

    val outside1 = {
      val inside1 = s"ブロックの中で宣言"
      inside1 // ← このブロックの値
    }
    val outside2 = outside1 // 大丈夫だ。問題ない。
    // val inside2 = inside1 // 💣💥 inside1はブロックの中で作られたので外側からは使えない！

    // 一方で、外側で作られた変数を、内側で見ることはできます。
    val outside3 = {
      outside2 // 大丈夫だ。問題ない。
    } // ※1行だけで演算もないので、ブロックにする意味がない例ですが・・🐣

    // これはブロックが入れ子になっても、
    // 同じようにルールが適応されます。

    // 最後に少しややこしい例をあげておしまいです。
    // コメントだらけで見づらいですが、内容的には、
    // ・外側の変数は内側で使える。
    // ・ブロック内で宣言された変数はそのブロックが終了してしまえば、それ以降は使えない。
    // というだけでござります。
    
    val scope1 = 1
    if (true) { // scope1が見えるブロック開始です。
      val scope2 = 2
      if (true) { // scope1,2が見えるブロック開始です。
        val scope3 = 3
        // ここでscope3はさよなら。
      } else { // scope1,2が見えるブロック開始です。scope3は見えません。
        val scope4 = 4
        // ここでscope4はさよなら。
      }
      // ここでscope2はさよなら。
    } else { // scope1が見えるブロック開始です。scope2,3,4は見えません。
      val scope5 = 5
      if (true) { // scope1,5が見えるブロック開始です。
        val scope6 = 6
        // ここでscope6はさよなら。
      } else { // scope1,5が見えるブロック開始です。scope6は見えません
        val scope7 = 7
        // ここでscope7はさよなら。
      }
      // ここでscope5がさよなら。
    }

    Ok(
      s"""
         |outside1 = $outside1<br>
         |outside2 = $outside2<br>
         |outside3 = $outside3<br>
         |""".stripMargin).as(HTML)
    
    //【補足】
    // 外側で既に宣言されている変数と同じ名前で、
    // 内側のブロックで重ねて宣言した場合は、
    // 内側にいる間は、外側の変数が隠れたような形で、内側で宣言したものが優先されます。
    // ブロックの外に出たら、元の外側の変数が見えるようになります。
    // ややこしいので、基本は避けるようにしたほうがいいかなと思います。
    
    // 例）
    // val x = "x"
    // val innerX = {
    //    val x = "えっくす" 
    //    x // このブロックが終わるまではxは"えっくす"になります。
    // }
    // // 外に出たらxは元の"x"に戻ります。innerXは"えっくす"です。
    // ややこしネ！

  }

}
