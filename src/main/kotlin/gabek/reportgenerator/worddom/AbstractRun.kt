package gabek.reportgenerator.worddom

import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.w3c.dom.Node

abstract class AbstractRun: WordNode<XWPFParagraph> {
    var text = ""
    override fun loadXML(baseNode: Node) {}
}