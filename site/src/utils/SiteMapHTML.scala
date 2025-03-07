package utils

import scalatags.Text.all._
import ba.sake.hepek.core.RelativePath
import ba.sake.hepek.html.statik.{BlogPostPage, StaticPage}

object SiteMapHTML {

  // Site map, HTML for users ("sitemap" is XML, dont be confused :p)
  def siteMap(pages: List[StaticPage])(implicit caller: RelativePath): Frag =
    ul(
      pages.map {
        case mp: BlogPostPage =>
          frag(
            li(
              a(href := caller.relTo(mp))(mp.pageSettings.title)
            ), // ovdje title jer "Home".. "Uvod" se ponavlja
            {
              val childPages = mp.categoryPosts.filterNot(_ == mp)
              Option.when(childPages.nonEmpty)(
                li(
                  ul(
                    mp.categoryPosts.filterNot(_ == mp).map { p =>
                      li(a(href := caller.relTo(p))(p.pageSettings.label))
                    }
                  )
                )
              )
            }
          )
        case mp =>
          li(a(href := caller.relTo(mp))(mp.pageSettings.label))
      }
    )

}
