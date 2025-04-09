package utils

import scalatags.Text.all.*
import scalatags.Text.tags2.nav
import ba.sake.hepek.html.statik.StaticPage
import ba.sake.hepek.Resource
import files.posts

object Site {

  val url  = "https://blog.sake.ba"
  val name = "blog.sake.ba"

  object bs {

    val programiranjePdf = Resource("pdfs/Programiranje.pdf")
    val scalaPdf         = Resource("pdfs/Scala.pdf")
    val matematikaPdf    = Resource("pdfs/Matematika.pdf")
  }

  object en {}
}
