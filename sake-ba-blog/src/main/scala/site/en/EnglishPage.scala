package site.en

import templates.SakeBaBlogStaticPage
import templates.SakeBaBlogPage

trait EnglishBlogPage extends SakeBaBlogPage with EnglishStaticPage

trait EnglishStaticPage extends SakeBaBlogStaticPage {

  override def pageSettings = super.pageSettings.withLanguage("en")

  override def staticSiteSettings =
    super.staticSiteSettings
      .withIndexPage(site.Index)
      .withMainPages(utils.Site.en.mainPages)
}
