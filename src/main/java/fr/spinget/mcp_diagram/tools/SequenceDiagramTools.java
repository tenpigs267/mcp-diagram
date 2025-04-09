package fr.spinget.mcp_diagram.tools;

import fr.spinget.diagrams.VisitContext;
import fr.spinget.diagrams.sequence.SequenceDiagram;
import fr.spinget.diagrams.visitors.MermaidSequenceVisitor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static fr.spinget.diagrams.DiagramsUtils.getMermaidURLParam;

@Service
public class SequenceDiagramTools {

    private final MermaidSequenceVisitor mermaidDiagramVisitor = new MermaidSequenceVisitor();

    @Tool(description = "Generate a markdown mermaid sequence diagram. An URL will be returned to open the diagram, you have to show this URL")
    public String getMermaidSequenceDiagram(SequenceDiagram diagram) throws IOException {
        return "Diagram is available on : https://mermaid.live/edit#pako:" + getMermaidURLParam(mermaidDiagramVisitor.visit(diagram, new VisitContext()));
    }

}
