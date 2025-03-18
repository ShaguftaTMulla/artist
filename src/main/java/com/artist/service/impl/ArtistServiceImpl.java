package com.artist.service.impl;

import com.artist.dto.ArtistRequest;
import com.artist.model.Artist;
import com.artist.repository.ArtistRepository;
import com.artist.service.ArtistService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public Artist createArtist(ArtistRequest artistRequest) {
        Artist artist = new Artist();
        artist.setFirstName(artistRequest.firstName());
        artist.setLastName(artistRequest.lastName());
        return artistRepository.save(artist);
        // return null;
    }

    @Override
    public List<Artist> getArtists() {
        return artistRepository.findAll();
        // return null;
    }

    @Override
    public Artist getArtistByID(Long id) {
        return artistRepository.findById(id).orElse(null);
        // return null;
    }

    @Override
    public void deleteArtist(Long id) {
        if (artistRepository.existsById(id)) {
            artistRepository.deleteById(id);
        }
    }
}
