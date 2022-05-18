package templates

import java.time.LocalDate

import scalatags.Text.all._
import ba.sake.hepek.theme.bootstrap3.{
  HepekBootstrap3BlogPage,
  TocSettings,
  TocType
}
import ba.sake.hepek.anchorjs.AnchorjsDependencies
import images.Images
import utils._
import Imports._

//////// engleski
trait EnBlogPage extends SakeBaBlogPage with EnStaticPage {

  override def tocSettings =
    Some(TocSettings(title = "Content", tocType = TocType.Scrollspy(55)))

}

trait EnStaticPage extends SakeBaBlogStaticPage {
  override def pageSettings = super.pageSettings.withLanguage("en")

  override def staticSiteSettings =
    super.staticSiteSettings
      .withIndexPage(site.en.Index)
      .withMainPages(Site.en.mainPages)
}

//////// bosanski
trait BsBlogPage extends BsStaticPage with SakeBaBlogPage {

  override def tocSettings =
    Some(TocSettings(title = "Sadržaj", tocType = TocType.Scrollspy(55)))

}

trait BsStaticPage extends SakeBaBlogStaticPage {
  override def pageSettings = super.pageSettings.withLanguage("bs")

  override def staticSiteSettings =
    super.staticSiteSettings
      .withIndexPage(site.Index)
      .withMainPages(Site.bs.mainPages)
}

//////////// common
trait SakeBaBlogPage extends HepekBootstrap3BlogPage

trait SakeBaBlogStaticPage extends StaticPage with AnchorjsDependencies {

  override def navbar = Some(Navbar)

  override def siteSettings =
    super.siteSettings
      .withName(Site.name)
      .withFaviconNormal(Images.favicon.ref)
      .withFaviconInverted(Images.logo.ref)

  override def headContent = frag(
    super.headContent,
    link(rel := "manifest", href := relTo(site.ManifestJSON))
  )

  override def styleURLs =
    super.styleURLs ++ List(
      resources.styles.css("main").ref,
      resources.styles.css("print").ref
    )
  override def scriptURLs = super.scriptURLs :+ resources.scripts.js("main").ref

  override def stylesInline = // overrides inline style, added with navbar... :/
    super.stylesInline ++ List("""
        body { padding-top: 57px; }
      """)

  // Bootswatch cyborg theme
  override def bootstrapDependencies =
    super.bootstrapDependencies.withCssDependencies(
      Dependencies().withDeps(
        Dependency(
          "cyborg/bootstrap.min.css",
          bootstrapSettings.version,
          "bootswatch"
        )
      )
    )

}
