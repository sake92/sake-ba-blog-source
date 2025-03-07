package utils

import scalatags.Text.all._
import ba.sake.hepek.core.RelativePath
import ba.sake.hepek.html.statik.{BlogPostPage, StaticPage}
import utils.Imports._

object SiteMapHTML {

  // Site map, HTML for users ("sitemap" is XML, dont be confused :p)
  def siteMap(pages: List[StaticPage])(implicit caller: RelativePath) = {
    val lis = pages.map {
      case mp: BlogPostPage =>
        val childrenPages = mp.categoryPosts.map { p =>
          li(a(href := caller.relTo(p))(p.pageSettings.label))
        }
        frag(
          li(a(href := caller.relTo(mp))(mp.pageSettings.title)),
          li(ul(childrenPages))
        )
      case mp =>
        li(a(href := caller.relTo(mp))(mp.pageSettings.title))
    }
    ul(lis)
  }

}
