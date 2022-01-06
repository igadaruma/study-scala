package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 6. クラス
    // 6-9. Any型・Object型

    // 今回は番外編です。
    // よくわからんぞ？となった場合でも気にせず次に進んで問題ないです。
    
    // まずはAny型って何？という話です。
    // おそらく、もう忘れちゃっていることと思いますが、
    // 3-4にて登場していた型です。折角なのでぜひこの機会に復習してみてください。

    // さて、このAny型は、
    // 全ての型の先祖クラスである、という特別な存在です。
    // よって、ヴェルタースオリジナルです(スルーしてください)。
    // なんと自分で定義したクラスについても同じで、
    // Anyをextendsしていなくても勝手にそうなるのです。
    
    // つまり以下のように、
    // なんでもかんでもAny型の変数に入れることができます。
    // ※関数の戻り値の型としても同様です。
    
    class Something() // 自分で定義したクラス
    
    val someAsAny: Any = new Something() // Any型の変数に入る。
    val stringAsAny: Any = "string"
    val intAsAny: Any = 10
    val boolAsAny: Any = true
    
    // 一方で、Any型がでてきてしまうと、
    // match等を使って型を判定する必要が多いので、
    // 原則避けるべき型という捉え方が良いのではないかと思います。
    
    // そして、さらに、
    // 類似したObjectという型(クラス)というのも存在します。
    // 以下のように、おおよそAny型と同じなのですが、
    // 数値型や真偽型はObject型ではないという罠もあります。
    val someAsObj: Object = new Something() // 自分で定義したクラスは必ずObject型として扱えます。
    val stringAsObj: Object = "string"
    // val intAsObj: Object = 10 // エラー
    // val boolAsObj: Object = true // エラー
    
    // この違いからも予想できるかもですが、
    // AnyはObjectよりも上位なので、Object型の値をAny型の変数には入れられますが、
    // 逆はだめです。
    
    // Object型には`toString`メソッド等がいくつか組込実装されています。
    // なので、空っぽ定義のSomething型のインスタンスでも、
    // toStringが呼び出せてしまいます。
    // わお。
    val someAsString = (new Something()).toString
    
    // ただし、Object型のtoStringが望ましい文字列を返さない事の方が多いと思われますので、
    // むしろ、仮に同名のtoStringというメソッドを独自実装したい場合は、
    // `override`する必要があるという点に注意しましょう。
    
    class SomethingGreat() {
      // Object型で定義されているメンバはoverrideが必要
      override def toString(): String = "偉大だわぁ"
      
      // ちなみに、`super.メンバ`で親クラスのメンバを呼び出せます。
      def toStringByObject(): String = super.toString // Object型で実装されている`toString`を呼び出しています。
    }
    
    val sg = new SomethingGreat()
    val sgAsString = sg.toString()
    val sgAsStringByObject = sg.toStringByObject()
    
    Ok(
      s"""
         |sgAsString = $sgAsString<br>
         |sgAsStringByObject = $sgAsStringByObject<br>
         |""".stripMargin).as(HTML)
  }

}
