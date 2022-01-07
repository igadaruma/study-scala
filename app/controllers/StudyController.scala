package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class StudyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def study = Action { _ =>
    // 7. 組込クラスと高階関数とラムダ式
    // 7-1. Option

    // 今回は、Scalaプログラミングにおいて、
    // とても重要なOption型に入門していきます。
    // 初めはちょっと難しく感じると思いますが、
    // 慣れれば簡単なので頑張っていきましょう。

    // まずは、型構造を解説します。
    // Option型といいつつ、登場する型が3+1個あります。
    // 1つ目: Option型 
    // 2つ目: Some型
    // 3つ目: None型
    // (4つ目: Some型に含まれる値の型)
    // です。

    // Option型は6-7の抽象クラスになっています。
    // そしてSome型とNone型がその子クラスになっています。
    // 他に子クラスは存在しません。

    // Option型は、ざっくり表現すると、
    // "値が有るかもしれないし無いかもしれない型"です。
    // 子クラスの、Some型は"値がある場合"、None型は"値がない場合"に対応します。
    // よくわからないと思いますので、実際のソースを見ていきます。

    // 例としまして、前述の"4つ目"の型がInt型のケースを考えてみます。
    // 言い換えると、Int型の"値が有るかもしれないし無いかもしれない"という場面を想定します。
    // まず、4-1で登場したList型に登場してきてもらいます。
    val nonEmptyNums: List[Int] = List(1, 2, 3)
    val emptyNums: List[Int] = List() // 空です。

    // 次に、これらのリストの先頭の値を取得したかったとします。
    // nonEmptyNums(0) // `nonEmptyNums.head`というメソッドでも同じことができます。
    // emptyNums(0) // `emptyNums.head`というメソッドでも同じことができます。
    // とかけば目的が達成できそうではあるのですが、
    // emptyNumsは空なので、実行時にエラーが発生してしまいます。
    // より安全な実装をするための方法の1つとしてOption型を利用できます。
    // List型を受け取って先頭の値があればそれを返却するOption型を利用した関数の例が以下です。
    def headOption(nums: List[Int]): Option[Int] = {
      // ListのnonEmptyメソッドは値が1つでも入っていればtrueになります。
      if (nums.nonEmpty) Some(nums.head) // `Some(値)`でSome型の値が生成できます。
      else None // Noneは値を含まないのでシンプルに`None`とだけ書きます。
    }

    // 注目点としまして、返り値の型がOption[Int]型になっています。
    // 6-8の内容になりますが、子クラスは親クラスの変数や関数の返り値の型の値として利用できます。
    // また、Option自体は抽象クラスなので、
    // この関数から返却される実際の値はSome型かNone型だということになります。
    // この関数では、Listが空でないときにだけ`head`メソッドを呼び出しているので、
    // 実行時にエラーになることが無い、安全な実装になっています。
    // 実際に利用する時はmatchと組合せて以下のように使うことができます。
    val numsHead1 = headOption(nonEmptyNums)
    val numsHead1AsString = numsHead1 match {
      // Some(変数名)をcaseに指定することでSome型だった場合に変数に中身の値を格納できます。
      case Some(n) => s"numsHead1はSomeで${n}が入っていました。"
      case None => "numsHead1はNoneでした。"
    }
    
    val numsHead2 = headOption(emptyNums)
    val numsHead2AsString = numsHead2 match {
      case Some(n) => s"numsHead2はSomeで${n}が入っていました。"
      case None => "numsHead2はNoneでした。"
    }

    // match式で型をチェックしている分、
    // ただただheadを呼び出すよりは複雑になりますが、
    // 実行時にエラーにならない安全な実装というのは、
    // とてつもなく重要だと考えています。
    // "値が有るかもしれないし無いかもしれない"という場面はよく遭遇するため、
    // この状況を安全に実装できるOption型は重宝されているわけです。

    Ok(
      s"""
         |$numsHead1AsString<br>
         |$numsHead2AsString<br>
         |""".stripMargin).as(HTML)
    
    // ちなみにちなみに、
    // 今回実装したheadOption関数は、
    // 実はListの組込メソッドとして既に用意されていまして、
    // 名前は同じheadOptionです。
    // nonEmptyNums.headOption
    // みたいに使えます。
    // このメソッドは、今回実装したList[Int]型専用ではなく、
    // 任意の型を含むListで利用できます。
  }

}
