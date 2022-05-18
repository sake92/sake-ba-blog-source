package site.en.posts.programming

import java.time.LocalDate
import scalatags.Text.all._
import utils.Imports._, grid._, Image._
import images.Images

object Index extends ProgrammingTemplate {

  override def pageSettings =
    super.pageSettings
      .withTitle("Programming")
      .withLabel("Programming")
      .withDescription("Programming ramblings.")

  override def blogSettings =
    super.blogSettings
      .withSections(
        introSection
      )

  def introSection = Section(
    "Intro",
    s"""
      These are my ramblings about programming.
    """.md
  )
}
