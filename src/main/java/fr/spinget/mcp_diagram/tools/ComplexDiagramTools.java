package fr.spinget.mcp_diagram.tools;

import fr.spinget.diagrams.VisitContext;
import fr.spinget.diagrams.complex.ComplexDiagram;
import fr.spinget.diagrams.visitors.D2ComplexDiagramVisitor;
import fr.spinget.diagrams.visitors.IconMatcher;
import fr.spinget.diagrams.visitors.MermaidComplexDiagramVisitor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static fr.spinget.diagrams.DiagramsUtils.getD2LangURLParam;
import static fr.spinget.diagrams.DiagramsUtils.getMermaidURLParam;

@Service
public class ComplexDiagramTools {

    private final D2ComplexDiagramVisitor d2ComplexDiagramVisitor = new D2ComplexDiagramVisitor(new IconMatcher());
    private final MermaidComplexDiagramVisitor mermaidComplexDiagramVisitor = new MermaidComplexDiagramVisitor(new IconMatcher());

    @Tool(description = "Generate a complex diagram in D2Lang declarative grammar; for example software architecture, network architecture, any diagram that contains containers or grouping. An URL will be returned to open the diagram, you have to show this URL")
    public String getD2LangComplexDiagram(
            @ToolParam(description = "it's important to set cloud providers mentionned in the diagram in cloudProviders field") ComplexDiagram diagram) throws IOException {
        return "Diagram is available on : https://play.d2lang.com/?script=" + getD2LangURLParam(d2ComplexDiagramVisitor.visit(diagram, new VisitContext()));
    }

    @Tool(description = "Generate a markdown mermaid complex diagram; for example software architecture, network architecture, any diagram that contains containers or grouping. An URL will be returned to open the diagram, you have to show this URL")
    public String getMermaidComplexDiagram(
            @ToolParam(description = "it's important to set cloud providers mentionned in the diagram in cloudProviders field") ComplexDiagram diagram) throws IOException {
        return "Diagram is available on : https://mermaid.live/edit#pako:" + getMermaidURLParam(mermaidComplexDiagramVisitor.visit(diagram, new VisitContext()));
    }

}
