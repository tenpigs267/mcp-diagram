package fr.spinget.mcp_diagram.config;

import fr.spinget.mcp_diagram.tools.*;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToolsConfiguration {

    @Bean
    ToolCallbackProvider diagramsTools(ComplexDiagramTools complexDiagramTools, ClassDiagramTools classDiagramTools, ErDiagramTools erDiagramTools, GanttDiagramTools ganttDiagramTools, MindMapTools mindMapTools, SequenceDiagramTools sequenceDiagramTools, TimelineDiagramTools timelineDiagramTools) {
        return MethodToolCallbackProvider
                .builder()
                .toolObjects(
                        classDiagramTools,
                        complexDiagramTools,
                        erDiagramTools,
                        ganttDiagramTools,
                        mindMapTools,
                        sequenceDiagramTools,
                        timelineDiagramTools
                )
                .build();
    }

}
