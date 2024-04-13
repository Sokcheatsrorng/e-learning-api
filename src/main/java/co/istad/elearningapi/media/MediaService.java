package co.istad.elearningapi.media;

import co.istad.elearningapi.media.dto.MediaResponse;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.util.List;

public interface MediaService {

    MediaResponse uploadSingle(MultipartFile file, String folderName);
    List<MediaResponse> uploadMultiple(List<MultipartFile> files, String folderName);

    MediaResponse loadMediaByName(String mediaName, String folderName);

    MediaResponse deleteMediaByName(String mediaName, String folderName);

    List<MediaResponse> loadAllMedias(String folderName);
    //    ResponseEntity<Resource> downloadMediaByName(String mediaName, String folderName);
    Resource downloadMediaByName(String mediaName, String folderName);

}
