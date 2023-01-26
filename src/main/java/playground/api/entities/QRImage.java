package playground.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Entity
@Table(name = "qr_image")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QRImage {
    @Id
    @GeneratedValue
    Long id;

    @Column(name="profileuuid")
    String profileUuid;

    @Column(name="qrRequestId")
    String qrRequestId;

    @Column(name="image", length = 3000)
    @Lob
    byte[] image;

}
