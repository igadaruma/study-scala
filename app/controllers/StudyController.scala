package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 6. クラス
    // 6-7. 抽象クラス
    
    // 継承の話の続編で、抽象クラスというのを紹介いたします。
    // すこーし今回は高度なトピックです。
    // まず名前がピンと来ないと思います。おそらく今回の説明を聞いても、
    // 実際の利用シーンがイメージできないとは思うのですが、
    // とりあえず雰囲気だけをまずは把握いただけると幸いです。
    
    // ・あるクラスがあり、
    // ・そのクラスを直接利用することは無く(または他のプログラマにさせたくなく)、
    // ・継承をさせた子クラスをインスタンス化して間接的に利用する(またはしてほしい)、
    // ということがしたい場面で、"あるクラス"部分を抽象クラスにすると都合が良いものになっています。
    
    // 例として、前回のBirdクラスはそのまま使いたくないんだよな、
    // という状況だったとして、Birdクラスを抽象クラスに変更してみます。
    
    // 抽象クラスは、
    // ・newでインスタンス生成ができない
    // ・"抽象フィールド"や"抽象メソッド"を含めることができる
    // という特徴を持ちます。
    // 具体例をみていきましょう。
    
    // 親の鳥"抽象"クラス。
    abstract class Bird() { 
      // 鳴き声フィールドです。
      // しかし、中身が空になっています。これが抽象フィールドです。
      val chirping: String
      
      // 歌うメソッドは定義があります。これはいつものメソッドです。
      def song() = s"$chirping。"
      
      // 飛ぶメソッドです。
      // しかし、中身が空になっています。これが抽象メソッドです。
      def fly(): String
    }

    // 子のぺんぎんクラス。
    class Penguin() extends Bird {
      // 親が持つ"抽象"メンバ(フィールド・メソッド)は、
      // 必ず子クラスで定義(実装)する必要があります。
      val chirping: String = "ぶるぁぁぁぁ！" 
      def fly(): String = "無理だよ"
      
      // 前回はしていませんでしたが、
      // 追加のフィールドやメソッドを追加することも可能です。
      // ※抽象クラス関係なしに可能です。
      def swim(): String = "すいすーい"
    }
    
    // 前回上書きの場合は、`override`というのがついていましたが、無くなっています。
    // 実は付けても問題ないのですが、上書きする対象が抽象メンバであった場合は、
    // `override`を省略してもいいということになっています。
    
    // ではこれらのクラスを使ってみましょう。
    // 親クラス・・・は抽象クラスなので以下のように書くとエラーになります！
    // val bird = new Bird // NG
    
    // 子クラス(ぺんぎん)は抽象クラスではないので、
    // (抽象クラスの対義語として具象クラスがありますが聞くのも言うのも機会が少ない印象です。)
    // 今まで通りnewできます。
    val penguin = new Penguin
    val penguinSong = penguin.song() 
    val penguinFly = penguin.fly()
    val penguinSwim = penguin.swim()
    
    Ok(
      s"""
         |penguinSong = $penguinSong<br>
         |penguinFly = $penguinFly<br>
         |penguinSwim = $penguinSwim<br>
         |""".stripMargin).as(HTML)
    
  }

}
