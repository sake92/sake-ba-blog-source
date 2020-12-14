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

trait SakeBaBlogPage extends SakeBaBlogStaticPage with HepekBootstrap3BlogPage {

  override def blogSettings =
    super.blogSettings
      .withAuthor("Sakib Hadžiavdić")
      .withCreatedDate(LocalDate.now)

  override def tocSettings =
    Some(TocSettings(title = "Sadržaj", tocType = TocType.Scrollspy(55)))

  override def pageHeader = None

}

trait SakeBaBlogStaticPage extends StaticPage with AnchorjsDependencies {

  override def navbar = Some(Navbar)

  override def staticSiteSettings =
    super.staticSiteSettings
      .withIndexPage(site.Index)
      .withMainPages(Site.mainPages)

  override def siteSettings =
    super.siteSettings
      .withName(Site.name)
      .withFaviconNormal(Images.favicon.ref)
      .withFaviconInverted(Images.faviconWhite.ref)

  override def pageSettings = super.pageSettings.withLanguage("bs")

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
        body { padding-top: 73px; }
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
