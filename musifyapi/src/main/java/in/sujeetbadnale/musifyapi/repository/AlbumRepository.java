package in.sujeetbadnale.musifyapi.repository;

import in.sujeetbadnale.musifyapi.document.Album;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlbumRepository extends MongoRepository<Album, String> {
}
