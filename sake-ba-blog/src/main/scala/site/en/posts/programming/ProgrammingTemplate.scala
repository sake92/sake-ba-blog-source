package site.en.posts.programming

import site.posts.programiranje.CodeTemplate

trait ProgrammingTemplate extends CodeTemplate with templates.EnBlogPage {

  override def pageCategory = Some("Programming")

  override def categoryPosts = List(
    Monads
  )

}
