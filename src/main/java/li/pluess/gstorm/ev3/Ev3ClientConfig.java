package li.pluess.gstorm.ev3;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Make sure we are using Jackson as JSON Encoder.
 *
 * @author Ernst Plüss
 * @since 26.07.20
 */
@Configuration
public class Ev3ClientConfig {

    @Bean
    public Decoder feignDecoder() {
        return new JacksonDecoder(new ObjectMapper());
    }


}
