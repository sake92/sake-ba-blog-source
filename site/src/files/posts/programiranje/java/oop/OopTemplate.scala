package files.posts.programiranje.java.oop

import files.posts.programiranje.CodeTemplate

trait OopTemplate extends CodeTemplate with templates.BsBlogPage {

  override def pageCategory = Some("OOP")

  override def categoryPosts = List(
    Index
  )

}
