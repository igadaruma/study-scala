package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 4. 繰り返しとコレクション
    // 4-4. 繰り返し処理のためのfor式 強化編その2 条件指定

    // さらに欲を出して、もーちょびっつ、
    // ループ処理を深堀りします。
    
    // 今まではコレクションの値を順番にループするだけでしたが、
    // たびたび登場する`if`様を使って、もう少し細やかに制御できます。
    // 具体例を見るが良しです。
    val one2nine = 1 to 9
    var str = ""
    for (i <- one2nine if i % 2 == 0) { 
      str = s"$str$i"
    }
    // `if`が入り込みました。
    // いつものように(?)、ifの後にはBoolean型になる式を書きます。
    // そしてこれは、その結果がtrueの時だけループ処理を実行するよ。という意味になってます。
    // iには1~9が順番に入るわけですが、そのうちの、
    // i%2が0 => iを2で割った余りが0 => iが2の倍数
    // の時だけ処理するよ。ということです。
    // 結果は str="2468" です。
    
    // 折角なのでもう一例出しておきます。
    var str2 = ""
    for (i <- one2nine if i >= 3 && i <= 5) {
      str2 = s"$str2$i"
    }
    // 結果は str="345" です。
    // おわかりいただけただろうか🤔
    
    Ok(
      s"""
         |str = $str<br>
         |str2 = $str2
         |""".stripMargin).as(HTML)
  }
  
}
