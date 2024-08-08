package com.holoview.holoview.service.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.azure.storage.blob.sas.BlobSasPermission;
import com.azure.storage.blob.sas.BlobServiceSasSignatureValues;
import com.holoview.holoview.model.entity.Product;
import com.holoview.holoview.model.entity.Shop;
import com.holoview.holoview.service.IBlobStorageService;
import com.holoview.holoview.service.exception.BlobStorageUploadException;

@Service
public class BlobStorageService implements IBlobStorageService {
    @Value("${azure.storage.container.name}")
    private String containerName;

    @Value("${azure.storage.connection-string}")
    private String connectionString;

    @Override
    public String uploadBlob(Shop shop, Product product, MultipartFile file) {
        BlobContainerClient containerClient = getBlobContainerClientByConnectionString();

        BlobHttpHeaders headers = new BlobHttpHeaders().setContentType("image/png");

        String blobName = String.format("%s-%s-%s",
                shop.getName(),
                product.getName(),
                LocalDateTime.now().atZone(ZoneId.of("America/Sao_Paulo")).toInstant().toEpochMilli())
                .replace(" ", "-");

        BlobClient blobClient = uploadBlob(containerClient, headers, blobName, file);

        return generateBlobSas(blobClient);
    }

    private BlobContainerClient getBlobContainerClientByConnectionString() {
        BlobServiceClient serviceClient = new BlobServiceClientBuilder()
                .connectionString(this.connectionString)
                .buildClient();

        return serviceClient.getBlobContainerClient(containerName);
    }

    private BlobClient uploadBlob(BlobContainerClient containerClient,
            BlobHttpHeaders headers,
            String blobName,
            MultipartFile file) {
        try {
            BlobClient blobClient = containerClient.getBlobClient(blobName);

            blobClient.upload(file.getInputStream(), file.getSize(), true);
            blobClient.setHttpHeaders(headers);

            return blobClient;
        } catch (IOException e) {
            throw new BlobStorageUploadException(e.getMessage());
        }
    }

    private String generateBlobSas(BlobClient blobClient) {
        OffsetDateTime expirationDate = OffsetDateTime.now().plusYears(10);

        BlobSasPermission permission = new BlobSasPermission().setReadPermission(true);

        BlobServiceSasSignatureValues sasSignatureValues = new BlobServiceSasSignatureValues(expirationDate, permission)
                .setStartTime(OffsetDateTime.now().minusMinutes(5));

        String sasToken = blobClient.generateSas(sasSignatureValues);

        return String.format("https://%s.blob.core.windows.net/%s/%s?%s",
                blobClient.getAccountName(),
                this.containerName,
                blobClient.getBlobName(),
                sasToken);
    }
}