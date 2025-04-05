package templates

import java.time.LocalDate
import scalatags.Text.all.*
import scalatags.Text.tags2.{article, aside, main, nav}
import ba.sake.hepek.anchorjs.AnchorjsDependencies
import utils.*
import Imports.*
import ba.sake.hepek.html.statik.{BlogPostPage, Section, StaticPage}

//////// engleski
trait EnBlogPage extends SakeBaBlogPage with EnStaticPage

trait EnStaticPage extends SakeBaBlogStaticPage {
  override def pageSettings = super.pageSettings.withLanguage("en")

  override def mainPages: List[StaticPage] = List(
    files.en.posts.programming.Index
  )

  override def staticSiteSettings =
    super.staticSiteSettings
      .withIndexPage(files.en.Index)
      .withMainPages(mainPages)
}

//////// bosanski
trait BsBlogPage extends BsStaticPage with SakeBaBlogPage

trait BsStaticPage extends SakeBaBlogStaticPage {
  override def pageSettings = super.pageSettings.withLanguage("bs")

  override def staticSiteSettings =
    super.staticSiteSettings
      .withIndexPage(files.Index)
      .withMainPages(mainPages)
}

//////////// common
trait SakeBaBlogPage extends SakeBaBlogStaticPage with BlogPostPage {
  override def pageContent: Frag = main(cls := "pico container")(
    nav(
      navLogo,
      ul(
        mainPages.map(mp =>
          val classes =
            if mp.pageCategory == this.pageCategory then "" else "secondary"
          li(a(href := mp.ref, cls := classes)(mp.pageSettings.title))
        )
      )
    ),
    div(cls := "blog-post")(
      aside(
        nav(
          ul(
            categoryPosts.map(bp =>
              val classes =
                if bp.relPath == this.relPath then "" else "secondary"
              li(a(href := bp.ref, cls := classes)(bp.pageSettings.label))
            )
          )
        )
      ),
      article(
        renderSections(blogSettings.sections)
      )
    )
  )

  def renderSections(secs: List[Section], depth: Int = 1): List[Frag] =
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

  def mainPages: List[StaticPage] = List(
    files.posts.programiranje.flowcharts.Index,
    files.posts.programiranje.java.Index,
    files.posts.programiranje.java.oop.Index,
    files.posts.programiranje.scala.Index,
    files.posts.matematika.Index
  )

  override def siteSettings =
    super.siteSettings
      .withName(Site.name)
      .withFaviconNormal(files.images.`favicon.svg`.ref)
      .withFaviconInverted(files.images.`logo.png`.ref)

  val navLogo = ul(
    li(
      a(href := staticSiteSettings.indexPage.map(_.ref).getOrElse("/"))(
        span(
          img(src := "/images/logo.png", alt := "logo", height := "32px")
        ),
        strong("blog.sake.ba")
      )
    )
  )

  override def headContent = frag(
    super.headContent,
    link(rel := "manifest", href := relTo(files.ManifestJSON))
  )

  override def styleURLs =
    super.styleURLs ++ List(
      files.styles.vendor.`pico.conditional.min.css`.ref,
      files.styles.`main.css`.ref
      //files.styles.`print.css`.ref
    )
  override def scriptURLs = super.scriptURLs.appended(
    files.scripts.`main.js`.ref
  )

}
