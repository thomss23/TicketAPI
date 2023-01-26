package playground.api.services;

import lombok.AllArgsConstructor;
import net.glxn.qrgen.QRCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import playground.api.entities.QRImage;
import playground.api.repositories.QRImagesRepository;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Component
@AllArgsConstructor
public class QRGeneratorService {

    private final QRImagesRepository qrImagesRepository;

    public File getQrCode(String content) {
        QRCode qr = getCode(content);
        return qr.file();
    }

    private QRCode getCode(String content) {
        QRCode qr = QRCode.from(content);
        return qr;
    }

    public byte[] getQrCodeBytes(String content) {

        QRCode qr = getCode(content);

        try (ByteArrayOutputStream stream = qr.stream()) {
            return stream.toByteArray();
        } catch (IOException e) {
            throw new IllegalStateException("Could not generate QR code");
        }
    }

    public void saveToDB(String profileUUID, String qrRequestID, byte[] image) {
        QRImage qrImage =  QRImage.builder()
                .profileUuid(profileUUID)
                .qrRequestId(qrRequestID)
                .image(image)
                .build();

        qrImagesRepository.save(qrImage);

    }

}

