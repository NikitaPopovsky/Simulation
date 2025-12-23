package org.example.utils;

import org.example.models.Coordinate;

public record Node (Coordinate coordinate, Node parent)  {

}
