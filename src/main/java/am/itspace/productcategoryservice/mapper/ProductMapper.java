package am.itspace.productcategoryservice.mapper;

import am.itspace.productcategoryservice.dto.CreateProductDTO;
import am.itspace.productcategoryservice.dto.ResponseProductDTO;
import am.itspace.productcategoryservice.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product map(CreateProductDTO createProductDTO);

    ResponseProductDTO map(Product product);

    List<ResponseProductDTO> map(List<Product> productList);

}
