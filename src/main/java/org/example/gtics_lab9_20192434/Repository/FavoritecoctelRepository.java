package org.example.gtics_lab9_20192434.Repository;

import org.example.gtics_lab9_20192434.Entity.Favoritecoctel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritecoctelRepository extends JpaRepository<Favoritecoctel, Integer> {

}
