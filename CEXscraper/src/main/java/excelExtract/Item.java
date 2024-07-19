package excelExtract;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class Item {
    private String wrtItemName;
    private String wrtSellPrice;
    private String wrtEAN;
    private String wrtStore;
    private String cexItemName;
    private String cexBuyPrice;
}
