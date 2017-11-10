package wicket;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

public class WicketConfiguration extends Configuration {
    // TODO: ta bort exemplet nedan sedan
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

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name) {
        this.defaultName = name;
    }
    // TODO: ta bort ovanför
    
}
