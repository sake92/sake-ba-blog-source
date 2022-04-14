package site.posts.programiranje

package flowcharts

trait FlowchartsTemplate extends CodeTemplate {

  override def pageCategory = Some("Vizuelno progamiranje")

  override def categoryPosts = List(
    Index,
    Variables,
    Input
  )

}
