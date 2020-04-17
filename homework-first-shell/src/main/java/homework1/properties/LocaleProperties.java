package homework1.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Getter
@Setter
@ConfigurationProperties("bundle")
public class LocaleProperties {

    private Locale locale;
}
