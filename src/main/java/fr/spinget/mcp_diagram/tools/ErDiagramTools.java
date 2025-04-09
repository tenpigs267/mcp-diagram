package fr.spinget.mcp_diagram.tools;

import fr.spinget.diagrams.VisitContext;
import fr.spinget.diagrams.entityrelationship.ErDiagram;
import fr.spinget.diagrams.visitors.MermaidErDiagramVisitor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static fr.spinget.diagrams.DiagramsUtils.getMermaidURLParam;

@Service
public class ErDiagramTools {

    private final MermaidErDiagramVisitor mermaidDiagramVisitor = new MermaidErDiagramVisitor();

    @Tool(description = "Generate a markdown mermaid Entity Relationship diagram. An URL will be returned to open the diagram, you have to show this URL")
    public String getMermaidErDiagram(ErDiagram diagram) throws IOException {
        return "Diagram is available on : https://mermaid.live/edit#pako:" + getMermaidURLParam(mermaidDiagramVisitor.visit(diagram, new VisitContext()));
    }

}
