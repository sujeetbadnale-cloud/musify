package in.sujeetbadnale.musifyapi.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import in.sujeetbadnale.musifyapi.document.Album;
import in.sujeetbadnale.musifyapi.dto.AlbumRequest;
import in.sujeetbadnale.musifyapi.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final Cloudinary cloudinary;

    public Album addAlbum(AlbumRequest request) throws IOException {
        Map<String, Object> imageUploadResult = cloudinary.uploader().upload(
                request.getImageFile().getBytes(),
                ObjectUtils.asMap("resource_type", "image"));
        Album album = Album.builder().id(request.getId())
                .name(request.getName())
                .desc(request.getDesc())
                .bgColour(request.getBgColour())
                .imageUrl((String) imageUploadResult.get("secure_url"))
                .build();
        return albumRepository.save(album);
    }
}
