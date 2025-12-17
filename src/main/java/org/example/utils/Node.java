package org.example.utils;

import org.example.models.Coordinates;

public record Node (Coordinates coordinates, Node parent)  {

}
