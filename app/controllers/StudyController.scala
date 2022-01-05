package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 6. クラス
    // 6-8. 型と継承と多態性

    // お馴染み"型"の話です。
    // 継承関係にある親子クラスの型に関して、
    // 少しばかし、ややこしい仕様があるので🤔
    // ここでその一部を解説します。

    // まずは説明のために、
    // 親子関係を持つクラスの定義です。

    // 鳥クラス(抽象)
    abstract class Bird() {
      val chirping: String // 抽象フィールド

      def fly(): String = "ぱたぱた"
    }

    // すずめクラス
    class Sparrow extends Bird() {
      val chirping: String = "ぴよぴよ"
    }

    // ぺんぎんクラス
    class Penguin() extends Bird {
      val chirping: String = "ぶるぁぁぁぁ！"

      override def fly(): String = "無理だよ" // 上書き

      def swim(): String = "すいすーい" // 追加
    }

    // それでは、、

    // ポイントその①
    // 子クラスは親(先祖)クラスの型の変数に格納できます。
    // 同様に、親クラス型が返り値型になっている関数の、
    // 返り値として指定できます。
    // 以下は変数の場合の例です。
    val bird1: Bird = new Sparrow
    val bird2: Bird = new Penguin

    // ポイントその②
    // 親(先祖)クラス型の変数に入っていたとしても、
    // メンバ(フィールド・メソッド)呼び出しを行った場合の処理は、
    // 実際の子クラスの処理結果になります。
    val bird1Song = bird1.fly() // "ぱたぱた"
    val bird2Song = bird2.fly() // "無理だよ"
    // ※chirpingも同様です。
    
    // 【補足事項】
    // これは覚えなくていいと思いますが、
    // こういった挙動を専門用語で多態性(ポリモーフィズム)と呼んだりします。
    // さらに細かくいうと、多態性の中でも、"サブタイプ多相"に分類されます。
    // 【補足事項 ここまで】

    // ポイントその③
    // 親(先祖)クラス型の変数に入ると、
    // "子で独自に追加されたメンバ"をそのまま参照することはできません。
    // Penguinでは`swim`が追加で定義されていますが、
    // bird2.swim()
    // はエラーになってしまいます💣🔥

    // ポイントその③'
    // swimを呼びたい場合はmatch式を使います。
    val bird2Swim = bird2 match {
      case b: Penguin => b.swim() // matchで型チェックが済んでるので、bはPenguin型として扱えます。
      case _ => "Penguin型ではないです。"
    }
    
    // もっと込み入った話題もありますが、
    // この③点と今までの話をおさえていれば、
    // おおよそ継承の基礎はマスターしていると言えると思います✨
    
    // ただ、今回の話だけですと、
    // これの何がうれしいの？ どこで使うの？
    // というのが、おそらくわからないと思います😭
    // もう少し後に解説する組込型の`Option`が、
    // 実際の応用例の1つになります。
    // 一旦は、そんなもんかぐらいで先に進んでみてください。
    
    Ok(
      s"""
         |bird1Song = $bird1Song<br>
         |bird2Song = $bird2Song<br>
         |bird2Swim = $bird2Swim<br>
         |""".stripMargin).as(HTML)
  }

}
