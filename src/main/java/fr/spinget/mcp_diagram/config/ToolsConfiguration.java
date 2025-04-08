package fr.spinget.mcp_diagram.config;

import fr.spinget.mcp_diagram.tools.ComplexDiagramTools;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToolsConfiguration {

    @Bean
    ToolCallbackProvider diagramsTools(ComplexDiagramTools complexDiagramTools) {
        return MethodToolCallbackProvider
                .builder()
                .toolObjects(complexDiagramTools)
                .build();
    }

}
