package site.posts.programiranje

package flowcharts

import scalatags.Text.all._

import utils.Imports._
import ba.sake.hepek.prismjs.PrismConsts

trait FlowchartsTemplate extends CodeTemplate {

  override def pageCategory = Some("Vizuelno progamiranje")

  override def categoryPosts = List(
    Index,
    Variables,
    Input,
    Ifs
  )

  override def bodyContent = frag(
    super.bodyContent,
    flowrunTemplateHtml
  )

  override def styleURLs =
    super.styleURLs.prependedAll(
      List(
        resources.resource("fonts/fonts.css").ref,
        resources.styles.css("vendor/toastify.min").ref,
        resources.styles.css("vendor/flowrun").ref,
        resources.styles.css("vendor/toastify.min").ref
      )
    )
  override def scriptURLs = super.scriptURLs.appendedAll(
    List(
      resources.scripts.js("vendor/hpcc-js-wasm@1.12.8.min").ref,
      resources.scripts.js("vendor/d3.v5.min").ref,
      resources.scripts.js("vendor/d3-graphviz@4.1.0.min").ref,
      resources.scripts.js("vendor/main").ref,
      resources.scripts.js("flowrun-init").ref
    )
  )

  override def prismSettings =
    super.prismSettings.withPlugins(
      PrismConsts.plugins.filter(p =>
        p._1 == "line-highlight" || p._1 == "line-numbers"
      )
    )

  private val flowrunTemplateHtml = raw("""
      |<div id="flowrun-template">
      |        <div class="flowrun-functions-list flowrun--panel">
      |            <div class="flowrun-fun-chooser"></div>
      |            <button class="flowrun-btn-fun-add" title="Add new function"> <span class="material-icons">add</span> </button>
      |        </div>
      |
      |        <div class="flowrun-diagram flowrun--panel">
      |            <div class="flowrun-diagram-header">
      |                <div class="flowrun-draw-copy-btns">
      |                    <button class="flowrun-btn-copy-source" title="Copy Source"> <span class="material-icons">file_copy</span> </button>
      |                    <button class="flowrun-btn-copy-dot" title="Copy DOT"> <span class="material-icons">account_tree</span> </button>
      |                </div>
      |                <div>
      |                    <input class="flowrun-program-name flowrun--disabled" type="text">
      |                </div>
      |                <div class="flowrun-draw-exec-btns">
      |                    <button class="flowrun-btn-run" title="Run"> <span class="material-icons">play_arrow</span> </button>
      |                    <button class="flowrun-btn-stop flowrun--disabled" title="Stop"> <span class="material-icons">stop</span> </button>
      |                </div>
      |            </div>
      |            <div class="flowrun-draw"> </div>
      |            
      |            <!-- context menus -->
      |        <div class="flowrun-edge-context-menu flowrun-context-menu">
      |            <button class="flowrun-paste-stmt"> <span class="material-icons">content_paste</span> Paste</button>
      |            <button class="flowrun-add-declare"> <span class="material-icons">post_add</span> Declare</button>
      |            <button class="flowrun-add-assign"> <span class="material-icons">sync_alt</span> Assign</button>
      |            <button class="flowrun-add-input"> <span class="material-icons">keyboard</span> Input</button>
      |            <button class="flowrun-add-output"> <span class="material-icons">desktop_windows</span> Output</button>
      |            <button class="flowrun-add-if"> <span class="material-icons">call_split</span> If</button>
      |            <button class="flowrun-add-while"> <span class="material-icons">repeat</span> While</button>
      |            <button class="flowrun-add-do-while"> <span class="material-icons">repeat</span> Do While</button>
      |            <button class="flowrun-add-for"> <span class="material-icons">repeat</span> For loop</button>
      |            <button class="flowrun-add-call"> <span class="material-icons">call</span> Call</button>
      |        </div>
      |
      |        <div class="flowrun-node-context-menu flowrun-context-menu">
      |            <button class="flowrun-copy-stmt"> <span class="material-icons">content_copy</span> Copy</button>
      |            <button class="flowrun-delete"> <span class="material-icons">remove</span> Delete</button>
      |        </div>
      |
      |            <div class="flowrun-debug-vars"></div>
      |            <details class="flowrun-config">
      |                <summary>Settings</summary>
      |                <div class="flowrun-config-layout">
      |                    <label>
      |                        <input class="flowrun-cb-show-functions" type="checkbox">
      |                        Show functions
      |                    </label>
      |                    <label>
      |                        <input class="flowrun-cb-show-gen-code" type="checkbox">
      |                        Show generated code
      |                    </label>
      |                </div>
      |            </details>
      |        </div>
      |
      |        <div class="flowrun-output flowrun--panel">
      |            <div class="flowrun-scratchpad">
      |                <div class="flowrun-output-statement"></div>
      |                <div class="flowrun-output-syntax"></div>
      |                <div class="flowrun-output-runtime"></div>
      |            </div>
      |        </div>
      |
      |        <div class="flowrun-gen-code flowrun--panel">
      |            <pre class="flowrun-code"></pre>
      |            <div class="flowrun-gen-code-btns">
      |                <button class="flowrun-btn-copy-gencode" title="Copy Generated Code"> <span class="material-icons">code</span> </button>
      |                <select class="flowrun-gencode-lang"></select>
      |            </div>
      |        </div>
      |
      |        <!-- this whole div will be deleted -->
      |        <div class="flowrun-template-transient">
      |            <button class="flowrun-btn-fun-delete" title="Delete function"> <span class="material-icons">remove</span> </button>
      |            <button class="flowrun-btn-fun-add-param" title="Add new parameter"> <span class="material-icons">add</span> </button>
      |            <button class="flowrun-btn-fun-delete-param" title="Delete parameter"> <span class="material-icons">remove</span> </button>
      |            <select class="flowrun-input-select"></select>
      |            <input class="flowrun-input-text" type="text" size="10">
      |            <input type="radio" class="flowrun-input-radio">
      |            <input type="checkbox" class="flowrun-input-checkbox">
      |            <button class="flowrun-btn-enter"> <span class="material-icons">input</span> Enter</button>
      |        </div>
      |    </div>
      |""".stripMargin)
}
