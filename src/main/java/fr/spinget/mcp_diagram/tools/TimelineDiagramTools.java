package fr.spinget.mcp_diagram.tools;

import fr.spinget.diagrams.VisitContext;
import fr.spinget.diagrams.timeline.Timeline;
import fr.spinget.diagrams.visitors.MermaidTimelineVisitor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static fr.spinget.diagrams.DiagramsUtils.getMermaidURLParam;

@Service
public class TimelineDiagramTools {

    private final MermaidTimelineVisitor mermaidDiagramVisitor = new MermaidTimelineVisitor();

    @Tool(description = "Generate a markdown mermaid timeline. An URL will be returned to open the diagram, you have to show this URL")
    public String getMermaidTimelineDiagram(Timeline diagram) throws IOException {
        return "Diagram is available on : https://mermaid.live/edit#pako:" + getMermaidURLParam(mermaidDiagramVisitor.visit(diagram, new VisitContext()));
    }

}
