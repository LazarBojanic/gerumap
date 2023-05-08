package rs.raf.gerumap.model.repository.implementation;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.raf.gerumap.model.repository.composite.MapNode;

import java.awt.*;
import java.util.UUID;

@Getter
@Setter
public class Link extends MapNode{

    private int thickness;
    private int borderColor;

    private transient Term sourceTerm;
    private transient Term destinationTerm;

    private UUID sourceTermGuid;
    private UUID destinationTermGuid;

    public Link(String name, MapNode parent, MapNode destination) {
        super(name, parent);

        thickness = 3;
        borderColor = Color.BLACK.getRGB();

        setSourceTerm((Term) parent);
        setDestinationTerm((Term) destination);

        setName("with");
    }
    public Link(){
    }

    public void setSourceTerm(Term sourceTerm) {
        this.sourceTerm = sourceTerm;
        this.sourceTermGuid = sourceTerm.getGuid();
    }

    public void setDestinationTerm(Term destinationTerm) {
        this.destinationTerm = destinationTerm;
        this.destinationTermGuid = destinationTerm.getGuid();
    }

    @Override
    public String toString() {
        return sourceTerm + " " + getName() + " " + destinationTerm;
    }
}
