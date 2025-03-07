package files

import java.nio.file.Paths
import ba.sake.tupson.toJson
import ba.sake.hepek.core.Renderable
import ba.sake.hepek.html.pwa.WebAppManifestIcon

object ManifestJSON extends Renderable {

  val IconSizes = List(72, 96, 128, 144, 152, 192, 384, 512)

  override def relPath = Paths.get("files/manifest.json")

  override def render = {
    val icons = IconSizes.map { s =>
      val sizeStr = s"${s}x${s}"
      val purpose = Option.when(s == 192)("any maskable")
      WebAppManifestIcon(
        s"images/icons/icon-$sizeStr.png",
        sizeStr,
        "image/png",
        purpose
      )
    }
    val mf = Index.manifest.copy(
      name = "Saketovi tutorijali",
      short_name = Some("Sake tuts"),
      description = Some("Tutorijali iz programiranja"),
      background_color = Some("#000"),
      theme_color = Some("#000"),
      icons = icons,
      display = "standalone"
    )

    mf.toJson
  }
}
