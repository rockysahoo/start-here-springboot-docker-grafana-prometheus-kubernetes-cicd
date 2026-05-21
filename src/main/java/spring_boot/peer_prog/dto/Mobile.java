package spring_boot.peer_prog.dto;

import spring_boot.peer_prog.entity.MobileEntity;

public record Mobile(
        Long id,
        String brand,
        String model
) {

    public static Mobile toMobile(MobileEntity mobileEntity) {
        return new Mobile(
                mobileEntity.getId(),
                mobileEntity.getBrand(),
                mobileEntity.getModel()
        );
    }

}
