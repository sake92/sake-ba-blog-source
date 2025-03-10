package files.posts.matematika

import ba.sake.hepek.katex.KatexDependencies
import ba.sake.hepek.html.statik.BlogPostPage

trait MatematikaTemplate extends templates.BsBlogPage with KatexDependencies {

  override def pageCategory = Some("Matematika")

  override def categoryPosts: List[BlogPostPage] = List(Index, TeorijaSkupova)

}
