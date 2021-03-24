package site.posts.programiranje

package flowcharts

trait FlowchartsTemplate extends CodeTemplate {

  override def pageCategory = Some("Dijagrami toka")

  override def categoryPosts = List(
    Index,
    Variables,
    Input
  )

}
