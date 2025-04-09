package fr.spinget.mcp_diagram.tools;

import fr.spinget.diagrams.VisitContext;
import fr.spinget.diagrams.mindmap.MindMap;
import fr.spinget.diagrams.visitors.MermaidMindMapVisitor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static fr.spinget.diagrams.DiagramsUtils.getMermaidURLParam;

@Service
public class MindMapTools {

    private final MermaidMindMapVisitor mermaidDiagramVisitor = new MermaidMindMapVisitor();

    @Tool(description = "Generate a markdown mermaid mind map. An URL will be returned to open the diagram, you have to show this URL")
    public String getMermaidMindMapDiagram(MindMap diagram) throws IOException {
        return "Diagram is available on : https://mermaid.live/edit#pako:" + getMermaidURLParam(mermaidDiagramVisitor.visit(diagram, new VisitContext()));
    }

}
