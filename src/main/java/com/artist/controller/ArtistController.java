package com.artist.controller;

import com.artist.dto.ArtistRequest;
import com.artist.model.Artist;
import com.artist.repository.ArtistRepository;
import com.artist.service.ArtistService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/artists")
public class ArtistController {
    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PostMapping()
    public ResponseEntity<Artist> createPlayList(@RequestBody ArtistRequest artistRequest) {

        Artist savedArtist = artistService.createArtist(artistRequest);
        return new ResponseEntity<>(savedArtist, HttpStatus.CREATED); // 201 artist created
        // return null;
    }

    @GetMapping("/{artistId}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long artistId) {
        Artist artist = artistService.getArtistByID(artistId);
        if (artist != null) {
            return new ResponseEntity<>(artist, HttpStatus.OK); // 200 OK
        }
        return new ResponseEntity<>(null, HttpStatus.OK); // 200 OK
        // return null;
    }

    @GetMapping
    public ResponseEntity<List<Artist>> getAllArtists() {
        List<Artist> artists = artistService.getArtists();
        return new ResponseEntity<>(artists, HttpStatus.OK); // 200 OK
        // return null;
    }

    @DeleteMapping("/{artistId}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long artistId) {
        if (artistService.getArtistByID(artistId) != null) {
            artistService.deleteArtist(artistId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not
        // return null;
    }
}
