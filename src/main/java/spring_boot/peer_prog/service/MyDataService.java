package spring_boot.peer_prog.service;

import org.springframework.stereotype.Service;
import spring_boot.peer_prog.dto.Mobile;
import spring_boot.peer_prog.dto.Product;
import spring_boot.peer_prog.entity.MobileEntity;
import spring_boot.peer_prog.entity.ProductEntity;
import spring_boot.peer_prog.repository.MobileRepository;
import spring_boot.peer_prog.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MyDataService {

    private final ProductRepository productRepository;
    private final MobileRepository mobileRepository;

    public MyDataService(ProductRepository productRepository, MobileRepository mobileRepository) {
        this.productRepository = productRepository;
        this.mobileRepository = mobileRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll().stream()
                .map(Product::toProduct)
                .toList();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id)
                .map(productEntity -> Product.toProduct(productEntity));
    }

    public Product addProduct(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(product.name());
        productEntity.setPrice(product.price());
        return Product.toProduct(productRepository.save(productEntity));
    }

    public boolean deleteProduct(Long id) {
        return productRepository.findById(id)
                .map(productEntity -> {
                    productRepository.delete(productEntity);
                    return true;
                })
                .orElse(false);
    }

    public List<Mobile> getAllMobiles() {
        return mobileRepository.findAll().stream()
                .map(Mobile::toMobile)
                .toList();
    }

    public Optional<Mobile> getMobileById(Long id) {
        return mobileRepository.findById(id)
                .map(mobileEntity -> Mobile.toMobile(mobileEntity));
    }

    public Mobile addMobile(Mobile mobile) {
        MobileEntity mobileEntity = new MobileEntity();
        mobileEntity.setBrand(mobile.brand());
        mobileEntity.setModel(mobile.model());
        return Mobile.toMobile(mobileRepository.save(mobileEntity));
    }

    public boolean deleteMobile(Long id) {
        return mobileRepository.findById(id)
                .map(mobileEntity -> {
                    mobileRepository.delete(mobileEntity);
                    return true;
                })
                .orElse(false);
    }



}

