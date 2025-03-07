package utils

import ba.sake.hepek.prismjs.PrismCodeHighlightComponents
import ba.sake.hepek.bootstrap3.statik.BootstrapStaticBundle
import ba.sake.hepek.plain.component.PlainUtilComponents

object Imports extends PlainUtilComponents /*extends BootstrapStaticBundle*/ {

 /* object resources extends Resources

  val grid = Grid.withScreenRatios(
    Grid.screenRatios
      .withSm(None) // stack on small
      .withXs(None) // and extra-small screens
  )
*/
  val chl = PrismCodeHighlightComponents.default

  def jshell = chl.batch.withPrompt("jshell>")

  val Section = ba.sake.hepek.html.statik.Section

}
