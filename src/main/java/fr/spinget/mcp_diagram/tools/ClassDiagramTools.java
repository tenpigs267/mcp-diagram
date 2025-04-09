package fr.spinget.mcp_diagram.tools;

import fr.spinget.diagrams.VisitContext;
import fr.spinget.diagrams.classdiagram.ClassDiagram;
import fr.spinget.diagrams.visitors.MermaidClassDiagramVisitor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static fr.spinget.diagrams.DiagramsUtils.getMermaidURLParam;

@Service
public class ClassDiagramTools {

    private final MermaidClassDiagramVisitor mermaidDiagramVisitor = new MermaidClassDiagramVisitor();

    @Tool(description = "Generate a markdown mermaid class diagram. An URL will be returned to open the diagram, you have to show this URL")
    public String getMermaidClassDiagram(ClassDiagram diagram) throws IOException {
        return "Diagram is available on : https://mermaid.live/edit#pako:" + getMermaidURLParam(mermaidDiagramVisitor.visit(diagram, new VisitContext()));
    }

}
