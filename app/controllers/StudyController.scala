package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 6. クラス
    // 6-6. クラスの継承

    // クラスは継承(extends)と言われる機能があります。
    // ざっくりとしたイメージは、
    // あるクラスのフィールド・メソッドを、
    // 別のクラスが引き継ぐというものです。

    // 具体例を見ていきましょう。
    // まずは今まで同様のクラス定義です。
    
    // 鳥クラス。
    class Bird() { 
      // 鳴き声です。今は空になっています。
      val chirping: String = ""
      // 鳴くことができます。
      def song() = s"$chirping。"
      // 飛ぶことができます。
      def fly() = "ぱたぱた。"
    }

    // 次が本番です。
    // 以下が鳥クラスを継承する、
    // すずめクラスです。
    class Sparrow() extends Bird {
      override val chirping: String = "ぴよぴよ"
    }
    
    // クラスを継承する時は、上記のように、
    // `extends 継承するクラス名`というのを記載します。
    // ちなみに、継承されたクラス同士に関して、
    // 今回だと、Birdは親(スーパー)クラス、Sparrowは子(サブ)クラスと表現されます。
    // ちなみに、Sparrowクラスをさらに継承させることも可能です。
    // この場合、親の親は先祖クラス、
    // 子の子は子孫クラスとかといった表現をする方が多いと思われます。
    
    // さらにoverrideというのがvalの前についています。
    // 親クラスがもっているものは、基本的にそのまま子クラスが使えるようになるのですが、
    // 継承する過程で、任意のフィールドやメソッドを子クラス側で上書きすることができます。
    // それをする際には、`override`というのをつけないとエラーになります。
    // ※これは意図せず間違えて上書きしてしまうことを抑制するためのプログラミング言語としての工夫だそうです。
    
    // それではこれらのクラスを使ってみましょう。
    // 親クラス(鳥)
    val bird = new Bird // クラスパラメータが空なら`()`を省略できます。
    val birdSong = bird.song()
    val birdFly = bird.fly()
    // 子クラス(すずめ)
    val sparrow = new Sparrow
    val sparrowSong = sparrow.song() // Sparrowのクラス定義では直接指定していませんが、Birdを継承しているので利用可能。 
    val sparrowFly = sparrow.fly()  // 同上。
    
    Ok(
      s"""
         |birdSong = $birdSong<br>
         |birdFly = $birdFly<br>
         |sparrowSong = $sparrowSong<br>
         |sparrowFly = $sparrowFly<br>
         |""".stripMargin).as(HTML)
    
  }

}
