package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 2. 型
    // 2-8. 基本型いろいろ

    // Scalaの基本型(言語に元から存在する型で最も基本的なもの)の一部を紹介しちゃいます。
    // 基本型以外にも、元から存在する型という意味では、もっと大量にあります。
    // それら全部はとても覚えられないので必要に応じて都度都度、勉強します。
    // 以下の基本型についても、おそらくBoolean, Int(Long), Double, String以外はそれほど使わないで済むことが多いと思います。

    // 論理系
    val boolean: Boolean = true // 論理型です。trueかfalseしか値を持ちません。ブーリアンとよみます。
    // true ≒ YES
    // false ≒ NO
    // みたいな感じです(?)
    // で、それなに？という感じかもしれません。もうちょっとしたら詳しくでてきますので、今は気にせず。

    // 数値系(整数) 持てる値の範囲によって色々あります。
    val byte: Byte = 1 // 整数値型 -128 ~ 127 
    val short: Short = 2 // 整数値型 -32768 ~ 32767
    val int: Int = 3 // 整数値型 -2147483648 ~ 2147483647
    val long: Long = 4L // 整数値型 Lを数字リテラルにつけるとLong型になります。 -9223372036854775808 ~ 9223372036854775807
    // 数値系(小数) 
    val float: Float = 0.1f // 小数値型 小数リテラルにfをつけるとFloat型になります。Doubleより扱える値の範囲が狭いです。
    val double: Double = 0.1 // 少数値型
    // 何かしらの必要に迫られない限りは、広い範囲が扱える型を使えば良いとお考えくださいませ。
    // 型の範囲外の値を入れようとすると、大変なことになります(一周回って数が減ったり増えたり)💥

    // 文字系
    val char: Char = 'c' // 文字型。1文字だけの型です。`'`の記号で挟んだ文字は文字型リテラルです。
    val string: String = "string" // 文字列型

    Ok(
      s"""boolean = $boolean<br>
         |byte = $byte<br>
         |short = $short<br>
         |int = $int<br>
         |long = $long<br>
         |float = $float<br>
         |double = $double<br>
         |char = $char<br>
         |string = $string<br>
         |""".stripMargin).as(HTML)

  }

}
