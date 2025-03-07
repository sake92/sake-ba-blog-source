package templates

import java.time.LocalDate
import scalatags.Text.all.*
import ba.sake.hepek.anchorjs.AnchorjsDependencies
import utils.*
import Imports.*
import ba.sake.hepek.html.statik.{BlogPostPage, Section, StaticPage}

//////// engleski
trait EnBlogPage extends SakeBaBlogPage with EnStaticPage 

trait EnStaticPage extends SakeBaBlogStaticPage {
  override def pageSettings = super.pageSettings.withLanguage("en")

  override def staticSiteSettings =
    super.staticSiteSettings
      .withIndexPage(files.en.Index)
      .withMainPages(Site.en.mainPages)
}

//////// bosanski
trait BsBlogPage extends BsStaticPage with SakeBaBlogPage

trait BsStaticPage extends SakeBaBlogStaticPage {
  override def pageSettings = super.pageSettings.withLanguage("bs")

  override def staticSiteSettings =
    super.staticSiteSettings
      .withIndexPage(files.Index)
      .withMainPages(Site.bs.mainPages)
}

//////////// common
trait SakeBaBlogPage extends SakeBaBlogStaticPage with BlogPostPage {
  override def pageContent: Frag = tag("main")(cls:="pico container")(
        
         /* div(cls := "hidden-print")(
            blogSettings.createdDate.map(cd => div(cd.format(blogSettings.dateFormat))),
            blogSettings.author.map(author => div(author))
          ),*/
          tag("article")(
            renderSections(blogSettings.sections)
          )
        
    )

   def renderSections(secs: List[Section], depth: Int = 2): List[Frag] =
    secs.map { s =>
      val hTag = tag("h" + (depth + 1))
      tag("section")(
        hTag(id := s.id)(s.name),
        s.content,
        renderSections(s.children, depth + 1)
      )
    }
}

trait SakeBaBlogStaticPage extends StaticPage with AnchorjsDependencies {
  
  override def siteSettings =
    super.siteSettings
      .withName(Site.name)
      .withFaviconNormal(files.images.`favicon.svg`.ref)
      .withFaviconInverted(files.images.`logo.png`.ref)

  override def headContent = frag(
    super.headContent,
    link(rel := "manifest", href := relTo(files.ManifestJSON))
  )

  override def pageContent: Frag = tag("main")(cls:="pico container")(
    super.pageContent
  )

  override def styleURLs =
    super.styleURLs ++ List(
      files.styles.vendor.`pico.conditional.min.css`.ref,
      files.styles.`main.css`.ref,
      //files.styles.`print.css`.ref
    )
  override def scriptURLs = super.scriptURLs.appended(
    files.scripts.`main.js`.ref
  )

}
