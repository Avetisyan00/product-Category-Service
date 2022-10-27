package am.itspace.productcategoryservice.mapper;

import am.itspace.productcategoryservice.dto.CategoryDTO;
import am.itspace.productcategoryservice.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category map(CategoryDTO categoryDTO);

    CategoryDTO map(Category category);

    List<CategoryDTO> map(List<Category> categoryList);

}
