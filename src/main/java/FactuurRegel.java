import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "factuur_factuurregel")

public class FactuurRegel  implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Factuur factuur;
    @Embedded
    private Artikel artikel;

    public FactuurRegel(){}

    public FactuurRegel(Factuur factuur, Artikel artikel){
        this.factuur = factuur;
        this.artikel = artikel;
    }

    @Override
    public String toString(){
        return artikel.getNaam();
    }
}
