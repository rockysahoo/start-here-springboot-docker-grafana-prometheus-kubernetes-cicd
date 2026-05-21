package spring_boot.peer_prog.dto;

import spring_boot.peer_prog.entity.ProductEntity;

public record Product(
        Long id,
        String name,
        Double price
) {

    public static Product toProduct(ProductEntity productEntity) {
        return new Product(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getPrice()
        );
    }

}
