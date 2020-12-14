package site.posts.programiranje.flowcharts

import java.time.LocalDate

import scalatags.Text.all._
import utils.Imports._
import Image._
import images.Images
import site.posts.programiranje.CodeTemplate
import utils.Imports.grid._

object Index extends CodeTemplate {

  override def pageSettings =
    super.pageSettings
      .withTitle("Uvod u dijagrame toka")
      .withLabel("Početna")
      .withDescription("Uvod u dijagrame toka.")

  override def blogSettings =
    super.blogSettings
      .withCreatedDate(LocalDate.of(2017, 7, 1))
      .withSections(
        uvodSection
      )

  def uvodSection = Section(
    "Uvod",
    """
      TODO
    """.md
  )

}
