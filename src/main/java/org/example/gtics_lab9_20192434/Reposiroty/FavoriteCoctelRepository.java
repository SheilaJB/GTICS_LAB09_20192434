package org.example.gtics_lab9_20192434.Reposiroty;

import org.example.gtics_lab9_20192434.Entity.FavoriteCoctel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteCoctelRepository extends JpaRepository<FavoriteCoctel, Integer> {
}
