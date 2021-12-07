package hepek

import java.io.File

import ba.sake.hepek.core.Renderable
import ba.sake.hepek.pdf._
import org.openqa.selenium.chrome.{ChromeDriver, ChromeOptions}

object PdfGenApp {

  def main(args: Array[String]): Unit = {
    if (args.isEmpty) {
      println(
        "You must pass the targetFolder value (base folder for generated pages)..."
      )
    }
    val targetFolder = args(0)

    System
      .setProperty(
        "webdriver.chrome.driver",
        """C:\hepek\chromedriver_win32\chromedriver.exe"""
      )

    val fonts = List(
      Font(new File("fonts/Roboto/Roboto-Regular.ttf"), "Roboto"),
      Font(new File("fonts/KaTeX_Main/KaTeX_Main-Regular.ttf"), "KaTeX_Main")
    )

    val pdfs: List[(String, List[Renderable])] = List(
      site.posts.programiranje.java.Index,
      site.posts.programiranje.scala.Index,
      site.posts.matematika.Index
    ).map(p => p.pageCategory.get -> p.categoryPosts)

    println("Rendering PDFs...")
    for ((fileName, pages) <- pdfs) {
      val file         = new File(s"$targetFolder/site/pdfs/$fileName.pdf")
      val pdfGenerator = getGenerator()
      pdfGenerator.generate(file, targetFolder, pages, fonts)
    }
  }

  def getGenerator(): PdfGenerator = {
    val options = new ChromeOptions()
    options.addArguments("headless") // don't open Chrome window...
    options.addArguments("window-size=1200x600")
    options.addArguments(
      "disable-web-security"
    ) // Ajax doesn't work without this !!!
    options.setBinary(
      """C:\Program Files\BraveSoftware\Brave-Browser\Application\brave.exe"""
    )
    val driver = new ChromeDriver(options)
    driver
      .manage()
      .timeouts()
      .setScriptTimeout(20, java.util.concurrent.TimeUnit.SECONDS)
    new PdfGenerator(driver)
  }
}
