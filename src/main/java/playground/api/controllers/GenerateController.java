package playground.api.controllers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import playground.api.model.GenerateRequest;
import playground.api.services.QRGeneratorService;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class GenerateController {

    private final QRGeneratorService qrGeneratorService;

    @PutMapping(path = "/generate", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateTicket(@RequestBody GenerateRequest generateRequest) {

        String input = generateRequest.profileuuid() + " " + generateRequest.uuid();
        byte[] generatedCodeArray = qrGeneratorService.getQrCodeBytes(input);
        qrGeneratorService.saveToDB(generateRequest.profileuuid(), generateRequest.uuid(), generatedCodeArray);

        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(generatedCodeArray);

    }
}