package playground.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.api.entities.QRImage;

public interface QRImagesRepository extends JpaRepository<QRImage, Long> {

}