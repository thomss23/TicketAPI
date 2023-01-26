package playground.api.services;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import playground.api.entities.QRImage;
import playground.api.repositories.QRImagesRepository;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


class QRGeneratorServiceTest {
    @Autowired
    @InjectMocks
    private QRGeneratorService qrGeneratorService;

    @Mock
    private QRImagesRepository qrImagesRepository;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveToDB() {
        String profileUUID = "123";
        String qrRequestID = "456";
        byte[] image = new byte[] {1, 2, 3};

        qrGeneratorService.saveToDB(profileUUID, qrRequestID, image);

        Mockito.verify(qrImagesRepository).save(any(QRImage.class));
    }

    @Test
    public void testGetQRCodeBytes() throws IOException, NotFoundException {
        String content = "example content";
        byte[] result = qrGeneratorService.getQrCodeBytes(content);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(result);
        BufferedImage img = ImageIO.read(inputStream);
        LuminanceSource source = new BufferedImageLuminanceSource(img);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result qrResult = new MultiFormatReader().decode(bitmap);
        assertEquals(content, qrResult.getText());
    }

}