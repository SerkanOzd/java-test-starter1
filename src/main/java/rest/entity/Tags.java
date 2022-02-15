package rest.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This class has the entities for one of the entities of the payload
 *
 * @author Serkan Özdemirci, MaibornWolff GmbH
 */
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Tags {
    private int id;
    private String name;
}

