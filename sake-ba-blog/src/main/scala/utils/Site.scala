package utils

import ba.sake.hepek.html.statik.StaticPage
import ba.sake.hepek.Resources
import site.posts

object Site {

  val url  = "https://blog.sake.ba"
  val name = "blog.sake.ba"

  object bs {

    def mainPages: List[StaticPage] = List(
      posts.programiranje.flowcharts.Index,
      posts.programiranje.java.Index,
      posts.programiranje.java.oop.Index,
      posts.programiranje.scala.Index,
      posts.matematika.Index
    )

    val programiranjePdf = Resources.resource("pdfs/Programiranje.pdf")
    val scalaPdf         = Resources.resource("pdfs/Scala.pdf")
    val matematikaPdf    = Resources.resource("pdfs/Matematika.pdf")
  }

  object en {

    def mainPages: List[StaticPage] = List(
      site.en.posts.programming.Index
    )
  }
}
