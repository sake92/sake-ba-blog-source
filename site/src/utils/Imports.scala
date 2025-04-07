package utils

import ba.sake.hepek.prismjs.PrismCodeHighlightComponents

object Imports {

  val chl = PrismCodeHighlightComponents.default

  def jshell = chl.batch.withPrompt("jshell>")

  val Section = ba.sake.hepek.html.statik.Section

}
