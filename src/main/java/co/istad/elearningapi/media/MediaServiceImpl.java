package co.istad.elearningapi.media;

import co.istad.elearningapi.media.dto.MediaResponse;
import co.istad.elearningapi.util.MediaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class MediaServiceImpl implements MediaService{

    @Value("${media.server-path}")
    private String serverPath;

    @Value("${media.base-uri}")
    private String baseUri;

    @Override
    public MediaResponse uploadSingle(MultipartFile file, String folderName) {

        String newName = UUID.randomUUID().toString();

        String extension = MediaUtil.extractExtension(file.getOriginalFilename());

        newName = newName + "." + extension;

        // Copy file to server
        Path path = Paths.get(serverPath + folderName + "\\" + newName);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getLocalizedMessage());
        }

        return MediaResponse.builder()
                .name(newName)
                .contentType(file.getContentType())
                .extension(extension)
                .size(file.getSize())
                .uri(String.format("%s%s/%s", baseUri, folderName, newName))
                .build();

    }
    @Override
    public List<MediaResponse> uploadMultiple(List<MultipartFile> files, String folderName) {

        // Create empty array list, wait for adding uploaded file
        List<MediaResponse> mediaResponses = new ArrayList<>();

        files.forEach(file -> {
            MediaResponse mediaResponse = this.uploadSingle(file, folderName);
            mediaResponses.add(mediaResponse);
        });

        return mediaResponses;
    }

    @Override
    public MediaResponse loadMediaByName(String mediaName, String folderName) {

        // Create absolute path
        Path path = Paths.get(serverPath + folderName + "\\" + mediaName);

        try {

            Resource resource = new UrlResource(path.toUri());
            log.info("Load resource: {}", resource.getFilename());

            if (!resource.exists()) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Media has not been found!"
                );
            }

            return MediaResponse.builder()
                    .name(mediaName)
                    .contentType(Files.probeContentType(path))
                    .extension(MediaUtil.extractExtension(mediaName))
                    .size(resource.contentLength())
                    .uri(String.format("%s%s/%s", baseUri, folderName, mediaName ))
                    .build();

        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getLocalizedMessage()
            );
        }
    }

    @Override
    public MediaResponse deleteMediaByName(String mediaName, String folderName) {

        Path path = Paths.get(serverPath + folderName + "\\" + mediaName);

        try {
            long size = Files.size(path);
            if (!Files.deleteIfExists(path)){
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Media has not been found."
                );

            }
            return MediaResponse.builder()
                    .name(mediaName)
                    .contentType(Files.probeContentType(path))
                    .extension(MediaUtil.extractExtension(mediaName))
                    .size(size)
                    .uri(String.format("%s%s/%s", baseUri, folderName, mediaName ))
                    .build();

        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("Media path %s cannot be deleted", e.getLocalizedMessage())
            );
        }
    }

    @Override
    public List<MediaResponse> loadAllMedias(String folderName) {
        Path path = Paths.get(serverPath + folderName + "\\");

        try {
            List<MediaResponse> mediaResponses = new ArrayList<>();
            Files.list(path).forEach(mediaPath -> {
                try {
                    String mediaName = mediaPath.getFileName().toString();
                    Resource resource = new UrlResource(mediaPath.toUri());
                    if (resource.exists()) {
                        MediaResponse mediaResponse = MediaResponse.builder()
                                .name(mediaName)
                                .contentType(Files.probeContentType(mediaPath))
                                .extension(MediaUtil.extractExtension(mediaName))
                                .size(resource.contentLength())
                                .uri(String.format("%s%s/%s", baseUri, folderName, mediaName))
                                .build();
                        mediaResponses.add(mediaResponse);
                    }
                } catch (IOException e) {
                    log.error("Error loading media: {}", e.getMessage());
                }
            });
            return mediaResponses;
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("Error loading media files from %s: %s", folderName, e.getMessage())
            );
        }
    }

    @Override
    public Resource downloadMediaByName(String mediaName, String folderName) {

        Path path = Paths.get(serverPath , folderName , mediaName);
        try {
            Resource resource = new UrlResource(path.toUri());
            if (!resource.exists()) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Media has not been found!"
                );
            }
        } catch (MalformedURLException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Media has not been found"
            );
        }

        return null;
    }
}
