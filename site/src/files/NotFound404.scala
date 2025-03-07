package files

import scalatags.Text.all.*
import ba.sake.hepek.core.RelativePath
import scalatags.Text.tags2.nav
import templates.SakeBaBlogStaticPage
import utils.Site
import utils.Imports.*

object NotFound extends SakeBaBlogStaticPage {

  override def pageSettings = super.pageSettings.withTitle("Not found")

  override def fileName: String = "404.shtml"

  /* Since we use RELATIVE LINKS when referring to JS files, pages etc.
   * it doesn't work well when you land on a page e.g. sake.ba/blah/sdfd/fdf/dsf/s
   */
  override def relTo(other: RelativePath) =
    Site.url + "/" + super.relTo(other)

  override def pageContent =
    tag("main")(cls:="pico container")(
      nav(navLogo),
      """
          This content is not available in your country.  
          Sorry about that. :/

          ---
          Just kidding, there's nothing here! ^_^
        """.md,
      raw(
        """<a href="https://<!--#echo var="HTTP_HOST" -->">GOTO: homepage</a>"""
      )
    )

}
