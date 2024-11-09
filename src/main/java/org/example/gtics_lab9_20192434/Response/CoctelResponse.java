package org.example.gtics_lab9_20192434.Response;

import lombok.Getter;
import lombok.Setter;
import org.example.gtics_lab9_20192434.Entity.Coctel;

import java.util.List;
@Getter
@Setter
public class CoctelResponse {
    private List<Coctel> drinks;
}
