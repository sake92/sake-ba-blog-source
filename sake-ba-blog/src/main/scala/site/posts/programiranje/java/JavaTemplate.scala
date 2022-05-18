package site.posts.programiranje

package java

trait JavaTemplate extends CodeTemplate with templates.BsBlogPage {

  override def pageCategory = Some("Java")

  override def categoryPosts = List(
    Index,
    Instalacija,
    Basics,
    Types,
    Statements,
    HelloWorld,
    IfStatement,
    SwitchStatement,
    WhileLoop,
    ForLoop,
    Functions,
    Arrays,
    Types2,
    Classes,
    Scope
  )

}
