package am.itspace.productcategoryservice.dto;

import am.itspace.productcategoryservice.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseProductDTO {
    private String title;
    private int count;
    private double price;
    private Category category;
}