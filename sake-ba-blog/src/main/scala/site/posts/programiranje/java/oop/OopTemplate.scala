package site.posts.programiranje.java.oop

import site.posts.programiranje.CodeTemplate

trait OopTemplate extends CodeTemplate with templates.BsBlogPage {

  override def pageCategory = Some("OOP")

  override def categoryPosts = List(
    Index
  )

}
