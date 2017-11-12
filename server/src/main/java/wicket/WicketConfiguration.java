package wicket;

import com.google.common.collect.ImmutableMap;
import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.*;
import wicket.core.Template;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

public class WicketConfiguration extends Configuration {

    @NotEmpty
    private String template;

    @NotEmpty
    private String defaultName = "Min grind";
    

    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    public Template buildTemplate() {
        return new Template(template, defaultName);
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }


    @JsonProperty("renderers")
    private Map<String, Map<String, String>> renderers = new HashMap<>();

    @JsonProperty("templatePaths")
    private List<String> templatePaths = new ArrayList<>();

    public Map<String, Map<String, String>> getRenderers() {
        return  renderers;
    }

    public List<String> getTemplatePaths() {
        return templatePaths;
    }

    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    public void setRenderers(Map<String, Map<String, String>> renderers) {
        this.renderers = renderers;
    }

    public void setTemplatePaths(List<String> templatePaths) {
        this.templatePaths = templatePaths;
    }

    @NotNull
    private Map<String, Map<String, String>> viewRendererConfiguration = Collections.emptyMap();

    @JsonProperty("viewRendererConfiguration")
    public Map<String, Map<String, String>> getViewRendererConfiguration() {
        return viewRendererConfiguration;
    }
    
    @JsonProperty("viewRendererConfiguration")
    public void setViewRendererConfiguration(Map<String, Map<String, String>> viewRendererConfiguration) {
        final ImmutableMap.Builder<String, Map<String, String>> builder = ImmutableMap.builder();
        for (Map.Entry<String, Map<String, String>> entry : viewRendererConfiguration.entrySet()) {
            builder.put(entry.getKey(), ImmutableMap.copyOf(entry.getValue()));
        }
        this.viewRendererConfiguration = builder.build();
    }


    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory factory) {
        this.database = factory;
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }
    
}
