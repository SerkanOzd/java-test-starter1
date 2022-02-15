package rest.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

/**
 * This class has all the entities for the payload
 *
 * @author Serkan Özdemirci, MaibornWolff GmbH
 */
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Pet {
    private int id;
    private Category category;
    private String name;
    @Singular
    private List<String> photoUrls;
    @Singular
    private List<Tags> tags;
    private String status;
}
