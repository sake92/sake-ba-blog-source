package utils

import ba.sake.hepek.html.statik.StaticPage
import ba.sake.hepek.Resource
import files.posts

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

    val programiranjePdf = Resource("pdfs/Programiranje.pdf")
    val scalaPdf         = Resource("pdfs/Scala.pdf")
    val matematikaPdf    = Resource("pdfs/Matematika.pdf")
  }

  object en {

    def mainPages: List[StaticPage] = List(
      files.en.posts.programming.Index
    )
  }
}
