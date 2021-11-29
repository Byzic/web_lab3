import javax.persistence.*;
import java.io.Serializable;
@Entity

public class Point implements Serializable {
    private Double xValue;
    private Double yValue;
    private Integer rValue;
    private String result;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    /*@Column(name = "x", nullable = false)
    private String x;
    @Column(name = "y", nullable = false)
    private String y;
    @Column(name = "r", nullable = false)
    private String r;
    @Column(name = "result", nullable = false)
    private String hitResult;
*/

}
