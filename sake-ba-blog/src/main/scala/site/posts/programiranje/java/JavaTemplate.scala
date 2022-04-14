package site.posts.programiranje

package java

trait JavaTemplate extends CodeTemplate {

  override def pageCategory = Some("Programiranje (Java)")

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
