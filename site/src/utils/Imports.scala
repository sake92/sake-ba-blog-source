package utils

import ba.sake.hepek.prismjs.PrismCodeHighlightComponents
import ba.sake.hepek.plain.component.PlainUtilComponents

object Imports extends PlainUtilComponents {

  val chl = PrismCodeHighlightComponents.default

  def jshell = chl.batch.withPrompt("jshell>")

  val Section = ba.sake.hepek.html.statik.Section

}
