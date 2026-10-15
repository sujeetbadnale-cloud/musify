package in.sujeetbadnale.musifyapi.controller;

import in.sujeetbadnale.musifyapi.document.Album;
import in.sujeetbadnale.musifyapi.dto.AlbumRequest;
import in.sujeetbadnale.musifyapi.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/albums")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;

    @PostMapping
    public ResponseEntity<?> addAlbum(
            @RequestPart("request") String request,
            @RequestPart("file") MultipartFile file){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            AlbumRequest albumRequest = objectMapper.readValue(request, AlbumRequest.class);
            albumRequest.setImageFile(file);
            Album album = albumService.addAlbum(albumRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(album);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
