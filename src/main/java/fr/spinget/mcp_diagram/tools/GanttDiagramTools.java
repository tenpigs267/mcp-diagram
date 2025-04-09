package fr.spinget.mcp_diagram.tools;

import fr.spinget.diagrams.VisitContext;
import fr.spinget.diagrams.gantt.GanttDiagram;
import fr.spinget.diagrams.visitors.MermaidGanttVisitor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static fr.spinget.diagrams.DiagramsUtils.getMermaidURLParam;

@Service
public class GanttDiagramTools {

    private final MermaidGanttVisitor mermaidDiagramVisitor = new MermaidGanttVisitor();

    @Tool(description = "Generate a markdown mermaid gantt diagram. An URL will be returned to open the diagram, you have to show this URL")
    public String getMermaidGanttDiagram(GanttDiagram diagram) throws IOException {
        return "Diagram is available on : https://mermaid.live/edit#pako:" + getMermaidURLParam(mermaidDiagramVisitor.visit(diagram, new VisitContext()));
    }

}
