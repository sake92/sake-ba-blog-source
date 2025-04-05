package files.en.posts.programming

import files.posts.programiranje.CodeTemplate

trait ProgrammingTemplate extends CodeTemplate with templates.EnBlogPage {

  override def pageCategory = Some("Programming")

  override def categoryPosts = List(
    Monads, ApiFirstScala
  )

}
