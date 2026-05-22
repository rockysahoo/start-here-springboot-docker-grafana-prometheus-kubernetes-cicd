package spring_boot.peer_prog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_boot.peer_prog.dto.Mobile;
import spring_boot.peer_prog.dto.Product;
import spring_boot.peer_prog.service.MyDataService;

import java.net.URI;
import java.util.List;


@RequestMapping("/app/v2")
@RestController
public class MyDataController {

    private final MyDataService myDataService;

    public MyDataController(MyDataService myDataService) {
        this.myDataService = myDataService;
    }

    // ===== Product endpoints =====
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return myDataService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return myDataService.getProductById(id)
                            .map(ResponseEntity::ok)
                            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        if (product == null) {
            return ResponseEntity.badRequest().build();
        }

        var productAdded = myDataService.addProduct(product);

        return ResponseEntity
                .created(URI.create("/app/v2/product/" +  product.id()))
                .body(productAdded);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        boolean removed = myDataService.deleteProduct(id);
        return removed
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    // ===== Mobile endpoints =====
    @GetMapping("/mobiles")
    public List<Mobile> getAllMobiles() {
        return myDataService.getAllMobiles();
    }

    @GetMapping("/mobiles/{id}")
    public ResponseEntity<Mobile> getMobileById(@PathVariable Long id) {
        return myDataService.getMobileById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/mobiles")
    public ResponseEntity<Mobile> addMobile(@RequestBody Mobile mobile) {
        if (mobile == null) {
            return ResponseEntity.badRequest().build();
        }

        var mobileAdded  = myDataService.addMobile(mobile);

        return ResponseEntity
                .created(URI.create("/app/v2/mobile/" +  mobileAdded.id()))
                .body(mobileAdded);
    }

    @DeleteMapping("/mobiles/{id}")
    public ResponseEntity<String> deleteMobile(@PathVariable Long id) {
        boolean removed = myDataService.deleteMobile(id);
        return removed
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}

