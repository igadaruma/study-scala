package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 7. 組込クラスと高階関数とラムダ式
    // 7-6. Map

    // 第2章の最後に紹介するのはMapです。
    // 次のチャプターで出てくるのでここで紹介します。
    // これはListのようなコレクション系の型の1つになります。
    // Listと比較して、
    // 値に加えて、値に対するキー(名前のような？タグのような？)ものをセットに保持するのが特徴です。
    // Listの中から値を取る際は、applyOrElseなどで何番目かを指定していました。
    // Mapでは代わりに、キーを指定して値を取得します。
    // 逆にMapはListと違い、入れた順番というのは保持されないので注意です。

    // まずは具体例を見ていきましょう。
    // 果物に対する色を保持するイメージのMap例です。
    val fruitColors: Map[String, String] = Map(
      "りんご" -> "赤",
      "ばなな" -> "黄",
      "ぶどう" -> "紫"
    )
    // `キー -> 値`というのをセットにして、
    // 値をカンマ区切りで指定します。

    // 【補足事項】
    // `->`というのが初めてでてきましたが、
    // これはタプル型の値が２つのものを生成するものになっています。
    // ですので、以下と書いても同じことになります。
    // Map(("りんご", "赤"), ("ばなな", "黄"), ("ぶどう", "紫"))
    // `->`は"キーに対する値"というイメージがしやすいため、
    // Mapのためにこの記法が用意されているんだと思われます。
    // 【補足事項おわり】

    // 次は、このMapの値から値をとってくる例です。
    // りんごの色を取得したい場面というイメージでしょうか。
    val appleColor: Option[String] = fruitColors.get("りんご") // Some("赤")
    // すっかりお馴染みの？Option型が返ってくる安全設計メソッドです。
    // fruitColors("りんご")
    // といった危険設計のapplyメソッドもあります。

    // なんとなくMapの特徴はわかっていただけたかな？と思います。
    // 上記の例ではキーも値もStringですが、両者とも任意の型を利用できます。
    // 例えば以下はキーがStringで値がIntの例です。
    val numEnglish: Map[String, Int] = Map("one" -> 1, "two" -> 2)
    // 値がListになったりもOKです。

    // では次にMapのメソッドを少し紹介です。

    // ①+, ++
    // 値を追加する系です。
    // `+`は1つのペアを追加、
    // `++`は別のMapと結合です。
    // 原則追加するペアはキー・値ともに型を揃えます。
    val newFruitColors1 = fruitColors + ("レモン" -> 10)
    val newFruitColors2 = fruitColors ++ Map("レモン" -> "黃", "ライム" -> "緑")

    // ②getOrElse
    // 前述のgetはOptionで結果が返ってきますが、
    // こちらは、なかった場合の値を引数を指定することで、
    // 安全なまま、Option無しで値を受け取れます。
    val peachColor: String = fruitColors.getOrElse("もも", "未登録") // "もも"のキーは無いので、"未登録"となります。

    // ③keys
    // キーだけを集めたIterable型(実体はSet型)のコレクションを受け取ります。
    // 性質が異なりますが、現時点ではListのようなものと捉えてみてください。
    val fruits: Iterable[String] = fruitColors.keys
    // Iterable型はtoListメソッドでList型に変換も可能です。
    val fruitsList: List[String] = fruitColors.keys.toList
    // ※順番は入れた順とは限りません！

    // ④values
    // こちらは値だけを集めたIterable型のコレクションを受け取ります。
    val colors: Iterable[String] = fruitColors.values
    // ※順番は入れた順とは限りません！

    // ⑤map
    // またしても変換用のmapが登場です。
    // ただし、キーと値の２つがあるので、
    // 引数高階関数の引数はタプル型で2つの値を持ち、
    // 返り値もタプル型で2つ値を返します。
    // 以下は値に"色"というのを末尾に付け加える変換をする例です。
    val fruitColors2 = fruitColors.map(fruitAndColor => fruitAndColor._1 -> s"${fruitAndColor._2}色")

    Ok(
      s"""
         |fruitColors = ${fruitColors}<br>
         |appleColor = ${appleColor}<br>
         |numEnglish = ${numEnglish}<br>
         |<h3>①+, ++</h3>
         |newFruitColors1 = ${newFruitColors1}<br>
         |newFruitColors2 = ${newFruitColors2}<br>
         |<h3>②getOrElse</h3>
         |peachColor = ${peachColor}<br>
         |<h3>③keys</h3>
         |fruits = ${fruits}<br>
         |fruitsList = ${fruitsList}<br>
         |<h3>④values</h3>
         |colors = ${colors}<br>
         |<h3>⑤map</h3>
         |fruitColors2 = ${fruitColors2}<br>
         |""".stripMargin).as(HTML)

    // ここまでで第2章の内容は終わりです！！
    // もしかすると、まだ理解が怪しいかもと思う部分があるかもしれませんが、
    // ぜひ第3章に進んでしまい、第3章の内容でわからない部分がでてきたら、
    // その時復習してみるという形で学習を進めてもらえたらと考えております。
    // それでは次章で会いましょう。
    
  }

}
