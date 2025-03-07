package files.posts.programiranje

package scala

trait ScalaTemplate extends CodeTemplate with templates.BsBlogPage {

  override def pageCategory = Some("Scala")

  override def categoryPosts = List(Index, Uvod)

}
