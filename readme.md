api/generate
- receives uuid, metadata
- header: jwt -> profileid
- generate QR image base64 based on uuid and profileid and metadata
- save table QRImages columns profileuuid (primary key), base64 of the image
- return image

