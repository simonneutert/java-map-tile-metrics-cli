package de.simon_neutert.map_tile_metrics_cli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import de.simon_neutert.map_tile_metrics.Point;

/**
 * The {@code JsonResult} class represents a JSON result.
 * This class can be used to encapsulate the result of an operation
 * in a JSON format.
 */
public class JsonResult {
    private ArrayList<HashSet<Point>> clusters;
    private ArrayList<HashSet<Point>> maxClusters;
    private int maxClustersSize;
    private ArrayList<Point> maxSquaresTopLeftCorners;
    private int maxSquaresSize;

    public JsonResult() {
        // No-argument constructor
    }

    public JsonResult(HashMap<String, ArrayList<HashSet<Point>>> clusterResult,
                      int maxClustersSize, 
                      ArrayList<HashMap<Point, Integer>> maxSquares,
                      int maxSquaresSize) {
        this.clusters = clusterResult.get("clusters");
        this.maxClusters = clusterResult.get("maxClusters");
        this.maxClustersSize = maxClustersSize;
        this.maxSquaresTopLeftCorners = calculateMaxSquaresTopLeftCorners(maxSquares);
        this.maxSquaresSize = maxSquaresSize;
    }

    private ArrayList<Point> calculateMaxSquaresTopLeftCorners(ArrayList<HashMap<Point, Integer>> maxSquares) {
        ArrayList<Point> maxSquaresTopLeftCorners = new ArrayList<>();
        maxSquares.forEach(square -> maxSquaresTopLeftCorners.add(square.keySet().iterator().next()));
        return maxSquaresTopLeftCorners;
    }

    public ArrayList<HashSet<Point>> getClusters() {
        return clusters;
    }

    public void setClusters(ArrayList<HashSet<Point>> clusters) {
        this.clusters = clusters;
    }

    public ArrayList<HashSet<Point>> getMaxClusters() {
        return maxClusters;
    }

    public void setMaxClusters(ArrayList<HashSet<Point>> maxClusters) {
        this.maxClusters = maxClusters;
    }

    public int getMaxClustersSize() {
        return maxClustersSize;
    }

    public void setMaxClustersSize(int maxClustersSize) {
        this.maxClustersSize = maxClustersSize;
    }

    public ArrayList<Point> getMaxSquaresTopLeftCorners() {
        return maxSquaresTopLeftCorners;
    }

    public void setMaxSquaresTopLeftCorners(ArrayList<Point> maxSquaresTopLeftCorners) {
        this.maxSquaresTopLeftCorners = maxSquaresTopLeftCorners;
    }

    public int getMaxSquaresSize() {
        return maxSquaresSize;
    }

    public void setMaxSquaresSize(int maxSquaresSize) {
        this.maxSquaresSize = maxSquaresSize;
    }
}