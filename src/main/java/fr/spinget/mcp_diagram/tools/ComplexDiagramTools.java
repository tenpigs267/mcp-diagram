package fr.spinget.mcp_diagram.tools;

import fr.spinget.diagrams.VisitContext;
import fr.spinget.diagrams.complex.ComplexDiagram;
import fr.spinget.diagrams.visitors.D2ComplexDiagramVisitor;
import fr.spinget.diagrams.visitors.IconMatcher;
import fr.spinget.diagrams.visitors.MermaidComplexDiagramVisitor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ComplexDiagramTools {
    
    private final D2ComplexDiagramVisitor d2ComplexDiagramVisitor = new D2ComplexDiagramVisitor(new IconMatcher());
    private final MermaidComplexDiagramVisitor mermaidComplexDiagramVisitor = new MermaidComplexDiagramVisitor(new IconMatcher());
    private final RestTemplate restTemplate = new RestTemplate();

    @Tool(description = "Generate a complex diagram in D2Lang declarative grammar; for example software architecture, network architecture, any diagram that contains containers or grouping and return a b64 svg image")
    public String getD2LangComplexDiagram(ComplexDiagram diagram) {
        return d2ComplexDiagramVisitor.visitComplexDiagram(diagram, new VisitContext());
    }

    @Tool(description = "Generate a markdown mermaid complex diagram; for example software architecture, network architecture, any diagram that contains containers or grouping")
    public String getMermaidLangComplexDiagram(
            @ToolParam(description = "it's important to set cloud providers mentionned in the diagram in cloudProviders field") ComplexDiagram diagram) {
        return mermaidComplexDiagramVisitor.visitComplexDiagram(diagram, new VisitContext());
    }

    @Tool(description = "generate a SVG from a D2Lang diagram grammar")
    public String getSVGComplexDiagram(String d2LangDiagram) {
        return this.getD2SVG(d2LangDiagram);
    }

    record KrokiDTO(String diagram_source, String diagram_type, String output_format,
                    Map<String, String> diagram_options) {
    }

    private String getD2SVG(String d2Diagram) {
        // Configuration des headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        KrokiDTO krokiDTO = new KrokiDTO(d2Diagram, "d2", "svg", Map.of("sketch", "", "layout", "elk"));

        // Création de la requête HTTP
        HttpEntity<KrokiDTO> request = new HttpEntity<>(krokiDTO, headers);

        // Appel à l'API Kroki
        return restTemplate.postForEntity("https://kroki.io", request, String.class).getBody();
        // return restTemplate.postForEntity("http://localhost:8000", request, String.class).getBody();
    }
}
